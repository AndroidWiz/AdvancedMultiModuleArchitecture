package com.demo.data.model

import com.google.gson.annotations.SerializedName

data class ErrorMessage(
  val code: Int,
  val message: String,
  val errorFieldList: List<String>,
)
