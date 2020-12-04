package com.eric.pokemon.utils

import android.util.Log

/**
 * Created by eric on 20-12-4
 */
object LogUtils {

    private const val TAG = "PokemonLog"

    fun i(msg: String){
        Log.i(TAG, msg)
    }

    fun e(msg: String){
        Log.e(TAG, msg)
    }

    fun v(msg: String){
        Log.v(TAG, msg)
    }
}