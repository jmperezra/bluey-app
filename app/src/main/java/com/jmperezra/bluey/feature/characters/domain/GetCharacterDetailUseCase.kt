package com.jmperezra.bluey.feature.characters.domain

import kotlinx.serialization.Serializable

class GetCharacterDetailUseCase(private val characterRepository: CharacterRepository) {

    suspend operator fun invoke(characterId: String): Result<Output> {
        return characterRepository.getCharacterDetail(characterId).map { character ->
            Output(character.id, character.name, character.urlPhoto, character.shortDescription)
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
