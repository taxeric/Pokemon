package com.eric.pokemon

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.preference.PreferenceManager
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
    private lateinit var preferenceManager: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pokemon_rv)
        setSupportActionBar(tool_bar)
        updatePreferenceManager()
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
            startActivity(Intent(this, SettingActivity::class.java))
//            LogUtils.i("click menu")
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

    private fun updatePreferenceManager(){
        preferenceManager = PreferenceManager.getDefaultSharedPreferences(this)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query.isNullOrEmpty()){
            Toast.makeText(this, "输入有误", Toast.LENGTH_SHORT).show()
            return true
        }
        val bundle = Bundle()
        bundle.putBoolean("isOrder", query.matches("[0-9]+".toRegex()))
        bundle.putString("queryContent", query)
        startActivity(Intent(this, PokemonInfoActivity::class.java).putExtra("pokemon", bundle))
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean = true
}
