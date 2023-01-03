package com.ashu.ocotopus.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.ashu.ocotopus.R
import com.ashu.ocotopus.data.Dish
import com.bumptech.glide.Glide

class DishAdapter(private var dishData: Dish?): RecyclerView.Adapter<DishAdapter.DishViewHolder>() {

    inner class DishViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val dishImage: AppCompatImageView
        val dishDescription: AppCompatTextView

        init {
            dishImage = view.findViewById(R.id.image_dish)
            dishDescription = view.findViewById(R.id.text_dish_name)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.dish_item, parent, false)
        return DishViewHolder(view)
    }

    override fun onBindViewHolder(holder: DishViewHolder, position: Int) {
        val data = dishData?.get(position)
        holder.apply {
            Glide.with(dishImage.context).load(data?.dishUrl)
                .error(R.drawable.octy).placeholder(R.drawable.octopus).into(dishImage)

            dishDescription.text = data?.dishDescription
        }
    }

    override fun getItemCount(): Int {
        return if (dishData?.size == 0) 0 else dishData?.size!!
    }

    fun setDish(dish: Dish?) {
        this.dishData = dish
        notifyDataSetChanged()
    }

    fun getDish(): Dish? {
        return dishData
    }
}