package com.eric.pokemon

import android.app.Application
import com.eric.pokemon.database.PokemonDatabase
import com.eric.pokemon.net.RetrofitConfig
import com.eric.pokemon.net.RetrofitUtils
import com.eric.pokemon.utils.LocalShareUtils

/**
 * Created by eric on 20-12-4
 */
class PokemonApp: Application(), RetrofitConfig{

    override fun onCreate() {
        super.onCreate()
        RetrofitUtils.init(this)
        PokemonDatabase.init(this)
        LocalShareUtils.init(this)
    }

    override fun baseUrl(): String = "https://pokeapi.co/api/v2/"
}

