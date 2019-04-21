package com.example.moviebuzz.repository.tmdb_service

import com.example.moviebuzz.repository.model.PopularTvShows
import com.example.moviebuzz.repository.model.TvAiringToday
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.GET

interface TvService {

    @GET("tv/popular")
    fun getPopulatTvShows(): Deferred<PopularTvShows>

    @GET("tv/airing_today")
    fun getTvAiringToday(): Call<TvAiringToday>
}