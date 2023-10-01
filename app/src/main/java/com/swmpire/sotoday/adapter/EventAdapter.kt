package com.swmpire.sotoday.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.swmpire.sotoday.R
import com.swmpire.sotoday.databinding.ItemDayBinding
import com.swmpire.sotoday.domain.model.Event

class EventAdapter(private val eventList: MutableList<Event>, private val listener: OnClickListener) : RecyclerView.Adapter<EventAdapter.ViewHolder>() {

    fun updateEventList(newEventList: List<Event>) {
        val diffResult = DiffUtil.calculateDiff(EventDiffUtilCallback(eventList, newEventList))
        eventList.clear()
        eventList.addAll(newEventList)
        diffResult.dispatchUpdatesTo(this)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.textview_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_day, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val event = eventList[position]
        holder.name.text = event.name
        holder.itemView.setOnClickListener {
            listener.onClick(event.name)
        }
    }

    override fun getItemCount(): Int = eventList.size

    class OnClickListener(val clickListener: (name: String) -> Unit) {
        fun onClick(name: String) = clickListener(name)
    }
}