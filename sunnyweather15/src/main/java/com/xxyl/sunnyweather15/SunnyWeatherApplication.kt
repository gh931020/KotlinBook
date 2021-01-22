package com.xxyl.sunnyweather15

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

/**
 * author: zhang
 * created on: 2021/1/11 11:03
 * description:
 */
class SunnyWeatherApplication: Application() {
    companion object{
        //彩云天气TOKEN
        const val TOKEN = "GzAQdSXvJ4MzPdrL"

        //提供全局获取Context的方式
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

}