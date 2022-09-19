package com.example.newsb

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils

class MainActivity : AppCompatActivity() {
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val view: View = findViewById(android.R.id.content)
        val mLoadAnimation: Animation = AnimationUtils.loadAnimation(applicationContext, R.drawable.fade_in)
        mLoadAnimation.duration = 500
        view.startAnimation(mLoadAnimation)

    }
}