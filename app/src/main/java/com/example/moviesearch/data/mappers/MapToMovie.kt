package com.example.moviesearch.data.mappers

import com.example.moviesearch.data.models.MovieResponse
import com.example.moviesearch.domain.models.Movie

fun MovieResponse.map(): Movie {
    return Movie(
        id = id,
        name = name,
        enName = enName,
        year = year,
        type = type,
        description = description,
        logoUrl = backdrop?.previewUrl,
        ageRating = ageRating ?: 0,
        countries = countries.map { it.name }
    )
}
