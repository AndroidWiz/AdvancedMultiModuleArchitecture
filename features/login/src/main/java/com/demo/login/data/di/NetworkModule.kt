package com.demo.login.data.di

import com.demo.data.connectivity.NetworkMonitorInterface
import com.demo.data.constants.DISPATCHER_DEFAULT_TAG
import com.demo.data.constants.USER_ID_TAG
import com.demo.data.factory.ServiceFactory
import com.demo.data.source.NetworkDataSource
import com.demo.login.data.service.LoginService
import com.demo.login.data.source.LoginRemoteInterface
import com.demo.login.data.source.LoginRemoteInterfaceImpl
import com.demo.login.domain.mapper.LoginMapper
import com.demo.login.domain.mapper.LoginMapperImpl
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
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
  fun provideLoginMapper(@Named(DISPATCHER_DEFAULT_TAG) coroutineDispatcher: CoroutineDispatcher): LoginMapper {
    return LoginMapperImpl(defaultDispatcher = coroutineDispatcher)
  }

  @Provides
  @Singleton
  fun provideLoginRemoteImpl(
    networkDataSource: NetworkDataSource<LoginService>,
    loginMapper: LoginMapper,
  ): LoginRemoteInterface {
    return LoginRemoteInterfaceImpl(
      networkDataSource = networkDataSource,
      loginMapper = loginMapper,
    )
  }
}
