package com.hernan.gymapp.main_screen.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hernan.gymapp.main_screen.domain.usecase.GetClientUsecase
import com.hernan.gymapp.main_screen.presentation.state.ResourceFlow
import com.hernan.gymapp.models.HarcodedLists
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClientViewModel @Inject constructor(
    private val getClientUsecase: GetClientUsecase
) : ViewModel() {

    private val _clients = MutableStateFlow<ResourceFlow<HarcodedLists>>(ResourceFlow.Loading)
    val clients: StateFlow<ResourceFlow<HarcodedLists>> get() = _clients

    init {
        getClients()
    }

    private fun getClients() {
        viewModelScope.launch {
            getClientUsecase().collect { result ->
                _clients.value = result // Actualiza el estado de los clientes
            }
        }
    }
}