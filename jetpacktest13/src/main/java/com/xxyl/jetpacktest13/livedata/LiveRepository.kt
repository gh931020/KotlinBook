package com.xxyl.jetpacktest13.livedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/**
 * author: zhang
 * created on: 2021/1/5 10:37
 * description:
 */
object LiveRepository {
    /**
     * 根据id获取用户
     * @param userId String
     * @return LiveData<User>
     */
    fun getUser(userId: String): LiveData<User>{
        val liveData = MutableLiveData<User>()
        liveData.value = User(userId, userId, 0)
        return liveData
    }
}