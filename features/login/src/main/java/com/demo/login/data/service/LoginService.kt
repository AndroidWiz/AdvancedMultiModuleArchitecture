package com.demo.login.data.service

import com.demo.login.data.requests.LoginRequestBody
import com.demo.login.data.responses.LoginResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

const val BASE_URL = "https://mydomain.com"
const val EMAIL = "email"

interface LoginService {

  @POST("$BASE_URL/Auth/Login")
  fun login(@Body loginRequestBody: LoginRequestBody): Deferred<LoginResponse>

  @POST("$BASE_URL/Auth/ForgotPassword")
  fun forgotPassword(@Query(EMAIL) email: String): Deferred<Unit>
}
