package com.project.myrecipe.pages.AddRecipe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.project.myrecipe.databinding.FragmentAddRecipeBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AddRecipe : Fragment() {

    private var _binding: FragmentAddRecipeBinding? = null
    private val binding get() = _binding!!

    private val viewModel:AddRecipeViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddRecipeBinding.inflate(inflater,container,false)
        val view = binding.root
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

         binding.btnSaveRecipe.setOnClickListener {
             viewModel.saveRecipe(binding.etRecipeTitle.text.toString(),binding.etDescription.text.toString(),
                 binding.etIngredients.text.toString(),false)
         }
    }

}