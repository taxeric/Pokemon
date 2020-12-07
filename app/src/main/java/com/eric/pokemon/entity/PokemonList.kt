package com.eric.pokemon.entity

/**
 * Created by eric on 20-12-4
 */
data class PokemonList(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PokemonListData>
)

data class PokemonListData(
    val name: String,
    val url: String
) {

    fun getImageUrl(): String{
        return "https://pokeres.bastionbot.org/images/pokemon/$url.png"
    }

//    fun getImageUrl(): String {
//        val index = url.split("/".toRegex()).dropLast(1).last()
//        return "https://pokeres.bastionbot.org/images/pokemon/$index.png"
//    }

    override fun toString(): String {
        return "ListingData(name='$name', url='$url')"
    }
}