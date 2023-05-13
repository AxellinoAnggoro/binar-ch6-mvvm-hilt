package com.axellinoanggoro.binar_ch6_news_api.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.axellinoanggoro.binar_ch6_news_api.databinding.ItemCategoryBinding
import com.axellinoanggoro.binar_ch6_news_api.model.CategoryData
import com.bumptech.glide.Glide

class CategoryAdapter(private var listCategory: List<CategoryData>) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    var onClick : ((CategoryData) -> Unit)? = null

    class ViewHolder(var binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.nameCategory.text = listCategory[position].name
        Glide.with(holder.itemView).load(listCategory[position].img)
            .into(holder.binding.imageCategory)
        holder.binding.itemCategory.setOnClickListener {
            onClick!!.invoke(listCategory[position])
        }
    }

    override fun getItemCount(): Int {
        return listCategory.size
    }
}