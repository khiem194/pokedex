package com.kdnt.pokedex.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.kdnt.pokedex.R
import com.kdnt.pokedex.core.base.BaseActivity
import com.kdnt.pokedex.data.model.Pokemon
import com.kdnt.pokedex.databinding.ActivityHomeBinding
import com.kdnt.pokedex.ui.details.DetailsActivity
import com.kdnt.pokedex.ui.home.adapter.PokemonAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.FieldPosition

class HomeActivity : BaseActivity<HomeViewModel, ActivityHomeBinding>() {
    private lateinit var mAdapter: PokemonAdapter
    private var mListPokemon = mutableListOf<Pokemon>()

    companion object {
        fun openActivity(context: Context): Intent = Intent(context, HomeActivity::class.java)
    }

    override fun getLayoutResId(): Int = R.layout.activity_home

    override val mViewModel: HomeViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mViewBinding.rcvPokemon.layoutManager = GridLayoutManager(this, 3)
        mAdapter = PokemonAdapter()
        mAdapter.onClickItemPokemon = this::clickDetailPokemon
        mViewBinding.rcvPokemon.adapter = mAdapter
        mViewModel.getListPokemon {
            runOnUiThread {
                mAdapter.setData(it)
            }
        }
    }

    private fun clickDetailPokemon(pokemon: Pokemon) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("id", pokemon)
        startActivity(intent)
    }

}