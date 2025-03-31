package com.hernan.gymapp.main_screen.domain.usecase

import com.hernan.gymapp.main_screen.domain.model.FirstState
import com.hernan.gymapp.main_screen.domain.repository.FirstStateRepository
import com.hernan.gymapp.common.state.ResourceFlow
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FirstStateUseCase @Inject constructor(
    private val firstStateRepository: FirstStateRepository
) {
    operator fun invoke(): Flow<ResourceFlow<List<FirstState>>> {
        return firstStateRepository.getFirstState()
    }
}