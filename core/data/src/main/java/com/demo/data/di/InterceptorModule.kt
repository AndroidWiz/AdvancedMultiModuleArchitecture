package com.demo.data.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.demo.data.BuildConfig
import com.demo.data.constants.CHUCKER_INTERCEPTOR_TAG
import com.demo.data.constants.HEADER_INTERCEPTOR_TAG
import com.demo.data.constants.LOGGING_INTERCEPTOR_TAG
import com.demo.data.interceptors.AUTHORIZATION_HEADER
import com.demo.data.interceptors.CLIENT_ID_HEADER
import com.demo.data.interceptors.HeaderInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

  // chucker interceptor
  @Provides
  @Singleton
  @Named(CHUCKER_INTERCEPTOR_TAG)
  fun provideChuckerInterceptor(@ApplicationContext context: Context): Interceptor {
    return ChuckerInterceptor.Builder(context)
      .collector(
        ChuckerCollector(
          context = context,
          // Toggles visibility of the notification
          showNotification = true,
          // Allows to customize the retention period of collected data
          retentionPeriod = RetentionManager.Period.ONE_HOUR
        )
      )
      // The max body content length in bytes, after this responses will be truncated.
      .maxContentLength(250_000L)
      // List of headers to replace with ** in the Chucker UI
      .redactHeaders(AUTHORIZATION_HEADER)
      // Read the whole response body even when the client does not consume the response completely.
      // This is useful in case of parsing errors or when the response body
      // is closed before being read like in Retrofit with Void and Unit types.
      .alwaysReadResponseBody(true)
      // Controls Android shortcut creation.
      .createShortcut(true)
      .build()
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
