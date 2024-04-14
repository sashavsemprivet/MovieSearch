package com.example.moviesearch.presentation.screens.searchmovie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviesearch.domain.AllMoviesRepository
import javax.inject.Inject

class SearchMoviesViewModelFactory @Inject constructor(
    private val allMoviesRepository: AllMoviesRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchMovieViewModel::class.java)) {
            return SearchMovieViewModel(allMoviesRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}