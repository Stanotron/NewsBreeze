package com.example.newsb.modules

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsb.modules.SavedNewsAdapter.*
import com.example.newsb.databinding.SavedNewsUnitBinding

class SavedNewsAdapter (val context: Context, val items : ArrayList<SavedNewsData>): RecyclerView.Adapter<ViewHolder>() {
    inner class ViewHolder(val binding : SavedNewsUnitBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = SavedNewsUnitBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(items[position]){
                binding.tvSavedTitle.text = this.title
                binding.tvSavedDate.text = this.date + " . " + this.editor
                binding.tvSavedTag.text = this.tag
                Glide.with(context).load(this.imageUrl).into(binding.ivSavedNewsImage)
                binding.tvSavedTitle.setOnClickListener{
                    val intent = Intent(binding.tvSavedTitle.context,NewsDescription::class.java)
                    val message = position.toString()
//                    Log.d("index","$message")
                    intent.putExtra("key",message)
                    binding.tvSavedTitle.context.startActivity(intent)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

}