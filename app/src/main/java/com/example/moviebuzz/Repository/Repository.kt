package com.example.moviebuzz.Repository

import com.example.moviebuzz.Repository.TMDB_Service.TmdbService
import com.example.moviebuzz.Repository.model.PopularMovie
import retrofit2.Retrofit
import javax.inject.Inject

const val TMDB_API_KEY = "0df6b45d76ac1a9e96a70e79c0c808f0"

class Repository @Inject constructor(var tmdbService: TmdbService){

    suspend fun getPopularMovies(): PopularMovie {

        return tmdbService.getPopularMoviesAsync()
            .await()
    }
}