package com.eric.pokemon

import android.app.Application
import com.eric.pokemon.database.PokemonDatabase
import com.eric.pokemon.net.RetrofitConfig
import com.eric.pokemon.net.RetrofitUtils

/**
 * Created by eric on 20-12-4
 */
class PokemonApp: Application(), RetrofitConfig{

    override fun onCreate() {
        super.onCreate()
        RetrofitUtils.init(this)
        PokemonDatabase.init(this)
    }

    override fun baseUrl(): String = "https://pokeapi.co/api/v2/"
}

