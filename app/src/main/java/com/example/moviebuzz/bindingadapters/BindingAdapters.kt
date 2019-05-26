package com.example.moviebuzz.bindingadapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviebuzz.R
import com.example.moviebuzz.customview.CommonRecyclerAdapter
import com.example.moviebuzz.network.BaseUrl
import com.example.moviebuzz.ui.moviefragment.MarginItemDecorator
import com.squareup.picasso.Picasso
import timber.log.Timber

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

@BindingAdapter("recyclerViewData")
fun setUpRecyclerView(recyclerView: RecyclerView, data: LiveData<List<String>>) {
    val adapter = CommonRecyclerAdapter(emptyList())
    recyclerView.layoutManager = LinearLayoutManager(recyclerView.context, RecyclerView.HORIZONTAL, false)
    recyclerView.addItemDecoration(MarginItemDecorator(recyclerView.context.resources.getDimensionPixelSize(R.dimen.recycler_view_margin)))
    recyclerView.adapter = adapter
    val observer = Observer<List<String>> {
        Timber.v("${it.size}")
        if (it.isNotEmpty()) {
            adapter.swapData(it)
        }
    }
    data.observeForever(observer)
}
