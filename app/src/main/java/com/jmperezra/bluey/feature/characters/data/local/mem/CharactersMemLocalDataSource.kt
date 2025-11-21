package com.jmperezra.bluey.feature.characters.data.local.mem

class CharactersMemLocalDataSource {

    private val characters: ArrayList<Character> =
        emptyList<Character>() as ArrayList<Character>

    private fun obtain(): Result<List<Character>> {
        return Result.success(characters)
    }

    private fun save(characters: List<Character>) {
        this.characters.clear()
        this.characters.addAll(characters)
    }
}