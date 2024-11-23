package com.demo.login.data.source

import com.demo.data.result.Outcome
import com.demo.login.data.requests.LoginRequestBody
import com.demo.login.domain.User

class LoginRemoteInterfaceImpl : LoginRemoteInterface {
    override suspend fun login(loginRequestBody: LoginRequestBody): Outcome<User> {

    }
}