package com.example.moviebuzz.repository.tmdb_service

import com.example.moviebuzz.repository.model.tvshows.PopularTvShows
import com.example.moviebuzz.repository.model.tvshows.TvAiringToday
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface TvService {

    @GET("tv/popular")
    fun getPopulatTvShowsAsync(): Deferred<PopularTvShows>

    @GET("tv/airing_today")
    fun getTvAiringTodayAsync(): Deferred<TvAiringToday>
}