package com.swmpire.sotoday.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.swmpire.sotoday.R
import com.swmpire.sotoday.databinding.ActivityMainBinding
import com.swmpire.sotoday.domain.usecase.GetTodayUseCase
import com.swmpire.sotoday.viewmodel.MainViewModel
import com.swmpire.sotoday.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var vm: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        vm = ViewModelProvider(this, MainViewModelFactory())
            .get(MainViewModel::class.java)

        vm.liveData.observe(this, Observer { text ->
            binding.textviewDay.text = text?.get(0) ?: "err"
        })

        vm.fetchData()

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}