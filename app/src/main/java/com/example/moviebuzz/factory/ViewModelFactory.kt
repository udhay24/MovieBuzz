package com.example.moviebuzz.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import java.lang.Exception
import java.lang.IllegalArgumentException
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class ViewModelFactory
@Inject constructor(private val viewModelMap: Map<Class<out ViewModel> ,
        @JvmSuppressWildcards Provider<ViewModel>>): ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        val creator = viewModelMap[modelClass]
            ?: viewModelMap.asIterable().firstOrNull { it.key.isAssignableFrom(modelClass) }?.value
            ?: throw IllegalArgumentException("ViewModel Provider is not present in any module")

        return try {
            @Suppress("UNCHECKED_CAST")
            creator.get() as T
        }catch (ex: Exception){
            throw RuntimeException(ex)
        }
    }
}