package com.demo.data.error

import com.demo.data.model.ErrorMessage
import com.demo.data.response.ErrorResponse
import com.google.gson.Gson

// mapping errorResponse to ErrorMessage
fun ErrorResponse.toDomain(errorCode: Int): ErrorMessage {
    return ErrorMessage(
        code = errorCode,
        message = errorMessage.orEmpty(),
        errorFieldList = errorFieldList ?: emptyList()
    )
}

// default error message
fun getDefaultErrorResponse(): ErrorResponse =
    ErrorResponse(errorCode = "", errorMessage = "", errorFieldList = emptyList())

// get error response from error body of type String
fun getErrorResponse(gson: Gson, errorBodyString: String): ErrorResponse =
    try {
        gson.fromJson(errorBodyString, ErrorResponse::class.java)
    } catch (e: Exception) {
        getDefaultErrorResponse()
    }
