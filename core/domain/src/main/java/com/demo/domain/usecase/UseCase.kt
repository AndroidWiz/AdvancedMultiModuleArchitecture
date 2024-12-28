package com.demo.domain.usecase

import com.demo.domain.result.Outcome

interface UseCase<R> {

  suspend fun onSuccess(success: Outcome.Success<R>)

  suspend fun onEmptyResponse()

  suspend fun onError(errorMessage: com.demo.domain.model.ErrorMessage)

//    suspend fun onComplete()
}
