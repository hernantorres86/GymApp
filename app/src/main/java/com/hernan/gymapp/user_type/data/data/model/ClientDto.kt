package com.hernan.gymapp.user_type.data.data.model

import com.hernan.gymapp.main_screen.domain.model.Client

data class ClientDto(
    val id: String = "",
    val name: String = "",
    val email: String = ""
) {
    fun toDomain(): Client {
        return Client(id, name, email)
    }
}
