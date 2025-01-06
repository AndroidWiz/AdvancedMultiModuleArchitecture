package com.demo.login.presentation.error

import com.demo.login.R

sealed class LoginError : Error() {

  abstract fun getErrorMessage(): Int

  // no entry - default state
  data object NoEntry : LoginError() {
    override fun getErrorMessage(): Int = R.string.no_error
  }

  // no error - default state
  data object NoError : LoginError() {
    override fun getErrorMessage(): Int = R.string.no_error
  }

  // incorrect username
  data object IncorrectUsername : LoginError() {
    override fun getErrorMessage(): Int = R.string.username_error
  }

  // incorrect password
  data object IncorrectPassword : LoginError() {
    override fun getErrorMessage(): Int = R.string.password_error
  }

  // incorrect username length
  data object IncorrectUsernameLength : LoginError() {
    override fun getErrorMessage(): Int = R.string.username_length_error
  }

  // incorrect password length
  data object IncorrectPasswordLength : LoginError() {
    override fun getErrorMessage(): Int = R.string.password_length_error
  }
}
