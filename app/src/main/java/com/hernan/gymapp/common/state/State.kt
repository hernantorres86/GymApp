package com.hernan.gymapp.common.state

sealed class ResourceFlow<out T> {
    data object Loading : ResourceFlow<Nothing>()
    data class Success<T>(val data: T) : ResourceFlow<T>()
    data class Error(val message: String) : ResourceFlow<Nothing>()
}