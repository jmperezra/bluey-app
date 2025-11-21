package com.jmperezra.bluey.feature.characters.domain

import kotlinx.serialization.Serializable

@Serializable
data class Character(
    val id: String,
    val name: String,
    val prashe: String,
    val age: String,
    val shortDescription: String,
    val description: String,
    val gender: String,
    val breed: String,
    val urlPhoto: String
)