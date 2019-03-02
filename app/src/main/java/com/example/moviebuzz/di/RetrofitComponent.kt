package com.example.moviebuzz.di

import com.example.moviebuzz.MainActivity
import dagger.Component
import retrofit2.Retrofit

@Component(modules = [RetrofitModule::class])
interface RetrofitComponent {

    fun getRetrofitClient(): Retrofit

    fun inject(mainActivity: MainActivity)
}