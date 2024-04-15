package com.example.moviesearch.presentation.screens.movieinfo

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.moviesearch.data.util.Answer
import com.example.moviesearch.domain.AllMoviesRepository
import com.example.moviesearch.domain.models.Movie
import com.example.moviesearch.domain.models.Review
import com.example.moviesearch.presentation.basecomponents.BaseViewModel
import com.example.moviesearch.presentation.mappers.DateTimeFormatter
import com.example.moviesearch.presentation.screens.UiState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

class MovieInfoViewModel @Inject constructor(
    private val allMoviesRepository: AllMoviesRepository
) : BaseViewModel() {

    private val _movieInfoFlow = MutableSharedFlow<UiState<Movie>>(replay = 1)
    val moviesInfoFlow = _movieInfoFlow.asSharedFlow()

    private val _reviewsFlow = MutableSharedFlow<PagingData<Review>>(replay = 1)
    val reviewsFlow = _reviewsFlow.asSharedFlow()

    fun getMovieInfo(movieId: Int) {
        doWork {
            when (val response = allMoviesRepository.getMovieInfoById(movieId)) {
                is Answer.Error -> _movieInfoFlow.emit(UiState.Error("Что то пошло не так"))
                is Answer.ServerError -> _movieInfoFlow.emit(UiState.Error("Ошибка: ${response.code}"))
                is Answer.Success -> {
                    _movieInfoFlow.emit(UiState.Success(response.response))
                    getReviewsByMovieId(movieId)
                }
            }
        }
    }

    private fun getReviewsByMovieId(movieId: Int) {
        doWork {
            val newReviewsFlow = Pager(PagingConfig(pageSize = 10)) {
                ReviewPagingSource(allMoviesRepository, movieId)
            }.flow
                .cachedIn(viewModelScope)

            newReviewsFlow.collect { pagingData ->
                pagingData.map {
                    DateTimeFormatter.mapToDayMonthYear(it.date)
                }
                _reviewsFlow.emit(pagingData)
            }
        }
    }
}