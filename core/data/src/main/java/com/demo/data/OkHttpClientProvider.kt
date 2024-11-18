package com.demo.data

import okhttp3.OkHttpClient

interface OkHttpClientProvider {

    fun getOkHttpClient(pin: String): OkHttpClient.Builder

    fun cancelAllRequests()
}