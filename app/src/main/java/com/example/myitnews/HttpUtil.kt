package com.example.myitnews

import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.view.View
import android.widget.ListView
import android.widget.ProgressBar
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.content_main.view.*

class HttpUtil {
    fun getNews(view: View, listview: ListView, url: String) {
        view.progress.visibility = ProgressBar.VISIBLE
        url.httpGet().responseString { request, response, result ->
            when (result) {
                is Result.Success -> {
                    val gson = GsonBuilder().setPrettyPrinting().create()
                    val data = result.get()
                    val json_data: List<News> = gson.fromJson(data, object : TypeToken<List<News>>() {}.type)
                    listview.adapter = NewsAdapter(json_data)
                    view.progress.visibility = ProgressBar.GONE
                }
                is Result.Failure -> {
                    val ex = result.getException()
                    view.progress.visibility = ProgressBar.GONE
                }
            }
        }
    }
    fun getNewsSwipeRefresh(swipe: SwipeRefreshLayout, listview: ListView, url: String) {
        swipe.isRefreshing = true
        url.httpGet().responseString { request, response, result ->
            when (result) {
                is Result.Success -> {
                    val gson = GsonBuilder().setPrettyPrinting().create()
                    val data = result.get()
                    val json_data: List<News> = gson.fromJson(data, object : TypeToken<List<News>>() {}.type)
                    listview.adapter = NewsAdapter(json_data)
                    swipe.isRefreshing = false
                }
                is Result.Failure -> {
                    val ex = result.getException()
                    swipe.isRefreshing = false
                }
            }
        }
    }
}