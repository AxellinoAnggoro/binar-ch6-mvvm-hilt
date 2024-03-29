package com.axellinoanggoro.binar_ch6_news_api.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.axellinoanggoro.binar_ch6_news_api.R
import com.axellinoanggoro.binar_ch6_news_api.databinding.ActivityCategoryBinding
import com.axellinoanggoro.binar_ch6_news_api.model.CategoryData
import com.axellinoanggoro.binar_ch6_news_api.view.adapter.CategoryAdapter

class CategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryBinding
    private lateinit var categoryAdapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listCategory = arrayListOf(
            CategoryData("BUSINESS", R.drawable.ic_launcher_background),
            CategoryData("ENTERTAINMENT", R.drawable.ic_launcher_background),
            CategoryData("GENERAL", R.drawable.ic_launcher_background),
            CategoryData("HEALTH", R.drawable.ic_launcher_background),
            CategoryData("SCIENCE", R.drawable.ic_launcher_background),
            CategoryData("SPORTS", R.drawable.ic_launcher_background),
            CategoryData("TECHNOLOGY", R.drawable.ic_launcher_background)
        )

        categoryAdapter = CategoryAdapter(listCategory)

        binding.rvCategory.apply {
            layoutManager = LinearLayoutManager(this@CategoryActivity, LinearLayoutManager.VERTICAL, false)
            adapter = categoryAdapter
            categoryAdapter.onClick = {
                val inten = Intent(context, SourceActivity::class.java)
                inten.putExtra("name", it.name)
                startActivity(inten)
            }
        }


    }


}