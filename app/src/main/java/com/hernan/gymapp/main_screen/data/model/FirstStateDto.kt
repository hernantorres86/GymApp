package com.hernan.gymapp.main_screen.data.model

import com.hernan.gymapp.main_screen.domain.model.FirstState

data class FirstStateDto(
    val name: String = ""
) {
    fun toDomain(): FirstState {
        return FirstState(name)
    }
}
