package com.kdnt.pokedex.di

import com.kdnt.pokedex.api.ApiPokemonService
import com.kdnt.pokedex.api.RetrofitPokemon
import org.koin.dsl.module
val networkModule = module {
    single { RetrofitPokemon.getInstance() }
}