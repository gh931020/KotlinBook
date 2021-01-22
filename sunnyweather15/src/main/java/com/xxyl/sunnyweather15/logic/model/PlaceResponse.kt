package com.xxyl.sunnyweather15.logic.model

import com.google.gson.annotations.SerializedName

/**
 * author: zhang
 * created on: 2021/1/11 11:09
 * description:地址相关数据类
 */

data class PlaceResponse(val status: String, val places: List<Place>)

data class Place(
    val name: String, val location: Location,
    //由于json中的一些字段的命名可能与kotlin中的命名规范不太一致, 因此这里使用了
    // @SerializedName注解的方式,来让JSON字段和Kotlin字段之间建立映射关系
    @SerializedName("formatted_address") val address: String
)

data class Location(val lng: String, val lat: String)