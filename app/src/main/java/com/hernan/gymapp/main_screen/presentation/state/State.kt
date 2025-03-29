package com.hernan.gymapp.main_screen.presentation.state

sealed class ResourceFlow<out T> {
    object Loading : ResourceFlow<Nothing>()
    data class Success<T>(val data: T) : ResourceFlow<T>()
    data class Error(val message: String) : ResourceFlow<Nothing>()
}