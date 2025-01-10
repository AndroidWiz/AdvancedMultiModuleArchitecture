package com.demo.domain.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data class User(
  val id: String,
  val fullName: String,
  val email: String,
  val photo: String,
)

fun User.toJson(): String = Json.encodeToString(value = this)
fun String.toUser(): User = Json.decodeFromString(string = this)
