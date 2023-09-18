package com.swmpire.sotoday.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.google.android.material.datepicker.MaterialDatePicker
import com.swmpire.sotoday.adapter.EventAdapter
import com.swmpire.sotoday.databinding.FragmentTodayAllEventsBinding
import com.swmpire.sotoday.viewmodel.EventViewModel
import java.util.Date


class TodayAllEventsFragment : Fragment() {

    private var _binding: FragmentTodayAllEventsBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: EventAdapter

    private val sharedViewModel: EventViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTodayAllEventsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = EventAdapter(mutableListOf())
        binding.recyclerview.adapter = adapter

        sharedViewModel.allEvents.observe(viewLifecycleOwner, Observer {events ->
            adapter.updateEventList(events)
            binding.recyclerview.adapter = adapter
        })

        sharedViewModel.date.observe(viewLifecycleOwner, Observer { date ->
            binding.textViewDate.text = "${date.split(", ").first()},"
            binding.textViewWeekDay.text = date.split(",").last()
        })

        binding.buttonSelectDate.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder
                .datePicker()
                .setTitleText("Выберите дату")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build()

            datePicker.show(parentFragmentManager, "tag")

            datePicker.addOnPositiveButtonClickListener {
                sharedViewModel.fetchData(Date(it))
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}