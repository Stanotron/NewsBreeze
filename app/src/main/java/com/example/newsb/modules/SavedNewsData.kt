package com.example.newsb.modules

data class SavedNewsData(
    val imageUrl : String = "",
    val tag : String? = "#protest",
    val title : String = "",
    val date : String = "",
    val editor : String = ""
)
