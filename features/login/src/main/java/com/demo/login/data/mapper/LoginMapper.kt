package com.demo.login.data.mapper

import com.demo.domain.model.User
import com.demo.login.data.responses.UserResponse

interface LoginMapper {
  suspend fun toDomain(userResponse: UserResponse): User
}
