package com.demo.protodatastore.manager.session

import androidx.datastore.core.DataStore
import com.demo.proto.Session
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class SessionDataStoreInterfaceImpl(
  private val sessionDataStore: DataStore<Session>,
) : SessionDataStoreInterface {

  override suspend fun setAccessToken(accessToken: String) {
    sessionDataStore.updateData { currentSessionData ->
      currentSessionData.toBuilder().setAccessToken(accessToken).build()
    }
  }

  override suspend fun setRefreshToken(refreshToken: String) {
    sessionDataStore.updateData { currentSessionData ->
      currentSessionData.toBuilder().setRefreshToken(refreshToken).build()
    }
  }

  override suspend fun setUserId(userId: String) {
    sessionDataStore.updateData { currentSessionData ->
      currentSessionData.toBuilder().setUserId(userId).build()
    }
  }

  override suspend fun setSession(accessToken: String, refreshToken: String, userId: String) {
    sessionDataStore.updateData { currentSessionData ->
      currentSessionData.toBuilder()
        .setAccessToken(accessToken)
        .setRefreshToken(refreshToken)
        .setUserId(userId)
        .build()
    }
  }

  override suspend fun getAccessToken(): String {
    return sessionDataStore.data.first().accessToken
  }

  override fun getAccessTokenFlow(): Flow<String> {
    return sessionDataStore.data.map { session -> session.accessToken }
  }

  override suspend fun getRefreshToken(): String {
    return sessionDataStore.data.first().refreshToken
  }

  override fun getRefreshTokenFlow(): Flow<String> {
    return sessionDataStore.data.map { session -> session.refreshToken }
  }

  override suspend fun getUserId(): String {
    return sessionDataStore.data.first().userId
  }

  override fun getUserIdFlow(): Flow<String> {
    return sessionDataStore.data.map { session -> session.userId }
  }

    /*
    getSession() func returns a single instance of Session synchronously using the first() operator.
    It waits for the first emitted value from the "sessionDataStore.data" flow and returns it immediately.
    This is useful when retrieving the current session data and using it for further processing.
     */
  override suspend fun getSession(): Session {
    return sessionDataStore.data.first()
  }

    /*
    getSessionFlow() func returns a Flow of Session instances asynchronously using the map operator.
    It does not retrieve the session data immediately but instead provides a stream of session data over time.
    This is useful when observing changes to session data continuously.
    Can be used with observe or collect
     */
  override fun getSessionFlow(): Flow<Session> {
    return sessionDataStore.data.map { session -> session }
  }
}
