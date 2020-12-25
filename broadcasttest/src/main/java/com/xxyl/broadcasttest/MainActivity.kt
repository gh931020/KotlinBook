package com.xxyl.broadcasttest

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.xxyl.broadcasttest.custombroad.CustomBroadcastActivity
import com.xxyl.broadcasttest.login.LoginActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var timeChangeReceiver: TimeChangeReceiver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intentFilter = IntentFilter()
        intentFilter.addAction("android.intent.action.TIME_TICK")
        timeChangeReceiver = TimeChangeReceiver()
//        registerReceiver(timeChangeReceiver, intentFilter)
        btn1.setOnClickListener {
            CustomBroadcastActivity.start(this)
        }
        btn2.setOnClickListener {
            LoginActivity.start(this)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
//        unregisterReceiver(timeChangeReceiver)
    }

    inner class TimeChangeReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            Toast.makeText(context, "Times has changed", Toast.LENGTH_SHORT).show()
        }
    }
}
