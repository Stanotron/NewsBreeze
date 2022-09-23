package com.example.newsb

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.newsb.Modules.NewsAdapter
import com.example.newsb.Modules.News
import com.example.newsb.databinding.ActivityMainBinding
import com.android.volley.Request
import com.android.volley.Response


@SuppressLint("StaticFieldLeak")
private lateinit var newsAdapter: NewsAdapter
val newsArray = ArrayList<News>()

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val view: View = findViewById(android.R.id.content)
        val mLoadAnimation: Animation = AnimationUtils.loadAnimation(applicationContext, R.drawable.fade_in)
        mLoadAnimation.duration = 500
        view.startAnimation(mLoadAnimation)

        binding.rvNews.layoutManager = LinearLayoutManager(this)
        newsAdapter = NewsAdapter(this)
        binding.rvNews.adapter = newsAdapter

        getNews()


    }

    private fun getNews(){
        val queue = Volley.newRequestQueue(this)
        val url = "https://newsapi.org/v2/top-headlines?country=in&apiKey=da99b4a370234e199a1094848ff2a760"
        val jsonObject = object : JsonObjectRequest(
            Request.Method.GET,url,null,
            Response.Listener {
                val newsJsonArray = it.getJSONArray("articles")
                for(i in 0 until newsJsonArray.length()){
                    val newsJsonObject = newsJsonArray.getJSONObject(i)
                    val news = News(
                        newsJsonObject.getString("urlToImage"),
                        newsJsonObject.getString("title"),
                        newsJsonObject.getString("description"),
                        newsJsonObject.getString("publishedAt").substring(0,10),
                        newsJsonObject.getString("url"),
                        newsJsonObject.getString("content"),
                        newsJsonObject.getString("author"),
//                        newsJsonObject.getString("name")
                        )
                    newsArray.add(news)
                }
                newsAdapter.update(newsArray)
            },
            Response.ErrorListener {

            }
        ) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["User-Agent"] = "Mozilla/5.0"
                return headers
            }
        }

        queue.add(jsonObject)
    }

}