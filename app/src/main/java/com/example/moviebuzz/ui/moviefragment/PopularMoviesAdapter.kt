package com.example.moviebuzz.ui.moviefragment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviebuzz.R
import com.example.moviebuzz.repository.model.PopularMovie

class PopularMoviesAdapter(private val context: Context,
                           private val popularMovie: PopularMovie)
    : RecyclerView.Adapter<PopularMoviesAdapter.PopularMoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMoviesViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.popular_movie_view_holder, parent, false)

        return PopularMoviesViewHolder(view)
    }

    override fun getItemCount(): Int {

        return popularMovie.results.count()
    }

    override fun onBindViewHolder(holder: PopularMoviesViewHolder, position: Int) {

    }

    class PopularMoviesViewHolder(private val view: View): RecyclerView.ViewHolder(view){

        val imageView = view.findViewById<ImageView>(R.id.movie_poster)
    }
}