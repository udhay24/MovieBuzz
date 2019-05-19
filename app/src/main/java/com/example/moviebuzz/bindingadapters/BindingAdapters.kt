package com.example.moviebuzz.bindingadapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import com.example.moviebuzz.R
import com.example.moviebuzz.network.BaseUrl
import com.squareup.picasso.Picasso

@BindingAdapter("tmdbPosterImageUrl")
fun ImageView.setTmdbPosterImageUrl(imageUrl: LiveData<String>?) {
    Picasso.get()
        .load(BaseUrl.getPosterPath(imageUrl?.value ?: ""))
        .error(R.drawable.ic_broken_image_black_24dp)
        .into(this)
}

@BindingAdapter("tmdbBackdropImageUrl")
fun ImageView.setTmdbBackDropImageUrl(imageUrl: LiveData<String>?) {
    Picasso.get()
        .load(BaseUrl.getBackdropPath(imageUrl?.value ?: ""))
        .error(R.drawable.ic_broken_image_black_24dp)
        .into(this)
}
