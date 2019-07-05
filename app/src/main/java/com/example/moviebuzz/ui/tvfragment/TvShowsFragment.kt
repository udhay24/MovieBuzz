package com.example.moviebuzz.ui.tvfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.data.network.NetworkStatus
import com.example.data.repository.model.tvshows.PopularTvShows
import com.example.moviebuzz.R
import kotlinx.android.synthetic.main.tv_shows_fragment.*
import org.koin.android.ext.android.inject

class TvShowsFragment : Fragment() {

    companion object {
        fun newInstance() = TvShowsFragment()
    }

    private val viewModel: TvShowsViewModel by inject()
    private val popularTvShowsAdapter: PopularTvShowsAdapter = PopularTvShowsAdapter(null)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.tv_shows_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        popular_tv_shows.adapter = popularTvShowsAdapter
        viewModel.popularTvShowsLiveData.observe(this, Observer {
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
        popularTvShowsAdapter.swapData(data)
    }
}
