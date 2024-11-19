package com.demo.data.di

import com.demo.data.interceptors.AUTHORIZATION_HEADER
import com.demo.data.BuildConfig
import com.demo.data.interceptors.CLIENT_ID_HEADER
import com.demo.data.interceptors.HeaderInterceptor
import com.demo.data.OkHttpClientProvider
import com.demo.data.constants.ACCESS_TOKEN_TAG
import com.demo.data.constants.CLIENT_ID_TAG
import com.demo.data.constants.HEADER_INTERCEPTOR_TAG
import com.demo.data.constants.LANGUAGE_TAG
import com.demo.data.constants.LOGGING_INTERCEPTOR_TAG
import com.demo.data.okhttp.OkHttpClientProviderInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Call
import okhttp3.Interceptor
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

  // okhttp client provider
  @Provides
  @Singleton
  @Named(LOGGING_INTERCEPTOR_TAG)
  fun provideOkHttpClientProvider(): OkHttpClientProviderInterface {
    return OkHttpClientProvider()
  }

  // okhttp factory
  @Provides
  @Singleton
  fun provideOkHttpCallFactory(
    @Named(LOGGING_INTERCEPTOR_TAG) okHttpLoggingInterceptor: Interceptor,
    @Named(HEADER_INTERCEPTOR_TAG) headerInterceptor: Interceptor,
    okHttpClientProvider: OkHttpClientProviderInterface,
  ): Call.Factory {
    return okHttpClientProvider.getOkHttpClient(BuildConfig.PIN_CERTIFICATE)
      .addInterceptor(okHttpLoggingInterceptor)
      .addInterceptor(headerInterceptor)
      .retryOnConnectionFailure(true)
      .followRedirects(false)
      .followSslRedirects(false)
      .connectTimeout(60, TimeUnit.SECONDS)
      .readTimeout(60, TimeUnit.SECONDS)
      .writeTimeout(60, TimeUnit.SECONDS)
      .build()
  }
}