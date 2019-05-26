package com.example.moviebuzz.repository.tmdb_service

import com.example.moviebuzz.repository.model.movie.LatestMovies
import com.example.moviebuzz.repository.model.movie.Movie
import com.example.moviebuzz.repository.model.movie.MovieReview
import com.example.moviebuzz.repository.model.movie.NowPlayingMovies
import com.example.moviebuzz.repository.model.movie.PopularMovies
import com.example.moviebuzz.repository.model.movie.SimilarMovies
import com.example.moviebuzz.repository.model.movie.TopRatedMovies
import com.example.moviebuzz.repository.model.movie.UpComingMovies
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
    fun getMovieDetailFromIdAsync(@Path("movie_id") id: Int): Deferred<Response<Movie>>

    @GET("movie/{movie_id}/reviews")
    fun getMovieReviewsAsync(@Path("movie_id") movieId: Int): Deferred<Response<MovieReview>>

    @GET("movie/{movie_id}/similar")
    fun getSimilarMoviesAsync(@Path("movie_id") movieId: Int): Deferred<Response<SimilarMovies>>
}
