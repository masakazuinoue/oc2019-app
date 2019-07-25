package com.example.myitnews

import android.app.Activity
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleRegistry
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView

class TabFragment(): Fragment() {

    private lateinit var life:LifecycleRegistry

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?  {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.content_main, container, false)
        val listview = view.findViewById(R.id.listView) as ListView
        val swipe = view.findViewById(R.id.mySwipeRefreshLayout) as SwipeRefreshLayout

        val args = arguments

        var url:String = ""
        if (args == null){
           url = ""
        }else{
           url = args.getString("newsUrl")
        }
        HttpUtil().getNews(view, listview, url)

        val pm = context?.packageManager
        listview.setOnItemClickListener { parent, view, position, id ->
            val news = parent.getItemAtPosition(position) as News
            val url = news.url
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            if (intent.resolveActivity(pm) != null){
                startActivity(intent)
            }
        }

        swipe.setOnRefreshListener {
            HttpUtil().getNewsSwipeRefresh(swipe, listview, url)
        }

        return view
    }

}