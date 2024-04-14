package com.example.moviesearch.data.util

sealed class Answer<T> {
    class Success<T>(val response: T) : Answer<T>()
    class ServerError<T>(val code: Int, val json: String?) : Answer<T>()
    class Error<T> : Answer<T>()
}
