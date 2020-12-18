package com.eric.pokemon.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load

/**
 * Created by eric on 20-12-7
 */
@BindingAdapter("app:bindImg")
fun bindAvatar(imageView: ImageView, url: String){
    imageView.load(url)
}

