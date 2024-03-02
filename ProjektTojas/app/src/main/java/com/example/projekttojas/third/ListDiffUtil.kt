package com.example.projekttojas.third

import androidx.recyclerview.widget.DiffUtil
import com.example.projekttojas.data.User

class ListDiffUtil(
    private val oldList: List<User>,
    private val newList: List<User>
): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].id != newList[newItemPosition].id -> {
                false
            }
            oldList[oldItemPosition].userName != newList[newItemPosition].userName ->{
                false
            }
            oldList[oldItemPosition].score != newList[newItemPosition].score ->{
                false
            }
            else -> true
        }
    }
}