package com.eric.pokemon.utils

import android.graphics.Color

/**
 * Created by eric on 20-12-7
 */
class ColorUtils {

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

        fun switchPokemonColor(colorName: String): Int{
            return when(colorName){
                "black" -> COLOR_BLACK
                "blue" -> COLOR_BLUE
                "brown" -> COLOR_BROWN
                "gray" -> COLOR_GRAY
                "green" -> COLOR_GREEN
                "pink" -> COLOR_PINK
                "purple" -> COLOR_PURPLE
                "red" -> COLOR_RED
                "white" -> COLOR_WHITE
                "yellow" -> COLOR_YELLOW
                else -> COLOR_WHITE
            }
        }
    }
}