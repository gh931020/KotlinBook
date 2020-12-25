package com.xxyl.broadcasttest.custombroad

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.xxyl.broadcasttest.R
import kotlinx.android.synthetic.main.activity_custom_broadcast.*

class CustomBroadcastActivity : AppCompatActivity() {
    companion object{
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, CustomBroadcastActivity::class.java)
            context.startActivity(starter)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_broadcast)
        //发送标准广播
        btnStandard.setOnClickListener {
            val intent = Intent("com.xxyl.broadcast.CUSTOM_BROADCAST")
            intent.setPackage(packageName)
            sendBroadcast(intent)
        }
        //发送有序广播
        btnOrdered.setOnClickListener {
            val intent = Intent("com.xxyl.broadcast.CUSTOM_BROADCAST")
            intent.setPackage(packageName)
            sendOrderedBroadcast(intent, null)
        }
    }
}