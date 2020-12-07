package com.eric.pokemon.net

import com.eric.pokemon.entity.PokemonDetailInfo
import com.eric.pokemon.entity.PokemonList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


/**
 * Created by eric on 20-12-4
 */
interface OpenApi {

    @GET("pokemon")
    suspend fun fetchPokemonList(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): PokemonList

    @GET("pokemon-species/{path}/")
    suspend fun searchPokemonDetailInfo(
        @Path("path") name_or_id: String
    ): PokemonDetailInfo
}