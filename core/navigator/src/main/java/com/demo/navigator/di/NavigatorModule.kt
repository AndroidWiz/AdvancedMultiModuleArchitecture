package com.demo.navigator.di

import com.demo.navigator.core.AppNavigator
import com.demo.navigator.core.AppNavigatorImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NavigatorModule {

  @Provides
  @Singleton
  fun navigator(): AppNavigator = AppNavigatorImpl()
}
