package com.example.testapp.core

import com.example.testapp.utils.BASE_URL
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Injection {

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder().build()
    }

    fun <T> createService(
        service: Class<T>
    ): T {
        val okHttpClient = okHttpClient.newBuilder().apply {
        }.build()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(service)
    }
}
