package com.example.moviesearch.di

import com.example.moviesearch.di.modules.NetworkModule
import com.example.moviesearch.di.modules.ViewModelModule
import com.example.moviesearch.presentation.screens.movieinfo.MovieInfoFragment
import com.example.moviesearch.presentation.screens.searchmovie.SearchMovieFragment
import dagger.Component

@Component(
    modules = [
        NetworkModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {

    fun inject(searchMovieFragment: SearchMovieFragment)
    fun inject(movieInfoFragment: MovieInfoFragment)
}