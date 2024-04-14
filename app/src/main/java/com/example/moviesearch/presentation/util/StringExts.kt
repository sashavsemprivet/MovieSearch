package com.example.moviesearch.presentation.util

fun String.addPlusToEnd(): String {
    return "$this+"
}

fun String.translateType(): String {
    return when (this) {
        "tv-series" -> "Сериал"
        "movie" -> "Фильм"
        "cartoon" -> "Мультфильм"

        else -> this
    }
}

fun String.endToYear(): String {
    return "$this г."
}
