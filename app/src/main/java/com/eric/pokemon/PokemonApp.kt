package com.eric.pokemon

import android.app.Application
import com.eric.pokemon.net.RetrofitConfig

/**
 * Created by eric on 20-12-4
 */
class PokemonApp: Application(), RetrofitConfig{

    override fun onCreate() {
        super.onCreate()
    }

    override fun baseUrl(): String = "https://pokeapi.co/api/v2/"
}

