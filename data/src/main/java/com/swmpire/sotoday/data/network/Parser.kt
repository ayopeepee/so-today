package com.swmpire.sotoday.data.network

import org.jsoup.Jsoup

class Parser {

    suspend fun getData(url : String) : MutableList<String> {
        val data = mutableListOf<String>()
        val doc = Jsoup.connect(url).get()
        val days = doc.select("div.main")
        days.forEach { _ ->
            data.add(days
                    .select("span")
                    .text())
        }
        return data
    }

}

