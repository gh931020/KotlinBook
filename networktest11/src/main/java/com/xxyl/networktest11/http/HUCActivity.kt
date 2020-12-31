package com.xxyl.networktest11.http

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.xxyl.networktest11.R
import kotlinx.android.synthetic.main.activity_huc.*
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

/**
 * author: zhang
 * created on: 2020/12/29 14:10
 * description:Http URL Connection
 */
class HUCActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_huc)
        btnRequest.setOnClickListener {
            sendRequestWithHttpURLConnection()
        }
        btnOkRequest.setOnClickListener {
            sendRequestWithOkHttp()
        }
    }

    private fun sendRequestWithOkHttp() {
        thread {
            try {
                val okHttpClient = OkHttpClient()
                val request = Request.Builder()
                    .url("https://www.baidu.com/")
                    .build()
                val response = okHttpClient.newCall(request).execute()
                val responseData = response.body?.string()
                if (responseData != null) {
                    showResponse(responseData)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun sendRequestWithHttpURLConnection() {
        thread {
            var connection: HttpURLConnection? = null
            try {
                val response = StringBuilder()
                val url = URL("https://www.baidu.com/")
                //通过url获取connection对象
                connection = url.openConnection() as HttpURLConnection
                //设置链接超时时间
                connection.connectTimeout = 8000
                //设置读取超时时间
                connection.readTimeout = 8000
                val input = connection.inputStream
                //对获取到的输入流进行读取
                val reader = BufferedReader(InputStreamReader(input))
                //.use自动关闭方法块中的流资源
                reader.use {
                    it.forEachLine {
                        response.append(it)
                    }
                }
                showResponse(response.toString())
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                connection?.disconnect()
            }
        }
    }

    private fun showResponse(response: String) {
        runOnUiThread {
            //在主线程中更新text view
            tvResponse.text = response
        }
    }
}