package com.eric.pokemon

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.eric.pokemon.entity.PokemonListData

/**
 * Created by eric on 20-12-7
 */
class PokemonListAdapter(
    private val context: Context,
    private val list: MutableList<PokemonListData>
): RecyclerView.Adapter<PokemonListAdapter.ViewHolder>() {

    inner class ViewHolder(view: View, val binding: ViewDataBinding): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        val binding =
            DataBindingUtil.inflate<ViewDataBinding>(
                LayoutInflater.from(context),
                R.layout.rv_item_pokemon, parent, false)
        return ViewHolder(binding.root, binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val entity = list[position]
        holder.binding.setVariable(BR.pokemon, entity)
//        holder.itemView.rv_item_pokemon_pic.load(entity.getImageUrl())
//        holder.itemView.rv_item_pokemon_name.text = entity.name
    }
}