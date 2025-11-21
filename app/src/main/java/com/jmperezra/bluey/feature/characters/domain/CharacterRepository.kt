package com.jmperezra.bluey.feature.characters.domain

interface CharacterRepository {

    suspend fun getCharacters(): Result<List<Character>>
}