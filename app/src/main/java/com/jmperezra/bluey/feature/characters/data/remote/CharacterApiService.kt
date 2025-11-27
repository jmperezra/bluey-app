package com.jmperezra.bluey.feature.characters.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterApiService {

    @GET("characters.json")
    suspend fun fetchAll(): Response<List<CharacterApiModel>>

    @GET("characters/{characterId}.json")
    suspend fun fetch(@Path("characterId") characterId: String): Response<CharacterApiModel>

}