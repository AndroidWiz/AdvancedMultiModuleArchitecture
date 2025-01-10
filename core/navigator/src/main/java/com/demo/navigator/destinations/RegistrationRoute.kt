package com.demo.navigator.destinations

import androidx.navigation.NamedNavArgument

const val REGISTRATION_SCREEN_ROUTE: String = "RegistrationRoute"
class RegistrationRoute: NavigationRoute {
    override fun route(): String = Screens.RegistrationScreenRoute.route

    override val arguments: List<NamedNavArgument>
        get() = listOf() // pass arguments as required
}