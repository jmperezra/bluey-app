package com.jmperezra.bluey.feature.characters.domain

class GetCharactersUseCase(private val characterRepository: CharacterRepository) {

    operator fun invoke(): Result<List<Character>> {
        return characterRepository.getCharacters()
    }

    data class Output(val name: String, val urlPhoto: String, val shortDescription: String)
}
