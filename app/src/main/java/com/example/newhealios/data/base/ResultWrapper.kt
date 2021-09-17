package com.example.newhealios.data.base

import java.io.IOException
import java.lang.Exception

sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T) : ResultWrapper<T>()
    data class GenericError(val throwable: Throwable? = null) : ResultWrapper<Nothing>()

    object NetworkError : ResultWrapper<Nothing>()

    @Throws(Exception::class)
    fun takeValueOrThrow(): T {
         return when (this) {
            is Success -> value
            is GenericError -> throw throwable ?: Throwable()
            is NetworkError -> throw IOException()
        }
    }
}