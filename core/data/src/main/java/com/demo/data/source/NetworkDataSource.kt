package com.demo.data.source

import com.demo.data.connectivity.NetworkMonitorInterface
import com.demo.data.error.toDomain
import com.demo.data.response.ErrorResponse
import com.demo.data.result.Outcome
import com.google.gson.Gson
import okhttp3.Headers
import retrofit2.Response

class NetworkDataSource<SERVICE>(
    private val service: SERVICE,
    private val gson: Gson,
    private val networkMonitorInterface: NetworkMonitorInterface,
    private val userIdProvider: () -> String,
) {
    suspend fun <R, T> performRequest(
        request: suspend SERVICE.(String) -> Response<R>,
        onSuccess: suspend (R, Headers) -> Outcome<T> = { _, _ -> Outcome.empty() },
        onRedirect: suspend (String, Int) -> Outcome<T> = { _, _ -> Outcome.empty() },
        onEmpty: suspend () -> Outcome<T> = { Outcome.empty() },
        onError: suspend (ErrorResponse, Int) -> Outcome<T> = { errorResponse, errorCode ->
            Outcome.error(errorMessage = errorResponse.toDomain(errorCode = errorCode))
        }
    ): Outcome<T> {

    }
}
