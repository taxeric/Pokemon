package com.eric.pokemon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.eric.pokemon.base.OnClickListener
import com.eric.pokemon.entity.PokemonListData
import com.eric.pokemon.net.RetrofitUtils
import com.eric.pokemon.utils.LogUtils
import kotlinx.android.synthetic.main.pokemon_rv.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher

class PokemonListActivity : AppCompatActivity(), OnClickListener {

    private lateinit var adapter: PokemonListAdapter
    private val list = mutableListOf<PokemonListData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pokemon_rv)

        adapter = PokemonListAdapter(this, list)
        adapter.setClickListener(this)
        pokemon_rv_list.layoutManager = GridLayoutManager(this, 2)
        pokemon_rv_list.adapter = adapter

        getData()
    }

    private fun getData(){
        CoroutineScope(Dispatchers.Main).launch {
            val pokemonList = withContext(Dispatchers.IO){
                RetrofitUtils.getInstance().get().fetchPokemonList().results
            }
            withContext(Dispatchers.IO){
                list.clear()
                list.addAll(pokemonList)
            }
            withContext(Dispatchers.Main){
                adapter.notifyDataSetChanged()
            }
        }
    }

    override fun onItemClick(view: View, order: Int) {
        LogUtils.i("should load order is $order")
        startActivity(Intent(this, PokemonInfoActivity::class.java).putExtra("order", order))
    }
}