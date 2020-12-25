package com.zzj.uilayouttest.bean

/**
 * author: zhang
 * created on: 2020/12/22 13:55
 * description:消息实体类
 */
class Msg(val content: String, val type: Int) {
    companion object{
        const val TYPE_RECEIVED = 0
        const val TYPE_SENT = 1
    }
}