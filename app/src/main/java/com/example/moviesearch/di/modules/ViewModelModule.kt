package com.example.moviesearch.di.modules

import androidx.lifecycle.ViewModelProvider
import com.example.moviesearch.presentation.screens.searchmovie.SearchMoviesViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun provideSearchViewModelFactory(
        factory: SearchMoviesViewModelFactory
    ): ViewModelProvider.Factory

}