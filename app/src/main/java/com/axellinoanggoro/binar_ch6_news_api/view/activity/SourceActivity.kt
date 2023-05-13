package com.axellinoanggoro.binar_ch6_news_api.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.axellinoanggoro.binar_ch6_news_api.databinding.ActivitySourceBinding
import com.axellinoanggoro.binar_ch6_news_api.view.adapter.SourceAdapter
import com.axellinoanggoro.binar_ch6_news_api.viewmodel.SourceViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SourceActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySourceBinding
    private lateinit var sourceAdapter: SourceAdapter
    private lateinit var sourceVm: SourceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySourceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataCategory = intent.extras?.getString("name", "").toString()
        val layout = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        sourceAdapter = SourceAdapter(ArrayList())

        binding.sourceRv.apply {
            layoutManager = layout
            adapter = sourceAdapter
            sourceAdapter.onClick = {
                val move = Intent(context, ArticleActivity::class.java)
                move.putExtra("data_source", it.id)
                startActivity(move)
            }
        }

        sourceVm = ViewModelProvider(this)[SourceViewModel::class.java]
        sourceVm.callApiSource(dataCategory)
        sourceVm.getDataSource().observe(this) { list ->
            list?.let {
                sourceAdapter.setSource(it)
            }
        }
    }
}