package com.example.moviebuzz.di

import dagger.Component
import retrofit2.Retrofit

@Component(modules = [NetworkModule::class])
interface NetworkComponent {

    fun getRetrofitClient(): Retrofit
}