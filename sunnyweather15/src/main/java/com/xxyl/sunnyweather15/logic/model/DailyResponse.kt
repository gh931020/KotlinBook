package com.xxyl.sunnyweather15.logic.model

import com.google.gson.annotations.SerializedName
import java.time.chrono.MinguoChronology
import java.util.*

/**
 * author: zhang
 * created on: 2021/1/21 17:45
 * description: 未来几天天气
 */
data class DailyResponse(val status: String, val result: Result){
    data class Result(val daily: Daily)
    data class Daily(val temperature: List<Temperature>, val skycon: List<Skycon>, @SerializedName("life_index") val lifeIndex: LifeIndex)
    data class Temperature(val max: Float, val min: Float)
    data class Skycon(val value: String, val date: Date)
    data class LifeIndex(val coldRisk:List<LifeDescription>, val carWashing:List<LifeDescription>,
    val ultraviolet: List<LifeDescription>, val dressing: List<LifeDescription>)
    data class LifeDescription(val desc: String)
}