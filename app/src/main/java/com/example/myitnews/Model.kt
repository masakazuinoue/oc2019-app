package com.example.myitnews

data class News(val division: String, val title: String, val byline: String, val date: String,val url: String, val thumbnail: String)

data class Urls(val title: String, val url: String)