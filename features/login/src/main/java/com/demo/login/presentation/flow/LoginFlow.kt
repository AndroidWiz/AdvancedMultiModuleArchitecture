package com.demo.login.presentation.flow

import com.demo.domain.model.ErrorMessage
import com.demo.login.presentation.error.LoginError

sealed class LoginInput {
  data class UsernameUpdated(val userName: String) : LoginInput()
  data class PasswordUpdated(val password: String) : LoginInput()
  data object LoginButtonClicked : LoginInput()
  data object RegisterButtonClicked : LoginInput()
}

sealed class LoginOutput {
  data object NavigateToHome : LoginOutput()
  data object NavigateToRegister : LoginOutput()
  data class ShowError(val errorMessage: ErrorMessage) : LoginOutput()
}


data class LoginViewState(
  val userName: String = "",
  val password: String = "",
  val isLoginButtonEnabled: Boolean = false,
  val userNameError: LoginError = LoginError.NoEntry,
  val passwordError: LoginError = LoginError.NoEntry,
){
  fun showPasswordError() = passwordError != LoginError.NoError && passwordError != LoginError.NoEntry
  fun showUsernameError() = userNameError != LoginError.NoError && userNameError != LoginError.NoEntry
}