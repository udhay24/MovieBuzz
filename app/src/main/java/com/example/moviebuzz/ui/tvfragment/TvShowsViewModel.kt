package com.example.moviebuzz.ui.tvfragment

import androidx.lifecycle.ViewModel
import com.example.moviebuzz.repository.remote.TvRepository
import javax.inject.Inject

class TvShowsViewModel
@Inject constructor(tvRepository: TvRepository) : ViewModel() {

    val popularTvShowsLiveData = tvRepository.fetchPopularTvShows()
}
