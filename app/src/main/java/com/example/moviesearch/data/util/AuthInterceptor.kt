package com.example.moviesearch.data.util

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val apiKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain
            .request()
            .newBuilder()
            .addHeader("accept", "application/json")
            .addHeader("X-API-KEY", apiKey)
            .build()
        return chain.proceed(request)
    }
}