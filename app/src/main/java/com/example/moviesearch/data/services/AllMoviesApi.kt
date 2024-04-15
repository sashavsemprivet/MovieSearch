package com.example.moviesearch.data.services

import com.example.moviesearch.data.models.MovieDetailResponse
import com.example.moviesearch.data.models.MovieResponse
import com.example.moviesearch.data.models.MoviesListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AllMoviesApi {

    @GET("v1.4/movie/search")
    suspend fun getAllMovies(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("query") query: String
    ): Response<MoviesListResponse>

    @GET("v1.4/movie/{id}")
    suspend fun getMovieInfoById(@Path("id") id: Int): Response<MovieDetailResponse>
}