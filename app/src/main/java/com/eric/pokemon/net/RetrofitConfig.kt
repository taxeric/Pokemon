package com.eric.pokemon.net


/**
 * Created by eric on 20-12-4
 */
interface RetrofitConfig {

    fun baseUrl(): String
    fun grabEnable(): Boolean = true
    fun logEnable(): Boolean = true
}