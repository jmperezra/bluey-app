package com.jmperezra.bluey.feature.characters.domain

interface CharacterRepository {

    fun getCharacters(): Result<List<Character>>
}