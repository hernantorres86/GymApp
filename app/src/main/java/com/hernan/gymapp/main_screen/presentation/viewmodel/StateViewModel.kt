package com.hernan.gymapp.main_screen.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hernan.gymapp.main_screen.domain.model.FirstState
import com.hernan.gymapp.main_screen.domain.usecase.FirstStateUseCase
import com.hernan.gymapp.common.state.ResourceFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StateViewModel @Inject constructor(
    private val firstStateUseCase: FirstStateUseCase
) : ViewModel() {
    private val _firstState = MutableStateFlow<ResourceFlow<List<FirstState>>>(ResourceFlow.Loading)
    val firstState: StateFlow<ResourceFlow<List<FirstState>>> = _firstState.asStateFlow()

    init {
        fetchFirstState()
    }
    fun fetchFirstState() {
        viewModelScope.launch {
            firstStateUseCase().collect { result ->
                _firstState.value = result
            }
        }
    }
}