package com.ugurrsnr.foodrecipes.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.ugurrsnr.foodrecipes.adapter.CategoriesAdapter
import com.ugurrsnr.foodrecipes.databinding.FragmentHomeBinding
import com.ugurrsnr.foodrecipes.viewmodel.CategoriesHomeViewModel

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var categoriesAdapter : CategoriesAdapter
    private lateinit var viewModel : CategoriesHomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[CategoriesHomeViewModel::class.java]
        viewModel.getCategories() //getting categories list

        categoriesAdapter = CategoriesAdapter()
        prepareCategoriesRecyclerView()
        observeCategoriesLiveData()

        onCategoryItemClick()
    }

    private fun prepareCategoriesRecyclerView() {
        binding.categoriesRecyclerView.apply {
            layoutManager = GridLayoutManager(activity,3, GridLayoutManager.VERTICAL,false)
            adapter = categoriesAdapter
        }
    }

    private fun observeCategoriesLiveData() {
        viewModel.observeCategoriesLiveData().observe(viewLifecycleOwner, Observer { categoryList ->

            categoriesAdapter.updateCategoriesList(categoryList)
            println(categoryList.size) //working fine so far

        })
    }

    private fun onCategoryItemClick() {
        categoriesAdapter.onItemClick = {
            val actionToMealDetailsFragment = HomeFragmentDirections.actionHomeFragmentToFoodDetailsFragment()
            actionToMealDetailsFragment.categoryName = it.strCategory
            Navigation.findNavController(view!!).navigate(actionToMealDetailsFragment)

        }
    }


}