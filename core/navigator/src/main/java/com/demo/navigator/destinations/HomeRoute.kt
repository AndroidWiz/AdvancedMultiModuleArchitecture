package com.demo.navigator.destinations

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

const val HOME_SCREEN_ROUTE: String = "HomeRoute"
const val USER_PARAMETER: String = "user"
const val USER_AGE: String = "age"
const val USER_FULL_NAME: String = "fullName"

class HomeRoute : NavigationRoute {

    fun createHome(user: String, fullName: String, age: Int): String =
        "$HOME_SCREEN_ROUTE/$user/$fullName/$age"

    override fun route(): String = Screens.HomeScreenRoute.route

    override val arguments: List<NamedNavArgument>
        get() = listOf(
            navArgument(USER_PARAMETER) { type = NavType.StringType },
            navArgument(USER_AGE) { type = NavType.IntType },
            navArgument(USER_FULL_NAME) { type = NavType.StringType },
        )
}