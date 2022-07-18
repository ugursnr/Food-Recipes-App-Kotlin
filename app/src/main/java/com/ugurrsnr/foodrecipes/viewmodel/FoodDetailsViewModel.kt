package com.ugurrsnr.foodrecipes.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ugurrsnr.foodrecipes.model.FoodsByCategory
import com.ugurrsnr.foodrecipes.model.FoodsByCategoryList
import com.ugurrsnr.foodrecipes.service.FoodApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FoodDetailsViewModel : ViewModel() {

    var mealsLiveData = MutableLiveData<List<FoodsByCategory>>()

    fun getFoodsByCategory(categoryName : String) {
        FoodApiService().getFoodsByCategory(categoryName).enqueue(object :
            Callback<FoodsByCategoryList> {
            override fun onResponse(
                call: Call<FoodsByCategoryList>,
                response: Response<FoodsByCategoryList>
            ) {
                if (response.body() != null){
                    mealsLiveData.value = response.body()!!.meals

                }
            }

            override fun onFailure(call: Call<FoodsByCategoryList>, t: Throwable) {
                Log.d("CategoryMealsActivity", t.message.toString())
            }

        })
    }

    fun observeMealsLiveData() : LiveData<List<FoodsByCategory>> {
        return mealsLiveData
    }
}