package com.demo.data.model

import com.google.gson.annotations.SerializedName

data class ErrorMessage(
  @SerializedName("code") val code: Int,
  @SerializedName("message") val message: String,
)
