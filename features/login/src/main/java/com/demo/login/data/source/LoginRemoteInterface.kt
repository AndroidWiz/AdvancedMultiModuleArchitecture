package com.demo.login.data.source

import com.demo.domain.result.Outcome
import com.demo.login.data.requests.LoginRequestBody
import com.demo.login.domain.models.User

interface LoginRemoteInterface {
  suspend fun login(loginRequestBody: LoginRequestBody): Outcome<User>
}
