package com.demo.login.data.mapper

import com.demo.domain.model.User
import com.demo.login.data.responses.UserResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class LoginMapperImpl(private val defaultDispatcher: CoroutineDispatcher) : LoginMapper {
  override suspend fun toDomain(userResponse: UserResponse): User {
    return withContext(defaultDispatcher) {
      User(
        id = userResponse.id.orEmpty(),
        fullName = userResponse.fullName.orEmpty(),
        email = userResponse.email.orEmpty(),
        photo = userResponse.photo.orEmpty(),
      )
    }
  }
}
