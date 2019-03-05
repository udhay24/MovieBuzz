package com.example.moviebuzz.di

import com.example.moviebuzz.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class,
ViewModelModule::class])
interface AppComponent {

    fun inject(mainActivity: MainActivity)

}