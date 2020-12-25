package com.xxyl.broadcasttest.login.base

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.internal.BaselineLayout
import com.xxyl.broadcasttest.login.LoginActivity
import com.xxyl.broadcasttest.login.util.ActivityController

/**
 * author: zhang
 * created on: 2020/12/23 14:29
 * description:
 */
open class BaseActivity: AppCompatActivity() {

    lateinit var loginBroadcastReceiver: LoginBroadcastReceiver
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        ActivityController.addActivity(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityController.removeActivity(this)
    }

    override fun onResume() {
        super.onResume()
        val intentFilter = IntentFilter()
        intentFilter.addAction("com.xxyl.broadcast.LOGIN_OUT")
        loginBroadcastReceiver = LoginBroadcastReceiver()
        registerReceiver(loginBroadcastReceiver, intentFilter)
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(loginBroadcastReceiver)
    }


    inner class LoginBroadcastReceiver: BroadcastReceiver(){
        override fun onReceive(context: Context, intent: Intent) {
            AlertDialog.Builder(context).apply {
                setTitle("Warning")
                setMessage("You are forced to be offline ,Please try to login again!")
                setCancelable(false)
                setPositiveButton("OK"){_,_->
                    ActivityController.finishAll()
                    context.startActivity(Intent(context, LoginActivity::class.java))
                }
                show()
            }
        }
    }
}