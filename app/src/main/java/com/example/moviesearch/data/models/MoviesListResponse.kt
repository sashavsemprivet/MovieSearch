package com.example.moviesearch.data.models

import com.google.gson.annotations.SerializedName

data class MoviesListResponse(
    @SerializedName("docs")
    val listMovieResponses: List<MovieResponse>,
    @SerializedName("total")
    val total: Int,
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("page")
    val page: Int,
    @SerializedName("pages")
    val pages: Int
)

data class MovieResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("alternativeName")
    val alternativeName: String,
    @SerializedName("enName")
    val enName: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("year")
    val year: Int,
    @SerializedName("description")
    val description: String,
    @SerializedName("shortDescription")
    val shortDescription: String,
    @SerializedName("backdrop")
    val backdrop: BackdropResponse?,
    @SerializedName("rating")
    val rating: RatingResponse,
    @SerializedName("genres")
    val genres: List<GenreResponse>,
    @SerializedName("countries")
    val countries: List<CountryResponse>,
    @SerializedName("releaseYears")
    val releaseYears: List<ReleaseYearResponse>,
    @SerializedName("ageRating")
    val ageRating: Int?,
)

data class BackdropResponse(
    @SerializedName("url")
    val url: String?,
    @SerializedName("previewUrl")
    val previewUrl: String?
)

data class RatingResponse(
    @SerializedName("kp")
    val kp: Double,
    @SerializedName("imdb")
    val imdb: Double,
    @SerializedName("tmdb")
    val tmdb: Double,
    @SerializedName("filmCritics")
    val filmCritics: Double,
    @SerializedName("russianFilmCritics")
    val russianFilmCritics: Double,
    @SerializedName("await")
    val await: Double
)

data class GenreResponse(
    @SerializedName("name")
    val name: String
)

data class CountryResponse(
    @SerializedName("name")
    val name: String
)

data class ReleaseYearResponse(
    @SerializedName("start")
    val start: Int,
    @SerializedName("end")
    val end: Int
)