package com.xxyl.networktest11.retrofit

import android.provider.ContactsContract
import androidx.annotation.IdRes
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

/**
 * author: zhang
 * created on: 2020/12/30 11:39
 * description:
 */
interface AppService {
    @GET("get_data.json")
    fun getAppData(): Call<List<App>>
}

interface ExampleService {
    @GET("{page}/get_data.json")
    fun getData(@Path("page") page: Int): Call<App>

    //http://example.com/get_data.json?u=<user>&t=<token>
    @Headers("User-Agent: okhttp", "Catche-Control: max-age=0")
    @GET("get_data.json")
    fun getData(@Query("u") user: String, @Query("t") token: String): Call<App>

    //http://example.com/data/<id>
    @DELETE("data/{id}")
    fun deleteData(@Path("id") id: String): Call<ResponseBody>

    @POST("data/create")
    fun createData(@Body data: App): Call<ResponseBody>

    @GET("get_data.json")
    fun getDataWithHeader(
        @Header("User-agent") userAgent: String,
        @Header("Cache-Control") catcheControl: String
    ): Call<App>

}