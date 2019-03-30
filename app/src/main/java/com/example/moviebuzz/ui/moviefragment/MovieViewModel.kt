package com.example.moviebuzz.ui.moviefragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviebuzz.repository.model.NowPlayingMovie
import com.example.moviebuzz.repository.model.PopularMovie
import com.example.moviebuzz.repository.remote.MovieRepository
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class MovieViewModel
    @Inject constructor(private val movieRepository: MovieRepository)
    : ViewModel(), CoroutineScope by CoroutineScope(Dispatchers.Default){

    val popularMovies: MutableLiveData<PopularMovie> = MutableLiveData()
    val nowPlayingMovies: MutableLiveData<NowPlayingMovie> = MutableLiveData()

    init {
        launch {
            popularMovies.postValue(movieRepository.getPopularMoviesAsync().await())
            nowPlayingMovies.postValue(movieRepository.getNowPlayingMovies().await())
        }
    }
}
