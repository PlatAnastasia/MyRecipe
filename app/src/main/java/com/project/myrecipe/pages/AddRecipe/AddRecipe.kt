package com.project.myrecipe.pages.AddRecipe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.project.myrecipe.databinding.FragmentAddRecipeBinding
import com.project.myrecipe.pages.generalFunctions.showMessage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@AndroidEntryPoint
class AddRecipe : Fragment() {

    private var _binding: FragmentAddRecipeBinding? = null
    private val binding get() = _binding!!
    private var isFavourite :Boolean = false
    private var idRecipe:Int? = null
    private lateinit var navController: NavController

    private val viewModel:AddRecipeViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddRecipeBinding.inflate(inflater,container,false)
        val view = binding.root
        return view

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { bundle ->
            idRecipe = bundle.getString("id")?.toInt()
        }
        navController = findNavController()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        idRecipe?.let { viewModel.getRecipeUsingID(it) }

         binding.btnSaveRecipe.setOnClickListener {
             viewModel.saveRecipe(binding.etRecipeTitle.text.toString(),binding.etInstructions.text.toString(),
                 binding.etIngredients.text.toString(),false)
         }


        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.recipe.collect{
                    if (it != null){
                        if  (idRecipe == null){  navController.navigateUp()
                        }else{
                            binding.etIngredients.setText(it.ingredients)
                            binding.etRecipeTitle.setText(it.title)
                            binding.etInstructions.setText(it.description)
                            binding.btnSaveRecipe.visibility = View.GONE
                        }

                    }
                }
            }
        }


        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.error.collect{
                    if(it!=null) {
                        showMessage(it.toString(), requireContext())
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // prevent memory leaks
    }

}