package com.bkosawa.network

import android.content.Context
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Cache
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit


const val NETWORK_CACHE_SIZE = (5 * 1024 * 1024).toLong()

class NetworkFacade(
    applicationContext: Context,
    baseUrl: String,
    networkCacheSize: Long = NETWORK_CACHE_SIZE
) {

    private val cache = Cache(applicationContext.applicationContext.cacheDir, networkCacheSize)

    private val httpClient: OkHttpClient = OkHttpClient.Builder()
        .cache(cache)
        .apply {
            if (BuildConfig.DEBUG) {
                val logging = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
                addInterceptor(logging)
            }
        }.build()

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(createNetworkConverterFactory())
        .client(httpClient)
        .build()

    private fun createNetworkConverterFactory(
        contentType: MediaType = MediaType.parse("application/json")!!
    ): Converter.Factory {
        val json = Json {
            ignoreUnknownKeys = true
        }
        return json.asConverterFactory(contentType)
    }
}