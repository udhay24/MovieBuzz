package com.example.moviebuzz.di

import com.example.moviebuzz.ui.MainActivity
import com.example.moviebuzz.ui.moviefragment.MovieFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class,
ViewModelModule::class])
interface AppComponent {

    fun inject(fragment: MovieFragment)

}