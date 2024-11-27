package com.demo.data.di

import com.demo.data.constants.ACCESS_TOKEN_TAG
import com.demo.data.constants.CLIENT_ID_TAG
import com.demo.data.constants.LANGUAGE_TAG
import com.demo.data.constants.USER_ID_TAG
import com.demo.protodatastore.manager.preferences.PreferencesDataStoreInterface
import com.demo.protodatastore.manager.session.SessionDataStoreInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.runBlocking
import java.util.Locale
import java.util.UUID
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ConfigModule {

  @Provides
  @Singleton
  @Named(USER_ID_TAG)
  fun provideUserId(sessionDataStoreInterface: SessionDataStoreInterface): () -> String? {
    val userId = runBlocking { sessionDataStoreInterface.getUserId() }
    return { userId }
  }

  @Provides
  @Singleton
  @Named(LANGUAGE_TAG)
  fun provideLanguage(prefsDataStoreInterface: PreferencesDataStoreInterface): () -> Locale {
    val language = runBlocking { prefsDataStoreInterface.getLanguage() }

    return if (language.isNotEmpty()) {
      { Locale(language) }
    } else {
      { Locale.ENGLISH }
    }
  }

  @Provides
  @Singleton
  @Named(ACCESS_TOKEN_TAG)
  fun provideAccessToken(sessionDataStoreInterface: SessionDataStoreInterface): () -> String? {
    val accessToken = runBlocking { sessionDataStoreInterface.getAccessToken() }
    return { accessToken }
  }

  @Provides
  @Singleton
  @Named(CLIENT_ID_TAG)
  fun provideClientId(): String {
    return UUID.randomUUID().toString()
  }
}
