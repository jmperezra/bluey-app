package com.jmperezra.bluey.feature.characters.data.local.xml

import com.jmperezra.bluey.core.data.local.xml.XmlCacheStorage
import com.jmperezra.bluey.core.data.local.xml.policies.CachePolicy
import com.jmperezra.bluey.core.domain.ErrorApp
import com.jmperezra.bluey.core.providers.TimeProvider
import com.jmperezra.bluey.feature.characters.domain.Character

class CharactersXmlLocalDataSource(
    private val xmlCacheStorage: XmlCacheStorage<CharacterXmlModel>,
    private val cachePolicy: CachePolicy<CharacterXmlModel>,
    private val timeProvider: TimeProvider
) {

    /**
     * Función que me devuelve un listado de personajes que se encuentran en la caché y cumplen con la política escogida.
     * El almacenamiento de caché me devuelve un Result. Valido las dos opciones (success y failure) con fold.
     * - Si es success compruebo si el dato cumple con la política de la caché, si es así, la añado a un listado, sino, no la añado.
     * - Si es error, lo transformo a un error de aplicación.
     */
    fun findAll(): Result<List<Character>> {
        return xmlCacheStorage.obtainAll().fold({ characterXmlModels ->
            val characters: MutableList<Character> = mutableListOf()
            characterXmlModels.forEach { characterXmlModel ->
                if (cachePolicy.isValid(characterXmlModel)) {
                    characters.add(characterXmlModel.toModel())
                }
            }
            Result.success(characters)
        }, {
            Result.failure(ErrorApp.CacheError)
        })
    }

    fun find(characterId: String): Result<List<Character>> {
        return xmlCacheStorage.obtainAll().fold({ characterXmlModels ->
            val characters: MutableList<Character> = mutableListOf()
            characterXmlModels.forEach { characterXmlModel ->
                if (cachePolicy.isValid(characterXmlModel)) {
                    characters.add(characterXmlModel.toModel())
                }
            }
            Result.success(characters)
        }, {
            Result.failure(ErrorApp.CacheError)
        })
    }

    fun save(characters: List<Character>): Result<Boolean> {
        val createdAt: Long = timeProvider.getCurrentTimeInMs()
        return xmlCacheStorage.save(characters.map { it.toXmlModel(createdAt) })
    }

    fun clear() {
        xmlCacheStorage.clear()
    }
}