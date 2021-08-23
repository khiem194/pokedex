package com.kdnt.pokedex.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pokemon(
    @SerializedName("name") val name : String,
    @SerializedName("url") val url : String,
) :Parcelable{
    fun getImageUrl(): String {
        val index = url.split("/".toRegex()).dropLast(1).last()
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/$index.png"
    }
}