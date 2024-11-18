package com.demo.data

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Call
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.Locale
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

  @Provides
  @Singleton
  @Named("Language")
  fun provideLanguage(): () -> Locale {
    return { Locale.getDefault() } // get locale from user
//        return Locale::getDefault // get locale from user
  }

  @Provides
  @Singleton
  @Named("AccessToken")
  fun provideAccessToken(): () -> String? {
    return { "" } // get access token from prefs
  }

  @Provides
  @Singleton
  @Named("ClientId")
  fun provideClientId(): String {
    return "" // get client id from prefs
  }

  @Provides
  @Singleton
  @Named("HeaderInterceptor")
  fun provideHeaderInterceptor(
    @Named("ClientId") clientId: String,
    @Named("AccessToken") accessToken: () -> String?,
    @Named("Language") language: () -> Locale,
  ): Interceptor {
    return HeaderInterceptor(
      clientId = clientId,
      accessTokenProvider = accessToken,
      languageProvider = language,
    )
  }

  // http logging interceptor
  @Provides
  @Singleton
  @Named("OkHttpLoggingInterceptor")
  fun provideOkHttpLoggingInterceptor(): Interceptor {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = if (BuildConfig.DEBUG) {
      HttpLoggingInterceptor.Level.BODY
    } else {
      HttpLoggingInterceptor.Level.NONE
    }

    if (!BuildConfig.DEBUG) {
      // remove headers containing sensitive data
      interceptor.redactHeader(CLIENT_ID_HEADER)
      interceptor.redactHeader(AUTHORIZATION_HEADER)
    }

    return interceptor
  }

  // okhttp factory
  @Provides
  @Singleton
  fun provideOkHttpCallFactory(interceptor: Interceptor): Call.Factory {
    return OkHttpClient.Builder().addInterceptor(interceptor)
      .retryOnConnectionFailure(true)
      .followRedirects(false)
      .followSslRedirects(false)
      .connectTimeout(60, TimeUnit.SECONDS)
      .readTimeout(60, TimeUnit.SECONDS)
      .writeTimeout(60, TimeUnit.SECONDS)
      .build()
  }
}
