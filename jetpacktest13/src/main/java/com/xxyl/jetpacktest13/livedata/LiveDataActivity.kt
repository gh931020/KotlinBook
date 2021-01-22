package com.xxyl.jetpacktest13.livedata

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.edit
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.xxyl.jetpacktest13.R
import kotlinx.android.synthetic.main.activity_live_data.*
import kotlinx.android.synthetic.main.activity_view_model.*

class LiveDataActivity : AppCompatActivity() {
    lateinit var viewModel: LiveViewModel
    lateinit var sp:SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_data)
        sp = getPreferences(Context.MODE_PRIVATE)
        val countReserved = sp.getInt(LiveViewModel.KEY_COUNT, 0)
        viewModel = ViewModelProviders.of(this, LiveViewModelFactory(countReserved)).get(LiveViewModel::class.java)
        viewModel.counter.observe(this, Observer {
            tvLiveCounter.text = it.toString()
        })
        tvLiveCounter.text = viewModel.counter.toString()
        btnPlus.setOnClickListener {
            viewModel.plusOne()
        }
        btnLiveClear.setOnClickListener {
            viewModel.clear()
        }
        btnLiveUser.setOnClickListener {
            viewModel.getUser("00001")
        }
        viewModel.user.observe(this, Observer {user->
            tvLiveUser.text = user.name
        })
    }

    override fun onPause() {
        super.onPause()
        sp.edit {
            putInt(LiveViewModel.KEY_COUNT, viewModel.counter.value ?: 0)
        }
    }
}