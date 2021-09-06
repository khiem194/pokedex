package com.kdnt.pokedex.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kdnt.pokedex.core.base.BaseViewModel
import com.kdnt.pokedex.data.model.PokemonInfo
import com.kdnt.pokedex.data.repository.PokedexRepository
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class DetailsViewModel(private val pokedexRepository: PokedexRepository) : BaseViewModel() {
    private val mInfoPokemonData: MutableLiveData<PokemonInfo> = MutableLiveData()

    fun getPokemonInfoLiveData() = mInfoPokemonData

    fun getInfoPokemon(name: String) {
        viewModelScope.launch {
            pokedexRepository.getPokemonInfo(name)
                .subscribeOn(Schedulers.io())
                .subscribe {
                    //success.invoke(it)
                    mInfoPokemonData.postValue(it)
                }
//            val instance = RetrofitPokemon().getRetrofit().create(ApiPokemonService::class.java)
//            instance.getPokemonInfo(name)
//                .subscribeOn(Schedulers.io())
//                .subscribe {
//                    success.invoke(it)
//                }
        }

    }

}