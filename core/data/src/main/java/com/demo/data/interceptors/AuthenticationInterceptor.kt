package com.demo.data.interceptors

import com.demo.data.response.TokenResponse
import com.demo.data.service.SessionService
import com.demo.data.source.DataSource.Companion.UNAUTHORIZED
import com.demo.protodatastore.manager.session.SessionDataStoreInterface
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthenticationInterceptor @Inject constructor(
    private val sessionDataStoreInterface: SessionDataStoreInterface,
    private val sessionService: SessionService,
    private val coroutineDispatcher: CoroutineDispatcher,
) : Interceptor {

    private val mutex: Mutex = Mutex()

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val accessToken = runBlocking(coroutineDispatcher) {
            sessionDataStoreInterface.getAccessToken()
        }

        val authenticatedRequest = request.newBuilder()
            .header(AUTHORIZATION_HEADER, "Bearer $accessToken")
            .build()

        val response = chain.proceed(authenticatedRequest)

        // access token is valid, resume request
        if (response.code != UNAUTHORIZED) return response

        // token is unauthorized, refresh access and refresh tokens
        val tokenResponse: TokenResponse? = runBlocking {
            mutex.withLock {
                val tokenResponse = getUpdatedToken().await()
                tokenResponse.body().also {
                    sessionDataStoreInterface.setAccessToken(accessToken = it?.accessToken ?: "")
                    sessionDataStoreInterface.setRefreshToken(refreshToken = it?.refreshToken ?: "")
                }
            }
        }

        return if (tokenResponse?.accessToken != null) {
            response.close()

            // retry original request with new token
            val retryAuthenticatedRequest = request.newBuilder()
                .header(AUTHORIZATION_HEADER, "Bearer ${tokenResponse.accessToken}")
                .build()

            val retryResponse = chain.proceed(retryAuthenticatedRequest)
            return retryResponse
        } else {
            response
        }

    }


    private suspend fun getUpdatedToken(): Deferred<retrofit2.Response<TokenResponse>> {
        val refreshToken = sessionDataStoreInterface.getRefreshToken()
        return withContext(coroutineDispatcher) {
            sessionService.getTokens(refreshToken = refreshToken)
        }
    }
}