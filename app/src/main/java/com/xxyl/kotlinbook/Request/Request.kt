package com.xxyl.kotlinbook.Request

import android.util.Log
import com.google.gson.Gson
import com.xxyl.kotlinbook.bean.ForecastResult
import java.net.URL

/**
 * author: zhang
 * created on: 2020/9/17 17:57
 * description:
 */
public class Request(val location: String) {
    /**
     * 括号中包裹的内容类似于java中的静态属性,常量或者函数
     */
    companion object{
        private val KEY = "b38b7c040cea4f1e8362657efdbabba6"
        private val URL = "https://devapi.heweather.net/v7/weather/3d"
        private val COMPLETE_URL = "$URL?key=$KEY&location="
    }

    fun execute(): ForecastResult{
        val forecastJsonStr = java.net.URL(COMPLETE_URL + location).readText()
        return Gson().fromJson(forecastJsonStr, ForecastResult::class.java)
    }

//    public fun run(){
//        val forecastJsonStr = URL(COMPLETE_URL + location).readText()
//        Log.d(javaClass.simpleName,forecastJsonStr)
//    }
}