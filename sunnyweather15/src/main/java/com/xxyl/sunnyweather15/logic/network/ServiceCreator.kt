package com.xxyl.sunnyweather15.logic.network

import android.util.Log
import com.xxyl.sunnyweather15.BuildConfig
import okhttp3.*
import okhttp3.internal.connection.ConnectInterceptor
import okio.Buffer
import okio.IOException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URLDecoder


/**
 * author: zhang
 * created on: 2021/1/11 11:19
 * description:
 */
object ServiceCreator {
    private const val BASE_URL = "http://api.caiyunapp.com/"

    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(NetWorkInterceptor())
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    //根据泛型创建服务接口类
    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)

    //利用泛型实化,返回对应的T,简化服务创建代码
    inline fun <reified T> create(): T = create(T::class.java)
}

class NetWorkInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        if (BuildConfig.DEBUG) {
            val methodName: String = request.method
            if (methodName.equals("GET", ignoreCase = true)) {
                Log.i(TAG, "-url--" + methodName + "--" + request.url
                )
            } else if (methodName.equals("POST", ignoreCase = true)) {
                val mRequestBody: RequestBody? = request.body
                if (mRequestBody != null) {
                    val msg = "-url--" + methodName + "--" + request.url
                    val content: String
                    content = if (msg.contains("uploadFile")) {
                        "--上传文件内容--"
                    } else {
                        getParam(mRequestBody)
                    }
                    Log.i(TAG, msg + content)
                }
            }
        }
        return chain.proceed(request)
    }

    /**
     * 读取参数
     *
     * @param requestBody
     * @return
     */
    private fun getParam(requestBody: RequestBody): String {
        val buffer = Buffer()
        var logParm: String
        try {
            requestBody.writeTo(buffer)
            logParm = buffer.readUtf8()
            logParm = URLDecoder.decode(logParm, "utf-8")
        } catch (e: IOException) {
            e.printStackTrace()
            return ""
        }
        return logParm
    }
    companion object {
        var TAG = "NetWorkInterceptor"
    }
}


