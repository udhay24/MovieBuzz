package com.example.moviebuzz.ui.tvfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviebuzz.R
import com.example.moviebuzz.factory.ViewModelFactory
import com.example.moviebuzz.network.NetworkStatus
import com.example.moviebuzz.repository.model.PopularTvShows
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.tv_shows_fragment.*
import timber.log.Timber
import javax.inject.Inject

class TvShowsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    companion object {
        fun newInstance() = TvShowsFragment()
    }

    private lateinit var viewModel: TvShowsViewModel
    private val popularTvShowsAdapter: PopularTvShowsAdapter = PopularTvShowsAdapter(null)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        AndroidSupportInjection.inject(this)
        return inflater.inflate(R.layout.tv_shows_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(TvShowsViewModel::class.java)
        popular_tv_shows.adapter = popularTvShowsAdapter
        viewModel.popularTvShowsLiveData.observe(this, Observer {
            Timber.v("Status ${it.status}, Result ${it.data?.results?.size ?: -1}")
            when (it.status) {
                NetworkStatus.SUCCESS -> {
                    seUpPopularTvShows(it.data?.results)
                    Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
                }
                NetworkStatus.LOADING -> {
                    Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()
                }
                NetworkStatus.FAILURE -> {
                    Toast.makeText(context, "Failure", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun seUpPopularTvShows(data: List<PopularTvShows.Result>?) {
        popular_tv_shows.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        popularTvShowsAdapter.swapData(data)
    }
}
