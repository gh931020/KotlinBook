package com.xxyl.jetpacktest13.livedata

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * author: zhang
 * created on: 2021/1/5 9:52
 * description:
 */
class LiveViewModelFactory(private val countReserved: Int): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LiveViewModel(countReserved) as T
    }
}