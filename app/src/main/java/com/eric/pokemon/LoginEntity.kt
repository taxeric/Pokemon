package com.eric.pokemon

import androidx.databinding.BaseObservable

/**
 * Created by eric on 20-12-4
 */
data class LoginEntity(
    var username: String,
    var password: String
): BaseObservable(){

    constructor(): this("", "")
}
