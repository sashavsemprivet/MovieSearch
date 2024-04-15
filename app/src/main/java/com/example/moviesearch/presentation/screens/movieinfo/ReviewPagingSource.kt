package com.example.moviesearch.presentation.screens.movieinfo

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.moviesearch.data.util.Answer
import com.example.moviesearch.domain.AllMoviesRepository
import com.example.moviesearch.domain.models.Review

class ReviewPagingSource(
    private val allMoviesRepository: AllMoviesRepository,
    private val movieId: Int
) : PagingSource<Int, Review>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Review> {
        val page = params.key ?: 1
        return try {

            when (val response =
                allMoviesRepository.getReviewsByMovieId(page, 5, "", movieId)) {
                is Answer.Error -> LoadResult.Error(Exception("Something went wrong"))
                is Answer.ServerError -> LoadResult.Error(Exception("Server Error ${response.code}"))
                is Answer.Success -> {
                    val reviews = response.response
                    LoadResult.Page(
                        data = reviews,
                        prevKey = null,
                        nextKey = if (reviews.isEmpty()) null else page + 1
                    )
                }
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Review>): Int? {
        return null
    }
}