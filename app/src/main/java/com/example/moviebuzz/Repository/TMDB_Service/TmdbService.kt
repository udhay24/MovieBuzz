package com.example.moviebuzz.Repository.TMDB_Service

import com.example.moviebuzz.Repository.model.PopularMovie
import retrofit2.Call
import retrofit2.http.GET

interface TmdbService {

    @GET("/3/movie/popular")
    fun getPopularMovies(): Call<PopularMovie>
}
