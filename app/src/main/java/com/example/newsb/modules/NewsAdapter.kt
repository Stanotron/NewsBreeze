package com.example.newsb.modules

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsb.databinding.NewsUnitBinding
import com.bumptech.glide.Glide
import com.example.newsb.R
import kotlin.collections.ArrayList

var SavedNewsArray : ArrayList<SavedNewsData> = arrayListOf()

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
                binding.tvDescription.text = this.desc
                binding.tvDate.text = this.date
                Glide.with(context).load(this.imageUrl).into(binding.ivNews)
                binding.clNewsUnit.setOnClickListener{
                    val intent = Intent(binding.clNewsUnit.context,NewsDescription::class.java)
                    val message = position.toString()
//                    Log.d("index","$message")
                    intent.putExtra("key",message)
                    binding.clNewsUnit.context.startActivity(intent)
                }
                binding.btRead.setOnClickListener{
                    val intent = Intent(binding.btRead.context,NewsDescription::class.java)
                    val message = position.toString()
//                    Log.d("index","$message")
                    intent.putExtra("key",message)
                    binding.btRead.context.startActivity(intent)
                }
                binding.btSave.setOnClickListener{
                    if(SavedNewsArray.any{ it.title == this.heading.substring(0,35)+"..."}){
                        binding.btBookmark.setImageResource(R.drawable.unsaved_bookmark)
                        SavedNewsArray.removeIf{
                            SavedNewsArray.any{ it.title == this.heading.substring(0,35)+"..."}
                        }
                    }else{
                        binding.btBookmark.setImageResource(R.drawable.saved_bookmark)
                        SavedNewsArray.add(
                            SavedNewsData(this.imageUrl,null,this.heading.substring(0,35)+"...",this.date,this.author)
                        )
                    }
                }
                binding.btBookmark.setOnClickListener{
                    if(SavedNewsArray.any{ it.title == this.heading.substring(0,35)+"..."}){
                        binding.btBookmark.setImageResource(R.drawable.unsaved_bookmark)
                        SavedNewsArray.removeIf{
                            SavedNewsArray.any{ it.title == this.heading.substring(0,35)+"..."}
                        }
                    }else{
                        binding.btBookmark.setImageResource(R.drawable.saved_bookmark)
                        SavedNewsArray.add(
                            SavedNewsData(this.imageUrl,null,this.heading.substring(0,35)+"...",this.date,this.author)
                        )
                    }
                }
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