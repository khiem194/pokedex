package com.kdnt.pokedex.data.model

import com.google.gson.annotations.SerializedName

data class ListPokemon(
    @SerializedName("count") val count : Int,
    @SerializedName("next") val next : String,
    @SerializedName("previous") val previous : String,
    @SerializedName("results") val results : MutableList<Pokemon>,
)