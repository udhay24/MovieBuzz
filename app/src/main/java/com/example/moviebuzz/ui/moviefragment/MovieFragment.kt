package com.example.moviebuzz.ui.moviefragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.moviebuzz.R
import com.example.moviebuzz.di.DaggerAppComponent
import com.example.moviebuzz.factory.ViewModelFactory
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class MovieFragment : Fragment() {

    companion object {
        fun newInstance() = MovieFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: MovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

       AndroidSupportInjection.inject(this)

        return inflater.inflate(R.layout.movie_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this , viewModelFactory).get(MovieViewModel::class.java)
    }

}
