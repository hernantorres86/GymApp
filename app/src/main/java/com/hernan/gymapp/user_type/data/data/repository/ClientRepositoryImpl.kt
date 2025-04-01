package com.hernan.gymapp.user_type.data.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.hernan.gymapp.user_type.data.data.domain.model.Client
import com.hernan.gymapp.user_type.data.data.domain.repository.ClientRepository
import com.hernan.gymapp.common.state.ResourceFlow
import com.hernan.gymapp.main_screen.data.model.FirstStateDto
import com.hernan.gymapp.user_type.data.data.model.ClientDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ClientRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : ClientRepository {

    override fun getClient(clientId: String): Flow<ResourceFlow<List<Client>>> = flow {
        emit(ResourceFlow.Loading)

        val snapshot = firestore.collection("Clients").get().await()
        val clients = snapshot.documents.mapNotNull { it.toObject(ClientDto::class.java)?.toDomain() }

        if (clients.isNotEmpty()) {
            emit(ResourceFlow.Success(clients))
        } else {
            emit(ResourceFlow.Error("No hay estados disponibles"))
        }
    }.catch { e ->
        emit(ResourceFlow.Error(e.localizedMessage ?: "Error desconocido"))
    }


}