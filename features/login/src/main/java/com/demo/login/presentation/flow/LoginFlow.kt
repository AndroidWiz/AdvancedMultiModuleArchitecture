package com.demo.login.presentation.flow

import com.demo.domain.model.ErrorMessage

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
