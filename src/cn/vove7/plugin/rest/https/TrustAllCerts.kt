package cn.vove7.plugin.rest.https

import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.X509TrustManager

class TrustAllCerts : X509TrustManager {
    override fun checkClientTrusted(p0: Array<out X509Certificate>?, p1: String?) {
    }

    override fun checkServerTrusted(p0: Array<out X509Certificate>?, p1: String?) {
    }

    override fun getAcceptedIssuers(): Array<X509Certificate> {
        return emptyArray()
    }

    companion object {
        fun createSSLSocketFactory(): SSLSocketFactory? {
            try {
                val sc: SSLContext = SSLContext.getInstance("TLS")
                sc.init(null, arrayOf(TrustAllCerts()), SecureRandom())
                return sc.socketFactory
            } catch (e: Exception) {
            }
            return null
        }
    }

}