package com.demo.protodatastore.di

import android.content.Context
import androidx.datastore.core.DataStore
import com.demo.proto.Preferences
import com.demo.proto.Session
import com.demo.protodatastore.factory.prefsDataStore
import com.demo.protodatastore.factory.sessionDataStore
import com.demo.protodatastore.manager.preferences.PreferencesDataStoreImpl
import com.demo.protodatastore.manager.preferences.PreferencesDataStoreInterface
import com.demo.protodatastore.manager.session.SessionDataStoreImpl
import com.demo.protodatastore.manager.session.SessionDataStoreInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

  @Provides
  @Singleton
  fun provideDataStoreSession(@ApplicationContext context: Context): DataStore<Session> {
    return context.sessionDataStore
  }

  @Provides
  @Singleton
  fun provideDataStorePreferences(@ApplicationContext context: Context): DataStore<Preferences> {
    return context.prefsDataStore
  }

  @Provides
  @Singleton
  fun provideSessionStoreManager(sessionDataStore: DataStore<Session>): SessionDataStoreInterface {
    return SessionDataStoreImpl(sessionDataStore)
  }

  @Provides
  @Singleton
  fun providePreferencesStoreManager(prefsDataStore: DataStore<Preferences>): PreferencesDataStoreInterface {
    return PreferencesDataStoreImpl(prefsDataStore)
  }
}
