package com.example.moviesearch.presentation.screens.searchmovie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import androidx.paging.map
import com.example.moviesearch.data.services.AllMoviesApi
import com.example.moviesearch.data.services.RetrofitService
import com.example.moviesearch.domain.AllMoviesRepository
import com.example.moviesearch.domain.models.Movie
import com.example.moviesearch.presentation.basecomponents.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchMovieViewModel @Inject constructor(
    private val allMoviesRepository: AllMoviesRepository
) : BaseViewModel() {

    private val _moviesFlow = MutableSharedFlow<PagingData<Movie>>(replay = 1)
    val moviesFlow = _moviesFlow.asSharedFlow()

    private var searchingText = ""

    init {
        doWork {
            getAllMovies(EMPTY_STRING)
        }
    }

    fun getAllMovies(searchText: String) {
        canceledJob?.cancel()

        doWork {
            delay(1000)
            searchingText = searchText
            val newMoviesFlow = Pager(PagingConfig(pageSize = 10)) {
                MoviePagingSource(allMoviesRepository, searchText)
            }.flow
                .cachedIn(viewModelScope)

            newMoviesFlow.collect { pagingData ->
                pagingData.map {
                    println("!!!!! $it")
                }
                _moviesFlow.emit(pagingData)
            }
        }
    }

    fun filterMovies(country: String?, year: Int?, age: Int?, isOlder: Boolean?) {
        canceledJob?.cancel()

        doWork {
            val newMoviesFlow = Pager(PagingConfig(pageSize = 10)) {
                MoviePagingSource(allMoviesRepository, searchingText)
            }.flow
                .map { pagingData ->
                    pagingData.filter { movie ->
                        val matchCountry = country?.let { movie.countries.contains(it) } ?: true
                        val matchYear = year?.let { movie.year == it } ?: true
                        val matchAge = if (age != null && isOlder != null) {
                            movie.ageRating < age == isOlder
                        } else {
                            true
                        }

                        matchCountry && matchYear && matchAge
                    }
                }.cachedIn(viewModelScope)

            newMoviesFlow.collect { pagingData ->
                _moviesFlow.emit(pagingData)
            }
        }
    }

    companion object {
        private const val EMPTY_STRING = ""
    }
}
