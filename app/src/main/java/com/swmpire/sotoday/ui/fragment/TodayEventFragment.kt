package com.swmpire.sotoday.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.MaterialTimePicker.INPUT_MODE_CLOCK
import com.google.android.material.timepicker.TimeFormat
import com.swmpire.sotoday.R
import com.swmpire.sotoday.databinding.FragmentTodayEventBinding
import com.swmpire.sotoday.util.ReminderManager
import com.swmpire.sotoday.viewmodel.EventViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.Date


@AndroidEntryPoint
class TodayEventFragment : Fragment() {

    private var _binding: FragmentTodayEventBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel: EventViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTodayEventBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel.date.observe(viewLifecycleOwner, Observer { date ->
            binding.textViewDate.text = "${date.split(", ").first()},"
            binding.textViewWeekDay.text = date.split(",").last()
        })

        sharedViewModel.fetchCurrentData()

        sharedViewModel.event.observe(viewLifecycleOwner, Observer { event ->
            if (event?.name.isNullOrEmpty()) {
                Toast.makeText(requireContext(), "Проверьте соединение", Toast.LENGTH_SHORT).show()
            } else {
                binding.textViewEvent.text = event?.name
            }

        })

        sharedViewModel.isNotificationOn.observe(viewLifecycleOwner, Observer { isOn ->
            when (isOn) {
                true -> {
                    binding.buttonNotification.setImageResource(R.drawable.ic_notification_on)
                }
                false -> {
                    binding.buttonNotification.setImageResource(R.drawable.ic_notification_off)
                }
            }
        })

        binding.buttonSelectDate.setOnClickListener {
            showDatePicker()
        }

        binding.buttonNotification.setOnClickListener {
            if (sharedViewModel.isNotificationOn.value == true) {
                ReminderManager.stopReminder(requireContext())
                sharedViewModel.setReminderOff()
            } else {
                showTimePicker()
            }
        }


    }

    private fun showTimePicker() {
        val timePicker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_24H)
            .setHour(12)
            .setMinute(0)
            .setTitleText("Выберите время для напоминания")
            .setInputMode(INPUT_MODE_CLOCK)
            .build()

        timePicker.show(parentFragmentManager, "tag")

        timePicker.addOnPositiveButtonClickListener {
            ReminderManager.startReminder(
                requireContext(),
                "${timePicker.hour}:${timePicker.minute}"
            )
            sharedViewModel.setReminderOn()
            Log.d("TAG", "showTimePicker: set reminder at ${timePicker.hour}:${timePicker.minute}")
        }
    }

    private fun showDatePicker() {
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


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}