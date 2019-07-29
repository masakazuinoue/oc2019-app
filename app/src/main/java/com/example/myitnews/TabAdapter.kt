package com.example.myitnews

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class TabAdapter(fm:FragmentManager, private val context: Context):FragmentPagerAdapter(fm) {
    private val baseUrl = "https://api.network.hsc.ac.jp/"

    private val urls: List<Urls> = listOf(
        Urls("おすすめ",baseUrl + "recommend"),
        Urls("@IT",baseUrl + "atit"),
        Urls("日経xTECH",baseUrl + "xtech"),
        Urls("週刊ASCII",baseUrl + "ascii"),
        Urls("GIZMODE",baseUrl + "gizmode")
        )

    override fun getItem(position: Int): Fragment {
        val frag = TabFragment()
        val bundle = Bundle()
        bundle.putString("newsUrl", urls[position].url)
        frag.arguments = bundle

        return frag
    }

    override fun getCount(): Int {
        return 5
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return urls[position].title
    }

    override fun getItemPosition(`object`: Any): Int {
        return super.getItemPosition(`object`)
    }
}