package com.xxyl.networktest11

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.xxyl.networktest11.http.HUCActivity
import com.xxyl.networktest11.json.JsonActivity
import com.xxyl.networktest11.retrofit.RetrofitActivity
import com.xxyl.networktest11.webview.WebViewActivity
import com.xxyl.networktest11.xmlparse.XmlParseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnWebView.setOnClickListener {
            startActivity<WebViewActivity>(this){}
        }
        btnHttp.setOnClickListener {
            startActivity<HUCActivity>(this){}
        }
        btnXMLParse.setOnClickListener {
            startActivity<XmlParseActivity>(this){}
        }
        btnJson.setOnClickListener {
            startActivity<JsonActivity>(this){}
        }
        btnRetrofit.setOnClickListener {
            startActivity<RetrofitActivity>(this){}
        }
    }
}

/**
 * 启动Activity
 * @param context Context 调用方
 * @param block [@kotlin.ExtensionFunctionType] Function1<Intent, Unit>
 * @return Unit
 */
inline fun <reified T> startActivity(context: Context, block: Intent.()->Unit){
    val intent = Intent(context, T::class.java)
    intent.block()
    context.startActivity(intent)
}
