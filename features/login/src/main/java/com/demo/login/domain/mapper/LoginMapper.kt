package com.demo.login.domain.mapper

import com.demo.login.data.responses.UserResponse
import com.demo.login.domain.models.User

interface LoginMapper {
  suspend fun toDomain(userResponse: UserResponse): User
}
