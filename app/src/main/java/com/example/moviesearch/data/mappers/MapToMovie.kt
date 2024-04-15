package com.example.moviesearch.data.mappers

import com.example.moviesearch.data.models.MovieDetailResponse
import com.example.moviesearch.data.models.MovieResponse
import com.example.moviesearch.data.models.MoviesListResponse
import com.example.moviesearch.domain.models.Movie
import com.example.moviesearch.domain.models.MovieDetailed

fun MoviesListResponse.map(): List<Movie> {
    return listMovieResponses.map { movie ->
        Movie(
            id = movie.id,
            name = movie.name,
            enName = movie.enName,
            year = movie.year,
            type = movie.type,
            description = movie.description,
            logoUrl = movie.backdrop?.previewUrl,
            ageRating = movie.ageRating ?: 0,
            countries = movie.countries.map { it.name }
        )
    }
}

fun MovieDetailResponse.map(): MovieDetailed {
    return MovieDetailed(
        id = id,
        name = name,
        description = description,
        mainImageUrl = backdrop.url
    )
}
