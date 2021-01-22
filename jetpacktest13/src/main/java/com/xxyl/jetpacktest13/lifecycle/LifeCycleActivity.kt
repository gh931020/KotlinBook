package com.xxyl.jetpacktest13.lifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.xxyl.jetpacktest13.R

class LifeCycleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_life_cycle)
    }
}