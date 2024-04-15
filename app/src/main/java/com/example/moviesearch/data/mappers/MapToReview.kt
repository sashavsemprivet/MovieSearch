package com.example.moviesearch.data.mappers

import com.example.moviesearch.data.models.ListReviewsResponse
import com.example.moviesearch.domain.models.Review

fun ListReviewsResponse.map(): List<Review>{
    return this.listMovieReviews.map {
        Review(
            id = it.id,
            movieId = it.movieId,
            title = it.title,
            review = it.review,
            date = it.date,
            author = it.author
        )
    }
}