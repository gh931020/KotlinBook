package com.xxyl.sunnyweather15.logic.model

import com.google.gson.annotations.SerializedName

/**
 * author: zhang
 * created on: 2021/1/21 17:33
 * description: 实时天气bean
 */

data class RealtimeResponse(val status:String, val result:Result){
    data class Result(val realtime: Realtime)
    data class Realtime(val temperature: Float, val skycon: String,@SerializedName("air_quality") val airQuality: AirQuality)
    data class AirQuality(val aqi: AQI)
    data class AQI(val chn: Float)
}