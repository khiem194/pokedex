package com.kdnt.pokedex.data.api

import com.kdnt.pokedex.data.model.ListPokemon
import com.kdnt.pokedex.data.model.PokemonInfo
import io.reactivex.Observable
import io.reactivex.Observer
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiPokemonService {
    @GET("pokemon")
    fun getPokemonList(
        @Query("limit") limit: Int = 10,
        @Query("offset") offset: Int = 0
    ): Observable<ListPokemon>

    @GET("pokemon/{name}")
    fun getPokemonInfo(@Path("name") name: String): Observable<PokemonInfo>
}