package com.kdnt.pokedex.data.model

import com.google.gson.annotations.SerializedName

data class PokemonStat(
    @SerializedName("base_stat")
    val pokemonBaseStat: Int
)
