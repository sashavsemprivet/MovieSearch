package com.example.moviesearch.data.models

import com.google.gson.annotations.SerializedName

data class MovieDetailResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
)