package com.example.data.repository.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.data.network.ApiEmptyResponse
import com.example.data.network.ApiErrorResponse
import com.example.data.network.ApiResponse
import com.example.data.network.ApiSuccessResponse
import com.example.data.network.NetworkBoundResource
import com.example.data.network.Resource
import com.example.data.repository.tmdb_service.TvService
import com.example.domain.entities.model.tvshows.PopularTvShows
import com.example.domain.entities.model.tvshows.TvAiringToday
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
                        val networkResponse = tvService.getPopulatTvShowsAsync()
                        if (networkResponse.isSuccessful and (networkResponse.body() != null)) {
                            mutableLiveTvShows.postValue(
                                ApiSuccessResponse(networkResponse.body()!!, "")
                            )
                        }
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
                        val networkResponse = tvService.getTvAiringTodayAsync()
                        if (networkResponse.isSuccessful and (networkResponse.body() != null)) {
                            tvShowsAiringTodayLiveData.postValue(
                                ApiSuccessResponse(networkResponse.body()!!, "")
                            )
                        }
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