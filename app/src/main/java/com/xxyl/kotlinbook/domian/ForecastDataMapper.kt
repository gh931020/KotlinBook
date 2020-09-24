package com.xxyl.kotlinbook.domian

import com.xxyl.kotlinbook.bean.Daily
import com.xxyl.kotlinbook.bean.ForecastResult
import java.text.DateFormat
import java.util.*

/**
 * author: zhang
 * created on: 2020/9/23 10:24
 * description:
 */
public class ForecastDataMapper {
    fun convertFromDataModel(forecast: ForecastResult): ForecastList{
        return ForecastList(forecast.code, forecast.updateTime, convertForecastListToDomain(forecast.daily))
    }

    private fun convertForecastListToDomain(daily: List<Daily>): List<Forecast> {
        return daily.map { convertForecastItemToDomain(it) }

    }

    private fun convertForecastItemToDomain(it: Daily):Forecast {
        return Forecast(it.fxDate,
                it.textDay,it.tempMax.toInt(),it.tempMin.toInt())
    }

    private fun convertDate(date: Long): String{
        val df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())
        return df.format(date * 1000)
    }
}