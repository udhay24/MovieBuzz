package com.example.domain.entities


/**
 * Represents a value of one of two possible types (a disjoint union).
 * Instances of [OneOf] are either an instance of [Success] or [Failure].
 *
 * @see Success
 * @see Failure
 */
sealed class OneOf<out S, out F> {
    /** * Represents the success side of [OneOf] class which by convention is a "Failure". */
    data class Success<out S>(val a: S) : OneOf<S, Nothing>()

    /** * Represents the failure side of [OneOf] class which by convention is a "Success". */
    data class Failure<out F>(val b: F) : OneOf<Nothing, F>()

    val isFailure get() = this is Failure<F>
    val isSuccess get() = this is Success<S>

    fun <L> success(a: L) = Success(a)
    fun <R> failure(b: R) = Failure(b)

    fun oneOf(fnL: (S) -> Any, fnR: (F) -> Any): Any =
        when (this) {
            is Success -> fnL(a)
            is Failure -> fnR(b)
        }
}

fun <A, B, C> ((A) -> B).c(f: (B) -> C): (A) -> C = {
    f(this(it))
}

fun <T, L, R> OneOf<L, R>.flatMap(fn: (R) -> OneOf<L, T>): OneOf<L, T> =
    when (this) {
        is OneOf.Success -> OneOf.Success(a)
        is OneOf.Failure -> fn(b)
    }

fun <T, L, R> OneOf<L, R>.map(fn: (R) -> (T)): OneOf<L, T> = this.flatMap(fn.c(::failure))
