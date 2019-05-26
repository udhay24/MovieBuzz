package com.example.moviebuzz.customview

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviebuzz.R
import com.example.moviebuzz.utils.loadImageFromUrl

class RecyclerViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(imageUrl: String) {
        val imageView: ImageView = view.findViewById(R.id.movie_poster)
        imageView.loadImageFromUrl(imageUrl)
    }
}