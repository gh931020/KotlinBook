package com.xxyl.kotlinbook.domian

import com.xxyl.kotlinbook.Request.Request
import com.xxyl.kotlinbook.bean.ForecastResult

/**
 * author: zhang
 * created on: 2020/9/23 16:46
 * description:
 */
class RequestForecastCommand(val zipCode: String): Commands<ForecastList> {
    override fun execute(): ForecastList {
        val forecastRequest = Request(zipCode)
        return ForecastDataMapper().convertFromDataModel(forecastRequest.execute())
    }
}