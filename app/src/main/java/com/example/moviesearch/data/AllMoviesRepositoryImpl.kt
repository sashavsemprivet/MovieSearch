package com.example.moviesearch.data

import com.example.moviesearch.data.models.MovieResponse
import com.example.moviesearch.data.models.MoviesListResponse
import com.example.moviesearch.data.services.AllMoviesApi
import com.example.moviesearch.data.util.Answer
import com.example.moviesearch.data.util.doRequest
import com.example.moviesearch.domain.AllMoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AllMoviesRepositoryImpl @Inject constructor(
    private val allMoviesService: AllMoviesApi
) : AllMoviesRepository {

    override suspend fun getAllMovies(
        page: Int,
        limit: Int,
        query: String
    ): Answer<MoviesListResponse> {
        return withContext(Dispatchers.IO) {
            allMoviesService.getAllMovies(page, limit, query).doRequest()
        }
    }

    override suspend fun getMovieInfoById(id: Int): Answer<MovieResponse> {
        return withContext(Dispatchers.IO) {
            allMoviesService.getMovieInfoById(id).doRequest()
        }
    }
}