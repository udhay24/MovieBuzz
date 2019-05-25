package com.example.moviebuzz.network

data class Resource <out T> (var status: NetworkStatus, val data: T?, val message: String?) {
    companion object{
        fun <T> success(data: T): Resource<T> = Resource(NetworkStatus.SUCCESS, data, null)

        fun <T> loading(data: T?): Resource<T> = Resource(NetworkStatus.LOADING, data, null)

        fun <T> failure(data: T?, errorMessage: String): Resource<T> =
            Resource(NetworkStatus.FAILURE, data, errorMessage)
    }
}

enum class NetworkStatus {
    SUCCESS, LOADING, FAILURE,
}