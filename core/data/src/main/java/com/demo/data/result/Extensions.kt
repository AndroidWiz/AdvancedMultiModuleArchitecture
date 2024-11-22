package com.demo.data.result

import kotlinx.coroutines.isActive
import kotlin.coroutines.coroutineContext

// Executes the given onSuccess action if the Outcome is successful and the coroutine is active.
suspend fun <T> Outcome<T>.doOnSuccess(onSuccess: suspend (T) -> Unit): Outcome<T> {
  if (this is Outcome.Success<T>) {
    if (coroutineContext.isActive) onSuccess(this.data)
  }
  return this
}

// Executes the given onEmpty action if the Outcome is empty and the coroutine is active.
suspend fun <T> Outcome<T>.doOnEmpty(onEmpty: suspend () -> Unit): Outcome<T> {
  if (this is Outcome.Empty) {
    if (coroutineContext.isActive) onEmpty()
  }
  return this
}

// Executes the given onError action if the Outcome is not successful.
suspend fun <T> Outcome<T>.doOnError(onError: () -> Unit): Outcome<T> {
  if (!this.isSuccess() && coroutineContext.isActive) onError()
  return this
}

// Transforms the successful Outcome into another type using the provided mapper function.
suspend fun <T, R> Outcome<T>.map(mapper: suspend (T) -> R): Outcome<R> {
  return when (this) {
    is Outcome.Success<T> -> { Outcome.success(mapper(this.data)) }
    is Outcome.Error<T> -> { Outcome.error(this.errorMessage()) }
    is Outcome.Empty<T> -> { Outcome.empty() }
  }
}

// Merges the current Outcome with another Outcome produced by a lazy function, using a merger function.
suspend fun <F, S, R> Outcome<F>.merge(lazy: suspend () -> Outcome<S>, merger: (F?, S?) -> R): Outcome<R> {
  return when (this) {
    is Outcome.Success<F> -> {
      when (val second = lazy()) {
        is Outcome.Success<S> -> { Outcome.success(merger(this.data, second.data)) }
        is Outcome.Empty<S> -> { Outcome.success(merger(this.data, null)) }
        is Outcome.Error<S> -> { Outcome.error(second.errorMessage()) }
      }
    }
    is Outcome.Error<F> -> { Outcome.error(this.errorMessage()) }
    is Outcome.Empty<F> -> {
      when (val second = lazy()) {
        is Outcome.Success<S> -> { Outcome.success(merger(null, second.data)) }
        is Outcome.Empty<S> -> { Outcome.empty() }
        is Outcome.Error<S> -> { Outcome.error(second.errorMessage()) }
      }
    }
  }
}
