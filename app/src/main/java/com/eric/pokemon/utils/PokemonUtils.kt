package com.eric.pokemon.utils

import android.graphics.Color

/**
 * Created by eric on 20-12-7
 */
class PokemonUtils {

    companion object{

        private val COLOR_BLACK = Color.parseColor("#1A1C25")
        private val COLOR_BLUE = Color.parseColor("#2085BE")
        private val COLOR_BROWN = Color.parseColor("#A37F5C")
        private val COLOR_GRAY = Color.parseColor("#BFB8B2")
        private val COLOR_GREEN = Color.parseColor("#5FC69C")
        private val COLOR_PINK = Color.parseColor("#F4A4B4")
        private val COLOR_PURPLE = Color.parseColor("#897295")
        private val COLOR_RED = Color.parseColor("#C7544A")
        private val COLOR_WHITE = Color.parseColor("#F4F6F8")
        private val COLOR_YELLOW = Color.parseColor("#F9EE7A")

        fun switchPokemonBaseColor(colorName: String): Int{
            return when(colorName){
                "black"      -> COLOR_BLACK
                "blue"       -> COLOR_BLUE
                "brown"      -> COLOR_BROWN
                "gray"       -> COLOR_GRAY
                "green"      -> COLOR_GREEN
                "pink"       -> COLOR_PINK
                "purple"     -> COLOR_PURPLE
                "red"        -> COLOR_RED
                "white"      -> COLOR_WHITE
                "yellow"     -> COLOR_YELLOW
                else         -> COLOR_WHITE
            }
        }

        fun switchPokemonType(type: String): String{
            return when(type){
                "normal"     -> "一般"
                "fighting"   -> "格斗"
                "flying"     -> "飞行"
                "poison"     -> "毒"
                "ground"     -> "地面"
                "rock"       -> "岩石"
                "bug"        -> "虫"
                "ghost"      -> "幽灵"
                "steel"      -> "钢"
                "fire"       -> "火"
                "water"      -> "水"
                "grass"      -> "草"
                "electric"   -> "电"
                "psychic"    -> "超能"
                "ice"        -> "冰"
                "dragon"     -> "龙"
                "dark"       -> "恶"
                "fairy"      -> "妖精"
                else         -> "未知"
            }
        }

        fun switchPokemonTypeColor(type: String): Int{
            return when(type){
                "一般"        -> Color.parseColor("#8F989F")
                "格斗"        -> Color.parseColor("#D9366A")
                "飞行"        -> Color.parseColor("#89A7DA")
                "毒"          -> Color.parseColor("#AF67C3")
                "地面"        -> Color.parseColor("#E2744B")
                "岩石"        -> Color.parseColor("#C6B68D")
                "虫"          -> Color.parseColor("#85C041")
                "幽灵"        -> Color.parseColor("#4F69A8")
                "钢"          -> Color.parseColor("#4E8D9F")
                "火"          -> Color.parseColor("#FE985D")
                "水"          -> Color.parseColor("#3F90D1")
                "草"          -> Color.parseColor("#44BC61")
                "电"          -> Color.parseColor("#F7D050")
                "超能"        -> Color.parseColor("#FE6B7A")
                "冰"          -> Color.parseColor("#59CFBE")
                "龙"          -> Color.parseColor("#006EBD")
                "恶"          -> Color.parseColor("#5B5364")
                "妖精"        -> Color.parseColor("#F58AE2")
                else         -> Color.parseColor("#3C695E")
            }
        }

        fun switchPokemonEggGroup(type: String): String{
            return when(type){
                "monster"        -> "怪兽"
                "water1"         -> "水1"
                "bug"            -> "虫"
                "flying"         -> "飞行"
                "ground"         -> "陆上"
                "fairy"          -> "妖精"
                "plant"          -> "植物"
                "humanshape"     -> "人型"
                "water3"         -> "水3"
                "mineral"        -> "矿物"
                "indeterminate"  -> "不定形"
                "water2"         -> "水2"
                "ditto"          -> "同上"
                "dragon"         -> "龙"
                "no-eggs"        -> "未知分组"
                else             -> "未知"
            }
        }
    }
}