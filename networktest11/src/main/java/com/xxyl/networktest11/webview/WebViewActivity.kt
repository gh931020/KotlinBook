package com.xxyl.networktest11.webview

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.xxyl.networktest11.R
import kotlinx.android.synthetic.main.activity_web_view.*

class WebViewActivity : AppCompatActivity() {
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        //这里初次安装运行出现net::ERR_ACCESS_DENIED,卸载重新运行后恢复
        webView.settings.setJavaScriptEnabled(true)
        webView.webViewClient = WebViewClient()
        webView.loadUrl("https://www.baidu.com/")
    }
}

