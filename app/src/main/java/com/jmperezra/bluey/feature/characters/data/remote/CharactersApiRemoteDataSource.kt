package com.jmperezra.bluey.feature.characters.data.remote

import com.jmperezra.bluey.core.data.remote.api.apiCall
import com.jmperezra.bluey.feature.characters.domain.Character
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CharactersApiRemoteDataSource(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val characterApiService: CharacterApiService
) {

    suspend fun fetchAll(): Result<List<Character>> {
        return withContext(dispatcher) {
            val resultApi = apiCall { characterApiService.fetchAll() }
            resultApi.map { charactersApiModels ->
                charactersApiModels.map { characterApiModel ->
                    characterApiModel.toDomain()
                }
            }
        }
    }
}