package com.hernan.gymapp.user_type.data.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.hernan.gymapp.main_screen.domain.model.Client
import com.hernan.gymapp.main_screen.domain.repository.ClientRepository
import com.hernan.gymapp.common.state.ResourceFlow
import com.hernan.gymapp.user_type.data.data.model.ClientDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ClientRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : ClientRepository {

    override fun getClient(clientId: String): Flow<ResourceFlow<Client>> = flow {
        emit(ResourceFlow.Loading)

        val snapshot = firestore.collection("Clients").get().await()
        val clients = snapshot.documents.mapNotNull { it.toObject(ClientDto::class.java)?.toDomain() }

        clients.forEach { client ->
            emit(ResourceFlow.Success(client)) // Emitir cada cliente individualmente
        }

        if (clients.isEmpty()) {
            emit(ResourceFlow.Error("No hay clientes disponibles"))
        }
    }.catch { e ->
        emit(ResourceFlow.Error(e.localizedMessage ?: "Error desconocido"))
    }
}