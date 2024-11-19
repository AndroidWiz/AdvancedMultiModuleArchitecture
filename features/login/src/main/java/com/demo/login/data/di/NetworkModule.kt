package com.demo.login.data.di

import com.demo.data.factory.ServiceFactory
import com.demo.login.data.service.LoginService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

  @Provides
  @Singleton
  fun provideLoginServiceFactory(serviceFactory: ServiceFactory): LoginService {
    return serviceFactory.create(LoginService::class.java)
  }
}
