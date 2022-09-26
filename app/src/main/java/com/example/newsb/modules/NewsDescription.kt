package com.example.newsb.modules

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.newsb.MainActivity
import com.example.newsb.R
import com.example.newsb.databinding.NewsDescriptionBinding
import com.example.newsb.databinding.NewsUnitBinding
import com.example.newsb.newsArray


class NewsDescription : AppCompatActivity() {

    private lateinit var binding: NewsDescriptionBinding
    private lateinit var binding2: NewsUnitBinding

    override fun onCreate (savedInstanceState: Bundle?){

        super.onCreate(savedInstanceState)
        binding = NewsDescriptionBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)

        binding2 = NewsUnitBinding.inflate(layoutInflater)

        val index = intent.getStringExtra("key")!!.toInt()
//        Log.d("aaaaaaaaaaaaaaaa","$index")
        binding.tvDescTitle.text = newsArray[index].heading
        binding.tvDescDate.text = newsArray[index].date
        binding.tvAuthor.text = newsArray[index].author
        binding.tvAuthorOrg.text = newsArray[index].org
        binding.tvDescDetail.text = newsArray[index].data
        Glide.with(this).load(newsArray[index].imageUrl).into(binding.ivDescImage)


        binding.ibBack.setOnClickListener{
            val intent = Intent(binding.ibBack.context,MainActivity::class.java)
            binding.ibBack.context.startActivity(intent)
        }

        binding.btDescSave.setOnClickListener {
            if(SavedNewsArray.any{ it.title == newsArray[index].heading.substring(0,35)+"..."}){
                binding2.btBookmark.setImageResource(R.drawable.unsaved_bookmark)
                SavedNewsArray.removeIf{
                    SavedNewsArray.any{ it.title == newsArray[index].heading.substring(0,35)+"..."}
                }
            }else{
                binding2.btBookmark.setImageResource(R.drawable.saved_bookmark)
                SavedNewsArray.add(
                    SavedNewsData(newsArray[index].imageUrl,"#protest",newsArray[index].heading.substring(0,35)+"...",newsArray[index].date,newsArray[index].author)
                )
            }
        }

        binding.ibDescBookmark.setOnClickListener{
            val intent = Intent(binding.ibDescBookmark.context,SavedNews::class.java)
            binding.ibDescBookmark.context.startActivity(intent)
        }
    }
}