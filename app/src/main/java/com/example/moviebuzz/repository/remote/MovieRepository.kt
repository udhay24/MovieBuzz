package com.example.moviebuzz.repository.remote

import com.example.moviebuzz.repository.tmdb_service.MovieService
import com.example.moviebuzz.repository.model.PopularMovie
import kotlinx.coroutines.Deferred
import javax.inject.Inject
import javax.inject.Singleton

class MovieRepository constructor(private val movieService: MovieService)
    :Repository{

    fun getPopularMoviesAsync(): Deferred<PopularMovie>{

        return movieService.getPopularMoviesAsync()
    }
}