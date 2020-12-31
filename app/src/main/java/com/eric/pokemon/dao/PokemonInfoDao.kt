package com.eric.pokemon.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.eric.pokemon.entity.PokemonInfo

/**
 * Created by eric on 20-12-8
 */
@Dao
interface PokemonInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(pokemon: PokemonInfo)

    @Query("select * from Pokemon where id = :id")
    fun query(id: Int): PokemonInfo

    @Query("select * from Pokemon where queryName = :name")
    fun query(name: String): PokemonInfo
}