package com.xxyl.datasavetest7

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_sp.*

class SpActivity : AppCompatActivity() {
    companion object{
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, SpActivity::class.java)
            context.startActivity(starter)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sp)
        btnSave.setOnClickListener {
            val editor = getSharedPreferences("data",Context.MODE_PRIVATE).edit()
            editor.putString("name", "Tome")
            editor.putInt("age", 18)
            editor.putBoolean("married", false)
            editor.apply()
        }
    }
}