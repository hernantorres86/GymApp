package com.hernan.gymapp.main_screen.domain.repository

import com.hernan.gymapp.main_screen.presentation.state.ResourceFlow
import com.hernan.gymapp.models.HarcodedLists
import kotlinx.coroutines.flow.Flow

interface ClientRepository {
    fun getClientRepository(): Flow<ResourceFlow<HarcodedLists>>
}