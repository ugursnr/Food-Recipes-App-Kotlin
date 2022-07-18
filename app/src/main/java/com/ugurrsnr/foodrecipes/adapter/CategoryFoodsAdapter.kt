package com.ugurrsnr.foodrecipes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ugurrsnr.foodrecipes.databinding.MealItemBinding

import com.ugurrsnr.foodrecipes.model.FoodsByCategory

class CategoryFoodsAdapter : RecyclerView.Adapter<CategoryFoodsAdapter.CategoryFoodsHolder>() {
    var foodsList = ArrayList<FoodsByCategory>()
    lateinit var onItemClick : ((FoodsByCategory) -> Unit)

    class CategoryFoodsHolder(val binding : MealItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryFoodsHolder {
        return CategoryFoodsAdapter.CategoryFoodsHolder(
            MealItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CategoryFoodsHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(foodsList[position].strMealThumb)
            .into(holder.binding.foodImage)

        holder.binding.imageNameTV.text = foodsList[position].strMeal

        holder.itemView.setOnClickListener {
            onItemClick.invoke(foodsList[position])
        }
    }

    fun setMealsList(mealsList : List<FoodsByCategory>){
        this.foodsList = mealsList as ArrayList<FoodsByCategory>
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return foodsList.size
    }

}