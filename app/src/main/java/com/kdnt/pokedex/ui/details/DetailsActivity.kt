package com.kdnt.pokedex.ui.details

import android.os.Bundle
import com.bumptech.glide.Glide
import com.kdnt.pokedex.R
import com.kdnt.pokedex.core.base.BaseActivity
import com.kdnt.pokedex.data.model.Pokemon
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
        mViewModel.getListFolderLiveData().observe(this,{ pokemonInfo ->
            mViewBinding.name.text = pokemonInfo.name
            mViewBinding.height.text = pokemonInfo.getHeightString()
            mViewBinding.weight.text = pokemonInfo.getWeightString()
            Glide.with(this).load(pokemonInfo.getImageUrl()).thumbnail(0.1f).into(mViewBinding.image)
        })
    }
}