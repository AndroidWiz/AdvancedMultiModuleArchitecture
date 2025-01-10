package com.demo.login.data.source

import com.demo.domain.model.User
import com.demo.domain.result.Outcome

interface LoginRemoteInterface {
  suspend fun login(userName: String, password: String): Outcome<User>
}
