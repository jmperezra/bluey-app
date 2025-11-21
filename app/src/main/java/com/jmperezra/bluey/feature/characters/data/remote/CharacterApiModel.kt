package com.jmperezra.bluey.feature.characters.data.remote

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonIgnoreUnknownKeys

@Serializable
@JsonIgnoreUnknownKeys
@OptIn(kotlinx.serialization.ExperimentalSerializationApi::class)
data class CharacterApiModel(
    val id: String,
    val name: String,
    val shortDescription: String,
    val urlPhoto: String
)