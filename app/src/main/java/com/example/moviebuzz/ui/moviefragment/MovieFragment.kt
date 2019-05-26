package com.example.moviebuzz.ui.moviefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.moviebuzz.R
import com.example.moviebuzz.databinding.MovieFragmentBinding
import org.koin.android.ext.android.inject

class MovieFragment : Fragment() {

    companion object {
        fun newInstance() = MovieFragment()
    }

    private val viewModel: MovieViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val dataBinding: MovieFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.movie_fragment, container, false)
        dataBinding.viewModel = viewModel
        return dataBinding.root
    }

    private fun openMovieDetailView(movieId: Int) {
        val navDirections = MovieFragmentDirections.actionMovieFragmentToMovieDetail(movieId)
        findNavController().navigate(navDirections)
    }
}
