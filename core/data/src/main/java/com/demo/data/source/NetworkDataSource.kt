package com.demo.data.source

import com.demo.data.constants.LOCATION_HEADER
import com.demo.data.error.getDefaultErrorResponse
import com.demo.data.error.getErrorResponse
import com.demo.data.interceptors.NoConnectivityException
import com.demo.data.mapper.toDomain
import com.demo.data.response.ErrorResponse
import com.demo.data.result.Outcome
import com.google.gson.Gson
import kotlinx.coroutines.isActive
import okhttp3.Headers
import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLHandshakeException
import javax.net.ssl.SSLPeerUnverifiedException
import kotlin.coroutines.coroutineContext

class NetworkDataSource<SERVICE>(
  private val service: SERVICE,
  private val gson: Gson,
  private val userIdProvider: () -> String,
) {
  suspend fun <R, T> performRequest(
    request: suspend SERVICE.(String) -> Response<R>,
    onSuccess: suspend (R, Headers) -> Outcome<T> = { _, _ -> Outcome.empty() },
    onRedirect: suspend (String, Int) -> Outcome<T> = { _, _ -> Outcome.empty() },
    onEmpty: suspend () -> Outcome<T> = { Outcome.empty() },
    onError: suspend (ErrorResponse, Int) -> Outcome<T> = { errorResponse, errorCode ->
      Outcome.error(errorMessage = errorResponse.toDomain(errorCode = errorCode))
    },
  ): Outcome<T> {
    try {
      val response = service.request(userIdProvider())
      val responseCode = response.code()
      val errorBody = response.errorBody()?.string()

      if (response.isSuccessful || responseCode == DataSource.SEE_OTHERS) {
        val responseBody = response.body()
        return if (responseBody != null && responseBody != Unit) {
          if (coroutineContext.isActive) onSuccess(responseBody, response.headers()) else onEmpty()
        } else {
          // response is successful but empty body or empty Unit
          val location = response.headers()[LOCATION_HEADER]
          if (location != null) onRedirect(location, responseCode) else onEmpty()
        }
      } else if (errorBody.isNullOrBlank()) {
        return onError(getDefaultErrorResponse(), responseCode)
      } else {
        return onError(getErrorResponse(gson = gson, errorBodyString = errorBody), responseCode)
      }
    } catch (e: Exception) {
      e.printStackTrace()
      val exceptionCode = when (e) {
        is SocketTimeoutException -> DataSource.TIMEOUT
        is UnknownHostException, is NoConnectivityException -> DataSource.NO_INTERNET
        is SSLPeerUnverifiedException, is SSLHandshakeException -> DataSource.SSL_PINNING
        else -> DataSource.UNKNOWN
      }
      return onError(getDefaultErrorResponse(), exceptionCode)
    }
  }
}
