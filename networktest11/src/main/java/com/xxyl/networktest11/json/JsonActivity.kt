package com.xxyl.networktest11.json

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.xxyl.networktest11.R
import kotlinx.android.synthetic.main.activity_json.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONArray
import java.lang.Exception

class JsonActivity : AppCompatActivity() {
    private var response: String?= null
    lateinit var responseData: Response
    private val TAG = this.javaClass.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_json)
        GlobalScope.launch(Dispatchers.Main) {
            response = getResponseByOkHttp()
            tvResponse.text = response
        }
        btnJsonObject.setOnClickListener {
            response?.let { it1 -> parseJSONWithJSONObject(it1) }
        }
        btnGson.setOnClickListener {
            response?.let { it1 -> parseJSONWithGSON(it1) }
        }
    }

    private fun parseJSONWithGSON(json: String) {
        val gson = Gson()
        val typeOf = object :TypeToken<List<Book>>(){}.type
        val books = gson.fromJson<List<Book>>(json, typeOf)
        books.forEach {
            Log.d(TAG, "byGson -> id:${it.id}, name: ${it.name}, version: ${it.version} ")
        }
    }

    private fun parseJSONWithJSONObject(json: String) {
        try {
            val jsonArray = JSONArray(json)
            for (i in 0 until jsonArray.length()){
                val jsonObject = jsonArray.getJSONObject(i)
                val id = jsonObject.getString("id")
                val version = jsonObject.getString("version")
                val name = jsonObject.getString("name")
                Log.d(TAG, "by JSONObject -> id:$id, name: $name, version: $version ")
            }
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    private suspend fun getResponseByOkHttp(): String {
        withContext(Dispatchers.IO){
            try {
                val client = OkHttpClient()
                val request = Request.Builder()
                    .url("http://10.0.2.2/get_data.json")
                    .build()
                responseData = client.newCall(request).execute()

            }catch (e: Exception){
                e.printStackTrace()
            }
        }
        return responseData.body?.string()?:""
    }
}

data class Book(var id: String, var name: String, var version: String)