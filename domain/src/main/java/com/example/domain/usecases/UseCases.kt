package com.example.domain.usecases

import com.example.domain.entities.Failure
import com.example.domain.entities.OneOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

internal abstract class UseCases<out Type, in Params> where Params : Any {

    abstract suspend fun run(params: Params): OneOf<Type, Failure>

    operator fun invoke(params: Params, onResult: (OneOf<Type, Failure>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = withContext(Dispatchers.Default) { run(params) }
            withContext(Dispatchers.Main) { onResult(response) }
        }

    }
}