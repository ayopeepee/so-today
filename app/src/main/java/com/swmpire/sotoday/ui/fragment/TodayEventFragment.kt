package com.swmpire.sotoday.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
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
import java.util.Date


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

        sharedViewModel.fetchCurrentData()

        sharedViewModel.event.observe(viewLifecycleOwner, Observer { event ->
            binding.textViewEvent.text = event?.name
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

        binding.buttonNotification.setOnClickListener {
                toggleNotificationButton(binding.buttonNotification)
            }


        }

    private fun toggleNotificationButton(button: ImageButton) {
        if (button.tag == "off") {
            val timePicker = MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .setHour(12)
                .setMinute(0)
                .setTitleText("Выберите время напоминания")
                .setInputMode(INPUT_MODE_CLOCK)
                .build()

            timePicker.show(parentFragmentManager, "tag")

            timePicker.addOnPositiveButtonClickListener {
                ReminderManager.startReminder(
                    requireContext(),
                    "${timePicker.hour}:${timePicker.minute}"
                )
                Toast.makeText(
                    requireContext(),
                    "Уведомление придет в ${timePicker.hour}:${timePicker.minute}",
                    Toast.LENGTH_SHORT
                ).show()
                binding.buttonNotification.apply {
                    setImageResource(R.drawable.ic_notification_on)
                    tag = "on"
                }
            }
        } else {
            ReminderManager.stopReminder(requireContext())
            Toast.makeText(requireContext(), "Уведомления отключены", Toast.LENGTH_SHORT)
                .show()
            binding.buttonNotification.apply {
                setImageResource(R.drawable.ic_notification_off)
                tag = "off"
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}