package com.example.moviebuzz.repository.tmdb_service

import com.example.moviebuzz.repository.model.NowPlayingMovie
import com.example.moviebuzz.repository.model.PopularMovie
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface MovieService {
    @GET("movie/popular")
    fun getPopularMoviesAsync(): Deferred<PopularMovie>

    @GET("movie/now_playing")
    fun getNowPlayingMoviesAsync(): Deferred<NowPlayingMovie>
}
