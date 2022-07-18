package com.ugurrsnr.foodrecipes.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ugurrsnr.foodrecipes.model.Category
import com.ugurrsnr.foodrecipes.model.CategoryList
import com.ugurrsnr.foodrecipes.service.FoodApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoriesHomeViewModel : ViewModel() {

    private var categoriesLiveData = MutableLiveData<List<Category>>()



    fun getCategories(){
        FoodApiService().getCategories().enqueue(object : Callback<CategoryList> {
            override fun onResponse(call: Call<CategoryList>, response: Response<CategoryList>) {
                response.body()?.let {
                    categoriesLiveData.postValue(it.categories)
                }
            }

            override fun onFailure(call: Call<CategoryList>, t: Throwable) {
                Log.d("HomeFragment", t.message.toString())
            }


        })
    }
    fun observeCategoriesLiveData() : LiveData<List<Category>> {
        return categoriesLiveData
    }


}