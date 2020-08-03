package com.bgomez.picker.remote.service.factory


import com.bgomez.picker.remote.security.SecInterceptor
import com.bgomez.picker.remote.metadata.UrlAnnotation
import com.bgomez.picker.remote.service.UnsplashService
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * Factory for retrofit remote services
 *
 * Created by bernatgomez on July,2020
 */
object RetrofitServFactory {

    object Config {
        const val TIMEOUT = 30000L
    }

    fun createUnsplashService(debug : Boolean = true) =
        this.createService(
            target= UnsplashService::class.java,
            debug= debug
        )

    private fun<T> createService(target : Class<T>, debug : Boolean) =
        Retrofit.Builder()
            .baseUrl(target.annotations.filterIsInstance<UrlAnnotation>().first().url)
            .client(this.createClient(debug))
            .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
            .addConverterFactory(GsonConverterFactory.create())
                .build()
                    .create(target)

    private fun createClient(debug : Boolean) =
        OkHttpClient().newBuilder().apply {
            connectTimeout(Config.TIMEOUT, TimeUnit.MILLISECONDS)
            readTimeout(Config.TIMEOUT, TimeUnit.MILLISECONDS)
            addInterceptor(HttpLoggingInterceptor().setLevel(if (debug) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE))
            addInterceptor(SecInterceptor())
            retryOnConnectionFailure(true)
        }.build()

    private fun createGson() =
        GsonBuilder().apply {
            setLenient()
        }.create()

}
