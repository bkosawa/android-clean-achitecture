package com.bkosawa.base.network.di

import android.content.Context
import com.bkosawa.network.NetworkFacade
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

const val BASE_URL = "https://<base-url>"

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofit(applicationContext: Context): Retrofit =
        NetworkFacade(applicationContext, BASE_URL).retrofit
}