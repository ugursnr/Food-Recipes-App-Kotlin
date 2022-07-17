package com.ugurrsnr.foodrecipes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ugurrsnr.foodrecipes.databinding.CategoriesRowBinding

class CategoriesAdapter: RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {

    private var categoriesList = ArrayList<Category>()
    lateinit var onItemClick : ((Category) -> Unit)

    class CategoriesViewHolder(val binding: CategoriesRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        return CategoriesViewHolder(CategoriesRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.binding.categoryNameTV.text = categoriesList[position].strCategory //Category Name

        Glide.with(holder.itemView)  //Category Image
            .load(categoriesList[position].strCategoryThumb)
            .into(holder.binding.categoryImageView)

        holder.itemView.setOnClickListener {
            onItemClick.invoke(categoriesList[position])
        }
    }

    override fun getItemCount(): Int {
        return categoriesList.size
    }

    fun updateCategoriesList(categoryList : List<Category>){
        this.categoriesList = categoryList as ArrayList<Category>
        notifyDataSetChanged()
    }

}