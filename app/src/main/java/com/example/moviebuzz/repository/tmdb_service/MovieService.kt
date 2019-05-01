package com.example.moviebuzz.repository.tmdb_service

import com.example.moviebuzz.repository.model.movie.*
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieService {
    @GET("movie/popular")
    fun getPopularMoviesAsync(): Deferred<PopularMovies>

    @GET("movie/now_playing")
    fun getNowPlayingMoviesAsync(): Deferred<NowPlayingMovies>

    @GET("movie/latest")
    fun getLatestMoviesAsync(): Deferred<Response<LatestMovies>>

    @GET("movie/top_rated")
    fun getTopRatedMoviesAsync(): Deferred<TopRatedMovies>

    @GET("movie/upcoming")
    fun getUpComingMoviesAsync(): Deferred<UpComingMovies>

    @GET("movie/{movie_id}")
    fun getMovieDetailFromId(@Path("movie_id") id: Double): Deferred<Response<Movie>>
}
