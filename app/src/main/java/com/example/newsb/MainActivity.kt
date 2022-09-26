package com.example.newsb

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.newsb.modules.NewsAdapter
import com.example.newsb.modules.News
import com.example.newsb.databinding.ActivityMainBinding
import com.android.volley.Request
import com.android.volley.Response
import com.example.newsb.databinding.NewsUnitBinding
import com.example.newsb.databinding.SortLayoutBinding
import com.example.newsb.modules.SavedNews


@SuppressLint("StaticFieldLeak")
private lateinit var newsAdapter: NewsAdapter
val newsArray = ArrayList<News>()

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var binding2: SortLayoutBinding
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding2 = SortLayoutBinding.inflate(layoutInflater)

        val view: View = findViewById(android.R.id.content)
        val mLoadAnimation: Animation = AnimationUtils.loadAnimation(applicationContext, R.drawable.fade_in)
        mLoadAnimation.duration = 500
        view.startAnimation(mLoadAnimation)

        binding.rvNews.layoutManager = LinearLayoutManager(this)
        newsAdapter = NewsAdapter(this)
        binding.rvNews.adapter = newsAdapter

        getNews("https://newsapi.org/v2/top-headlines?country=in&apiKey=da99b4a370234e199a1094848ff2a760")

        binding.tvNewsBreeze.setOnClickListener{
            newsArray.clear()
            getNews("https://newsapi.org/v2/top-headlines?country=in&apiKey=da99b4a370234e199a1094848ff2a760")
        }

        binding.ibMainBookmark.setOnClickListener{
            val intent = Intent(binding.ibMainBookmark.context,SavedNews::class.java)
            binding.ibMainBookmark.context.startActivity(intent)
        }

        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int){}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int){
                Log.d("aaaaaaaaaa","${newsArray.size}")
                newsArray.clear()
                if(binding.etSearch.text.isNotEmpty()){
                    val text = binding.etSearch.text
                    getNews("https://newsapi.org/v2/everything?q=$text&pageSize=20&apiKey=da99b4a370234e199a1094848ff2a760")
                }
                else{
                    getNews("https://newsapi.org/v2/top-headlines?country=in&apiKey=da99b4a370234e199a1094848ff2a760")
                }
            }

            override fun afterTextChanged(s: Editable) {}
        })

        binding.ibSort.setOnClickListener{
            AlertDialog.Builder(this)
                .setTitle("Sort by Date")
                .setView(R.layout.sort_layout)
                .setPositiveButton("OK"){_,_ ->
                    val from = binding2.etDateFrom.text
                    val to = binding2.etDateTo.text
                    Log.d("dddddddd","$from")
                    newsArray.clear()
                    getNews("https://newsapi.org/v2/everything?q=a&from=$from&to=$to&sortBy=popularity&apiKey=da99b4a370234e199a1094848ff2a760")
                }
                .show()

        }

    }

    private fun getNews(Url:String){
        val queue = Volley.newRequestQueue(this)
        val url = Url
        val jsonObject = object : JsonObjectRequest(
            Request.Method.GET,url,null,
            Response.Listener {
                val newsJsonArray = it.getJSONArray("articles")

                for(i in 0 until newsJsonArray.length()){
                    val newsJsonObject = newsJsonArray.getJSONObject(i)
                    val sourceObject = newsJsonObject.getJSONObject("source")
                    val news = News(
                        newsJsonObject.getString("urlToImage"),
                        newsJsonObject.getString("title"),
                        newsJsonObject.getString("description"),
                        newsJsonObject.getString("publishedAt").substring(0,10),
                        newsJsonObject.getString("url"),
                        newsJsonObject.getString("content"),
                        newsJsonObject.getString("author"),
                        sourceObject.getString("name")
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