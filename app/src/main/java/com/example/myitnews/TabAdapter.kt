package com.example.myitnews

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class TabAdapter(fm:FragmentManager, private val context: Context):FragmentPagerAdapter(fm) {
    private val urls: List<Urls> = listOf(
        Urls("おすすめ","https://api.network.hsc.ac.jp/recommend"),
        Urls("@IT","https://api.network.hsc.ac.jp/atit"),
        Urls("週刊ASCII","https://api.network.hsc.ac.jp/ascii"),
        Urls("GIZMODE","https://api.network.hsc.ac.jp/gizmode")
        )

    override fun getItem(position: Int): Fragment {
        val frag = TabFragment()
        val bundle = Bundle()
        bundle.putString("newsUrl", urls[position].url)
        frag.arguments = bundle

        return frag
    }

    override fun getCount(): Int {
        return 4
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return urls[position].title
    }

    override fun getItemPosition(`object`: Any): Int {
        return super.getItemPosition(`object`)
    }
}