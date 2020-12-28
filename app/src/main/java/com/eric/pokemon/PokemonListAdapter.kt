package com.eric.pokemon

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.eric.pokemon.base.OnClickListener
import com.eric.pokemon.entity.PokemonListData

/**
 * Created by eric on 20-12-7
 */
class PokemonListAdapter(
    private val context: Context,
    private val list: MutableList<PokemonListData>
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class ViewHolder(view: View, val binding: ViewDataBinding): RecyclerView.ViewHolder(view)

    inner class FootHolder(view: View): RecyclerView.ViewHolder(view)

    private var listener: OnClickListener ?= null

    fun setClickListener(listener: OnClickListener){
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder{
        return if (viewType == 0){
            FootHolder(LayoutInflater.from(context).inflate(R.layout.rv_item_foot, parent, false))
        } else {
            val binding = DataBindingUtil.inflate<ViewDataBinding>(
                LayoutInflater.from(context), R.layout.rv_item_pokemon, parent, false
            )
            ViewHolder(binding.root, binding)
        }
    }

    override fun getItemCount(): Int = if (list.size == 0) 0 else list.size + 1

    override fun getItemViewType(position: Int): Int = if (position == list.size) 0 else 1

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder) {
            holder.binding.setVariable(BR.pokemon, list[position])
            if (listener != null) {
                holder.binding.setVariable(BR.clickListener, listener)
            }
        } else {
            holder.itemView.setOnClickListener {
                listener?.onItemClick(true, position)
            }
        }
    }
}