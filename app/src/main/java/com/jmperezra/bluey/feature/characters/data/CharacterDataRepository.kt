package com.jmperezra.bluey.feature.characters.data

import com.jmperezra.bluey.feature.characters.data.local.xml.CharactersXmlLocalDataSource
import com.jmperezra.bluey.feature.characters.data.remote.CharactersApiRemoteDataSource
import com.jmperezra.bluey.feature.characters.domain.Character
import com.jmperezra.bluey.feature.characters.domain.CharacterRepository

class CharacterDataRepository(
    private val remoteDataSource: CharactersApiRemoteDataSource,
    private val localDataSource: CharactersXmlLocalDataSource
) : CharacterRepository {

    override suspend fun getCharacters(): Result<List<Character>> {
        val resultLocal = localDataSource.findAll()
        return if (resultLocal.getOrDefault(emptyList()).isEmpty()) {
            remoteDataSource.fetchAll().onSuccess {
                localDataSource.clear()
                localDataSource.save(it)
            }
        } else {
            resultLocal
        }
    }

    override suspend fun getCharacterDetail(characterId: String): Result<Character> {
        localDataSource.find(characterId).getOrNull()?.let { character ->
            return Result.success(character)
        }
        return remoteDataSource.fetch(characterId).onSuccess {
            localDataSource.clear()
            localDataSource.save(it)
        }
    }
}