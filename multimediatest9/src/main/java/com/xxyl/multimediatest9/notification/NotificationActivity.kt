package com.xxyl.multimediatest9.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import com.xxyl.multimediatest9.R
import kotlinx.android.synthetic.main.activity_notification.*

class NotificationActivity : AppCompatActivity() {
    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, NotificationActivity::class.java)
            context.startActivity(starter)
        }
    }

    private val channelId = "normal"
    private val channel2Id = "important"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //声明默认等级通知，通知栏静默通知，可提示音
            val channel =
                NotificationChannel(channelId, "Normal", NotificationManager.IMPORTANCE_DEFAULT)
            //声明高优先级通知，弹窗通知
            val channel2 =
                NotificationChannel(channel2Id, "Important", NotificationManager.IMPORTANCE_HIGH)
            //创建通知频道
            manager.createNotificationChannel(channel)
            manager.createNotificationChannel(channel2)
        }
        btnNotification.setOnClickListener {
            val intent = Intent(this, PendingActivity::class.java)
            val pi = PendingIntent.getActivity(this, 0, intent, 0)
            //根据频道id（channelID）归类，判定通知优先级
            val notification = NotificationCompat.Builder(this, channelId)
                .setContentTitle("This is a content title")
//                .setContentText("This is a content text")
//                .setStyle(NotificationCompat.BigTextStyle().bigText("Learn how to build notification" +
//                        ", send and sync data, and use voice actions. Get the official Android IDE and " +
//                        "developer tools to build apps for Android."))//设置长文本完全显示
                .setStyle(
                    NotificationCompat.BigPictureStyle()
                        .bigPicture(BitmapFactory.decodeResource(resources, R.drawable.big_image))
                )//设置显示图片
                .setSmallIcon(R.drawable.small_icon)
                .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.large_icon))
                .setContentIntent(pi)//设置点击跳转
                .setAutoCancel(true)//通知被点击后自动消失
                .build()
            //创建通知消息
            manager.notify(1, notification)
        }

    }
}