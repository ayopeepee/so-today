package com.swmpire.sotoday

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jsoup.Jsoup

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch(Dispatchers.IO) {
            getData()
        }
    }

    suspend fun getData() {
        val data = mutableListOf<String>()
        val url = "https://kakoysegodnyaprazdnik.ru/baza/sentyabr/11"
        val doc = Jsoup.connect(url).get()
        val days = doc.select("div.main")
        for (i in 0 until days.size) {
            val title = days
                .select("span")
                .eq(i)
                .text()
            data.add(title)
            Log.d("tag", title)
        }
    }
}