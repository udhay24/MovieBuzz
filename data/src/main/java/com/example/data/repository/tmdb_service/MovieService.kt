package com.example.data.repository.tmdb_service

import com.example.domain.entities.model.movie.LatestMovies
import com.example.domain.entities.model.movie.Movie
import com.example.domain.entities.model.movie.MovieReview
import com.example.domain.entities.model.movie.NowPlayingMovies
import com.example.domain.entities.model.movie.PopularMovies
import com.example.domain.entities.model.movie.SimilarMovies
import com.example.domain.entities.model.movie.TopRatedMovies
import com.example.domain.entities.model.movie.UpComingMovies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieService {
    @GET("movie/popular")
    suspend fun getPopularMovies(): Response<PopularMovies>

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(): Response<NowPlayingMovies>

    @GET("movie/latest")
    suspend fun getLatestMovies(): Response<LatestMovies>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(): Response<TopRatedMovies>

    @GET("movie/upcoming")
    suspend fun getUpComingMovies(): Response<UpComingMovies>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetailFromId(@Path("movie_id") id: Int): Response<Movie>

    @GET("movie/{movie_id}/reviews")
    suspend fun getMovieReviews(@Path("movie_id") movieId: Int): Response<MovieReview>

    @GET("movie/{movie_id}/similar")
    suspend fun getSimilarMovies(@Path("movie_id") movieId: Int): Response<SimilarMovies>
}
