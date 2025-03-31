package com.hernan.gymapp.common.state.module

import com.google.firebase.firestore.FirebaseFirestore
import com.hernan.gymapp.main_screen.data.repository.FirstStateRepositoryImpl
import com.hernan.gymapp.main_screen.domain.repository.ClientRepository
import com.hernan.gymapp.main_screen.domain.repository.FirstStateRepository
import com.hernan.gymapp.main_screen.domain.usecase.FirstStateUseCase
import com.hernan.gymapp.user_type.data.data.domain.usecase.GetClientUsecase
import com.hernan.gymapp.user_type.data.data.repository.ClientRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideClientRepository(
        firestore: FirebaseFirestore
    ): ClientRepository {
        return ClientRepositoryImpl(firestore)
    }
}

@Module
@InstallIn(SingletonComponent::class)
object UseCaseClientModule {
    @Provides
    fun provideGetClientUsecase(repository: ClientRepository): GetClientUsecase {
        return GetClientUsecase(repository)
    }
}

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {
    @Provides
    @Singleton
    fun provideFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()
}

@Module
@InstallIn(SingletonComponent::class)
object FirstTateRepositoryModule {
    @Provides
    @Singleton
    fun provideFirstState(
        firestore: FirebaseFirestore
    ): FirstStateRepository {
        return FirstStateRepositoryImpl(firestore)
    }
}

@Module
@InstallIn(SingletonComponent::class)
object FirstStateUseCaseModule {
    @Provides
    fun provideFirstStateUsecase(repository: FirstStateRepository): FirstStateUseCase {
        return FirstStateUseCase(repository)
    }
}