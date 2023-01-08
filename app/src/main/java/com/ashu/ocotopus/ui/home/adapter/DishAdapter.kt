package com.ashu.ocotopus.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatRatingBar
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.ashu.ocotopus.R
import com.ashu.ocotopus.data.Dish
import com.bumptech.glide.Glide

class DishAdapter(private var dishData: Dish?) :
    RecyclerView.Adapter<DishAdapter.DishViewHolder>() {

    interface OnItemClicked {
        fun rateDish(position: Int, rating: Float, dishId: Long)
    }

    inner class DishViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val dishImage: AppCompatImageView
        val dishName: AppCompatTextView
        val dishDescription: AppCompatTextView
        val dishRating: AppCompatRatingBar
        val dishRateMe: AppCompatRatingBar
        val totalRating: AppCompatTextView

        init {
            dishImage = view.findViewById(R.id.image_dish)
            dishName = view.findViewById(R.id.text_dish_name)
            dishDescription = view.findViewById(R.id.text_dish_description)
            dishRating = view.findViewById(R.id.dish_rating)
            dishRateMe = view.findViewById(R.id.dish_rate_it)
            totalRating = view.findViewById(R.id.text_total_rating)
        }
    }

    private var itemClick: OnItemClicked? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.dish_item, parent, false)
        return DishViewHolder(view)
    }

    override fun onBindViewHolder(holder: DishViewHolder, position: Int) {
        val data = dishData?.get(position)
        data?.let {
            holder.apply {
                Glide.with(dishImage.context).load(data.dishUrl)
                    .error(R.drawable.octopus).placeholder(R.drawable.octopus).into(dishImage)
                dishName.text = data.dishName
                dishDescription.text = data.dishDescription
                dishRating.rating = data.dishRating!!.toFloat()
                totalRating.text = buildString {
                    append("(")
                    append(data.totalRatings.toString())
                    append(")")
                }

                dishRateMe.setOnRatingBarChangeListener { ratingBar, rating, b ->
                    itemClick?.rateDish(position, rating, data.dishId)
                }
            }
        }
    }

    fun setItemClick(itemClick: OnItemClicked?) {
        this.itemClick = itemClick
    }

    override fun getItemCount(): Int {
        return if (dishData?.size == 0) 0 else dishData?.size!!
    }

    fun setDish(dish: Dish?) {
        this.dishData = dish
    }

    fun updateRating(position: Int, rating: Double?, totalRating: Long?) {
        this.dishData?.get(position)?.dishRating = rating
        this.dishData?.get(position)?.totalRatings = totalRating
        notifyItemChanged(position)
    }

    fun getDish(): Dish? {
        return dishData
    }
}