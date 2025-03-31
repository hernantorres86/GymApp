package com.hernan.gymapp.user_type.data.data.domain.usecase

import com.hernan.gymapp.main_screen.domain.model.Client
import com.hernan.gymapp.main_screen.domain.repository.ClientRepository
import com.hernan.gymapp.common.state.ResourceFlow
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetClientUsecase @Inject constructor(
    private val repository: ClientRepository
) {
    operator fun invoke(clientId: String): Flow<ResourceFlow<Client>> {
        return repository.getClient(clientId)
    }
}