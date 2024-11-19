package com.demo.data.di

import com.demo.data.constants.ACCESS_TOKEN_TAG
import com.demo.data.constants.CLIENT_ID_TAG
import com.demo.data.constants.LANGUAGE_TAG
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.Locale
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ConfigModule {

  @Provides
  @Singleton
  @Named(LANGUAGE_TAG)
  fun provideLanguage(): () -> Locale {
    return { Locale.getDefault() } // get locale from user
//        return Locale::getDefault // get locale from user
  }

  @Provides
  @Singleton
  @Named(ACCESS_TOKEN_TAG)
  fun provideAccessToken(): () -> String? {
    return { "" } // get access token from prefs
  }

  @Provides
  @Singleton
  @Named(CLIENT_ID_TAG)
  fun provideClientId(): String {
    return "" // get client id from prefs
  }
}
