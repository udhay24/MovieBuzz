package com.example.moviebuzz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.UiThread
import com.example.moviebuzz.Repository.Repository
import com.example.moviebuzz.Repository.TMDB_Service.TmdbService
import com.example.moviebuzz.Repository.model.PopularMovie
import com.example.moviebuzz.di.DaggerRetrofitComponent
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var repository: Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DaggerRetrofitComponent.create().inject(this)

        runBlocking {

            Toast.makeText(this@MainActivity , "${repository.getPopularMovies().results.size}" , Toast.LENGTH_SHORT).show()
        }
    }
}
