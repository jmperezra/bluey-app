package com.jmperezra.bluey.core.data.local.xml

import android.content.Context
import android.util.Log
import androidx.core.content.edit
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json

class XmlCacheStorage<T : XmlModel>(
    private val context: Context,
    private val nameXml: String,
    private val dataSerializer: KSerializer<T>
) {

    private val prefs = context.getSharedPreferences(nameXml, Context.MODE_PRIVATE)

    private val json = Json {
        ignoreUnknownKeys = true
        prettyPrint = true
        isLenient = true
    }

    fun save(model: T): Result<Boolean> {
        return try {
            val jsonString = json.encodeToString(dataSerializer, model)
            //Al no usar el parámetro commit = true (sync), se está usando apply (async)
            prefs.edit { putString(model.getId(), jsonString) }
            Result.success(true)
        } catch (e: SerializationException) {
            Log.e(
                XmlCacheStorage::class.simpleName,
                "Error when saving model: ${e.message}"
            )
            Result.failure(e)
        }
    }

    fun save(models: List<T>): Result<Boolean> {
        models.forEach {
            save(it).onFailure { return Result.failure(it) }
        }
        return Result.success(true)
    }

    fun obtainAll(): Result<List<T>> {
        try {
            val models: MutableList<T> = mutableListOf()
            prefs.all.map {
                val jsonString = it.value as String
                models.add(json.decodeFromString(dataSerializer, jsonString))
            }
            return Result.success(models)
        } catch (e: SerializationException) {
            Log.e(
                XmlCacheStorage::class.simpleName,
                "Error when obtain all models : ${e.message}"
            )
            return Result.failure(e)
        }
    }

    fun obtain(id: String): Result<T?> {
        try {
            val jsonString = prefs.getString(id, null) ?: return Result.success(null)
            return Result.success(json.decodeFromString(dataSerializer, jsonString))
        } catch (e: SerializationException) {
            Log.e(
                XmlCacheStorage::class.simpleName,
                "Error when obtain model with key $id: ${e.message}"
            )
            return Result.failure(e)
        }
    }

    fun update(model: T): Result<Boolean> {
        return save(model)
    }

    fun delete(model: T) {
        prefs.edit { remove(model.getId()) }
    }

    fun clear() {
        prefs.edit { clear() }
    }
}