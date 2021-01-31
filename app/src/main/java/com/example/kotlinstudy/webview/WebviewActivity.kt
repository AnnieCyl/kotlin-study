package com.example.kotlinstudy.webview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import com.example.kotlinstudy.R
import kotlinx.android.synthetic.main.activity_webview.*

class WebviewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)

        wv_test.settings.javaScriptEnabled = true
        wv_test.webViewClient = WebViewClient()
        wv_test.loadUrl("https://www.baidu.com")
    }
}