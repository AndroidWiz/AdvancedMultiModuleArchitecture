package com.demo.data.mapper

import com.demo.data.response.ErrorResponse
import com.demo.domain.model.ErrorMessage

// mapping errorResponse to ErrorMessage
fun ErrorResponse.toDomain(errorCode: Int): ErrorMessage {
  return ErrorMessage(
    code = errorCode,
    message = errorMessage.orEmpty(),
    errorFieldList = errorFieldList ?: emptyList(),
  )
}
