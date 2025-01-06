package com.demo.login.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.login.domain.usecase.LoginUseCase
import com.demo.login.presentation.error.LoginError
import com.demo.login.presentation.flow.LoginInput
import com.demo.login.presentation.flow.LoginOutput
import com.demo.login.presentation.flow.LoginViewState
import com.demo.login.presentation.validator.LoginValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {

  private var loginViewState = LoginViewState()

  // output of viewmodel
  private val _viewOutput: Channel<LoginOutput> = Channel()
  val viewOutput = _viewOutput.receiveAsFlow()

  fun setInput(input: LoginInput) {
    when (input) {
      is LoginInput.LoginButtonClicked -> login()
      is LoginInput.PasswordUpdated -> updateState { copy(password = input.password) }
      is LoginInput.RegisterButtonClicked -> sendOutput { LoginOutput.NavigateToRegister }
      is LoginInput.UsernameUpdated -> updateState { copy(userName = input.userName) }
    }
  }

  private fun updateState(updateState: LoginViewState.() -> LoginViewState) {
    loginViewState = loginViewState.updateState()
    validateInputs()
  }

  private fun validateInputs() {
    val usernameError: LoginError =
      LoginValidator.usernameError(userName = loginViewState.userName)
    val passwordError: LoginError =
      LoginValidator.passwordError(password = loginViewState.password)
    val isLoginButtonEnabled: Boolean =
      LoginValidator.canLogin(usernameError = usernameError, passwordError = passwordError)

    loginViewState = loginViewState.copy(
      userNameError = usernameError,
      passwordError = passwordError,
      isLoginButtonEnabled = isLoginButtonEnabled,
    )
  }

  fun login() {
    viewModelScope.launch {
      loginUseCase.execute(
        input = LoginUseCase.Input(
          userName = loginViewState.userName,
          password = loginViewState.password
        ),
        success = {},
        error = {}
      )
    }
  }

  private fun sendOutput(action: () -> LoginOutput) {
    viewModelScope.launch {
      _viewOutput.send(action())
    }
  }
}
