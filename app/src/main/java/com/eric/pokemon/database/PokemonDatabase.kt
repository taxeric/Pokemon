package com.eric.pokemon.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.eric.pokemon.dao.PokemonInfoDao
import com.eric.pokemon.entity.PokemonInfo

/**
 * Created by eric on 20-12-8
 */
@Database(entities = [PokemonInfo::class], version = 1)
abstract class PokemonDatabase: RoomDatabase() {

    abstract fun pokemonDao(): PokemonInfoDao

    companion object{

        private var INSTANCE: PokemonDatabase ?= null

        fun init(context: Context){
            if (INSTANCE == null){
                synchronized(PokemonDatabase::class.java){
                    if (INSTANCE == null){
                        INSTANCE = Room.databaseBuilder(
                            context, PokemonDatabase::class.java, "pokemon.db"
                        ).allowMainThreadQueries().build()
                    }
                }
            }
        }

        fun getInstance(): PokemonDatabase ?= INSTANCE
    }
}