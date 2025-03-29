package com.hernan.gymapp.main_screen.data.repository

import com.hernan.gymapp.main_screen.presentation.state.ResourceFlow
import com.hernan.gymapp.models.HarcodedLists
import retrofit2.Response
import retrofit2.http.GET

interface ClienApi {
    @GET("ALGO")
    suspend fun getClient(): Response<HarcodedLists>
}