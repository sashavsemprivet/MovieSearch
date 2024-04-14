package com.example.moviesearch.data.util

import retrofit2.Response

fun <T> Response<T>.doRequest(): Answer<T> {
    return try {
        if (this.isSuccessful) {
            val resultBody = this.body() ?: return Answer.ServerError(666, "Null")
            Answer.Success(resultBody)
        } else {
            Answer.ServerError(this.code(), this.errorBody()?.string().orEmpty())
        }
    } catch (e: Throwable) {
        Answer.Error()
    }
}
