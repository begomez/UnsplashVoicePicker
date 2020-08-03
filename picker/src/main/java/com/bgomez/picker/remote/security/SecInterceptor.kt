package com.bgomez.picker.remote.security


import com.bgomez.picker.common.utils.LogWrapper
import okhttp3.Interceptor
import javax.inject.Inject


val TAG : String = SecInterceptor::class.java.simpleName


/**
 * Security interceptor for remote API requests
 *
 * Created by bernatgomez on July,2020
 */
class SecInterceptor @Inject constructor() : Interceptor {

    private interface Credentials {
        companion object {
            //TODO: Unsplash API token goes here...
            val TOKEN = ""
        }
    }

    private interface Headers {
        companion object {
            val NAME = "Authorization"
            val VALUE = "Client-ID ${Credentials.TOKEN}"
        }
    }

    override fun intercept(chain: Interceptor.Chain) =
        try {
            var mRes = chain.request().newBuilder().apply {
                header(Headers.NAME, Headers.VALUE)
                method(chain.request().method(), chain.request().body())
            }.build()

            chain.proceed(mRes)

        } catch (e : Exception) {
            LogWrapper.e(TAG, "intercept()", e)

            chain.proceed(chain.request())
        }
}