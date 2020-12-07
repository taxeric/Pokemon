package com.eric.pokemon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.eric.pokemon.entity.PokemonListData
import kotlinx.android.synthetic.main.pokemon_rv.*

class PokemonList : AppCompatActivity() {

    private lateinit var adapter: PokemonListAdapter
    private val list = mutableListOf<PokemonListData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val binding = DataBindingUtil.setContentView<RvItemPokemonBinding>()
        setContentView(R.layout.pokemon_rv)

        for (i in 0 until 10){
            val entity = PokemonListData("皮卡丘", (i + 1).toString())
            list.add(entity)
        }

        adapter = PokemonListAdapter(this, list)
        pokemon_rv_list.layoutManager = GridLayoutManager(this, 2)
        pokemon_rv_list.adapter = adapter
    }
}