package com.demo.navigator.destinations

sealed class Screens(val route: String) {

  data object LoginScreenRoute : Screens(route = LOGIN_SCREEN_ROUTE)

  data object RegistrationScreenRoute : Screens(route = REGISTRATION_SCREEN_ROUTE)

  data object HomeScreenRoute :
    Screens(route = "$HOME_SCREEN_ROUTE/$USER_PARAMETER/$USER_FULL_NAME/$USER_AGE")
}
