package com.jmperezra.bluey.feature.characters.domain

import kotlinx.serialization.Serializable

class GetCharactersUseCase(private val characterRepository: CharacterRepository) {

    suspend operator fun invoke(): Result<List<Output>> {
        return characterRepository.getCharacters().map { characters ->
            characters.map {
                Output(it.id, it.name, it.urlPhoto, it.shortDescription)
            }
        }
    }

    @Serializable
    data class Output(
        val id: String,
        val name: String,
        val urlPhoto: String,
        val shortDescription: String
    )
}
