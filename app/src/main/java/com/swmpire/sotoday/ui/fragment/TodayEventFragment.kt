package com.swmpire.sotoday.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.swmpire.sotoday.R
import com.swmpire.sotoday.databinding.FragmentTodayEventBinding
import com.swmpire.sotoday.viewmodel.EventViewModel
import com.swmpire.sotoday.viewmodel.EventViewModelFactory


class TodayEventFragment : Fragment() {

    private var _binding: FragmentTodayEventBinding? = null
    private val binding get() = _binding!!

    private lateinit var sharedViewModel: EventViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTodayEventBinding.inflate(inflater, container, false)

        sharedViewModel = ViewModelProvider(requireActivity(), EventViewModelFactory()).get(EventViewModel::class.java)

        sharedViewModel.event.observe(viewLifecycleOwner, Observer {event ->
            binding.eventTextView.text = event?.name
        })

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}