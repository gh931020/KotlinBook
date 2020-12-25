package com.zzj.uiwidgettest

import android.app.Application

/**
 * author: zhang
 * created on: 2020/12/21 14:02
 * description:
 */
class UiApplication: Application() {

    companion object{
       lateinit var application: UiApplication
    }

    override fun onCreate() {
        super.onCreate()
        application = this
    }

}