package com.kdnt.pokedex.data.repository

import com.kdnt.pokedex.data.api.ApiPokemonService
import com.kdnt.pokedex.data.model.ListPokemon
import com.kdnt.pokedex.data.model.PokemonInfo
import io.reactivex.Observable

class PokedexRepository(private val apiPokemonService: ApiPokemonService) {
    fun getPokemonList(): Observable<ListPokemon> = apiPokemonService.getPokemonList()
    fun getPokemonInfo(name : String) : Observable<PokemonInfo> = apiPokemonService.getPokemonInfo(name)
}