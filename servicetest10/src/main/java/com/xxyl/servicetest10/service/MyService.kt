package com.xxyl.servicetest10.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.xxyl.servicetest10.R

class MyService : Service() {
    private val TAG = this.javaClass.simpleName

    private val binder = DownLoaderBinder()

    class DownLoaderBinder : Binder(){
        private val TAG = this.javaClass.simpleName
        fun startDownload(){
            Log.d(TAG, "startDownload: ")
        }

        fun getProgress(): Int{
            Log.d(TAG, "getProgress: ")
            return 0
        }
    }

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    override fun onCreate() {
        super.onCreate()
        //只有第一次startService时会被调用
        Log.d(TAG, "onCreate: ")
        //创建前台Service，使用前台服务必须在AndroidManifest中声明权限
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel("my_service","前台Service通知",NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel)
        }
        val intent =Intent(this, ServiceActivity::class.java)
        val pi = PendingIntent.getActivity(this, 0, intent,0)
        val notification = NotificationCompat.Builder(this, "my_service")
            .setContentTitle("This is content title")
            .setContentText("This is content text!!!!")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.ic_launcher_foreground))
            .setContentIntent(pi)
            .build()
        //启动前台服务
        startForeground(1, notification)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        //每次startService都会被调用
        Log.d(TAG, "onStartCommand: ")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
//        stopService时调用
        Log.d(TAG, "onDestroy: ")
    }
}
