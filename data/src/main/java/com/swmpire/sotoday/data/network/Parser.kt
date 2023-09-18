package com.swmpire.sotoday.data.network

import org.jsoup.Jsoup
import javax.inject.Inject

class Parser @Inject constructor() {

    fun getData(url : String) : MutableList<String> {
        val data = mutableListOf<String>()
        val doc = Jsoup.connect(url).get()
        val days = doc.select("div.main")
        for (i in 0 until days.size) {
            data.add(days
                .select("span")
                .eq(i)
                .text())
        }
        return data
    }

}

