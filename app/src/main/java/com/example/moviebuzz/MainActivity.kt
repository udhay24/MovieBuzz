package com.example.moviebuzz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.moviebuzz.Repository.RetrofitClient
import com.example.moviebuzz.Repository.TMDB_Service.TmdbService
import com.example.moviebuzz.Repository.model.PopularMovie
import com.example.moviebuzz.di.DaggerRetrofitComponent
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var retrofit: Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DaggerRetrofitComponent.create().inject(this)

        retrofit.create(TmdbService::class.java)
            .getPopularMovies()
            .enqueue(object : Callback<PopularMovie> {
                override fun onFailure(call: Call<PopularMovie>, t: Throwable) {

                    Toast.makeText(this@MainActivity , "Failed" , Toast.LENGTH_LONG).show()
                }

                override fun onResponse(call: Call<PopularMovie>, response: Response<PopularMovie>) {

                    Toast.makeText(this@MainActivity , response.body()?.results?.size.toString() , Toast.LENGTH_SHORT).show()
                }
            })

    }
}
