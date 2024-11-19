package com.demo.data.source

import com.demo.data.connectivity.NetworkMonitorInterface
import com.google.gson.Gson

class NetworkDataSource<SERVICE>(
  private val service: SERVICE,
  private val gson: Gson,
  private val networkMonitorInterface: NetworkMonitorInterface,
  private val userIdProvider: () -> String,
)
