package com.demo.advancedmultimodulearchitecture.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.demo.home.HomeScreen
import com.demo.login.presentation.view.LoginScreen
import com.demo.navigator.core.AppNavigator
import com.demo.navigator.destinations.HomeRoute
import com.demo.navigator.destinations.LoginRoute
import com.demo.navigator.destinations.NavigationRoute
import com.demo.navigator.destinations.RegistrationRoute
import com.demo.registration.RegistrationScreen

private val composableRoutes: Map<NavigationRoute, @Composable (AppNavigator, NavHostController) -> Unit> =
  mapOf(
    RegistrationRoute() to { _, _ -> RegistrationScreen() },
    HomeRoute to { _, navController -> HomeScreen(navController = navController) },
    LoginRoute() to { appNavigator, _ -> LoginScreen(appNavigator = appNavigator) },
  )

fun NavGraphBuilder.addComposableRoutes(
  appNavigator: AppNavigator,
  navHostController: NavHostController,
) {
  composableRoutes.forEach { entry ->
    val destination = entry.key
    composable(
      route = destination.route(),
      arguments = destination.arguments,
      deepLinks = destination.deepLinks,
    ) {
      entry.value(appNavigator, navHostController)
    }
  }
}
