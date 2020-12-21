package com.eric.pokemon.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.LinearLayout
import com.eric.pokemon.R
import com.eric.pokemon.widget.PkmTypeTextView
import kotlinx.android.synthetic.main.pokemon_details_info.*
import kotlinx.android.synthetic.main.test_layout.*
import kotlin.random.Random
import kotlin.random.nextInt

class TestActivity : AppCompatActivity() {

    private val types = arrayOf(
        "一般", "格斗", "飞行", "毒", "地面", "岩石",
        "虫", "幽灵", "钢", "火", "水", "草",
        "电", "超能", "冰", "龙", "恶", "妖精", "未知"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test_layout)
        show()
        pkm_tv.text = types[Random.nextInt(types.size - 1)]
        switch_color.setOnClickListener {
            pkm_tv.text = types[Random.nextInt(types.size - 1)]
            progress_tv.startAnimation()
            progress_tv2.startAnimation()
            progress_tv3.startAnimation()
        }
    }

    override fun onResume() {
        super.onResume()
    }

    private fun show(){
        progress_tv.run {
            baseValue = Random.nextInt(200)
            maxValue = 200
            setAnimDuration(2)
            text = "$baseValue/$maxValue"
        }
        progress_tv2.run {
            baseValue = Random.nextInt(150)
            maxValue = 150
            setAnimDuration(2)
            text = "$baseValue/$maxValue"
        }
        progress_tv3.run {
            baseValue = Random.nextInt(100)
            maxValue = 100
            setAnimDuration(2)
            text = "$baseValue/$maxValue"
        }
    }
}