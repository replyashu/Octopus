package com.ashu.ocotopus.ui.favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatRatingBar
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.ashu.ocotopus.R
import com.ashu.ocotopus.data.Dish
import com.ashu.ocotopus.util.toSinglePrecision
import com.bumptech.glide.Glide

class FavoriteAdapter(private val dishList: Dish?): RecyclerView.Adapter<FavoriteAdapter.FavoriteAdapterViewHolder>() {

    inner class FavoriteAdapterViewHolder(view: View): RecyclerView.ViewHolder(view) {

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteAdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.dish_item, parent, false)
        return FavoriteAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteAdapterViewHolder, position: Int) {
        val data = dishList?.get(position)
        data?.let {
            holder.apply {
                Glide.with(dishImage.context).load(data.dishUrl)
                    .error(R.drawable.octopus).placeholder(R.drawable.octopus).into(dishImage)
                dishName.text = data.dishName
                dishDescription.text = data.dishDescription
                dishRating.rating = data.dishRating!!.toFloat()
                totalRating.text = buildString {
                    append(data.dishRating!!.toSinglePrecision())
                    append("(")
                    append(data.totalRatings.toString())
                    append(")")
                }
            }
        }
    }

    override fun getItemCount(): Int {
        if (dishList.isNullOrEmpty())
            return 0
        else
            return dishList.size
    }
}