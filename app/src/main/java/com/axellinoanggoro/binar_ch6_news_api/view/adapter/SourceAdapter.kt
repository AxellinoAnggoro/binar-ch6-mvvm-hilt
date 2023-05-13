package com.axellinoanggoro.binar_ch6_news_api.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.axellinoanggoro.binar_ch6_news_api.databinding.ItemSourceBinding
import com.axellinoanggoro.binar_ch6_news_api.model.Source

class SourceAdapter(private var listSource: List<Source>) :
    RecyclerView.Adapter<SourceAdapter.ViewHolder>() {

    var onClick: ((Source) -> Unit)? = null

    class ViewHolder(var binding: ItemSourceBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemSourceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listSource.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.sourceName.text = listSource[position].name
        holder.binding.sourceCv.setOnClickListener {
            onClick?.invoke(listSource[position])
        }
    }

    fun setSource(listSource: List<Source>) {
        this.listSource = listSource
        notifyDataSetChanged()
    }
}