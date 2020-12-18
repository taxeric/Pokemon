package com.eric.pokemon.test

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.eric.pokemon.R
import com.eric.pokemon.databinding.ActivityMainBinding
import com.eric.pokemon.utils.LogUtils
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import java.net.URL
import kotlin.random.Random

class MainActivity : AppCompatActivity(), LoginCallback {

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this,
            R.layout.activity_main
        )
        binding.viewMode = ViewModelProvider(this,
            LoginViewModelFactory()
        ).get(LoginViewModel::class.java)
        binding.lifecycleOwner = this

        change_txt.setOnClickListener {
            getImage(Random.nextInt(891))
        }
    }

    private fun getImage(page: Int) {
        val url = "https://pokeres.bastionbot.org/images/pokemon/$page.png"
        CoroutineScope(Dispatchers.Main).launch {
            val bitmap = withContext(Dispatchers.IO){
                val inputStream = URL(url).openConnection().getInputStream()
                BitmapFactory.decodeStream(inputStream)
            }
            show_stwb.setImageBitmap(bitmap)
        }
    }

    override fun success(msg: String) {
        LogUtils.i("success $msg")
    }

    override fun failed() {
        LogUtils.i("failed")
    }
}