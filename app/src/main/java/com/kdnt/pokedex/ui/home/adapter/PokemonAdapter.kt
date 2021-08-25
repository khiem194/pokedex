package com.kdnt.pokedex.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kdnt.pokedex.data.model.Pokemon
import com.kdnt.pokedex.databinding.ItemPokemonBinding

class PokemonAdapter : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {
    private var mListPokemon = mutableListOf<Pokemon>()
    var onClickItemPokemon: ((pokemon: Pokemon) -> Unit)? = null

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

}