package com.hernan.gymapp.main_screen.data.repository

import com.hernan.gymapp.main_screen.domain.repository.ClientRepository
import com.hernan.gymapp.main_screen.presentation.state.ResourceFlow
import com.hernan.gymapp.models.HarcodedLists
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ClientRepositoryImpl @Inject constructor(
    private val apiClientRepository: ClienApi
) : ClientRepository{
    override fun getClientRepository(): Flow<ResourceFlow<HarcodedLists>> = flow {
        emit(ResourceFlow.Loading) // Emitimos estado de carga

        try {
            val response = apiClientRepository.getClient()

            if (response.isSuccessful) {
                response.body()?.let {
                    emit(ResourceFlow.Success(it)) // Emitimos los datos si la respuesta es exitosa
                } ?: emit(ResourceFlow.Error("Respuesta vacía"))
            } else {
                emit(ResourceFlow.Error("Error en la respuesta: ${response.code()}"))
            }
        } catch (e: Exception) {
            emit(ResourceFlow.Error(e.localizedMessage ?: "Error desconocido")) // Emitimos error en caso de excepción
        }
    }


}