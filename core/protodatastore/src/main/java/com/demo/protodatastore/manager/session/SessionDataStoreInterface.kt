package com.demo.protodatastore.manager.session

import com.demo.proto.Session
import kotlinx.coroutines.flow.Flow

interface SessionDataStoreInterface {

  // setters
  suspend fun setAccessToken(accessToken: String)
  suspend fun setRefreshToken(refreshToken: String)
  suspend fun setUserId(userId: String)
  suspend fun setSession(accessToken: String, refreshToken: String, userId: String)

  // getters
  suspend fun getAccessToken(): String
  fun getAccessTokenFlow(): Flow<String>

  suspend fun getRefreshToken(): String
  fun getRefreshTokenFlow(): Flow<String>

  suspend fun getUserId(): String
  fun getUserIdFlow(): Flow<String>

  suspend fun getSession(): Session
  fun getSessionFlow(): Flow<Session>
}
