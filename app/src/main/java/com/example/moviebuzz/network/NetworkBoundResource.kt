package com.example.moviebuzz.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

abstract class NetworkBoundResource<ResultType, RequestType>()
    :CoroutineScope by CoroutineScope(Dispatchers.Default){

    private val result = MediatorLiveData<Resource<ResultType>>()

    init {
        launch {
            fetchFromNetwork()
        }
    }

    private fun setValue(value: Resource<ResultType>){

        if(result.value != value){
            result.value = value
        }
    }

    private suspend fun fetchFromNetwork(){

        val apiResult = networkCall()

        result.addSource(apiResult){ data ->

            result.removeSource(apiResult)

            when(data){
                is ApiSuccessResponse -> {

                    setValue(Resource.success(convertToResultType(data.body)))
                }
            }
        }
    }

    fun asLiveData() = result as LiveData<Resource<ResultType>>

    abstract suspend fun networkCall(): LiveData<ApiResponse<RequestType>>

    abstract fun convertToResultType(requestType: RequestType): ResultType

}