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
import com.swmpire.sotoday.adapter.EventAdapter
import com.swmpire.sotoday.databinding.FragmentTodayAllEventsBinding
import com.swmpire.sotoday.databinding.FragmentTodayEventBinding
import com.swmpire.sotoday.viewmodel.EventViewModel
import com.swmpire.sotoday.viewmodel.EventViewModelFactory


class TodayAllEventsFragment : Fragment() {

    private var _binding: FragmentTodayAllEventsBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: EventAdapter

    private lateinit var sharedViewModel: EventViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTodayAllEventsBinding.inflate(inflater, container, false)

        sharedViewModel = ViewModelProvider(requireActivity(), EventViewModelFactory()).get(EventViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = EventAdapter()
        val recyclerView = binding.recyclerview
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)

        sharedViewModel.allEvents.observe(viewLifecycleOwner, Observer {events ->
            adapter.differ.submitList(events)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}