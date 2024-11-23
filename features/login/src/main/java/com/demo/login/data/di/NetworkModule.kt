package com.demo.login.data.di

import com.demo.data.connectivity.NetworkMonitorInterface
import com.demo.data.constants.USER_ID_TAG
import com.demo.data.factory.ServiceFactory
import com.demo.data.source.NetworkDataSource
import com.demo.login.data.service.LoginService
import com.demo.login.data.source.LoginRemoteInterface
import com.demo.login.data.source.LoginRemoteInterfaceImpl
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

  @Provides
  @Singleton
  fun provideLoginServiceFactory(serviceFactory: ServiceFactory): LoginService {
    return serviceFactory.create(LoginService::class.java)
  }

  @Provides
  @Singleton
  fun provideNetworkDataSource(
    loginService: LoginService,
    gson: Gson,
    networkMonitorInterface: NetworkMonitorInterface,
    @Named(USER_ID_TAG) userIdProvider: () -> String,
  ): NetworkDataSource<LoginService> {
    return NetworkDataSource(
      service = loginService,
      gson = gson,
      networkMonitorInterface = networkMonitorInterface,
      userIdProvider = userIdProvider,
    )
  }

  @Provides
  @Singleton
  fun provideLoginRemoteImpl(networkDataSource: NetworkDataSource<LoginService>): LoginRemoteInterface {
    return LoginRemoteInterfaceImpl(networkDataSource = networkDataSource)
  }
}
