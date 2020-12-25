package com.xxyl.datasavetest7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Space
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnFile.setOnClickListener {
            FileActivity.start(this)
        }
        btnSp.setOnClickListener {
            SpActivity.start(this)
        }
        btnDB.setOnClickListener {
            DbActivity.start(this)
        }
    }
}
