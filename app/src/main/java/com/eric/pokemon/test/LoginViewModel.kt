package com.eric.pokemon.test

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eric.pokemon.test.LoginEntity
import com.eric.pokemon.utils.LogUtils
import java.lang.Thread.sleep
import kotlin.concurrent.thread

/**
 * Created by eric on 20-12-4
 */
class LoginViewModel : ViewModel() {

    var user = MutableLiveData<LoginEntity>()

    init {
        user.value = LoginEntity("Eric", "123456")

        thread {
            sleep(2000)
            user.value?.username = "Chad"
            user.value?.password = "654321"
            user.postValue(user.value)
        }
    }

    override fun onCleared() {
        LogUtils.i("on cleared")
    }
}