package com.xxyl.sunnyweather15.logic.network

import com.xxyl.sunnyweather15.SunnyWeatherApplication
import com.xxyl.sunnyweather15.logic.model.PlaceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * author: zhang
 * created on: 2021/1/11 11:13
 * description: 地址信息接口
 */
interface PlaceService {
    /***
     * 根据城市名获取信息
     * @param query String
     * @return Call<PlaceResponse>
     */
    @GET("v2/place?token=${SunnyWeatherApplication.TOKEN}&lang=zh_CN")
    fun searchPlaces(@Query("query") query: String): Call<PlaceResponse>
}