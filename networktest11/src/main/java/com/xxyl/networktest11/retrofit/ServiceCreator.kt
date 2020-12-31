package com.xxyl.networktest11.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * author: zhang
 * created on: 2020/12/30 16:39
 * description:
 */
object ServiceCreator {
    private const val BASE_URL = "http://10.0.2.2/"
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()

    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)

    inline fun <reified T>create():T = create(T::class.java)

}