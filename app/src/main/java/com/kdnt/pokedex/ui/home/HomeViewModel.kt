package com.kdnt.pokedex.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kdnt.pokedex.data.api.ApiPokemonService
import com.kdnt.pokedex.data.api.RetrofitPokemon
import com.kdnt.pokedex.core.base.BaseViewModel
import com.kdnt.pokedex.data.model.Pokemon
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeViewModel : BaseViewModel() {

    fun getListPokemon(success: (ArrayList<Pokemon>) -> Unit = {}) {
        GlobalScope.launch {
            val appAPI = RetrofitPokemon().getRetrofit().create(ApiPokemonService::class.java)
            appAPI.getPokemonList()
                .subscribeOn(Schedulers.io())
                .subscribe {
                    val listPoke = it.results
                    success(listPoke)
                    Log.d("----", it.toString())
                }
        }
    }

}