package com.axellinoanggoro.binar_ch6_news_api.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.axellinoanggoro.binar_ch6_news_api.databinding.ActivityArticleBinding
import com.axellinoanggoro.binar_ch6_news_api.viewmodel.ArticleViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleActivity : AppCompatActivity() {

    private lateinit var binding : ActivityArticleBinding
    private lateinit var articleVm : ArticleViewModel
    private lateinit var articleAdapter: ArticleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataSource = intent.extras?.getString("data_source","") ?: ""
        val layout = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)

        articleAdapter = ArticleAdapter(ArrayList())
        binding.rvArticle.layoutManager = layout
        binding.rvArticle.adapter = articleAdapter

        articleVm = ViewModelProvider(this)[ArticleViewModel::class.java]
        articleVm.callApiArticle(dataSource)
        articleVm.getDataArticle().observe(this) { list ->
            list?.let {
                articleAdapter.setArticle(it)
            }
        }

        articleAdapter.onClick = {article ->
            val move = Bundle().apply {
                putString("data_article", article.url)
            }
            Intent(this, DetailActivity::class.java).putExtras(move)
            startActivity(Intent(this, DetailActivity::class.java))
        }

    }
}