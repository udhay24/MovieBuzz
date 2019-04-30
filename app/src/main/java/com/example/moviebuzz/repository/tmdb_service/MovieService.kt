package com.example.moviebuzz.repository.tmdb_service

import com.example.moviebuzz.repository.model.movie.*
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface MovieService {
    @GET("movie/popular")
    fun getPopularMoviesAsync(): Deferred<PopularMovies>

    @GET("movie/now_playing")
    fun getNowPlayingMoviesAsync(): Deferred<NowPlayingMovies>

    @GET("movie/latest")
    fun getLatestMoviesAsync(): Deferred<LatestMovies>

    @GET("movie/top_rated")
    fun getTopRatedMoviesAsync(): Deferred<TopRatedMovies>

    @GET("movie/upcoming")
    fun getUpComingMoviesAsync(): Deferred<UpComingMovies>
}
