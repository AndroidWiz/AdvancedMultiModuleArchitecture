package com.demo.presentation

import androidx.annotation.StringRes
import com.demo.domain.model.ErrorMessage

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
}
