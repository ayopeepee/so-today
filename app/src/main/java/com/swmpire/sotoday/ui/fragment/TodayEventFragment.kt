package com.swmpire.sotoday.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.michalsvec.singlerowcalendar.calendar.CalendarChangesObserver
import com.michalsvec.singlerowcalendar.calendar.CalendarViewManager
import com.michalsvec.singlerowcalendar.calendar.SingleRowCalendarAdapter
import com.michalsvec.singlerowcalendar.selection.CalendarSelectionManager
import com.michalsvec.singlerowcalendar.utils.DateUtils
import com.swmpire.sotoday.R
import com.swmpire.sotoday.databinding.FragmentTodayEventBinding
import com.swmpire.sotoday.databinding.ItemCalendarSelectedBinding
import com.swmpire.sotoday.databinding.ItemCalendarUnselectedBinding
import com.swmpire.sotoday.viewmodel.CalendarViewModel
import com.swmpire.sotoday.viewmodel.CalendarViewModelFactory
import com.swmpire.sotoday.viewmodel.EventViewModel
import com.swmpire.sotoday.viewmodel.EventViewModelFactory
import java.util.Date


class TodayEventFragment : Fragment() {

    private var _binding: FragmentTodayEventBinding? = null
    private val binding get() = _binding!!

    private lateinit var sharedEventViewModel: EventViewModel
    private lateinit var calendarViewModel: CalendarViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTodayEventBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedEventViewModel = ViewModelProvider(requireActivity(), EventViewModelFactory()).get(EventViewModel::class.java)
        calendarViewModel = ViewModelProvider(requireActivity(), CalendarViewModelFactory()).get(CalendarViewModel::class.java)

        setupCalendar()

        sharedEventViewModel.event.observe(viewLifecycleOwner, Observer { event ->
            binding.eventTextView.text = event?.name
        })

        calendarViewModel.dates.observe(viewLifecycleOwner, Observer { dates ->
            binding.calendar.setDates(dates)
        })

        calendarViewModel.selectedDate.observe(viewLifecycleOwner, Observer {date ->
            date?.let {
                binding.textViewDate.text = "${DateUtils.getMonthName(date)}, ${DateUtils.getDayNumber(date)}"
                binding.textViewWeekDay.text = DateUtils.getDayName(date)

            }
        })

        calendarViewModel.loadCurrentDate()

        binding.buttonLeft.setOnClickListener {
            calendarViewModel.loadPreviousMonthDates()
        }

        binding.buttonRight.setOnClickListener {
            calendarViewModel.loadNextMonthDates()
        }
    }

    private fun setupCalendar() {

        val rowCalendarViewManager = object : CalendarViewManager {
            override fun setCalendarViewResourceId(
                position: Int,
                date: Date,
                isSelected: Boolean
            ): Int {
                return if (isSelected)
                    R.layout.item_calendar_selected
                else
                    R.layout.item_calendar_unselected
            }

            override fun bindDataToCalendarView(
                holder: SingleRowCalendarAdapter.CalendarViewHolder,
                date: Date,
                position: Int,
                isSelected: Boolean
            ) {
                if (isSelected) {

                    val selectedItemBinding: ItemCalendarSelectedBinding = ItemCalendarSelectedBinding.bind(holder.itemView)
                    selectedItemBinding.textViewCalendarItemDate.text = DateUtils.getDayNumber(date)
                    selectedItemBinding.textViewCalendarItemWeekDay.text = DateUtils.getDay3LettersName(date)
                } else {
                    val unselectedItemBinding: ItemCalendarUnselectedBinding = ItemCalendarUnselectedBinding.bind(holder.itemView)
                    unselectedItemBinding.textViewCalendarItemDate.text = DateUtils.getDayNumber(date)
                    unselectedItemBinding.textViewCalendarItemWeekDay.text = DateUtils.getDay3LettersName(date)
                }
            }
        }

        val rowCalendarChangesObserver = object : CalendarChangesObserver {
            override fun whenSelectionChanged(isSelected: Boolean, position: Int, date: Date) {
                binding.textViewDate.text = "${DateUtils.getMonthName(date)}, ${DateUtils.getDayNumber(date)}"
                binding.textViewWeekDay.text = DateUtils.getDayName(date)

                if (isSelected) {
                    calendarViewModel.selectDate(date)
                }
                super.whenSelectionChanged(isSelected, position, date)
            }
        }

        val rowCalendarSelectionManager = object : CalendarSelectionManager {
            override fun canBeItemSelected(position: Int, date: Date): Boolean {
                return true
            }

        }

        binding.calendar.apply {
            calendarViewManager = rowCalendarViewManager
            calendarChangesObserver = rowCalendarChangesObserver
            calendarSelectionManager = rowCalendarSelectionManager
            init()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}