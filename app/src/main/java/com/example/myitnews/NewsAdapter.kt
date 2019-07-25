package com.example.myitnews

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import java.util.Base64

class NewsAdapter(val newslist: List<News>): BaseAdapter()  {

    override fun getCount(): Int {
        return newslist.count()
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItem(position: Int): News {
        return newslist[position]
    }

    inner class ViewHolder(cell: View){
        val thumbnail = cell.findViewById<ImageView>(R.id.newsThumbnail)
        val title = cell.findViewById<TextView>(R.id.titleText)
        val date = cell.findViewById<TextView>(R.id.dateText)
        val byline = cell.findViewById<TextView>(R.id.bylineText)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val viewHolder: ViewHolder

        when (convertView){
            null -> {
                val inflater = LayoutInflater.from(parent?.context)
                view = inflater.inflate(R.layout.list_item,parent,false)
                viewHolder = ViewHolder(view)
                view.tag = viewHolder
            }
            else -> {
                view = convertView
                viewHolder = view.tag as ViewHolder
            }
        }

        val news = getItem(position) as News
        viewHolder.title.text = news.title
        viewHolder.date.text = news.date
        viewHolder.byline.text = news.byline

        if (news.thumbnail != "No Image"){
            val decordBytes = Base64.getDecoder().decode(news.thumbnail.toByteArray())
            viewHolder.thumbnail.setImageBitmap(BitmapFactory.decodeByteArray(decordBytes,0,decordBytes.size))
        }else{
            viewHolder.thumbnail.setImageResource(R.drawable.no_image)
        }

        return view
    }

}
