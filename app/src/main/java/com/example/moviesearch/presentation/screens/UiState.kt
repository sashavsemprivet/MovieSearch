package com.example.moviesearch.presentation.screens

import com.example.moviesearch.domain.models.MovieDetailed

sealed class UiState<T> {
    data class Success<T>(val data: MovieDetailed): UiState<T>()
    data class Error<T>(val errorMessage: String): UiState<T>()
}