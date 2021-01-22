package com.xxyl.sunnyweather15.logic.network

import com.xxyl.sunnyweather15.SunnyWeatherApplication
import com.xxyl.sunnyweather15.logic.model.DailyResponse
import com.xxyl.sunnyweather15.logic.model.RealtimeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * author: zhang
 * created on: 2021/1/22 10:01
 * description:
 */
interface WeatherService {

    @GET("v2.5/${SunnyWeatherApplication.TOKEN}/{lng},{lat}/realtime.json")
    fun getRealtimeWeather(@Path("lng") lng: String, @Path("lat") lat: String): Call<RealtimeResponse>

    @GET("v2.5/${SunnyWeatherApplication.TOKEN}/{lng},{lat}/daily.json")
    fun getDailyWeather(@Path("lng") lng: String, @Path("lat") lat: String): Call<DailyResponse>
}