package com.demo.navigator.core

import androidx.navigation.NavOptionsBuilder
import com.demo.navigator.event.NavigatorEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

class AppNavigatorImpl : AppNavigator {

    private val navigationEvents = Channel<NavigatorEvent>()


    override fun navigateUp(): Boolean =
        navigationEvents.trySend(NavigatorEvent.NavigateUp).isSuccess

    override fun popBackStack() {
        navigationEvents.trySend(NavigatorEvent.PopBackStack)
    }

    override fun navigateTo(
        route: String,
        navOptionsBuilder: NavOptionsBuilder.() -> Unit,
    ): Boolean = navigationEvents.trySend(
        NavigatorEvent.NavigateTo(
            destination = route,
            navOptionsBuilder = navOptionsBuilder
        )
    ).isSuccess

    override val destinations: Flow<NavigatorEvent>
        get() = navigationEvents.receiveAsFlow()
}