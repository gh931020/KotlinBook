package com.xxyl.sunnyweather15.ui.weather

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.xxyl.sunnyweather15.R
import com.xxyl.sunnyweather15.logic.model.Weather
import com.xxyl.sunnyweather15.logic.model.getSky
import kotlinx.android.synthetic.main.activity_weather.*
import kotlinx.android.synthetic.main.layout_forecast.*
import kotlinx.android.synthetic.main.layout_life_index.*
import kotlinx.android.synthetic.main.layout_now.*
import java.text.SimpleDateFormat
import java.util.*

class WeatherActivity : AppCompatActivity() {

    companion object{
        @JvmStatic
        fun start(context: Context, locationLng: String, locationLat: String, placeName: String) {
            val starter = Intent(context, WeatherActivity::class.java)
            starter.putExtra("location_lng", locationLng)
            starter.putExtra("location_lat", locationLat)
            starter.putExtra("placeName", placeName)
            context.startActivity(starter)
        }
    }

    val viewModel by lazy { ViewModelProviders.of(this).get(WeatherViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
        if (viewModel.locationLng.isEmpty()){
            viewModel.locationLng = intent.getStringExtra("location_lng")?: ""
        }
        if (viewModel.locationLat.isEmpty()){
            viewModel.locationLat = intent.getStringExtra("location_lat")?: ""
        }
        if (viewModel.placeName.isEmpty()){
            viewModel.placeName = intent.getStringExtra("placeName")?: ""
        }
        viewModel.weatherLiveData.observe(this, Observer {result ->
            val weather = result.getOrNull()
            if (weather != null){
                showWeatherInfo(weather)
            }else{
                Toast.makeText(this, "无法获取天气信息", Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }
        })
        viewModel.refreshWeather(viewModel.locationLng, viewModel.locationLat)
    }

    private fun showWeatherInfo(weather: Weather) {
        tvPlaceName.text = viewModel.placeName
        val realtime = weather.realtime
        val daily = weather.daily
        //填充now布局
        val currentTempText = "${realtime.temperature.toInt()} ℃"
        tvCurrentTemp.text = currentTempText
        tvCurrentSky.text = getSky(weather.realtime.skycon).info
        val currentPM25Text = "空气指数 ${realtime.airQuality.aqi.chn.toInt()}"
        tvCurrentAQI.text = currentPM25Text
        rlNow.setBackgroundResource(getSky(realtime.skycon).bg)
        //填充forecast.xml布局中的数据
        llForecast.removeAllViews()
        val days = daily.skycon.size
        for (i in 0 until days){
            val skycon = daily.skycon[i]
            val temperature = daily.temperature[i]
            val view = LayoutInflater.from(this).inflate(R.layout.layout_forecast_item, llForecast, false)
            val tvDateInfo = view.findViewById<TextView>(R.id.tvDateInfo)
            val ivSkyIcon = view.findViewById<ImageView>(R.id.ivSkyIcon)
            val tvSkyInfo = view.findViewById<TextView>(R.id.tvSkyInfo)
            val tvTemperature = view.findViewById<TextView>(R.id.tvTemperatureInfo)
            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            tvDateInfo.text = simpleDateFormat.format(skycon.date)
            val sky = getSky(skycon.value)
            ivSkyIcon.setImageResource(sky.icon)
            tvSkyInfo.text = sky.info
            val tempText = "${temperature.min.toInt()} ~ ${temperature.max.toInt()} ℃"
            tvTemperature.text = tempText
            llForecast.addView(view)
        }
        //填充index布局
        val lifeIndex = daily.lifeIndex
        tvColdRisk.text = lifeIndex.coldRisk[0].desc
        tvDressing.text = lifeIndex.dressing[0].desc
        tvUltraviolet.text = lifeIndex.ultraviolet[0].desc
        tvCarWashing.text = lifeIndex.carWashing[0].desc
        slWeather.visibility = View.VISIBLE
    }
}