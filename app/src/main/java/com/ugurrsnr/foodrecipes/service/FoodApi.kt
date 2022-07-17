package com.ugurrsnr.foodrecipes.service

import com.ugurrsnr.foodrecipes.adapter.CategoryList
import retrofit2.Call
import retrofit2.http.GET

interface FoodApi {

    @GET("categories.php")
    fun getCategories(): Call<CategoryList>
}