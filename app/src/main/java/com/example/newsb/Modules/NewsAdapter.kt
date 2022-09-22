package com.example.newsb.Modules

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsb.databinding.NewsUnitBinding
import java.util.concurrent.Executors
import android.os.Handler


class NewsAdapter(val context: Context, val newsList: ArrayList<NewsData>): RecyclerView.Adapter<NewsAdapter.ViewHolder>(){

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
                binding.ivNews.setImageBitmap(picture(this))
            }
        }
    }

    private fun picture(position : NewsData): Bitmap? {
        val executor = Executors.newSingleThreadExecutor()
        val handler = Handler(Looper.getMainLooper())
        var image : Bitmap? = null
        executor.execute{
            val imageURL = position.image
            val picture = java.net.URL(imageURL).openStream()
            image = BitmapFactory.decodeStream(picture)
        }
        return image
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

}