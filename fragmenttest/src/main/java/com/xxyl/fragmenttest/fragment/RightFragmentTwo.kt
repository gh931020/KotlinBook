package com.xxyl.fragmenttest.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.xxyl.fragmenttest.R

/**
 * author: zhang
 * created on: 2020/12/22 15:58
 * description:
 */
class RightFragmentTwo: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_right_two, container, false)
    }
}