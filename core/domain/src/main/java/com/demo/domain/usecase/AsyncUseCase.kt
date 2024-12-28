package com.demo.domain.usecase

import com.demo.domain.model.ErrorMessage
import com.demo.domain.result.Outcome

abstract class AsyncUseCase<I, R> : UseCase<R> {

  private lateinit var success: suspend (R) -> Unit

  private lateinit var empty: suspend () -> Unit

  private lateinit var error: suspend (ErrorMessage) -> Unit

  suspend fun execute(
    input: I,
    success: suspend (R) -> Unit = {},
    empty: suspend () -> Unit = {},
    error: suspend (ErrorMessage) -> Unit = {},
  ) {
    this.success = success
    this.empty = empty
    this.error = error

    run(input = input).accept(useCase = this)
  }

  abstract suspend fun run(input: I): Outcome<R>

  override suspend fun onSuccess(success: Outcome.Success<R>) {
    success(success.data)
  }

  override suspend fun onEmptyResponse() {
    empty()
  }

  override suspend fun onError(errorMessage: ErrorMessage) {
    error(errorMessage)
  }
}
