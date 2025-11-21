package com.jmperezra.bluey.feature.characters.data.remote

import retrofit2.Response
import retrofit2.http.GET

interface CharacterApiService {

    @GET("characters.json")
    suspend fun fetchAll(): Response<List<CharacterApiModel>>

}