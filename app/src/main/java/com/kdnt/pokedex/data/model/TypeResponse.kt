package com.kdnt.pokedex.data.model

import com.google.gson.annotations.SerializedName

data class TypeResponse(
    @SerializedName("slot") val slot: Int,
    @SerializedName("type") val type: Type,
)