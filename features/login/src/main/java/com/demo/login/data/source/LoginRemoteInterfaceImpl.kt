package com.demo.login.data.source

import com.demo.data.mapper.toDomain
import com.demo.data.source.NetworkDataSource
import com.demo.domain.result.Outcome
import com.demo.login.data.mapper.LoginMapper
import com.demo.login.data.requests.LoginRequestBody
import com.demo.login.data.service.LoginService
import com.demo.login.domain.models.User

class LoginRemoteInterfaceImpl(
  private val networkDataSource: NetworkDataSource<LoginService>,
  private val loginMapper: LoginMapper,
) :
  LoginRemoteInterface {
  override suspend fun login(userName: String, password: String): Outcome<User> {
    return networkDataSource.performRequest(
      request = {
        login(
          LoginRequestBody(
            username = userName,
            password = password,
          ),
        ).await()
      },
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
