package com.swmpire.sotoday.adapter

import androidx.recyclerview.widget.DiffUtil
import com.swmpire.sotoday.domain.model.Event

class EventDiffUtilCallback(private val oldList: List<Event>, private val newList: List<Event>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].name == newList[newItemPosition].name
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].name == newList[newItemPosition].name
    }

}