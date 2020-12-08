package com.eric.pokemon.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by eric on 20-12-7
 */
@Entity(tableName = "Pokemon")
data class PokemonInfo(
    @PrimaryKey
    @NonNull
    val id: Int,//编号
    @ColumnInfo val picUrl: String,     //图片地址
    @ColumnInfo val name: String,       //名字
    @ColumnInfo val queryName: String,  //用于查询的名字
    @ColumnInfo val types: String,      //属性
    @ColumnInfo val height: Int,        //身高
    @ColumnInfo val weight: Int,        //重量
    @ColumnInfo val genus: String,      //类别，比如妙蛙种子是种子宝可梦
    @ColumnInfo val eggGroups: String,  //蛋组
    @ColumnInfo val captureRate: Int,   //捕获率
    @ColumnInfo val baseColor: String,  //基础颜色
    @ColumnInfo val baseHappiness: Int  //基础亲密度
) {
    override fun toString(): String {
        return "PokemonInfo(id=$id, picUrl='$picUrl', name='$name', queryName='$queryName', types='$types', height=$height, weight=$weight, genus='$genus', eggGroups='$eggGroups', captureRate=$captureRate, baseColor='$baseColor', baseHappiness=$baseHappiness)"
    }
}

