package com.kdnt.pokedex.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kdnt.pokedex.data.model.Pokemon
import com.kdnt.pokedex.databinding.ItemPokemonBinding

class PokemonAdapter : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>(), Filterable {
    private var mListPokemon = mutableListOf<Pokemon>()
    var pokemonListFiltered = ArrayList<Pokemon>()
    var onClickItemPokemon: ((pokemon: Pokemon) -> Unit)? = null

    init {
        pokemonListFiltered = mListPokemon as ArrayList<Pokemon>
    }

    fun setData(list: MutableList<Pokemon>) {
        mListPokemon.clear()
        mListPokemon.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        return PokemonViewHolder(
            ItemPokemonBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.binding.name.text = mListPokemon[position].name
        Glide.with(holder.binding.image.context)
            .load(mListPokemon[position].getImageUrl())
            .centerCrop()
            .thumbnail(0.1f)
            .into(holder.binding.image)
        holder.itemView.setOnClickListener {
            onClickItemPokemon?.invoke(mListPokemon[holder.layoutPosition])
        }
    }

    override fun getItemCount(): Int = mListPokemon.size

    class PokemonViewHolder(val binding: ItemPokemonBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun getFilter(): Filter {
       return object : Filter(){
           override fun performFiltering(constraint: CharSequence?): FilterResults {
               val charSearch = constraint.toString()
               pokemonListFiltered = if (charSearch.isEmpty()) {
                   mListPokemon as ArrayList<Pokemon>
               } else {
                   val resultList = ArrayList<Pokemon>()
                   for (pokemon in mListPokemon) {
                       if (pokemon.name.toLowerCase().contains(constraint.toString().toLowerCase())) {
                           resultList.add(pokemon)
                       }
                   }
                   resultList
               }
               val filterResults = FilterResults()
               filterResults.values = pokemonListFiltered
               return filterResults
           }

           override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
               pokemonListFiltered = results?.values as ArrayList<Pokemon>
               notifyDataSetChanged()
           }
       }
    }

}