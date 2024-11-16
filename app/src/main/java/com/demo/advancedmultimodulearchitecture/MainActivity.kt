package com.demo.advancedmultimodulearchitecture

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.demo.advancedmultimodulearchitecture.ui.theme.AdvancedMultiModuleArchitectureTheme
import com.demo.info.MapProvider
import com.demo.provider.DataProvider

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      AdvancedMultiModuleArchitectureTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
          Greeting(
            name = "${DataProvider.USERNAME} ${MapProvider.MAP_ID}",
            modifier = Modifier.padding(innerPadding),
          )
        }
      }
    }

    val counter = 100
    println(counter.toString())
  }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
  Column {
    Text(
      text = "Hello $name!",
      modifier = modifier,
    )
    Text(
      text = "Base url: ${BuildConfig.BASE_URL}",
      modifier = modifier,
    )
    Text(
      text = "DB Version: ${BuildConfig.DB_VERSION}",
      modifier = modifier,
    )
    Text(
      text = "Can clear cache: ${BuildConfig.CAN_CLEAR_CACHE}",
      modifier = modifier,
    )
    Text(
      text = "Map Key: ${BuildConfig.MAP_KEY}",
      modifier = modifier,
    )
  }
}

fun MainMainMainMainMainMainMainMainMainMainMainMainMainMainMainMainMainMainMainMainMainMainMainMain() {
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
  AdvancedMultiModuleArchitectureTheme {
    Greeting("Android")
  }
}
