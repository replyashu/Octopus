package com.ashu.ocotopus.ui.home.adapter

import androidx.recyclerview.widget.DiffUtil
import com.ashu.ocotopus.data.Dish

class DishDiffCallback(
    private val old: Dish,
    private val new: Dish
): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return old.size
    }

    override fun getNewListSize(): Int {
        return new.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return old[oldItemPosition].dishId == new[newItemPosition].dishId
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return old[oldItemPosition] == new[newItemPosition]
    }
}