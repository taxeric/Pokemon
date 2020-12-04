package com.eric.pokemon

import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.eric.pokemon.databinding.ActivityMainBinding
import com.eric.pokemon.utils.LogUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), LoginCallback {

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.viewMode = ViewModelProvider(this, LoginViewModelFactory()).get(LoginViewModel::class.java)
        binding.lifecycleOwner = this

//        loginViewModel = LoginViewModel()

//        val unmObserver = Observer<String> { t -> success(t) }
//        loginViewModel.username.observe(this, unmObserver)

//        change_txt.setOnClickListener {
//            loginViewModel.username.value = edit_unm.text.toString()
//        }
    }

    override fun success(msg: String) {
        LogUtils.i("success $msg")
    }

    override fun failed() {
        LogUtils.i("failed")
    }
}