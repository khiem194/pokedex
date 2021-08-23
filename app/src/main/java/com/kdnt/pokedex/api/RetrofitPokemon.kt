package com.kdnt.pokedex.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

abstract class RetrofitPokemon {
    //https://pokeapi.co/api/v2/pokemon/ditto
    companion object {
        private const val BASE_URL = "https://pokeapi.co/api/v2/"
        fun getInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }

    abstract fun apiPokemonService() : ApiPokemonService
}