package com.demo.data.source

interface DataSource {
  companion object {
    const val SUCCESS: Int = 200
    const val SEE_OTHERS: Int = 300
    const val CREATED: Int = 201
    const val BAD_REQUEST: Int = 400
    const val UNAUTHORIZED: Int = 401
    const val FORBIDDEN: Int = 403
    const val NOT_FOUND: Int = 404
    const val CONFLICT: Int = 409
    const val INTERNAL_SERVER_ERROR: Int = 500

    const val UNKNOWN: Int = -1
    const val NO_INTERNET: Int = -2
    const val TIMEOUT: Int = -3
    const val SSL_PINNING: Int = -4
  }
}
