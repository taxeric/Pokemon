package com.eric.pokemon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import coil.load
import com.eric.pokemon.database.PokemonDatabase
import com.eric.pokemon.entity.PokemonInfo
import com.eric.pokemon.net.RetrofitUtils
import com.eric.pokemon.utils.LogUtils
import com.eric.pokemon.utils.PokemonUtils
import kotlinx.android.synthetic.main.pokemon_details_info.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PokemonInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pokemon_details_info)
//        val binding = DataBindingUtil.setContentView<PokemonDetailsInfoBinding>(
//            this, R.layout.pokemon_details_info)
        val order = intent.getIntExtra("order", 1)
        checkRoomData(order)
//        setPokemonInfo(order)
    }

    private fun checkRoomData(order: Int){
        val pokemon = PokemonDatabase.getInstance()?.pokemonDao()?.query(order)
        if (pokemon == null){
            setAndShowPokemonInfo(order)
        } else {
            showPokemonInfo(pokemon)
        }
    }

    private fun setAndShowPokemonInfo(order: Int){
        CoroutineScope(Dispatchers.Main).launch {
            val detailsData = withContext(Dispatchers.IO){
                RetrofitUtils.getInstance().get().searchPokemonDetailInfo(order.toString())
            }
            val baseData = withContext(Dispatchers.IO){
                RetrofitUtils.getInstance().get().describeInfo(order.toString())
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
                showPokemonInfo(pokemon)
            }
        }
    }

    private fun showPokemonInfo(pokemon: PokemonInfo){
        show_pokemon_avatar.load(pokemon.picUrl)
        show_pokemon_types.text = pokemon.types
        show_pokemon_base_happiness.text = "${pokemon.baseHappiness}%"
        show_pokemon_capture_rate.text = "${pokemon.captureRate}%"
        show_pokemon_egg_groups.text = pokemon.eggGroups
        show_pokemon_genus.text = pokemon.genus
        show_pokemon_name.text = pokemon.name
        show_pokemon_order.text = pokemon.id.toString()
    }
}