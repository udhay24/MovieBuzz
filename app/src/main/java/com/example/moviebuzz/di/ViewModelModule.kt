package com.example.moviebuzz.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviebuzz.factory.ViewModelFactory
import com.example.moviebuzz.ui.moviefragment.MovieViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelMapKey(MovieViewModel::class)
    internal abstract fun getMovieViewModel(movieViewModel: MovieViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}