package com.xxyl.multimediatest9.notification

import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.xxyl.multimediatest9.R

/**
 * author: zhang
 * created on: 2020/12/25 14:12
 * description:
 */
class PendingActivity: AppCompatActivity() {
    companion object{
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, PendingActivity::class.java)
            context.startActivity(starter)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pending)
        //取消通知栏提示
//        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//        manager.cancel(1)//同manager.notify（1，notification）
    }
}