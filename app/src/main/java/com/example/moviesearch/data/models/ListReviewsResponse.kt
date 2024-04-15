package com.example.moviesearch.data.models

import com.google.gson.annotations.SerializedName

data class ListReviewsResponse(
    @SerializedName("docs")
    val listMovieReviews: List<ReviewResponse>,
    @SerializedName("total")
    val total: Int,
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("page")
    val page: Int,
    @SerializedName("pages")
    val pages: Int
)

data class ReviewResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("movieId")
    val movieId: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("review")
    val review: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("author")
    val author: String
)