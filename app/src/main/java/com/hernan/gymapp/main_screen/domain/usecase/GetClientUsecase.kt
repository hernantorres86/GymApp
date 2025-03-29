package com.hernan.gymapp.main_screen.domain.usecase

import com.hernan.gymapp.main_screen.data.repository.ClientRepositoryImpl
import com.hernan.gymapp.main_screen.presentation.state.ResourceFlow
import com.hernan.gymapp.models.HarcodedLists
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetClientUsecase @Inject constructor(private val repository: ClientRepositoryImpl) {
    operator fun invoke(): Flow<ResourceFlow<HarcodedLists>> = repository.getClientRepository()
}