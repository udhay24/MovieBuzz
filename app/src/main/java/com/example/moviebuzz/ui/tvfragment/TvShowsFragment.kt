package com.example.moviebuzz.ui.tvfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.moviebuzz.R
import com.example.moviebuzz.factory.ViewModelFactory
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class TvShowsFragment : Fragment() {

    @Inject
    lateinit var viewmodelFactory: ViewModelFactory
    private lateinit var tvShowsViewModel: TvShowsViewModel

    companion object {
        fun newInstance() = TvShowsFragment()
    }

    private lateinit var viewModel: TvShowsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        AndroidSupportInjection.inject(this)
        return inflater.inflate(R.layout.tv_shows_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewmodelFactory).get(TvShowsViewModel::class.java)

        viewModel.popularTvShowsLiveData.observe(this, Observer {

            Toast.makeText(this@TvShowsFragment.context, "${it.data?.total_results ?: -1}", Toast.LENGTH_SHORT).show()
        })
    }

}
