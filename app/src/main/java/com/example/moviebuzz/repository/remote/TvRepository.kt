package com.example.moviebuzz.repository.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviebuzz.network.*
import com.example.moviebuzz.repository.model.tvshows.PopularTvShows
import com.example.moviebuzz.repository.model.tvshows.TvAiringToday
import com.example.moviebuzz.repository.tmdb_service.TvService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TvRepository(private val tvService: TvService) : Repository,
    CoroutineScope by CoroutineScope(Dispatchers.Default) {

    fun fetchPopularTvShows(): LiveData<Resource<PopularTvShows>> {
        return object : NetworkBoundResource<PopularTvShows, PopularTvShows>() {

            override fun networkCall(): LiveData<ApiResponse<PopularTvShows>> {
                val mutableLiveTvShows = MutableLiveData<ApiResponse<PopularTvShows>>(ApiEmptyResponse())
                launch {
                    try {
                        val response = tvService.getPopulatTvShowsAsync().await()
                        mutableLiveTvShows.postValue(ApiSuccessResponse(response, ""))
                    } catch (exception: Exception) {
                        mutableLiveTvShows.postValue(ApiErrorResponse(exception.message ?: "Unknown Error"))
                    }
                }
                return mutableLiveTvShows
            }

            override fun convertToResultType(requestType: PopularTvShows): PopularTvShows = requestType

        }.asLiveData()
    }

    fun fetchAiringTvShows(): LiveData<Resource<TvAiringToday>> {
        return object : NetworkBoundResource<TvAiringToday, TvAiringToday>() {

            override fun networkCall(): LiveData<ApiResponse<TvAiringToday>> {
                val tvShowsAiringTodayLiveData = MutableLiveData<ApiResponse<TvAiringToday>>(ApiEmptyResponse())
                launch {
                    try {
                        val tvShowsAiringToday = tvService.getTvAiringTodayAsync().await()
                        tvShowsAiringTodayLiveData.postValue(
                            ApiSuccessResponse(tvShowsAiringToday, "")
                        )
                    } catch (exception: Exception) {
                        tvShowsAiringTodayLiveData.postValue(
                            ApiErrorResponse(exception.message ?: "Unknown error")
                        )
                    }
                }
                return tvShowsAiringTodayLiveData
            }

            override fun convertToResultType(requestType: TvAiringToday): TvAiringToday = requestType

        }.asLiveData()
    }
}