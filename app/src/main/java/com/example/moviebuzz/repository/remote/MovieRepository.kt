package com.example.moviebuzz.repository.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviebuzz.network.ApiErrorResponse
import com.example.moviebuzz.network.ApiResponse
import com.example.moviebuzz.network.NetworkBoundResource
import com.example.moviebuzz.network.Resource
import com.example.moviebuzz.repository.model.NowPlayingMovie
import com.example.moviebuzz.repository.model.PopularMovie
import com.example.moviebuzz.repository.tmdb_service.MovieService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository constructor(private val movieService: MovieService)
    :Repository, CoroutineScope by CoroutineScope(Dispatchers.Default){

    fun fetchNowPlayingMovies(): LiveData<Resource<NowPlayingMovie>> {
        return object : NetworkBoundResource<NowPlayingMovie, NowPlayingMovie>() {

            override fun networkCall(): LiveData<ApiResponse<NowPlayingMovie>> {
                val nowPlayingLiveData = MutableLiveData<ApiResponse<NowPlayingMovie>>()
                movieService.getNowPlayingMovies().enqueue(
                    object : Callback<NowPlayingMovie> {

                        override fun onFailure(call: Call<NowPlayingMovie>, t: Throwable) {
                            nowPlayingLiveData.postValue(ApiErrorResponse(t.message ?: "Unknown Error"))
                        }

                        override fun onResponse(call: Call<NowPlayingMovie>, response: Response<NowPlayingMovie>) {
                            nowPlayingLiveData.postValue(
                                ApiResponse.create(response)
                            )
                        }
                    }
                )
                return nowPlayingLiveData
            }

            override fun convertToResultType(requestType: NowPlayingMovie): NowPlayingMovie = requestType
        }.asLiveData()
    }

    fun fetchPopularMovies(): LiveData<Resource<PopularMovie>> {
        return object : NetworkBoundResource<PopularMovie, PopularMovie>() {

            override fun networkCall(): LiveData<ApiResponse<PopularMovie>> {
                val popularMovieLiveData = MutableLiveData<ApiResponse<PopularMovie>>()

                movieService.getPopularMovies().enqueue(
                    object : Callback<PopularMovie> {

                        override fun onFailure(call: Call<PopularMovie>, t: Throwable) {
                            popularMovieLiveData.postValue(ApiErrorResponse(t.message ?: "Unknown error"))
                        }

                        override fun onResponse(call: Call<PopularMovie>, response: Response<PopularMovie>) {
                            popularMovieLiveData.postValue(ApiResponse.create(response))
                        }
                    }
                )
                return popularMovieLiveData
            }

            override fun convertToResultType(requestType: PopularMovie): PopularMovie = requestType

        }.asLiveData()
    }
}