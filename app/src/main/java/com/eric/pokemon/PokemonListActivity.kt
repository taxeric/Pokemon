package com.eric.pokemon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
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

class PokemonListActivity : AppCompatActivity(), OnClickListener {

    private lateinit var adapter: PokemonListAdapter
    private val list = mutableListOf<PokemonListData>()

    private var offset = 0
    private var hasNext = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pokemon_rv)
        adapter = PokemonListAdapter(this, list)
        pokemon_rv_list.adapter = adapter
        adapter.setClickListener(this)
        val manager = GridLayoutManager(this, 2)
        manager.spanSizeLookup = object: GridLayoutManager.SpanSizeLookup(){
            override fun getSpanSize(position: Int): Int {
                if (position != adapter.itemCount - 1)
                    return 1
                return 2
            }
        }
        pokemon_rv_list.layoutManager = manager

        getData()
    }

    private fun getData(){
        if (!hasNext){
            Toast.makeText(this, "没有更多数据了", Toast.LENGTH_SHORT).show()
            return
        }
        CoroutineScope(Dispatchers.Main).launch {
            val pokemonList = withContext(Dispatchers.IO){
                RetrofitUtils.getInstance().get().fetchPokemonList(20, offset).results
            }
            withContext(Dispatchers.IO){
//                list.clear()
                list.addAll(pokemonList)
                if (pokemonList.size == 20) {
                    offset += 20
                } else {
                    hasNext = false
                }
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

    override fun onItemClick(loadMore: Boolean, position: Int) {
        if (loadMore){
            getData()
        }
    }
}