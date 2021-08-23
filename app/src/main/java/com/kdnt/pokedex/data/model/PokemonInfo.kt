package com.kdnt.pokedex.data.model

import com.google.gson.annotations.SerializedName
import kotlin.random.Random

data class PokemonInfo(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("height") val height: String,
    @SerializedName("weight") val weight: String,
    @SerializedName("base_experience") val experience: Int,
    @SerializedName("types") val types: MutableList<TypeResponse>,
    val hp: Int = Random.nextInt(maxHp),
    val attack: Int = Random.nextInt(maxAttack),
    val defense: Int = Random.nextInt(maxDefense),
    val speed: Int = Random.nextInt(maxSpeed),
    val exp: Int = Random.nextInt(maxExp)

){
    fun getImageUrl(): String {
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/$id.png"
    }
    fun getIdString(): String = String.format("#%03d", id)
    fun getWeightString(): String = String.format("%.1f KG", weight.toFloat() / 10)
    fun getHeightString(): String = String.format("%.1f M", height.toFloat() / 10)
    fun getHpString(): String = "$hp/$maxHp"
    fun getAttackString(): String = "$attack/$maxAttack"
    fun getDefenseString(): String = "$defense/$maxDefense"
    fun getSpeedString(): String = "$speed/$maxSpeed"
    fun getExpString(): String = "$exp/$maxExp"

    companion object {
        const val maxHp = 300
        const val maxAttack = 300
        const val maxDefense = 300
        const val maxSpeed = 300
        const val maxExp = 1000
    }
}