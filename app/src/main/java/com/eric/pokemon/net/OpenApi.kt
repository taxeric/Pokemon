package com.eric.pokemon.net

import com.eric.pokemon.entity.PokemonBaseInfo
import com.eric.pokemon.entity.PokemonDetailInfo
import com.eric.pokemon.entity.PokemonList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by eric on 20-12-4
 */
interface OpenApi {

    @GET("pokemon")
    suspend fun fetchPokemonList(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): PokemonList

    /**
     * 该接口可获得：
     * 基础亲密度
     * 基础颜色
     * 名字
     * 蛋组
     * 性别比例--暂未添加
     * 类别
     * 捕获率
     */
    @GET("pokemon-species/{path}/")
    suspend fun searchPokemonDetailInfo(
        @Path("path") baseName_or_id: String
    ): PokemonDetailInfo

    /**
     * 该接口可获得：
     * id
     * 用于查询的名字
     * 高度
     * 重量
     * 属性
     */
    @GET("pokemon/{path}/")
    suspend fun describeInfo(
        @Path("path") baseName_or_id: String
    ): PokemonBaseInfo
}