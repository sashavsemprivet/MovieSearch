package com.example.moviesearch.data.util

import retrofit2.Response

inline fun <T, R> Response<T>.doRequest(map: T.() -> R): Answer<R> {
    return try {
        if (this.isSuccessful) {
            val resultBody = this.body() ?: return Answer.ServerError(666, "Null")
            Answer.Success(resultBody.map())
        } else {
            Answer.ServerError(this.code(), this.errorBody()?.string().orEmpty())
        }
    } catch (e: Throwable) {
        Answer.Error()
    }
}
