package com.demo.login.data.source

import com.demo.data.result.Outcome
import com.demo.data.source.NetworkDataSource
import com.demo.login.data.requests.LoginRequestBody
import com.demo.login.data.service.LoginService
import com.demo.login.domain.User

class LoginRemoteInterfaceImpl(private val networkDataSource: NetworkDataSource<LoginService>) :
    LoginRemoteInterface {
    override suspend fun login(loginRequestBody: LoginRequestBody): Outcome<User> {

    }
}