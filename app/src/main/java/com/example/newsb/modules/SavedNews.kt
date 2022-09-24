package com.example.newsb.modules

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsb.databinding.NewsSavedBinding

private lateinit var SavedNewsArray : ArrayList<SavedNewsData>

class SavedNews : AppCompatActivity() {
    private lateinit var binding: NewsSavedBinding
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = NewsSavedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvSavedNews.layoutManager = LinearLayoutManager(this)
        var savedNewsAdapter = SavedNewsAdapter(this, SavedNewsArray)
        binding.rvSavedNews.adapter = savedNewsAdapter

    }
}