package com.eric.pokemon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import com.eric.pokemon.base.OnClickListener
import com.eric.pokemon.entity.PokemonListData
import com.eric.pokemon.net.RetrofitUtils
import com.eric.pokemon.utils.LocalShareUtils
import com.eric.pokemon.utils.LogUtils
import kotlinx.android.synthetic.main.pokemon_rv.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PokemonListActivity : AppCompatActivity(), OnClickListener, SearchView.OnQueryTextListener {

    private lateinit var adapter: PokemonListAdapter
    private val list = mutableListOf<PokemonListData>()

    private var offset = 0
    private var hasNext = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pokemon_rv)
        setSupportActionBar(tool_bar)
        search_view.setOnQueryTextListener(this)
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_item_setting){
            LogUtils.i("click menu")
        }
        return true
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
        startActivity(Intent(this, PokemonInfoActivity::class.java).putExtra("order", order.toString()))
    }

    override fun onItemClick(loadMore: Boolean, position: Int) {
        if (loadMore){
            getData()
        }
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        val isQueryOrder = LocalShareUtils.getInstance()?.getInt("queryMode")
        if (isQueryOrder == -1){
            Toast.makeText(this, "暂未定", Toast.LENGTH_SHORT).show()
            return true
        }
        var key = ""
        when (isQueryOrder){
            1 -> key = "order"
            2 -> key = "name"
        }
        if (key.isNotEmpty()){
            startActivity(Intent(this, PokemonInfoActivity::class.java).putExtra(key, query))
        }
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean = true
}