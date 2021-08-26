package com.kdnt.pokedex.ui.home

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.appcompat.widget.SearchView
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
    private lateinit var searchView : SearchView

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_item, menu)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView = menu?.findItem(R.id.search)?.actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.maxWidth = Int.MAX_VALUE
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                mAdapter.filter.filter(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                mAdapter.filter.filter(newText)
                return false
            }
        })

        return true
    }


    private fun clickDetailPokemon(pokemon: Pokemon) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("id", pokemon)
        startActivity(intent)
    }

}