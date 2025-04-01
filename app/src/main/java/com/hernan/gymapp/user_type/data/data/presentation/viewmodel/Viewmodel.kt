package com.hernan.gymapp.main_screen.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hernan.gymapp.user_type.data.data.domain.model.Client
import com.hernan.gymapp.user_type.data.data.domain.usecase.GetClientUsecase
import com.hernan.gymapp.common.state.ResourceFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClientViewModel @Inject constructor(
    private val getClientUsecase: GetClientUsecase
) : ViewModel() {

    private val _clientState = MutableStateFlow<ResourceFlow<List<Client>>>(ResourceFlow.Loading)
    val clientState: StateFlow<ResourceFlow<List<Client>>> = _clientState.asStateFlow()

    init {
        fetchClient("")
    }
    fun fetchClient(clientId: String) {
        viewModelScope.launch {
            getClientUsecase(clientId).collect { result ->
                _clientState.value = result
            }
        }
    }
}