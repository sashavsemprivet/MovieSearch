package com.example.moviesearch.domain

import com.example.moviesearch.data.models.MovieResponse
import com.example.moviesearch.data.util.Answer
import com.example.moviesearch.data.models.MoviesListResponse
import com.example.moviesearch.domain.models.Movie
import com.example.moviesearch.domain.models.MovieDetailed
import retrofit2.http.Query

interface AllMoviesRepository {

    suspend fun getAllMovies(page: Int, limit: Int, query: String): Answer<List<Movie>>

    suspend fun getMovieInfoById(id: Int): Answer<MovieDetailed>
}