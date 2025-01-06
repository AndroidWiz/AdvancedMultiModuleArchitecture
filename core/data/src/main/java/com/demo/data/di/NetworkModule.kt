package com.demo.data.di

import android.content.Context
import com.demo.data.BuildConfig
import com.demo.data.OkHttpClientProvider
import com.demo.data.connectivity.NetworkMonitorInterface
import com.demo.data.connectivity.NetworkMonitorInterfaceImpl
import com.demo.data.constants.AUTHENTICATION_INTERCEPTOR_TAG
import com.demo.data.constants.CHUCKER_INTERCEPTOR_TAG
import com.demo.data.constants.CONNECTIVITY_INTERCEPTOR_TAG
import com.demo.data.constants.HEADER_INTERCEPTOR_TAG
import com.demo.data.constants.LOGGING_INTERCEPTOR_TAG
import com.demo.data.factory.ServiceFactory
import com.demo.data.okhttp.OkHttpClientProviderInterface
import com.demo.data.service.BASE_URL
import com.demo.data.service.SessionService
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

  @Provides
  @Singleton
  fun provideGson(): Gson {
    return Gson()
  }

  @Provides
  @Singleton
  fun provideNetworkMonitor(context: Context): NetworkMonitorInterface {
    return NetworkMonitorInterfaceImpl(context = context)
  }

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
    @Named(CHUCKER_INTERCEPTOR_TAG) chuckerInterceptor: Interceptor,
    @Named(AUTHENTICATION_INTERCEPTOR_TAG) authenticationInterceptor: Interceptor,
    @Named(CONNECTIVITY_INTERCEPTOR_TAG) connectivityInterceptor: Interceptor,
    okHttpClientProvider: OkHttpClientProviderInterface,
  ): OkHttpClient {
    return okHttpClientProvider.getOkHttpClient(BuildConfig.PIN_CERTIFICATE)
      .addInterceptor(okHttpLoggingInterceptor)
      .addInterceptor(headerInterceptor)
      .addInterceptor(chuckerInterceptor)
      .addInterceptor(connectivityInterceptor)
      .addInterceptor(authenticationInterceptor)
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
      .baseUrl(BASE_URL)
      .client(okHttpClient)
      .addConverterFactory(GsonConverterFactory.create())
      .addCallAdapterFactory(CoroutineCallAdapterFactory())

    return builder.build()
  }

  // service factory
  @Provides
  @Singleton
  fun provideServiceFactory(retrofit: Retrofit): ServiceFactory {
    return ServiceFactory(retrofit)
  }

  @Provides
  @Singleton
  fun provideSessionService(serviceFactory: ServiceFactory): SessionService {
    return serviceFactory.create(SessionService::class.java)
  }
}
