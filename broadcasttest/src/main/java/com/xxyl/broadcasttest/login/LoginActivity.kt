package com.xxyl.broadcasttest.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.xxyl.broadcasttest.R
import com.xxyl.broadcasttest.login.ContentActivity
import com.xxyl.broadcasttest.login.base.BaseActivity

class LoginActivity : BaseActivity() {
    companion object{
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, LoginActivity::class.java)
            context.startActivity(starter)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)
        val username = findViewById<EditText>(R.id.etUserName)
        val password = findViewById<EditText>(R.id.etPassWord)
        val login = findViewById<Button>(R.id.btnLogin)
        login.setOnClickListener {
            verifyLogin(username.text.toString(), password.text.toString())
        }
    }

    private fun verifyLogin(username: String, password: String) {
        if (username == "zzj" && password == "123456"){
            ContentActivity.start(this)
        }else{
            Toast.makeText(this, "login error", Toast.LENGTH_SHORT).show()
        }
    }
}

