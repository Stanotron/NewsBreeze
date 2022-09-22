package com.example.newsb.Modules

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsb.databinding.NewsUnitBinding
import com.bumptech.glide.Glide


class NewsAdapter(val context: Context): RecyclerView.Adapter<NewsAdapter.ViewHolder>(){

    private val newsList = ArrayList<News>()

    inner class ViewHolder(val binding: NewsUnitBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = NewsUnitBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(newsList[position]){
                binding.tvNews.text = this.heading
                binding.tvDescritpion.text = this.desc
                binding.tvDate.text = this.date
                Glide.with(context).load(this.imageUrl).into(binding.ivNews)
            }
        }
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    fun update(newArray : ArrayList<News>){
        newsList.clear()
        newsList.addAll(newArray)
        notifyDataSetChanged()
    }

}