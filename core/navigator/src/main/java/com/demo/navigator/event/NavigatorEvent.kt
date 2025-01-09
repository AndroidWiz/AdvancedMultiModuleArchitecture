package com.demo.navigator.event

import androidx.navigation.NavOptionsBuilder

sealed class NavigatorEvent {

    data object NavigateUp : NavigatorEvent()

    data object PopBackStack : NavigatorEvent()

    class NavigateTo(
        val destination: String,
        val navOptionsBuilder: NavOptionsBuilder.() -> Unit,
    ) : NavigatorEvent()
}