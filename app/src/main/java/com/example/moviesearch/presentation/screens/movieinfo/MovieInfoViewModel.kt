package com.example.moviesearch.presentation.screens.movieinfo

import com.example.moviesearch.data.util.Answer
import com.example.moviesearch.domain.AllMoviesRepository
import com.example.moviesearch.domain.models.Movie
import com.example.moviesearch.presentation.basecomponents.BaseViewModel
import com.example.moviesearch.presentation.screens.UiState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

class MovieInfoViewModel @Inject constructor(
    private val allMoviesRepository: AllMoviesRepository
) : BaseViewModel() {

    private val _movieInfoFlow = MutableSharedFlow<UiState<Movie>>(replay = 1)
    val moviesInfo = _movieInfoFlow.asSharedFlow()

    fun getMovieInfo(id: Int) {
        doWork {
            when (val response = allMoviesRepository.getMovieInfoById(id)) {
                is Answer.Error -> _movieInfoFlow.emit(UiState.Error("Что то пошло не так"))
                is Answer.ServerError -> _movieInfoFlow.emit(UiState.Error("Ошибка: ${response.code}"))
                is Answer.Success -> _movieInfoFlow.emit(UiState.Success(response.response))
            }
        }
    }
}