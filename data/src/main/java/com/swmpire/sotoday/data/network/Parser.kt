package com.swmpire.sotoday.data.network

import org.jsoup.Jsoup

class Parser {

    suspend fun getData(url : String) : MutableList<String> {
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

