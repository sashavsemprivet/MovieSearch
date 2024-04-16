package com.example.moviesearch.data

import com.example.moviesearch.data.mappers.map
import com.example.moviesearch.data.services.AllMoviesApi
import com.example.moviesearch.data.util.Answer
import com.example.moviesearch.data.util.doRequest
import com.example.moviesearch.domain.AllMoviesRepository
import com.example.moviesearch.domain.models.Movie
import com.example.moviesearch.domain.models.MovieDetailed
import com.example.moviesearch.domain.models.Review
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
    ): Answer<List<Movie>> {
        return withContext(Dispatchers.IO) {
            allMoviesService.getAllMovies(page, limit, query)
                .doRequest { map() }
        }
    }

    override suspend fun getMovieInfoById(id: Int): Answer<MovieDetailed> {
        return withContext(Dispatchers.IO) {
            allMoviesService.getMovieInfoById(id)
                .doRequest { map() }
        }
    }


    override suspend fun getReviewsByMovieId(
        page: Int,
        limit: Int,
        movieId: Int
    ): Answer<List<Review>> {
        return withContext(Dispatchers.IO) {
            allMoviesService.getReviewsByMovieId(page, limit, movieId)
                .doRequest { map() }
        }
    }
}