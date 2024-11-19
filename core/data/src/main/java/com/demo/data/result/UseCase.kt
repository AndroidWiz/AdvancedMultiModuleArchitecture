package com.demo.data.result

import com.demo.data.model.ErrorMessage

interface UseCase<R> {

  suspend fun onSuccess(success: Outcome.Success<R>)

  suspend fun onEmptyResponse()

  suspend fun onError(errorMessage: ErrorMessage)

//    suspend fun onComplete()
}
