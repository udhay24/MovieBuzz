package com.example.moviebuzz.Repository.repository

import com.example.moviebuzz.Repository.TMDB_Service.MovieService
import com.example.moviebuzz.Repository.model.PopularMovie
import kotlinx.coroutines.Deferred
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository
@Inject constructor(private val movieService: MovieService)
    :Repository{

    suspend fun getPopularMoviesAsync(): PopularMovie{

        return movieService.getPopularMoviesAsync().await()
    }
}