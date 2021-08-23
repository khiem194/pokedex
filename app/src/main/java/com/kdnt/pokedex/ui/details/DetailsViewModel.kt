package com.kdnt.pokedex.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kdnt.pokedex.api.ApiPokemonService
import com.kdnt.pokedex.api.RetrofitPokemon
import com.kdnt.pokedex.core.base.BaseViewModel
import com.kdnt.pokedex.data.model.PokemonInfo
import kotlinx.coroutines.launch

class DetailsViewModel : BaseViewModel() {
    private val mInfoPokemonData: MutableLiveData<PokemonInfo> = MutableLiveData()

    fun getListFolderLiveData() = mInfoPokemonData


    fun getInfoPokemon(name: String) {
        viewModelScope.launch {
            val instance = RetrofitPokemon.getInstance().create(ApiPokemonService::class.java)
            val infoPokemon = instance.getPokemonInfo(name)
            mInfoPokemonData.postValue(infoPokemon)
        }

    }

}