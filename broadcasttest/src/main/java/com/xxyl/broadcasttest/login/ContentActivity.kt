package com.xxyl.broadcasttest.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.xxyl.broadcasttest.R
import com.xxyl.broadcasttest.login.base.BaseActivity
import kotlinx.android.synthetic.main.activity_content.*

/**
 * author: zhang
 * created on: 2020/12/23 14:59
 * description:
 */
class ContentActivity: BaseActivity() {

    companion object{
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, ContentActivity::class.java)
            context.startActivity(starter)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content)
        btnExit.setOnClickListener {
            val intent = Intent("com.xxyl.broadcast.LOGIN_OUT")
            intent.setPackage(packageName)
            sendBroadcast(intent)
        }
    }
}