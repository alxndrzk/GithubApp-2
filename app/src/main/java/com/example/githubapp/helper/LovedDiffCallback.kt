package com.example.githubapp.helper

import androidx.recyclerview.widget.DiffUtil
import com.example.githubapp.database.Loved

class LovedDiffCallback(private val oldList: List<Loved>, private val newList: List<Loved>):DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldLoved = oldList[oldItemPosition]
        val newLoved = newList[newItemPosition]
        return oldLoved.username == newLoved.username && oldLoved.avatar == newLoved.avatar
    }
}