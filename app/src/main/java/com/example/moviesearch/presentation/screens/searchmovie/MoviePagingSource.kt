package com.example.moviesearch.presentation.screens.searchmovie

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.moviesearch.data.mappers.map
import com.example.moviesearch.data.services.AllMoviesApi
import com.example.moviesearch.data.util.Answer
import com.example.moviesearch.domain.AllMoviesRepository
import com.example.moviesearch.domain.models.Movie
import javax.inject.Inject

class MoviePagingSource @Inject constructor(
    private val allMoviesRepository: AllMoviesRepository,
    private val query: String
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: 1
        return try {

            when (val response = allMoviesRepository.getAllMovies(page, params.loadSize, query)) {
                is Answer.Error -> LoadResult.Error(Exception("Something went wrong"))
                is Answer.ServerError -> LoadResult.Error(Exception("Server Error ${response.code}"))
                is Answer.Success -> {
                    val movies = response.response
                    LoadResult.Page(
                        data = movies,
                        prevKey = null,
                        nextKey = if (movies.isEmpty()) null else page + 1
                    )
                }
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return null
    }
}
