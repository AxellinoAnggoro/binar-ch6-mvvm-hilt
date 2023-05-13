package com.axellinoanggoro.binar_ch6_news_api.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.axellinoanggoro.binar_ch6_news_api.databinding.ActivitySourceBinding
import com.axellinoanggoro.binar_ch6_news_api.model.Source
import com.axellinoanggoro.binar_ch6_news_api.viewmodel.SourceViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel

@AndroidEntryPoint
class SourceActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySourceBinding
    private lateinit var sourceAdapter: SourceAdapter
    private lateinit var sourceVm: SourceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySourceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataCategory = intent.extras?.getString("name", "") ?: ""
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        sourceAdapter = SourceAdapter(ArrayList())
        binding.sourceRv.layoutManager = layoutManager
        binding.sourceRv.adapter = sourceAdapter

        sourceVm = ViewModelProvider(this).get(SourceViewModel::class.java)
        sourceVm.callApiSource(dataCategory)
        sourceVm.getDataSource().observe(this, Observer { list ->
            list?.let {
                sourceAdapter.setSource(it)
            }
        })

        sourceAdapter.onClick = { source ->
            val move = Bundle().apply {
                putString("data_source", source.name)
            }
            Intent(this, ArticleActivity::class.java).putExtras(move)
            startActivity(Intent(this, ArticleActivity::class.java))
        }
    }
}