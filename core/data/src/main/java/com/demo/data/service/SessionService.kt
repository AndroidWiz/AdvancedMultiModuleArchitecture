package com.demo.data.service

import com.demo.data.response.TokenResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

const val BASE_URL = "https://example.com/"
const val REFRESH_TOKEN = "refreshToken"

interface SessionService {

  @GET("${BASE_URL}Auth/GetSession")
  fun getTokens(
    @Header(REFRESH_TOKEN) refreshToken: String,
  ): Deferred<Response<TokenResponse>>

  @GET("${BASE_URL}Auth/DeleteSession")
  fun logout(
    @Header(REFRESH_TOKEN) refreshToken: String,
  ): Deferred<Response<Unit>>
}
