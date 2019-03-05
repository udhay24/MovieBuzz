package com.example.moviebuzz.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviebuzz.Repository.model.PopularMovie
import com.example.moviebuzz.Repository.repository.MovieRepository
import kotlinx.coroutines.*
import javax.inject.Inject

class MovieViewModel
    @Inject constructor(private val movieRepository: MovieRepository) : ViewModel() {

    val popularMovies: MutableLiveData<Deferred<PopularMovie>> = MutableLiveData()

    init {

            popularMovies.value = movieRepository.getPopularMoviesAsync()
        }

}
