package com.xxyl.jetpacktest13.livedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

/**
 * author: zhang
 * created on: 2021/1/5 9:45
 * description: 比较推荐的做法是永远只暴露不可变的LiveData给外部,这样在非ViewModel中就只能观察LiveData的数据
 * 变化,而不能给LiveData设置数据.保证数据的封装性
 */
class LiveViewModel(countReserved: Int): ViewModel() {

    companion object{
        const val KEY_COUNT = "key_count"
    }

    //对于外部来说只能获取值,无法更改
    val counter: LiveData<Int>
        get() = _counter
    //对于外部不可见
    private var _counter = MutableLiveData<Int>()

    init {
        _counter.value = countReserved
    }

    fun plusOne(){
        val count = _counter.value ?:0
        _counter.value = count + 1
    }

    fun clear(){
        _counter.value = 0
    }

    //map : 转换指定live data
    private val userLiveData = MutableLiveData<User>()
    val userName: LiveData<String> = Transformations.map(userLiveData){user ->
        "${user.name}: ${user.lastName}"
    }

    //switch map:如果ViewModel中的某个LiveData对象是调用另外的方法获取的,那么我们就可以借助switchMap方法,
    // 将这个LiveData对象转换成另外一个可观察的对象
    private val userIdLiveData = MutableLiveData<String>()
    //switchMap会观察userIdLiveData的变化,一旦发生改变,就会执行lambda表达式内的逻辑
    val user: LiveData<User> = Transformations.switchMap(userIdLiveData){userId ->
        LiveRepository.getUser(userId)
    }

    /**
     * 首先,当外部调用ViewModel的getUser方法来获取用户数据是,并不会发起任何请求或者函数调用,只会将传入的userId
     * 设置到userIdLiveData当中,一旦userIdLiveData的数据发生变化,那么观察userIdLiveData的switchMap就会执行,
     * 并且调用我们编写的转换函数,然后转换函数中调用Repository.getUser()方法获取真正的用户数据.同时,switchMap\
     * 方法会将Repository.getUser()方法返回的LiveData对象转换成一个可观察的LiveData对象,对于Activity而言,只要去
     * 观察这个LiveData对象就可以了
     */
    fun getUser(userId: String){
        userIdLiveData.value = userId
    }

}