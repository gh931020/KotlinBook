package com.xxyl.sunnyweather15.ui.place

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.xxyl.sunnyweather15.logic.Repository
import com.xxyl.sunnyweather15.logic.model.Place

/**
 * author: zhang
 * created on: 2021/1/11 11:43
 * description:
 */
class PlaceViewModel: ViewModel() {

    private val searchLiveData = MutableLiveData<String>()

    val placeList = ArrayList<Place>()
    //map : 将实际包含数据的LiveData和仅用于观察数据的LiveData进行转换,比如一个userLiveData我们只关心user的name,就可以用map进行转换
    //switchMap: 如果ViewModel中的某个LiveData对象是调用另外的方法获取的,那么我们就可以借助switchMap将这个LiveData是对象转换成另外
    // 一个可观察的LiveData对象,此处便是将searchLiveData转换成了由Repository.searchPlaces返回的placeLiveData
    val placeLiveData = Transformations.switchMap(searchLiveData){query ->
        Repository.searchPlaces(query)
    }

    fun searchPlaces(query: String){
        searchLiveData.value = query
    }

}