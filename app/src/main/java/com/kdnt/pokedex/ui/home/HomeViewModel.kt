package com.kdnt.pokedex.ui.home

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kdnt.pokedex.api.ApiPokemonService
import com.kdnt.pokedex.api.RetrofitPokemon
import com.kdnt.pokedex.core.base.BaseViewModel
import com.kdnt.pokedex.data.model.ListPokemon
import com.kdnt.pokedex.data.model.Pokemon
import kotlinx.coroutines.launch
import retrofit2.create

class HomeViewModel() : BaseViewModel() {
    init {
        getListPokemon()
    }
    private val mListPokemonData: MutableLiveData<MutableList<Pokemon>> = MutableLiveData()

    fun getListFolderLiveData() = mListPokemonData


    private fun getListPokemon(){
        viewModelScope.launch {
            val instance = RetrofitPokemon.getInstance().create(ApiPokemonService::class.java)
            val listPokemon = instance.getPokemonList().results
            mListPokemonData.postValue(listPokemon)
        }

    }

}