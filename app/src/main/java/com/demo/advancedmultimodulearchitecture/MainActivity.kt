package com.demo.advancedmultimodulearchitecture

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.demo.advancedmultimodulearchitecture.ui.screens.SettingsScreen
import com.demo.advancedmultimodulearchitecture.ui.theme.AdvancedMultiModuleArchitectureTheme
import com.demo.datastore.settings.AppSettings
import com.demo.datastore.settings.AppSettingsSerializer
import com.demo.protodatastore.manager.preferences.PreferencesDataStoreInterface
import com.demo.protodatastore.manager.session.SessionDataStoreInterface
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

  @Inject
  lateinit var sessionDataStoreInterface: SessionDataStoreInterface

  @Inject
  lateinit var preferencesDataStoreInterface: PreferencesDataStoreInterface

  lateinit var appSettingsDataStore: DataStore<AppSettings>

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    appSettingsDataStore = DataStoreFactory.create(
      serializer = AppSettingsSerializer(),
      produceFile = { dataStoreFile("app_settings.json") },
      scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
    )

//    enableEdgeToEdge()
    setContent {
      AdvancedMultiModuleArchitectureTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
          SettingsScreen(
            modifier = Modifier.padding(innerPadding),
            appSettingsDataStore = appSettingsDataStore,
            sessionDataStoreInterface = sessionDataStoreInterface,
          )
        }
      }
    }
  }
}
