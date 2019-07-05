package com.example.moviebuzz.ui.moviedetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.data.network.BaseUrl
import com.example.data.repository.model.movie.SimilarMovies
import com.example.moviebuzz.R
import com.example.moviebuzz.utils.loadImageFromUrl

class SimilarMoviesAdapter(
    val similarMovies: SimilarMovies,
    val onClickListener: (Int) -> Unit
) : RecyclerView.Adapter<SimilarMoviesAdapter.RecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.single_image_layout, parent, false)
        return RecyclerViewHolder(view)
    }

    override fun getItemCount(): Int = similarMovies.results.size

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class RecyclerViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        private val posterImage: ImageView = view.findViewById(R.id.movie_poster)
        fun bind(position: Int) {

            posterImage.loadImageFromUrl(
                BaseUrl.getPosterPath(similarMovies.results[position].poster_path ?: "Error Image")
            )
            view.setOnClickListener {
                onClickListener.invoke(similarMovies.results[position].id)
            }
        }
    }
}