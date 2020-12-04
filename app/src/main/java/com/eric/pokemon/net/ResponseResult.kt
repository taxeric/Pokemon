package com.eric.pokemon.net

import com.eric.pokemon.utils.LogUtils


/**
 * Created by eric on 20-12-4
 */
interface ResponseResult<T> {

    fun onSuccess(t: T)
    fun onFail(code: Int, msg: String){
        LogUtils.e("code = $code & msg = $msg")
    }
}