package com.demo.data

import okhttp3.Interceptor
import okhttp3.Response
import java.util.Locale

// headers
const val AUTHORIZATION_HEADER: String = "Authorization"
const val ACCEPT_HEADER: String = "Accept"
const val CONTENT_TYPE_HEADER: String = "Content-Type"
const val ACCEPT_LANGUAGE: String = "Accept-Language"
const val CLIENT_ID_HEADER: String = "Client-Id"

// headers values
const val JSON: String = "application/json"
const val ENGLISH_LANGUAGE: String = "en-US"
const val BANGLA_LANGUAGE: String = "bn-BD"

class HeaderInterceptor(
  private val clientId: String,
  private val accessTokenProvider: () -> String?,
  private val languageProvider: () -> Locale,
) : Interceptor {
  override fun intercept(chain: Interceptor.Chain): Response {
    val request = chain.request()
    val builder = request.newBuilder()
    val language = if (languageProvider() == Locale.ENGLISH) {
      ENGLISH_LANGUAGE
    } else {
      BANGLA_LANGUAGE
    }

    builder.header(CLIENT_ID_HEADER, clientId)
      .header(ACCEPT_HEADER, JSON)
      .header(CONTENT_TYPE_HEADER, JSON)
      .header(ACCEPT_LANGUAGE, language)

    accessTokenProvider()?.let {
      builder.header(AUTHORIZATION_HEADER, "Bearer $it")
    }

    return chain.proceed(builder.build())
  }
}
