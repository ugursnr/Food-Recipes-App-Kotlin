package com.ugurrsnr.foodrecipes.service

import com.ugurrsnr.foodrecipes.adapter.CategoryList
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FoodApiService {

    private val api = Retrofit.Builder()
        .baseUrl("https://www.themealdb.com/api/json/v1/1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(FoodApi::class.java)


    fun getCategories() : Call<CategoryList> {
        return api.getCategories()
    }
}