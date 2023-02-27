package com.sid.newsistan.webView

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.sid.newsistan.databinding.ActivityWebViewBinding


class WebViewActivity : AppCompatActivity() {

    lateinit var binding : ActivityWebViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        val url = intent.getStringExtra("URL")
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.progressBar.visibility = View.VISIBLE
        binding.webView.webViewClient = MyBrowser()
        binding.webView.settings.setLoadsImagesAutomatically(true)
        binding.webView.getSettings().setJavaScriptEnabled(true)
        binding.webView.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
       binding.webView.loadUrl(url!!)


       binding.webView.setWebViewClient(object : WebViewClient() {
            override fun onReceivedError(
                view: WebView, errorCode: Int,
                description: String, failingUrl: String
            ) {
                // TODO Auto-generated method stub
                super.onReceivedError(view, errorCode, description, failingUrl)
            }

           override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
               binding.progressBar.visibility = View.GONE
               super.onPageStarted(view, url, favicon)
           }

            override fun onPageFinished(view: WebView, url: String) {
                // TODO Auto-generated method stub
                super.onPageFinished(view, url)
            }
        })



    }

    private class MyBrowser : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return true
        }
    }
}