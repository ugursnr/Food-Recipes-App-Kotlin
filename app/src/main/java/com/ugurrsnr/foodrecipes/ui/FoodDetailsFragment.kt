package com.ugurrsnr.foodrecipes.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.ugurrsnr.foodrecipes.adapter.CategoryFoodsAdapter
import com.ugurrsnr.foodrecipes.databinding.FragmentFoodDetailsBinding
import com.ugurrsnr.foodrecipes.viewmodel.FoodDetailsViewModel


class FoodDetailsFragment : Fragment() {
    private var _binding: FragmentFoodDetailsBinding? = null
    private val binding get() = _binding!!

    private lateinit var categoryName : String
    private lateinit var viewModel : FoodDetailsViewModel
    private lateinit var categoryFoodsAdapter : CategoryFoodsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFoodDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            this.categoryName = FoodDetailsFragmentArgs.fromBundle(it).categoryName
        }
        viewModel = ViewModelProvider(this)[FoodDetailsViewModel::class.java]
        viewModel.getFoodsByCategory(categoryName)
        observeMealLiveData()
        prepareRecyclerView()


        println(categoryName)
    }

    private fun prepareRecyclerView() {
        categoryFoodsAdapter = CategoryFoodsAdapter()
        binding.apply {
            foodsRV.layoutManager = GridLayoutManager(context!! ,2,
                GridLayoutManager.VERTICAL,false)
            foodsRV.adapter = categoryFoodsAdapter
        }
    }

    fun observeMealLiveData(){
        viewModel.observeMealsLiveData().observe(viewLifecycleOwner, Observer { foodList ->
            categoryFoodsAdapter.setMealsList(foodList)
        })

    }

}