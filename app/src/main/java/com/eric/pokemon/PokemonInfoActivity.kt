package com.eric.pokemon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import coil.load
import com.eric.pokemon.database.PokemonDatabase
import com.eric.pokemon.entity.PokemonInfo
import com.eric.pokemon.net.RetrofitUtils
import com.eric.pokemon.utils.LogUtils
import com.eric.pokemon.utils.PokemonUtils
import com.eric.pokemon.widget.PkmTypeTextView
import kotlinx.android.synthetic.main.pokemon_details_info.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.http.QueryName
import java.lang.Thread.sleep

class PokemonInfoActivity : AppCompatActivity() {

    private var pokemon: PokemonInfo ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pokemon_details_info)
        setSupportActionBar(tool_bar)

        val bundle = intent.getBundleExtra("pokemon")!!
        checkRoomData(bundle)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun checkRoomData(bundle: Bundle){
        val isOrder = bundle.getBoolean("isOrder")
        val queryContent = bundle.getString("queryContent")
        pokemon = if (isOrder){
            PokemonDatabase.getInstance()?.pokemonDao()?.query(queryContent!!.toInt())
        } else {
            PokemonDatabase.getInstance()?.pokemonDao()?.query(queryContent!!)
        }
        if (pokemon != null){
            showPokemonInfo(pokemon!!, true)
        } else {
            setAndShowPokemonInfo(queryContent!!)
        }
    }

    private fun setAndShowPokemonInfo(order_or_name: String){
        CoroutineScope(Dispatchers.Main).launch {
            val detailsData = withContext(Dispatchers.IO){
                RetrofitUtils.getInstance().get().searchPokemonDetailInfo(order_or_name)
            }
            val baseData = withContext(Dispatchers.IO){
                RetrofitUtils.getInstance().get().describeInfo(order_or_name)
            }
            val pokemon = withContext(Dispatchers.IO){
                val id = baseData.id
                val picUrl = "https://pokeres.bastionbot.org/images/pokemon/$id.png"
                val queryName = baseData.name
                val height = baseData.height
                val weight = baseData.weight
                var targetTypes = ""
                for ((index, types) in baseData.types.withIndex()){
                    targetTypes += PokemonUtils.switchPokemonType(types.type.name)
                    if (index != baseData.types.size - 1){
                        targetTypes += ","
                    }
                }

                var targetIndex = 0
                for ((index, name) in detailsData.names.withIndex()){
                    if (name.language.name == "zh-Hans"){
                        targetIndex = index
                        break
                    }
                }
                val name = detailsData.names[targetIndex].name
                val baseHappiness = detailsData.base_happiness
                val color = detailsData.color.name
                var targetEggGroup = ""
                for ((index, eggGroup) in detailsData.egg_groups.withIndex()){
                    targetEggGroup += PokemonUtils.switchPokemonEggGroup(eggGroup.name)
                    if (index != detailsData.egg_groups.size - 1){
                        targetEggGroup += ","
                    }
                }
                targetIndex = 0
                for ((index, genus) in detailsData.genera.withIndex()){
                    if (genus.language.name == "zh-Hans") {
                        targetIndex = index
                        break
                    }
                }
                val genus = detailsData.genera[targetIndex].genus
                val captureRate = detailsData.capture_rate
                PokemonInfo(id, picUrl, name, queryName, targetTypes, height, weight, genus, targetEggGroup, captureRate, color, baseHappiness)
            }
            PokemonDatabase.getInstance()?.pokemonDao()?.insert(pokemon)
            withContext(Dispatchers.Main){
                showPokemonInfo(pokemon, false)
            }
        }
    }

    private fun showPokemonInfo(pokemon: PokemonInfo, delay: Boolean){
        show_pokemon_avatar.load(pokemon.picUrl)
        val types = pokemon.types.split(",")
        for (i in types){
            val tv = PkmTypeTextView(this)
            tv.text = i
            val params = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            params.setMargins(10, 0, 10, 0)
            tv.layoutParams = params
            show_pokemon_types.addView(tv)
        }
        show_pokemon_egg_groups.text = pokemon.eggGroups
        show_pokemon_genus.text = pokemon.genus
        show_pokemon_name.text = pokemon.name
        show_pokemon_order.text = "#${pokemon.id}"
        show_pokemon_height.text = "${pokemon.height.toFloat() / 10.0} M"
        show_pokemon_weight.text = "${pokemon.weight.toFloat() / 10.0} KG"
        show_pokemon_base_happiness.text = "${pokemon.baseHappiness}/100"
        show_pokemon_base_happiness.baseValue = pokemon.baseHappiness
        show_pokemon_base_happiness.maxValue = 100
        if (delay){
            show_pokemon_base_happiness.delayStartAnimation(20)
        } else {
            show_pokemon_base_happiness.startAnimation()
        }
    }
}