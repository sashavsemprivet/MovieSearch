package com.example.moviesearch.presentation.screens.movieinfo

import com.example.moviesearch.data.AllMoviesRepositoryImpl
import com.example.moviesearch.data.mappers.map
import com.example.moviesearch.data.services.RetrofitService
import com.example.moviesearch.data.util.Answer
import com.example.moviesearch.domain.AllMoviesRepository
import com.example.moviesearch.domain.models.Movie
import com.example.moviesearch.presentation.basecomponents.BaseViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

class MovieInfoViewModel @Inject constructor(
    private val allMoviesRepository: AllMoviesRepository
) : BaseViewModel() {

    private val _movieInfoFlow = MutableSharedFlow<Movie>(replay = 1)
    val moviesInfo = _movieInfoFlow.asSharedFlow()

    fun getMovieInfo(id: Int) {
        doWork {
//            when (val data = allMoviesRepository.getMovieInfoById(id)) {
//                is Answer.Error -> _movieInfoFlow.emit(Movie(0, "Error", "enName"))
//                is Answer.ServerError -> _movieInfoFlow.emit(Movie(0, "Error", 1000))
//                is Answer.Success -> _movieInfoFlow.emit(data.response.map())
//            }
        }
    }
}