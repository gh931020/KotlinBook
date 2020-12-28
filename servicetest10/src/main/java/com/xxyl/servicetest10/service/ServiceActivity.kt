package com.xxyl.servicetest10.service

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import com.xxyl.servicetest10.R
import kotlinx.android.synthetic.main.activity_service.*

/**
 * 启动Service的两种方法
 * 1.startService -> onCreate() -> onStartCommand()   stopService -> onDestroy
 * 2.创建mBinder：Binder， 创建 connect= object: ServerConnection -> 获取binder对象 -> 调用binder方法
 *      bindService -> onCreate() -> 可调用binder内方法
 * * 多次调用startService（）方法只会调用一次onCreate（），每次都会调用onStartCommand（）
 * * 既调用startService 又调用 bindService时onCreate也是只调用一次，但是需 stopService和unBindService都调用
 *   onDestroy()才会执行
 * @property binder DownLoaderBinder
 * @property connection <no name provided>
 */
class ServiceActivity : AppCompatActivity() {
    private val TAG = this.javaClass.simpleName
    companion object{
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, ServiceActivity::class.java)
            context.startActivity(starter)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)
        btnStart.setOnClickListener {
            val intent = Intent(this, MyService::class.java)
            startService(intent)
        }
        btnStop.setOnClickListener {
            val intent = Intent(this, MyService::class.java)
            stopService(intent)
        }

        btnBind.setOnClickListener {
            val intent = Intent(this, MyService::class.java)
            //绑定Service
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
        btnUnBind.setOnClickListener {
            unbindService(connection)
        }

        btnIntentService.setOnClickListener {
            //打印主线程的id
            Log.d(TAG, "Main Thread id is ${Thread.currentThread().name}")
            val intent = Intent(this, MyIntentService::class.java)
            startService(intent)
        }
    }
    //bind Service
    private lateinit var binder: MyService.DownLoaderBinder
    private val connection = object: ServiceConnection{
        override fun onServiceDisconnected(name: ComponentName?) {
            Log.d("connection", "onServiceDisconnected: ")
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            binder = service as MyService.DownLoaderBinder
            binder.startDownload()
            binder.getProgress()
        }
    }

}