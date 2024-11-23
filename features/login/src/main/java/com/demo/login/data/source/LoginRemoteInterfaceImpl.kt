package com.demo.login.data.source

import com.demo.data.error.toDomain
import com.demo.data.result.Outcome
import com.demo.data.source.NetworkDataSource
import com.demo.login.data.requests.LoginRequestBody
import com.demo.login.data.service.LoginService
import com.demo.login.domain.mapper.LoginMapper
import com.demo.login.domain.models.User

class LoginRemoteInterfaceImpl(
  private val networkDataSource: NetworkDataSource<LoginService>,
  private val loginMapper: LoginMapper,
) :
  LoginRemoteInterface {
  override suspend fun login(loginRequestBody: LoginRequestBody): Outcome<User> {
    return networkDataSource.performRequest(
      request = { login(loginRequestBody = loginRequestBody).await() },
      onSuccess = { response, _ -> Outcome.success(data = loginMapper.toDomain(userResponse = response)) },
      onError = { errorResponse, errorCode ->
        Outcome.error(
          errorMessage = errorResponse.toDomain(
            errorCode = errorCode,
          ),
        )
      },
    )
  }
}
