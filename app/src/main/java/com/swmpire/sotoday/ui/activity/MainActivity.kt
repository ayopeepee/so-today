package com.swmpire.sotoday.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.swmpire.sotoday.R
import com.swmpire.sotoday.ui.fragment.TodayAllEventsFragment
import com.swmpire.sotoday.ui.fragment.TodayEventFragment
import com.swmpire.sotoday.viewmodel.EventViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var bottomNav: BottomNavigationView
    private val sharedViewModel: EventViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNav = findViewById(R.id.bottom_menu)

        bottomNav.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.home -> {
                    loadFragment(TodayEventFragment())
                    true
                }
                R.id.list -> {
                    loadFragment(TodayAllEventsFragment())
                    true
                }

                else -> {true}
            }
        }
    }

    private fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.nav_host_fragment_container,fragment)
        transaction.commit()
    }

}