package com.example.moviesearch.domain.models

data class MovieDetailed(
    val id: Int,
    val name: String,
    val description: String? = null,
    val mainImageUrl: String? = null
)