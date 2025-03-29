package com.hernan.gymapp.main_screen.data.model

import com.hernan.gymapp.main_screen.data.repository.ClienApi
import com.hernan.gymapp.main_screen.data.repository.ClientRepositoryImpl
import com.hernan.gymapp.main_screen.domain.repository.ClientRepository
import com.hernan.gymapp.main_screen.domain.usecase.GetClientUsecase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ModuleClientRepo {
    @Provides
    @Singleton
    fun provideDepartmentChildApi(retrofit: Retrofit): ClienApi {
        return retrofit.create(ClienApi::class.java)
    }

    @Provides
    @Singleton
    fun provideClientRepository(api: ClienApi): ClientRepository {
        return ClientRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideGetClientUseCase(repository: ClientRepositoryImpl): GetClientUsecase {
        return GetClientUsecase(repository)
    }
}