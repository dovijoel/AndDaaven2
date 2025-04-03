package tech.debuggingmadejoyful.anddaaven.data

import kotlinx.coroutines.flow.Flow

interface TefillaRepository {
    fun getTefilla(type: TefillaType): Result<Tefilla>
}

/**
 * A generic class that holds a value or an exception
 */
sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}

fun <T> Result<T>.successOr(fallback: T): T {
    return (this as? Result.Success<T>)?.data ?: fallback
}