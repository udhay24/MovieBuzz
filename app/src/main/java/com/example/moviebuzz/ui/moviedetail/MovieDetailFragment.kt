package com.example.moviebuzz.ui.moviedetail

import android.animation.Animator
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.moviebuzz.R
import com.example.moviebuzz.databinding.MovieDetailFragmentBinding
import com.example.moviebuzz.ui.mainactivity.MainActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MovieDetailFragment : Fragment() {

    companion object {
        fun newInstance() = MovieDetailFragment()
    }

    private val args: MovieDetailFragmentArgs by navArgs()
    private val viewModel: MovieDetailViewModel by viewModel { parametersOf(args.movieId) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: MovieDetailFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.movie_detail_fragment, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.fragment = this
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).bottom_navigation.apply {
            animate()
                .translationYBy(this.height.toFloat())
                .setListener(object : Animator.AnimatorListener {
                    override fun onAnimationRepeat(animation: Animator?) {}
                    override fun onAnimationEnd(animation: Animator?) {}
                    override fun onAnimationCancel(animation: Animator?) {}
                    override fun onAnimationStart(animation: Animator?) {
                        this@apply.visibility = View.GONE
                    }
                })
                .duration = 200
        }
    }

    override fun onDetach() {
        super.onDetach()
        (activity as MainActivity).bottom_navigation.apply {
            animate()
                .translationYBy(-this.height.toFloat())
                .setListener(object : Animator.AnimatorListener {
                    override fun onAnimationRepeat(animation: Animator?) {}
                    override fun onAnimationEnd(animation: Animator?) {}
                    override fun onAnimationCancel(animation: Animator?) {}
                    override fun onAnimationStart(animation: Animator?) {
                        this@apply.visibility = View.VISIBLE
                    }
                })
                .duration = 200
        }
    }
}
