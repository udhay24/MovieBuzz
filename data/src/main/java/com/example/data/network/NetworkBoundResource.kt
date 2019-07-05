package com.example.data.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

abstract class NetworkBoundResource<ResultType, RequestType> :
    CoroutineScope by CoroutineScope(Dispatchers.Default) {

    private val result = MediatorLiveData<Resource<ResultType>>()

    init {
        fetchFromNetwork()
    }

    private fun setValue(value: Resource<ResultType>) {
        if (result.value != value) {
            result.value = value
        }
    }

    private fun fetchFromNetwork() {
        val apiResult = networkCall()
        result.addSource(apiResult) { data ->
            //            result.removeSource(apiResult)
            when (data) {
                is ApiSuccessResponse -> {
                    setValue(Resource.success(convertToResultType(data.body)))
                }
                is ApiEmptyResponse -> {
                    setValue(Resource.loading(null))
                }
                is ApiErrorResponse -> {
                    setValue(Resource.failure(null, data.errorMessage))
                }
            }
        }
    }

    fun asLiveData() = result as LiveData<Resource<ResultType>>

    abstract fun networkCall(): LiveData<ApiResponse<RequestType>>

    abstract fun convertToResultType(requestType: RequestType): ResultType
}