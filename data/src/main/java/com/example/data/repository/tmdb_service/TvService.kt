package com.example.data.repository.tmdb_service

import com.example.data.repository.model.tvshows.PopularTvShows
import com.example.data.repository.model.tvshows.TvAiringToday
import retrofit2.Response
import retrofit2.http.GET

interface TvService {

    @GET("tv/popular")
    suspend fun getPopulatTvShowsAsync(): Response<PopularTvShows>

    @GET("tv/airing_today")
    suspend fun getTvAiringTodayAsync(): Response<TvAiringToday>
}