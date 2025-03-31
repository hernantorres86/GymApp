package com.hernan.gymapp.main_screen.domain.repository

import com.hernan.gymapp.main_screen.domain.model.Client
import com.hernan.gymapp.common.state.ResourceFlow
import kotlinx.coroutines.flow.Flow

interface ClientRepository {
    fun getClient(clientId: String): Flow<ResourceFlow<Client>>
}