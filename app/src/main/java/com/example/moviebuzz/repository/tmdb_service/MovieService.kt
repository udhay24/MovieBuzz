package com.example.moviebuzz.repository.tmdb_service

import com.example.moviebuzz.repository.model.NowPlayingMovie
import com.example.moviebuzz.repository.model.PopularMovie
import retrofit2.Call
import retrofit2.http.GET

interface MovieService {
    @GET("movie/popular")
    fun getPopularMovies(): Call<PopularMovie>

    @GET("movie/now_playing")
    fun getNowPlayingMovies(): Call<NowPlayingMovie>
}
