package com.example.moviesearch.domain.models

data class Movie(
    val id: Int,
    val name: String,
    val enName: String,
    val year: Int,
    val type: String,
    val description: String = "",
    val logoUrl: String? = null,
    val ageRating: Int = 0,
    val countries: List<String>
)