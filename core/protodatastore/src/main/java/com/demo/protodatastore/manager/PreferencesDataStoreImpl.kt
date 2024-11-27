package com.demo.protodatastore.manager

import androidx.datastore.core.DataStore
import com.demo.proto.Preferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class PreferencesDataStoreImpl(
  private val preferencesDataStore: DataStore<Preferences>,
) : PreferencesDataStoreInterface {

  override suspend fun setLanguage(language: String) {
    preferencesDataStore.updateData { currentPreferencesData ->
      currentPreferencesData.toBuilder().setLanguage(language).build()
    }
  }

  override suspend fun setIsAppLockEnabled(isAppLockEnabled: Boolean) {
    preferencesDataStore.updateData { currentPreferencesData ->
      currentPreferencesData.toBuilder().setIsAppLockEnabled(isAppLockEnabled).build()
    }
  }

  override suspend fun setNotificationCount(notificationCount: Int) {
    preferencesDataStore.updateData { currentPreferencesData ->
      currentPreferencesData.toBuilder().setNotificationCount(notificationCount).build()
    }
  }

  override suspend fun setMoneyBalance(moneyBalance: Long) {
    preferencesDataStore.updateData { currentPreferencesData ->
      currentPreferencesData.toBuilder().setMoneyBalance(moneyBalance).build()
    }
  }

  override suspend fun getLanguage(): String {
    return preferencesDataStore.data.first().language
  }

  override fun getLanguageFlow(): Flow<String> {
    return preferencesDataStore.data.map { prefs ->
      prefs.language
    }
  }

  override suspend fun getIsAppLockEnabled(): Boolean {
    return preferencesDataStore.data.first().isAppLockEnabled
  }

  override fun getIsAppLockEnabledFlow(): Flow<Boolean> {
    return preferencesDataStore.data.map { prefs ->
      prefs.isAppLockEnabled
    }
  }

  override suspend fun getNotificationCount(): Int {
    return preferencesDataStore.data.first().notificationCount
  }

  override fun getNotificationCountFlow(): Flow<Int> {
    return preferencesDataStore.data.map { prefs ->
      prefs.notificationCount
    }
  }

  override suspend fun getMoneyBalance(): Long {
    return preferencesDataStore.data.first().moneyBalance
  }

  override fun getMoneyBalanceFlow(): Flow<Long> {
    return preferencesDataStore.data.map { prefs ->
      prefs.moneyBalance
    }
  }
}
