package com.example.moviesearch.data

import com.example.moviesearch.data.mappers.map
import com.example.moviesearch.data.services.AllMoviesApi
import com.example.moviesearch.data.util.Answer
import com.example.moviesearch.data.util.doRequest
import com.example.moviesearch.domain.AllMoviesRepository
import com.example.moviesearch.domain.models.Movie
import com.example.moviesearch.domain.models.MovieDetailed
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
            when (val response = allMoviesService.getAllMovies(page, limit, query).doRequest()) {
                is Answer.Error -> Answer.Error()
                is Answer.ServerError -> Answer.ServerError(response.code, response.json)
                is Answer.Success -> Answer.Success(response.response.map())
            }
        }
    }

    override suspend fun getMovieInfoById(id: Int): Answer<MovieDetailed> {
        return withContext(Dispatchers.IO) {
            when (val response = allMoviesService.getMovieInfoById(id).doRequest()) {
                is Answer.Error -> Answer.Error()
                is Answer.ServerError -> Answer.ServerError(response.code, response.json)
                is Answer.Success -> Answer.Success(response.response.map())
            }
        }
    }
}