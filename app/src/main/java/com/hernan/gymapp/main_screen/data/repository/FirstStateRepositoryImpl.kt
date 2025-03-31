package com.hernan.gymapp.main_screen.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.hernan.gymapp.main_screen.data.model.FirstStateDto
import com.hernan.gymapp.main_screen.domain.model.FirstState
import com.hernan.gymapp.main_screen.domain.repository.FirstStateRepository
import com.hernan.gymapp.common.state.ResourceFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirstStateRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : FirstStateRepository {
    override fun getFirstState(): Flow<ResourceFlow<List<FirstState>>> = flow {
        emit(ResourceFlow.Loading)

        val snapshot = firestore.collection("MainScreenColection").get().await()
        val firstStateList = snapshot.documents.mapNotNull { it.toObject(FirstStateDto::class.java)?.toDomain() }

        if (firstStateList.isNotEmpty()) {
            emit(ResourceFlow.Success(firstStateList)) // ✅ Emitimos la lista completa
        } else {
            emit(ResourceFlow.Error("No hay estados disponibles"))
        }
    }.catch { e ->
        emit(ResourceFlow.Error(e.localizedMessage ?: "Error desconocido"))
    }
}