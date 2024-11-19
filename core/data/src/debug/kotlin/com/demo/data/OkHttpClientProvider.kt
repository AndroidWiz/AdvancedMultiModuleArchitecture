package com.demo.data

import com.demo.data.okhttp.OkHttpClientProviderInterface
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

class OkHttpClientProvider : OkHttpClientProviderInterface {
  private var dispatcher = Dispatcher()

  override fun getOkHttpClient(pin: String): OkHttpClient.Builder {
    try {
      // create a trust manager that does not validate certificate chains
      val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {

        @Throws(CertificateException::class)
        override fun checkClientTrusted(
          chain: Array<out X509Certificate>?,
          authType: String?,
        ) {
        }

        override fun checkServerTrusted(
          chain: Array<out X509Certificate>?,
          authType: String?,
        ) {
        }

        override fun getAcceptedIssuers(): Array<X509Certificate> {
          return arrayOf()
        }
      })

      // install the all-trusting trust manager
      val sslContext = SSLContext.getInstance("TLS")
      sslContext.init(null, trustAllCerts, SecureRandom())

      // create an ssl socket factory ith all-trusting manager
      val sslSocketFactory = sslContext.socketFactory

      val builder = OkHttpClient.Builder()
      builder.dispatcher(dispatcher)
      builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
      builder.hostnameVerifier(HostnameVerifier { _, _ -> true })
      return builder
    } catch (e: Exception) {
      throw RuntimeException(e)
    }
  }

  override fun cancelAllRequests() {
    dispatcher.cancelAll()
  }
}
