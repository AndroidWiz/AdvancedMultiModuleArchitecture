package com.demo.login.domain.usecase

import com.demo.domain.model.User
import com.demo.domain.result.Outcome
import com.demo.domain.usecase.AsyncUseCase
import com.demo.login.data.source.LoginRemoteInterface
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val loginRemote: LoginRemoteInterface) : AsyncUseCase<LoginUseCase.Input, User>() {

  data class Input(val userName: String, val password: String)

  override suspend fun run(input: Input): Outcome<User> {
    return loginRemote.login(userName = input.userName, password = input.password)
  }
}
