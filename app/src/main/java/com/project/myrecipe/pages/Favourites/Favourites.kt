package com.project.myrecipe.pages.Favourites

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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.myrecipe.R
import com.project.myrecipe.databinding.FragmentFavouritesBinding
import com.project.myrecipe.databinding.FragmentRecipesBinding
import com.project.myrecipe.pages.Recipes.RecipesViewModel
import com.project.myrecipe.pages.adapter.RecipeAdapter
import com.project.myrecipe.pages.generalFunctions.showMessage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class Favourites : Fragment() {

    private var _binding: FragmentFavouritesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FavouritesViewModel by viewModels()
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navController = findNavController()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavouritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getFavRecipes()


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
                            navController.navigate(R.id.action_favourites_to_addRecipe,bundle)
                        },
                        onFavoriteClick = { recipe ->
                            viewModel.updateFavouriteRecipe(recipe.id, recipe.isFavourite)
                        }
                    )
                    binding.favRecyclerView.apply {
                        adapter = recipeAdapter
                        layoutManager = LinearLayoutManager(requireContext())

                    }
                }
            }
        }

    }


}