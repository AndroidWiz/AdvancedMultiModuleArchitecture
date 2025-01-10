package com.demo.advancedmultimodulearchitecture

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.demo.advancedmultimodulearchitecture.ui.theme.AdvancedMultiModuleArchitectureTheme
import com.demo.navigator.core.AppNavigator
import com.demo.navigator.event.NavigatorEvent
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RoutingActivity : ComponentActivity() {

    @Inject
    lateinit var appNavigator: AppNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            AdvancedMultiModuleArchitectureTheme {
                AppScaffold(appNavigator = appNavigator)
            }
        }
    }
}

@Composable
fun AppScaffold(appNavigator: AppNavigator) {
    val navController = rememberNavController()

    LaunchedEffect(navController) {
        appNavigator.destinations.collect { event ->
            when (event) {
                is NavigatorEvent.NavigateTo -> appNavigator.navigateTo(
                    route = event.destination,
                    navOptionsBuilder = event.navOptionsBuilder
                )
                NavigatorEvent.NavigateUp -> appNavigator.navigateUp()
                NavigatorEvent.PopBackStack -> appNavigator.popBackStack()
            }
        }
    }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

    }
}