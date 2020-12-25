package com.xxyl.contentprovidertest8

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.xxyl.contentprovidertest8.permission.CallActivity
import com.xxyl.contentprovidertest8.provider.ContactsActivity
import com.xxyl.contentprovidertest8.provider.UseProviderActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnPermission.setOnClickListener {
            CallActivity.start(this)
        }
        btnContact.setOnClickListener {
            ContactsActivity.start(this)
        }
        btnUseProvider.setOnClickListener{
            UseProviderActivity.start(this)
        }
    }
}
