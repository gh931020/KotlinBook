package com.xxyl.sunnyweather15.logic.network

import com.xxyl.sunnyweather15.logic.model.Place
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await
import java.lang.RuntimeException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * author: zhang
 * created on: 2021/1/11 11:26
 * description:
 */
object SunnyWeatherNetWork {

    private val placeService = ServiceCreator.create<PlaceService>()

    private val weatherService = ServiceCreator.create<WeatherService>()
   //获取位置信息
    suspend fun searchPlaces(query: String) = placeService.searchPlaces(query).await()
    //获取实时天气
    suspend fun getRealtimeWeather(lng: String, lat: String) = weatherService.getRealtimeWeather(lng, lat).await()
   //获取未来几天天气
    suspend fun getDailyWeather(lng: String, lat: String) = weatherService.getDailyWeather(lng, lat).await()

    private suspend fun <T> Call<T>.await(): T{
        return suspendCoroutine {continuation ->
            enqueue(object :Callback<T>{
                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }

                override fun onResponse(call: Call<T>, response: Response<T>) {
                    //请求成功获取请求body
                    val body = response.body()
                    //判断body是否为空,不为空返回结果,否则抛出异常
                    if (body != null) continuation.resume(body)
                    else continuation.resumeWithException(RuntimeException("response body is null"))
                }
            })
        }
    }
}