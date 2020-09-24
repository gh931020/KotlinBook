package com.xxyl.kotlinbook.domian


/**
 * author: zhang
 * created on: 2020/9/21 13:48
 * description:
 */
public interface Commands<T> {
    fun execute(): T
}

data class ForecastList(val code: String, val updateTime: String, val dailyForecast: List<Forecast>)

data class Forecast(val date: String, val description: String, val high: Int, val low: Int)