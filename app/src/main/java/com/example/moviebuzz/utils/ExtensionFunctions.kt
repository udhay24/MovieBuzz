package com.example.moviebuzz.utils

import android.widget.ImageView
import com.example.moviebuzz.R
import com.squareup.picasso.Picasso

fun ImageView.loadImageFromUrl(url: String) {
    Picasso.get()
        .load(url)
        .error(R.drawable.ic_broken_image_black_24dp)
        .into(this)
}