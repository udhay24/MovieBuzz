package com.example.moviebuzz.bindingadapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import com.example.moviebuzz.network.BaseUrl
import com.squareup.picasso.Picasso

@BindingAdapter("app:tmdbImageUrl")
fun ImageView.setTmdbImageUrl(imageUrl: LiveData<String>?) {
    Picasso.get()
        .load(BaseUrl.getPosterPath(imageUrl?.value ?: ""))
        .into(this)
}