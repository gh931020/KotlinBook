package com.xxyl.jetpacktest13.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

/**
 * author: zhang
 * created on: 2021/1/4 17:19
 * description:
 */
class MyViewModelFactory(private val countReserved: Int): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MyViewModel(countReserved) as T
    }
}