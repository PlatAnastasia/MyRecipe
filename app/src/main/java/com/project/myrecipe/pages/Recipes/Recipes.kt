package com.project.myrecipe.pages.Recipes

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.myrecipe.R
import com.project.myrecipe.databinding.FragmentRecipesBinding
import com.project.myrecipe.pages.adapter.RecipeAdapter
import com.project.myrecipe.pages.generalFunctions.showMessage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class Recipes : Fragment() {

    private var _binding: FragmentRecipesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: RecipesViewModel by viewModels()
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navController = findNavController()

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.getAllRecipes()


        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.error.collect { error ->
                    error?.let {
                        showMessage(it, requireContext())
                    }
                }
            }
        }



        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.recipe.collect { recipes ->
                    val recipeAdapter = RecipeAdapter(
                        recipes,
                        onReadMoreClick = { recipe ->
                            val bundle = Bundle().apply {
                                putString("id",recipe.id.toString())
                            }
                            navController.navigate(R.id.action_recipes_to_addRecipe,bundle)
                        },
                        onFavoriteClick = { recipe ->
                            viewModel.updateFavouriteRecipe(recipe.id, recipe.isFavourite)
                        }
                    )
                    binding.recipeRecyclerView.apply {
                        adapter = recipeAdapter
                        layoutManager = LinearLayoutManager(requireContext())

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
