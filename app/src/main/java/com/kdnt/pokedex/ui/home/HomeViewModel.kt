package com.kdnt.pokedex.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.kdnt.pokedex.core.base.BaseViewModel
import com.kdnt.pokedex.data.model.Pokemon
import com.kdnt.pokedex.data.repository.PokedexRepository
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeViewModel(private val pokedexRepository: PokedexRepository) : BaseViewModel() {
    init {
        getListPokemon()
    }
    private val mListPokemonData: MutableLiveData<MutableList<Pokemon>> = MutableLiveData()

    fun getListPokemonLiveData() = mListPokemonData

    private fun getListPokemon() {
        GlobalScope.launch {
            pokedexRepository.getPokemonList()
                .subscribeOn(Schedulers.io())
                .subscribe{
                    val listPoke = it.results
                    //success(listPoke)
                    mListPokemonData.postValue(listPoke)
                    Log.d("----", it.toString())
                }

//            val appAPI = RetrofitPokemon().getRetrofit().create(ApiPokemonService::class.java)
//            appAPI.getPokemonList()
//                .subscribeOn(Schedulers.io())
//                .subscribe {
//                    val listPoke = it.results
//                    success(listPoke)
//                    Log.d("----", it.toString())
//                }
        }
    }

}