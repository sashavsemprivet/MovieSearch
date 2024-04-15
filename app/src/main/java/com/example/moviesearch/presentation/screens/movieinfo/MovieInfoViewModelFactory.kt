package com.example.moviesearch.presentation.screens.movieinfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviesearch.domain.AllMoviesRepository
import javax.inject.Inject

class MovieInfoViewModelFactory @Inject constructor(
    private val allMoviesRepository: AllMoviesRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieInfoViewModel::class.java)) {
            return MovieInfoViewModel(allMoviesRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}