package com.example.newsb.modules

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
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
                binding.tvSavedTitle.text = this.tag
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

}