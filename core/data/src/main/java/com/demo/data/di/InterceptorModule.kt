package com.demo.data.di

import com.demo.data.BuildConfig
import com.demo.data.constants.HEADER_INTERCEPTOR_TAG
import com.demo.data.constants.LOGGING_INTERCEPTOR_TAG
import com.demo.data.interceptors.AUTHORIZATION_HEADER
import com.demo.data.interceptors.CLIENT_ID_HEADER
import com.demo.data.interceptors.HeaderInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import java.util.Locale
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class InterceptorModule {

  @Provides
  @Singleton
  @Named(HEADER_INTERCEPTOR_TAG)
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
  @Named(LOGGING_INTERCEPTOR_TAG)
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
}
