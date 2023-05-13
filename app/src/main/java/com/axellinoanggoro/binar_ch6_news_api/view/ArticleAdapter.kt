package com.axellinoanggoro.binar_ch6_news_api.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.axellinoanggoro.binar_ch6_news_api.databinding.ItemArticleBinding
import com.axellinoanggoro.binar_ch6_news_api.model.Article
import com.axellinoanggoro.binar_ch6_news_api.model.Source
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

class ArticleAdapter(var listArticle : List<Article>):RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {

    var onClick : ((Article) -> Unit)? = null

    class ViewHolder(var binding: ItemArticleBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = ItemArticleBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listArticle.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.articleTitle.text = listArticle[position].title
        holder.binding.articleDate.text = listArticle[position].publishedAt
        holder.binding.articleDesc.text = listArticle[position].description
        Glide.with(holder.itemView)
            .load(listArticle[position].urlToImage)
            .into(holder.binding.articleImg)
        holder.binding.articleCv.setOnClickListener {
            onClick?.invoke(listArticle[position])
        }
    }

    fun setArticle(listArticle: List<Article>){
        this.listArticle = listArticle
    }

}