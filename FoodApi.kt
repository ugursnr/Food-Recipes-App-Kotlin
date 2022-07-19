package com.ugurrsnr.foodrecipes.service

import com.ugurrsnr.foodorderapptutorial.model.FoodList
import com.ugurrsnr.foodrecipes.model.CategoryList
import com.ugurrsnr.foodrecipes.model.FoodsByCategoryList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodApi {

    @GET("categories.php")
    fun getCategories(): Call<CategoryList>

    @GET("filter.php")
    fun getMealsByCategory(@Query("c") categoryName: String): Call<FoodsByCategoryList>

    @GET("lookup.php?")
    fun getFoodDetails(@Query("i") id: String): Call<FoodList>
}