package com.example.newsb

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsb.Modules.NewsAdapter
import com.example.newsb.Modules.NewsData
import com.example.newsb.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.URL

private lateinit var binding: ActivityMainBinding

val news : ArrayList<NewsData> = arrayListOf()

class MainActivity : AppCompatActivity() {
//    private val news2 : ArrayList<NewsData> = arrayListOf(
//        NewsData("heading1\ncontinues...", "line 1 \nline 2\nline 3", "20-01-2022"),
//        NewsData("heading2\n" +
//                "continues...", "line 1 \nline 2\nline 3", "20-01-2022"),
//        NewsData("heading3\n" +
//                "continues...", "line 1 \nline 2\nline 3", "20-01-2022"),
//        NewsData("heading4\n" +
//                "continues...", "line 1 \nline 2\nline 3", "20-01-2022"),
//        )
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val view: View = findViewById(android.R.id.content)
        val mLoadAnimation: Animation = AnimationUtils.loadAnimation(applicationContext, R.drawable.fade_in)
        mLoadAnimation.duration = 500
        view.startAnimation(mLoadAnimation)

        getApiResult()

        binding.rvNews.layoutManager = LinearLayoutManager(this)
        val newsAdapter = NewsAdapter(this,news)
        binding.rvNews.adapter = newsAdapter


    }

    private fun getApiResult(){
            val API = "https://newsapi.org/v2/top-headlines?country=in&apiKey=da99b4a370234e199a1094848ff2a760"
            Log.d("ApI","$API")
                GlobalScope.launch(Dispatchers.IO){
                    try{
                        val apiResult = URL(API).readText()
                        val jsonObject = JSONObject(apiResult)
                        val heading : String = jsonObject.getJSONObject("title").toString()
                        val desc : String = jsonObject.getJSONObject("description").toString()
                        val date : String = jsonObject.getJSONObject("publishedAt").toString()
                        val image : String = jsonObject.getJSONObject("urlToImage").toString()
                        Log.d("Main","$heading")
                        Log.d("Main",apiResult)
                        withContext(Dispatchers.Main){
                            news.add(NewsData(image,heading,desc,date))
                        }
                    }
                    catch (e:Exception){
                        Log.e("Main","$e")
                    }
                }
    }
}