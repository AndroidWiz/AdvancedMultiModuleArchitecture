package com.demo.presentation

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import com.demo.domain.model.ErrorMessage
import com.demo.presentation.views.RenderEmptyScreen
import com.demo.presentation.views.RenderErrorFullScreen
import com.demo.presentation.views.RenderErrorPopupScreen
import com.demo.presentation.views.RenderLoadingFullScreen
import com.demo.presentation.views.RenderLoadingPopupScreen

sealed class StateRenderer<out S, O> { // S for view state and O for output
  // content state
  class ScreenContent<S, O>(val viewState: S) : StateRenderer<S, O>()

  // loading states
  // a. popup loading state
  data class LoadingPopup<S, O>(
    val viewState: S,
    @StringRes val loadingMessage: Int = R.string.loading,
  ) : StateRenderer<S, O>()

  // b. full screen loading state
  data class LoadingFullScreen<S, O>(
    val viewState: S,
    @StringRes val loadingMessage: Int = R.string.loading,
  ) : StateRenderer<S, O>()

  // error state
  // a. popup error state
  data class ErrorPopup<S, O>(
    val viewState: S,
    val errorMessage: ErrorMessage,
  ) : StateRenderer<S, O>()

  // b. full screen error state
  data class ErrorFullScreen<S, O>(
    val viewState: S,
    val errorMessage: ErrorMessage,
  ) : StateRenderer<S, O>()

  // empty state
  data class Empty<S, O>(
    val viewState: S,
    @StringRes val emptyMessage: Int = R.string.no_data,
  ) : StateRenderer<S, O>()

  // success state
  data class Success<S, O>(val output: O) : StateRenderer<S, O>()

  // ScreenContent
  @Composable
  fun onUiState(action: @Composable (S) -> Unit): StateRenderer<S, O> {
    if (this is ScreenContent) action(viewState)
    return this
  }

  // Loading
  @Composable
  fun onLoadingState(action: @Composable (S) -> Unit): StateRenderer<S, O> {
    if (this is LoadingPopup) action(viewState) else if (this is LoadingFullScreen) action(viewState)
    return this
  }

  // Success
  @Composable
  fun onSuccessState(action: (O) -> Unit): StateRenderer<S, O> {
    if (this is Success) action(output)
    return this
  }

  // Error
  @Composable
  fun onErrorState(action: @Composable (S) -> Unit): StateRenderer<S, O> {
    if (this is ErrorPopup) action(viewState) else if (this is ErrorFullScreen)action(viewState)
    return this
  }

  // Empty
  @Composable
  fun onEmptyState(action: () -> Unit): StateRenderer<S, O> {
    if (this is Empty) action()
    return this
  }

  companion object {
    @Composable
    fun <S, O> of(
      retryAction: () -> Unit = {},
      stateRenderer: StateRenderer<S, O>,
      block: @Composable StateRenderer<S, O>.() -> Unit,
    ): StateRenderer<S, O> {
      stateRenderer.block() // show this first before doing anything

      when (stateRenderer) {
        is Empty -> RenderEmptyScreen(emptyMessage = stateRenderer.emptyMessage)
        is ErrorFullScreen -> RenderErrorFullScreen(errorMessage = stateRenderer.errorMessage, retryAction = retryAction)
        is ErrorPopup -> RenderErrorPopupScreen(errorMessage = stateRenderer.errorMessage, retryAction = retryAction)
        is LoadingFullScreen -> RenderLoadingFullScreen(loadingMessage = stateRenderer.loadingMessage)
        is LoadingPopup -> RenderLoadingPopupScreen(loadingMessage = stateRenderer.loadingMessage)
        else -> {}
      }

      return stateRenderer
    }
  }
}
