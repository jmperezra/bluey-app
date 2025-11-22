package com.jmperezra.bluey.feature.characters.domain

interface CharacterRepository {

    suspend fun getCharacters(): Result<List<Character>>
    suspend fun getCharacterDetail(characterId: String): Result<Character>
}