package com.example.moviebuzz.di

import com.example.moviebuzz.repository.remote.MovieRepository
import com.example.moviebuzz.repository.tmdb_service.MovieService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class MovieFragmentModule {

    @Singleton
    @Provides
    fun providesMovieService(retrofit: Retrofit): MovieService = retrofit.create(MovieService::class.java)

    @Singleton
    @Provides
    fun providesMovieRepository(movieService: MovieService): MovieRepository = MovieRepository(movieService)
}