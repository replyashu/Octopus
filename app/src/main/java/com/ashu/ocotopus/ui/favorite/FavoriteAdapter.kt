package com.ashu.ocotopus.ui.favorite

import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatRatingBar
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.ashu.ocotopus.R
import com.ashu.ocotopus.data.Dish
import com.ashu.ocotopus.data.DishItem
import com.ashu.ocotopus.util.toSinglePrecision
import com.bumptech.glide.Glide

class FavoriteAdapter(private val dishList: Dish?,
                      private var onItemClicked: ((dish: DishItem) -> Unit)): RecyclerView.Adapter<FavoriteAdapter.FavoriteAdapterViewHolder>() {

    inner class FavoriteAdapterViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val dishImage: AppCompatImageView
        private val dishName: AppCompatTextView
        private val dishDescription: AppCompatTextView
        private val dishRating: AppCompatRatingBar
        private val dishRateMe: AppCompatRatingBar
        private val totalRating: AppCompatTextView
        private val deleteButton: AppCompatImageView

        init {
            dishImage = view.findViewById(R.id.image_dish)
            dishName = view.findViewById(R.id.text_dish_name)
            dishDescription = view.findViewById(R.id.text_dish_description)
            dishRating = view.findViewById(R.id.dish_rating)
            dishRateMe = view.findViewById(R.id.dish_rate_it)
            totalRating = view.findViewById(R.id.text_total_rating)
            deleteButton = view.findViewById(R.id.image_delete_dish)
        }

        fun bind(dishItem: DishItem?) {
            dishItem?.let {
                val url = it.dishUrl
                val imageByteArray: ByteArray = Base64.decode(url, Base64.DEFAULT)

                val bmp = BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.size)
                Glide.with(dishImage.context).load(bmp)
                    .error(R.drawable.octopus).placeholder(R.drawable.octopus).into(dishImage)
                dishName.text = dishItem.dishName
                dishDescription.text = dishItem.dishDescription
                dishRating.rating = dishItem.dishRating!!.toFloat()
                totalRating.text = buildString {
                    append(dishItem.dishRating!!.toSinglePrecision())
                    append("(")
                    append(dishItem.totalRatings.toString())
                    append(")")
                }
                deleteButton.setOnClickListener { _ ->
                    onItemClicked(it)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteAdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.favorite_item, parent, false)
        return FavoriteAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteAdapterViewHolder, position: Int) {
        val data = dishList?.get(position)

        holder.bind(data)
    }

    override fun getItemCount(): Int {
        if (dishList.isNullOrEmpty())
            return 0
        else
            return dishList.size
    }

    fun deleteDish(dishItem: DishItem?) {
        val pos: Int = dishList?.indexOf(dishItem)!!
        dishList.removeAt(pos)
        notifyItemRemoved(pos)
    }
}