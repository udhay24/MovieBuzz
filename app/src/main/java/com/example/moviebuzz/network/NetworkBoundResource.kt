package com.example.moviebuzz.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

abstract class NetworkBoundResource<ResultType, RequestType> {

    private val result = MediatorLiveData<Resource<ResultType>>()

    init {
        fetchFromNetwork()
    }

    private fun setValue(value: Resource<ResultType>){

        if(result.value != value){
            result.value = value
        }
    }

    private fun fetchFromNetwork(){

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

    abstract fun networkCall(): LiveData<ApiResponse<RequestType>>

    abstract fun convertToResultType(requestType: RequestType): ResultType

}