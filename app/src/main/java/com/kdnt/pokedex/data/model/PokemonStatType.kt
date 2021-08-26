package com.kdnt.pokedex.data.model

import com.google.gson.annotations.SerializedName

data class PokemonStatType(
    @SerializedName("name")
    val statTypeName: String
)
