package com.xxyl.networktest11.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.xxyl.networktest11.R
import kotlinx.android.synthetic.main.activity_retrofit.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitActivity : AppCompatActivity() {
    private val TAG = this.javaClass.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)
        btnGetAppData.setOnClickListener {
            Log.d(TAG, "onCreate: ")
//            val retrofit = Retrofit.Builder()
//                .baseUrl("http://10.0.2.2/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//            val appService = retrofit.create(AppService::class.java)
            //使用内联函数泛型实化简化创建操作
            val appService = ServiceCreator.create<AppService>()
            appService.getAppData().enqueue(object :Callback<List<App>>{
                override fun onFailure(call: Call<List<App>>, t: Throwable) {
                    Log.d(TAG, "onFailure: ")
                }

                override fun onResponse(call: Call<List<App>>, response: Response<List<App>>) {
                    val list = response.body()
                    list?.forEach {
                        Log.d(TAG, "Retrofit -> id: ${it.id}, name: ${it.name}, version: ${it.version}")
                    }
                }

            })
        }
    }
}