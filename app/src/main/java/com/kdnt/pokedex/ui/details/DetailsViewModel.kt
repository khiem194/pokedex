package com.kdnt.pokedex.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kdnt.pokedex.core.base.BaseViewModel
import com.kdnt.pokedex.data.api.ApiPokemonService
import com.kdnt.pokedex.data.api.RetrofitPokemon
import com.kdnt.pokedex.data.model.PokemonInfo
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class DetailsViewModel : BaseViewModel() {
    private val mInfoPokemonData: MutableLiveData<PokemonInfo> = MutableLiveData()

    fun getInfoPokemon(name: String, success: (PokemonInfo) -> Unit) {
        viewModelScope.launch {
            val instance = RetrofitPokemon().getRetrofit().create(ApiPokemonService::class.java)
            instance.getPokemonInfo(name)
                .subscribeOn(Schedulers.io())
                .subscribe {
                    success.invoke(it)
                }
        }

    }

}