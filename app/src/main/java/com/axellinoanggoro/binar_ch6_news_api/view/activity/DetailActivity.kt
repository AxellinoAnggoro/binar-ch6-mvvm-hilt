package com.axellinoanggoro.binar_ch6_news_api.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import com.axellinoanggoro.binar_ch6_news_api.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataArticle = intent.extras?.getString("data_article","").toString()

        binding.detailWv.apply {
            webViewClient = WebViewClient()
            loadUrl(dataArticle)
            settings.javaScriptEnabled = true
            settings.setSupportZoom(true)
        }
    }
}