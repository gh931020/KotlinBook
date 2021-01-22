package com.xxyl.jetpacktest13.viewmodel

import androidx.lifecycle.ViewModel

/**
 * author: zhang
 * created on: 2021/1/4 16:44
 * description: 带初始化值的构造,通过ViewModelProvider.Factory使用
 */
class MyViewModel(countReserved: Int): ViewModel() {
    var counter = countReserved
}