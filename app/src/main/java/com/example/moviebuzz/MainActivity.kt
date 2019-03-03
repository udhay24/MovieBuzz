package com.example.moviebuzz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.moviebuzz.Repository.TMDB_Service.MovieService
import com.example.moviebuzz.Repository.repository.Repository
import com.example.moviebuzz.di.DaggerAppComponent
import kotlinx.coroutines.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var movieService: MovieService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DaggerAppComponent.create().inject(this)

        runBlocking {

            Toast.makeText(this@MainActivity , "${movieService.getPopularMoviesAsync().await().results.size}" , Toast.LENGTH_SHORT).show()
        }
    }
}
