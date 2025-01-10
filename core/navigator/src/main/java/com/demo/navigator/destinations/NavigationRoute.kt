package com.demo.navigator.destinations

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavDeepLink

interface NavigationRoute {

  fun route(): String

  val arguments: List<NamedNavArgument> get() = emptyList()

  val deepLinks: List<NavDeepLink> get() = emptyList()
}
