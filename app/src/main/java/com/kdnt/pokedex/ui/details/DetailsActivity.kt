package com.kdnt.pokedex.ui.details

import android.os.Bundle
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.kdnt.pokedex.R
import com.kdnt.pokedex.core.base.BaseActivity
import com.kdnt.pokedex.data.model.Pokemon
import com.kdnt.pokedex.data.model.PokemonInfo
import com.kdnt.pokedex.databinding.ActivityDetailsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsActivity : BaseActivity<DetailsViewModel, ActivityDetailsBinding>() {
    override fun getLayoutResId(): Int = R.layout.activity_details

    override val mViewModel: DetailsViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val pokemon = intent.extras?.get("id") as Pokemon
        mViewModel.getInfoPokemon(pokemon.name)
        title = pokemon.name
        mViewModel.getPokemonInfoLiveData().observe(this, { pokemonInfo ->
            setInfoPokemon(pokemonInfo)
        })
    }

    private fun setInfoPokemon(pokemonInfo: PokemonInfo) {
        Log.d("----", pokemonInfo.toString())
        //pkInfo.types[0].type.name
        mViewBinding.name.text = pokemonInfo.name
        mViewBinding.height.text = pokemonInfo.getHeightString()
        mViewBinding.weight.text = pokemonInfo.getWeightString()
        Glide.with(this).load(pokemonInfo.getImageUrl()).thumbnail(0.1f).into(mViewBinding.image)
        val arr = pokemonInfo.types
        Log.d("infoPoke", arr.size.toString())
        if (arr.size >= 2) {
            mViewBinding.type1.text = pokemonInfo.types[0].type.name
            mViewBinding.type2.text = pokemonInfo.types[1].type.name
        } else if (arr.size == 1) {
            Log.d("infoPoke", pokemonInfo.types.size.toString())
            mViewBinding.type1.text = pokemonInfo.types[0].type.name
            mViewBinding.type2.visibility = View.GONE
        }
        mViewBinding.progressHp.progress = pokemonInfo.pokemonStats[0].pokemonBaseStat
        mViewBinding.progressAtk.progress = pokemonInfo.pokemonStats[1].pokemonBaseStat
        mViewBinding.progressDef.progress = pokemonInfo.pokemonStats[2].pokemonBaseStat
        mViewBinding.progressSpecialAttack.progress = pokemonInfo.pokemonStats[3].pokemonBaseStat
        mViewBinding.progressSpecialDefense.progress = pokemonInfo.pokemonStats[4].pokemonBaseStat
        mViewBinding.progressSpeed.progress = pokemonInfo.pokemonStats[5].pokemonBaseStat
    }
}


//PokemonInfo(id=1, name=bulbasaur, height=7, weight=69, experience=64, types=[TypeResponse(slot=1, type=Type(name=grass)), TypeResponse(slot=2, type=Type(name=poison))], hp=0, attack=0, defense=0, speed=0, exp=0)