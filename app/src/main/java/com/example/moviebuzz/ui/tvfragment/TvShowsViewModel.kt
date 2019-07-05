package com.example.moviebuzz.ui.tvfragment

import androidx.lifecycle.ViewModel
import com.example.data.repository.remote.TvRepository

class TvShowsViewModel(tvRepository: TvRepository) : ViewModel() {

    val popularTvShowsLiveData = tvRepository.fetchPopularTvShows()
}
