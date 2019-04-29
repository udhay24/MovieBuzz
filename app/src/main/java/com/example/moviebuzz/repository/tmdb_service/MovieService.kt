package com.example.moviebuzz.repository.tmdb_service

import com.example.moviebuzz.repository.model.movie.LatestMovie
import com.example.moviebuzz.repository.model.movie.NowPlayingMovie
import com.example.moviebuzz.repository.model.movie.PopularMovie
import com.example.moviebuzz.repository.model.movie.TopRatedMovies
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface MovieService {
    @GET("movie/popular")
    fun getPopularMoviesAsync(): Deferred<PopularMovie>

    @GET("movie/now_playing")
    fun getNowPlayingMoviesAsync(): Deferred<NowPlayingMovie>

    @GET("movie/latest")
    fun getLatestMoviesAsync(): Deferred<LatestMovie>

    @GET("movie/top_rated")
    fun getTopRatedMoviesAsync(): Deferred<TopRatedMovies>
}
