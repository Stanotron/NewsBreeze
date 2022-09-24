package com.example.newsb.modules

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.newsb.MainActivity
import com.example.newsb.databinding.NewsDescriptionBinding
import com.example.newsb.newsArray


class NewsDescription : AppCompatActivity() {

    private lateinit var binding: NewsDescriptionBinding

    override fun onCreate (savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = NewsDescriptionBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)

        val index = intent.getStringExtra("key")!!.toInt()
        Log.d("aaaaaaaaaaaaaaaa","$index")
        binding.tvDescTitle.text = newsArray[index].heading
        binding.tvDescDate.text = newsArray[index].date
        binding.tvAuthor.text = newsArray[index].author
//        binding.tvAuthorOrg.text = newsArray[index].org
        binding.tvDescDetail.text = newsArray[index].data
        Glide.with(this).load(newsArray[index].imageUrl).into(binding.ivDescImage)


        binding.ibBack.setOnClickListener{
            val intent = Intent(binding.ibBack.context,MainActivity::class.java)
            binding.ibBack.context.startActivity(intent)
        }
    }
}