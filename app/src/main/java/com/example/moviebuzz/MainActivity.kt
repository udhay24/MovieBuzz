package com.example.moviebuzz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.moviebuzz.Repository.TMDB_Service.MovieService
import com.example.moviebuzz.Repository.repository.Repository
import com.example.moviebuzz.di.AppComponent
import com.example.moviebuzz.di.DaggerAppComponent
import com.example.moviebuzz.factory.ViewModelFactory
import com.example.moviebuzz.ui.MovieViewModel
import kotlinx.coroutines.*
import java.util.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory : ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DaggerAppComponent.create().inject(this)

        val movieViewModel = ViewModelProviders.of(this , viewModelFactory).get(MovieViewModel::class.java)

        movieViewModel.popularMovies.observe(this , androidx.lifecycle.Observer {

            runBlocking {
                Toast.makeText(this@MainActivity, "${it.await().results.size}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
