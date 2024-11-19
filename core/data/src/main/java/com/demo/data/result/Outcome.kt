package com.demo.data.result

import com.demo.data.model.ErrorMessage

sealed class Outcome<T> {
  abstract fun isSuccess(): Boolean

  open fun errorMessage(): ErrorMessage? = null

  abstract suspend fun accept(useCase: UseCase<T>)

  class Success<T>(val data: T) : Outcome<T>() {
    override fun isSuccess(): Boolean = true
    override suspend fun accept(useCase: UseCase<T>) = useCase.onSuccess(this)
  }

  class Error<T>(private val errorMessage: ErrorMessage) : Outcome<T>() {
    override fun isSuccess(): Boolean = false
    override fun errorMessage(): ErrorMessage = errorMessage
    override suspend fun accept(useCase: UseCase<T>) = useCase.onError(errorMessage)
  }

  class Empty<T>() : Outcome<T>() {
    override fun isSuccess(): Boolean = true
    override suspend fun accept(useCase: UseCase<T>) = useCase.onEmptyResponse()
  }

  companion object {
    fun <T> success(data: T) = Success<T>(data)
    fun <T> error(errorMessage: ErrorMessage) = Error<T>(errorMessage)
    fun <T> empty() = Empty<T>()
  }
}
