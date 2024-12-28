package com.demo.data.result

import com.demo.domain.model.ErrorMessage

interface UseCase<R> {

  suspend fun onSuccess(success: Outcome.Success<R>)

  suspend fun onEmptyResponse()

  suspend fun onError(errorMessage: com.demo.domain.model.ErrorMessage)

//    suspend fun onComplete()
}
