package com.demo.login.data.service

import com.demo.login.data.requests.LoginRequestBody
import com.demo.login.data.responses.UserResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

const val BASE_URL = "https://api.mockfly.dev/mocks/8e3fe440-3b42-4c2d-ad02-488457149542"
const val EMAIL = "email"

interface LoginService {

  @POST("$BASE_URL/Auth/Login")
  fun login(@Body loginRequestBody: LoginRequestBody): Deferred<Response<UserResponse>>

  @POST("$BASE_URL/Auth/ForgotPassword")
  fun forgotPassword(@Query(EMAIL) email: String): Deferred<Response<Unit>>
}
