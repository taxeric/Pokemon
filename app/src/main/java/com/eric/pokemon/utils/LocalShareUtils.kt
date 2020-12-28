package com.eric.pokemon.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE

/**
 * Created by eric on 20-12-28
 */
class LocalShareUtils private constructor(
    context: Context
){

    private val sharedPreferences = context.getSharedPreferences("LocalShare", MODE_PRIVATE)

    fun put(key: String, any: Any){
        when (any){
            is String -> sharedPreferences.edit().putString(key, any).apply()
            is Boolean -> sharedPreferences.edit().putBoolean(key, any).apply()
            is Int -> sharedPreferences.edit().putInt(key, any).apply()
            is Set<*> -> sharedPreferences.edit().putStringSet(key, any as Set<String>).apply()
        }
    }

    fun getString(key: String): String{
        return sharedPreferences.getString(key, "")!!
    }

    fun getBoolean(key: String): Boolean{
        return sharedPreferences.getBoolean(key, false)
    }

    fun getInt(key: String): Int{
        return sharedPreferences.getInt(key, -1)
    }

    companion object{
        private var INSTANCE: LocalShareUtils ?= null

        fun init(context: Context){
            if (INSTANCE == null){
                synchronized(LocalShareUtils::class.java){
                    if (INSTANCE == null){
                        INSTANCE = LocalShareUtils(context)
                    }
                }
            }
        }

        fun getInstance(): LocalShareUtils?{
            return INSTANCE
        }
    }
}