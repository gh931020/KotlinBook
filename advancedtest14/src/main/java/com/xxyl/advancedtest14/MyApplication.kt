package com.xxyl.advancedtest14

import android.app.Application
import android.content.Context

/**
 * author: zhang
 * created on: 2021/1/5 15:25
 * description:创建全局的context
 */
class MyApplication: Application() {

    companion object{
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

}