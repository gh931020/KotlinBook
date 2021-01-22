package com.xxyl.sunnyweather15.logic.model

/**
 * author: zhang
 * created on: 2021/1/22 9:24
 * description: 天气类,包含实时天气及未来几天天气
 */

data class Weather(val realtime: RealtimeResponse.Realtime, val daily: DailyResponse.Daily)