package com.example.moviebuzz.repository.tmdb_service

import com.example.moviebuzz.repository.model.tvshows.PopularTvShows
import com.example.moviebuzz.repository.model.tvshows.TvAiringToday
import retrofit2.Response
import retrofit2.http.GET

interface TvService {

    @GET("tv/popular")
    suspend fun getPopulatTvShowsAsync(): Response<PopularTvShows>

    @GET("tv/airing_today")
    suspend fun getTvAiringTodayAsync(): Response<TvAiringToday>
}