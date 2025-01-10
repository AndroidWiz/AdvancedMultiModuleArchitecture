package com.demo.navigator.core

import androidx.navigation.NavOptionsBuilder
import com.demo.navigator.event.NavigatorEvent
import kotlinx.coroutines.flow.Flow

interface AppNavigator {

  fun navigateUp(): Boolean

  fun popBackStack()

  fun navigateTo(
    route: String,
    navOptionsBuilder: NavOptionsBuilder.() -> Unit = { launchSingleTop = true },
  ): Boolean

  val destinations: Flow<NavigatorEvent>
}
