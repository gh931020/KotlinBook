package com.xxyl.contentprovidertest8.provider

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.contentValuesOf
import com.xxyl.contentprovidertest8.R
import kotlinx.android.synthetic.main.activity_use_provider.*
import java.net.URI

class UseProviderActivity : AppCompatActivity() {
    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, UseProviderActivity::class.java)
            context.startActivity(starter)
        }
    }

    var bookId: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_use_provider)
        btnAdd.setOnClickListener {
            val uri = Uri.parse("content://com.xxyl.datasavetest7.provider/book")
            val values = contentValuesOf(
                "name" to "A Clash of Kings",
                "author" to "George Martin",
                "pages" to 1040,
                "price" to 22.85
            )
            val newUri = contentResolver.insert(uri, values)
            bookId = newUri?.pathSegments?.get(1)
            Toast.makeText(this, "insert successful： $bookId", Toast.LENGTH_SHORT).show()
        }
        btnDelet.setOnClickListener {
            bookId?.let {
                val uri = Uri.parse("content://com.xxyl.datasavetest7.provider/book/$it")
                contentResolver.delete(uri, null, null)
            }
        }
        btnUpdate.setOnClickListener {
            bookId?.let {
                val uri = Uri.parse("content://com.xxyl.datasavetest7.provider/book/$it")
                val values = contentValuesOf("name" to "A Storm of Swords", "pages" to 1216, "price" to 24.05)
                contentResolver.update(uri, values, null,null)
            }
        }
        btnQuery.setOnClickListener {
            val uri = Uri.parse("content://com.xxyl.datasavetest7.provider/book")
            contentResolver.query(uri,null,null,null,null)?.apply {
                while (moveToNext()){
                    //获取值
                    val name = getString(getColumnIndex("name"))
                    val author = getString(getColumnIndex("author"))
                    val pages = getInt(getColumnIndex("pages"))
                    val price = getDouble(getColumnIndex("price"))
                    Log.d(UseProviderActivity.javaClass.simpleName, "$name : $author, $pages , $price$")
                }
                close()
            }
        }
    }
}