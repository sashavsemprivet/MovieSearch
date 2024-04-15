package com.example.moviesearch.data.services

import com.example.moviesearch.data.util.AuthInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {

    private const val BASE_URL = "https://api.kinopoisk.dev/"
    private const val X_API_KEY = "WF76VQQ-HQB4P5G-JFJH8DF-CRKDP1M"


    private val client = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor(X_API_KEY))
        .build()


    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val allMoviesApi: AllMoviesApi = retrofit.create(AllMoviesApi::class.java)
}