package com.demo.data.di

import com.demo.data.BuildConfig
import com.demo.data.OkHttpClientProvider
import com.demo.data.constants.HEADER_INTERCEPTOR_TAG
import com.demo.data.constants.LOGGING_INTERCEPTOR_TAG
import com.demo.data.okhttp.OkHttpClientProviderInterface
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Call
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

  // okhttp client provider
  @Provides
  @Singleton
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
  ): OkHttpClient {
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

    // retrofit
  @Provides
  @Singleton
  fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    val builder = Retrofit.Builder()
      .baseUrl("")
      .client(okHttpClient)
      .addCallAdapterFactory(CoroutineCallAdapterFactory())

    return builder.build()
  }
}
