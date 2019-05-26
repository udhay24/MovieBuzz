package com.example.moviebuzz.ui.moviedetail

import android.text.TextUtils
import android.transition.TransitionManager
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.moviebuzz.network.BaseUrl
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

    private val similarMovies = movieRepository.fetchSimilarMovies(movieId)

    val similarMoviesPosterList: LiveData<List<String>> = Transformations.map(similarMovies) {
        when (it.status) {
            NetworkStatus.SUCCESS -> {
                it.data!!.results.map { similarMovies -> BaseUrl.getPosterPath(similarMovies.poster_path) }
            }
            else -> emptyList()
        }
    }

    fun expandTextView(view: View) {
        TransitionManager.beginDelayedTransition(view.parent as ViewGroup)
        if (view is TextView) {
            view.maxLines = Integer.MAX_VALUE
            view.ellipsize = TextUtils.TruncateAt.MARQUEE
        }
    }
}
