package com.kdnt.pokedex.api

import com.kdnt.pokedex.data.model.ListPokemon
import com.kdnt.pokedex.data.model.PokemonInfo
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiPokemonService {
    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int = 200,
        @Query("offset") offset: Int = 0
    ): ListPokemon

    @GET("pokemon/{name}")
    suspend fun getPokemonInfo(@Path("name") name: String): PokemonInfo
}