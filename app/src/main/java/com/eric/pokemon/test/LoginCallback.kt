package com.eric.pokemon.test

/**
 * Created by eric on 20-12-4
 */
interface LoginCallback {

    fun success(msg: String)
    fun failed()
}