package com.example.newsb.modules

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsb.MainActivity
import com.example.newsb.databinding.NewsSavedBinding



class SavedNews : AppCompatActivity() {
    private lateinit var binding: NewsSavedBinding
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = NewsSavedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvSavedNews.layoutManager = LinearLayoutManager(this)
        var savedNewsAdapter = SavedNewsAdapter(this, SavedNewsArray)
        binding.rvSavedNews.adapter = savedNewsAdapter

        binding.ibSavedBack.setOnClickListener{
            val intent = Intent(binding.ibSavedBack.context,MainActivity::class.java)
            binding.ibSavedBack.context.startActivity(intent)
        }

    }
}