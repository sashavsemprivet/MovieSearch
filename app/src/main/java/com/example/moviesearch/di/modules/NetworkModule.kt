package com.example.moviesearch.di.modules

import com.example.moviesearch.data.AllMoviesRepositoryImpl
import com.example.moviesearch.data.services.AllMoviesApi
import com.example.moviesearch.data.services.RetrofitService
import com.example.moviesearch.data.util.AuthInterceptor
import com.example.moviesearch.domain.AllMoviesRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit

@Module
class NetworkModule {

    @Provides
    fun provideRetrofitService(): AllMoviesApi {
        return RetrofitService.allMoviesApi
    }

    @Provides
    fun provideRepository(api: AllMoviesApi): AllMoviesRepository {
        return AllMoviesRepositoryImpl(api)
    }
}