package com.swmpire.sotoday.data.network

import android.util.Log
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.IOException
import javax.inject.Inject

class Parser @Inject constructor() {

    fun getEventList(url: String): MutableList<String> {
        val data = mutableListOf<String>()
        try {
            val doc = Jsoup.connect(url).timeout(3000).get()
            val days = doc.select("div.main")
            for (i in 0 until days.size) {
                data.add(
                    days
                        .select("span")
                        .eq(i)
                        .text()
                )
            }
        } catch (e: Exception) {
            return mutableListOf()
        }

        return data
    }

    fun getFirstEvent(url: String) : String {
        return try {
            val doc = Jsoup.connect(url).timeout(3000).get()
            doc.select("div.main").select("span").eq(0).text()
        } catch (e: Exception) {
            ""
        }

    }

}

