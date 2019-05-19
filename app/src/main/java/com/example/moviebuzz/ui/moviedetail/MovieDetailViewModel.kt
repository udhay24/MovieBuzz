package com.example.moviebuzz.ui.moviedetail

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.moviebuzz.network.NetworkStatus
import com.example.moviebuzz.repository.remote.MovieRepository

class MovieDetailViewModel(movieRepository: MovieRepository, movieId: Int) : ViewModel() {

    val movie = movieRepository.fetchMovieById(movieId)

    val movieName = Transformations.map(movie) {
        when (it.status) {
            NetworkStatus.FAILURE -> ""
            NetworkStatus.LOADING -> "Loading"
            NetworkStatus.SUCCESS -> it.data?.title
        }
    }

    val movieRating = Transformations.map(movie) {
        when (it.status) {
            NetworkStatus.SUCCESS -> it.data?.voteAverage
            else -> 0.0
        }
    }

    val runningTime = Transformations.map(movie) {
        when (it.status) {
            NetworkStatus.SUCCESS -> it.data?.runtime
            else -> 0
        }
    }

    val description = Transformations.map(movie) {
        when (it.status) {
            NetworkStatus.SUCCESS -> it.data?.overview
            else -> ""
        }
    }

    val posterImageUrl = Transformations.map(movie) {
        when (it.status) {
            NetworkStatus.SUCCESS -> it.data?.posterPath
            else -> ""
        }
    }

    val backdropImageUrl = Transformations.map(movie) {
        when (it.status) {
            NetworkStatus.SUCCESS -> it.data?.backdropPath
            else -> ""
        }
    }


}
