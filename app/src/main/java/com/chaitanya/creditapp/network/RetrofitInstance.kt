package com.chaitanya.creditapp.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.net.ssl.HttpsURLConnection

object RetrofitInstance {
    private const val BASE_URL = "https://api.mocklets.com/"

    //setupclient and timeout
    val okHttpClient = OkHttpClient.Builder()
        .hostnameVerifier { hostname, session ->
            hostname == "api.mocklets.com" || HttpsURLConnection.getDefaultHostnameVerifier().verify(hostname, session)
        }
        .connectTimeout(30, TimeUnit.SECONDS) //increase connect timeout
        .writeTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()

    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}