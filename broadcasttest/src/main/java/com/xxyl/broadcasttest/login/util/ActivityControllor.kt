package com.xxyl.broadcasttest.login.util

import android.app.Activity

/**
 * author: zhang
 * created on: 2020/12/23 14:25
 * description:
 */
object ActivityController {
    val activitys = ArrayList<Activity>()

    fun addActivity(activity: Activity){
        activitys.add(activity)
    }

    fun removeActivity(activity: Activity){
        activitys.remove(activity)
    }

    fun finishAll(){
        activitys.forEach {
            it.finish()
        }
        activitys.clear()
    }

}