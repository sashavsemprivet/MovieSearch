package com.example.moviesearch.domain.models

data class Review(
    val id: Int,
    val movieId: Int,
    val title: String,
    val review: String,
    val date: String,
    val author: String
)