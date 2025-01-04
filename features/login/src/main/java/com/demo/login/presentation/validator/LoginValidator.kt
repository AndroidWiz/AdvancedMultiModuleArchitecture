package com.demo.login.presentation.validator

import com.demo.login.presentation.error.LoginError

private const val USERNAME_LENGTH: Int = 5
private const val PASSWORD_MIN_LENGTH: Int = 8
private const val PASSWORD_MAX_LENGTH: Int = 12

object LoginValidator {

  fun usernameError(userName: String): LoginError {
    return when {
      userName.isEmpty() -> LoginError.NoEntry
      !isValidUsernameLength(userName = userName) -> LoginError.IncorrectUsernameLength
      !userName.isAlphaNumeric() -> LoginError.IncorrectUsername
      else -> LoginError.NoError
    }
  }

  fun passwordError(password: String): LoginError {
    return when {
      password.isEmpty() -> LoginError.NoEntry
      !isValidPasswordLength(password = password) -> LoginError.IncorrectPasswordLength
      !password.isAlphaNumericSpecialCharacters() -> LoginError.IncorrectPassword
      else -> LoginError.NoError
    }
  }

  private fun isValidPasswordLength(password: String): Boolean {
    return password.count() in PASSWORD_MIN_LENGTH..PASSWORD_MAX_LENGTH
  }

  private fun isValidUsernameLength(userName: String): Boolean {
    return userName.count() > USERNAME_LENGTH
  }

  private fun String.isAlphaNumeric() = matches("[a-zA-Z0-9]+".toRegex())

  private fun String.isAlphaNumericSpecialCharacters(): Boolean {
    val containsLowercase = any { it.isLowerCase() }
    val containsUppercase = any { it.isUpperCase() }
    val containsSpecialCharacters = any { it.isLetterOrDigit() }
    val containsDigits = any { it.isDigit() }

    return containsLowercase && containsUppercase && containsSpecialCharacters && containsDigits
  }
}
