package com.demo.navigator.destinations

import androidx.navigation.NamedNavArgument

const val LOGIN_SCREEN_ROUTE: String = "LoginRoute"
class LoginRoute: NavigationRoute {
    override fun route(): String = Screens.LoginScreenRoute.route

    override val arguments: List<NamedNavArgument>
        get() = listOf() // pass arguments as required
}