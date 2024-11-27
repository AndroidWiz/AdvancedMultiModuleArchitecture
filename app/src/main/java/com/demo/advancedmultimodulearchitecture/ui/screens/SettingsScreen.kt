package com.demo.advancedmultimodulearchitecture.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.datastore.core.DataStore
import com.demo.datastore.settings.AppSettings
import com.demo.datastore.settings.Language
import com.demo.datastore.settings.Location
import com.demo.protodatastore.manager.session.SessionDataStoreInterface
import kotlinx.coroutines.launch

@Composable
fun SettingsScreen(
  modifier: Modifier,
  appSettingsDataStore: DataStore<AppSettings>,
  sessionDataStoreInterface: SessionDataStoreInterface,
) {
  val scope = rememberCoroutineScope()
  val appSettings by appSettingsDataStore.data.collectAsState(initial = AppSettings())

  val accessTokenFlow by sessionDataStoreInterface.getAccessTokenFlow()
    .collectAsState(initial = "")
  var accessToken by remember { mutableStateOf("") }

  Column(modifier = Modifier.padding(50.dp)) {
    // access token
    Text(text = "Access Token Flow: $accessTokenFlow")
    Spacer(modifier = modifier.height(16.dp))

    LaunchedEffect(Unit) {
      scope.launch {
        accessToken = sessionDataStoreInterface.getAccessToken()
      }
    }
    Text(text = "Access Token: $accessToken")
    Spacer(modifier = modifier.height(16.dp))

    // button
    Button(onClick = {
      scope.launch {
        sessionDataStoreInterface.setAccessToken("Access Token ${System.currentTimeMillis()}")
      }
    }) {
      Text(text = "Insert token")
    }

    // language
    Text(text = "Language: ${appSettings.language}")
    Spacer(modifier = modifier.height(16.dp))

    // last known location
    Text(text = "Last known location: ")
    appSettings.lastKnownLocations.forEach { location ->
      Spacer(modifier = modifier.height(16.dp))
      Text(text = "Latitude: ${location.lat}, Longitude: ${location.long}")
    }
    Spacer(modifier = modifier.height(16.dp))

    // dropdown menu to display all languages}
    val newLocation = Location(lat = 37.123, long = 122.908)
    Language.entries.forEach { language ->
      DropdownMenuItem(
        text = { Text(text = language.name) },
        onClick = {
          scope.launch {
            appSettingsDataStore.updateData { currentSettings ->
              currentSettings.copy(
                language = language,
                lastKnownLocations = currentSettings.lastKnownLocations.add(
                  newLocation,
                ),
              )
            }
          }
        },
      )
    }
  }
}
