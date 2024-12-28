package com.demo.data.result

import com.demo.domain.model.ErrorMessage

sealed class Outcome<T> {
  abstract fun isSuccess(): Boolean

  open fun errorMessage(): com.demo.domain.model.ErrorMessage? = null

  abstract suspend fun accept(useCase: UseCase<T>)

  class Success<T>(val data: T) : Outcome<T>() {
    override fun isSuccess(): Boolean = true
    override suspend fun accept(useCase: UseCase<T>) = useCase.onSuccess(this)
  }

  class Error<T>(private val errorMessage: com.demo.domain.model.ErrorMessage) : Outcome<T>() {
    override fun isSuccess(): Boolean = false
    override fun errorMessage(): com.demo.domain.model.ErrorMessage = errorMessage
    override suspend fun accept(useCase: UseCase<T>) = useCase.onError(errorMessage)
  }

  class Empty<T>() : Outcome<T>() {
    override fun isSuccess(): Boolean = true
    override suspend fun accept(useCase: UseCase<T>) = useCase.onEmptyResponse()
  }

  companion object {
    fun <T> success(data: T) = Success<T>(data)
    fun <T> error(errorMessage: com.demo.domain.model.ErrorMessage) = Error<T>(errorMessage)
    fun <T> empty() = Empty<T>()
  }
}
