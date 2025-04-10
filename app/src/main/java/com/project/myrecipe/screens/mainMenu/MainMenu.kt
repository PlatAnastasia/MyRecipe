package com.project.myrecipe.screens.mainMenu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.project.myrecipe.R
import com.project.myrecipe.databinding.FragmentMainMenuBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainMenu : Fragment() {

    private var _binding: FragmentMainMenuBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainMenuBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnAddRecipe.setOnClickListener {
            navigate(1)
        }
        binding.btnFavorites.setOnClickListener {
            navigate(2)
        }
        binding.btnMyRecipes.setOnClickListener {
            navigate(0)
        }


    }

    private fun navigate(Destination:Int){
        val navController = findNavController()
        val bundle = Bundle().apply {  }
        when(Destination){
            0 -> { navController.navigate(R.id.action_mainMenu_to_recipes,bundle)}
            1 -> { navController.navigate(R.id.action_mainMenu_to_addRecipe,bundle)}
            2 -> { navController.navigate(R.id.action_mainMenu_to_favourites,bundle)}
        }

    }
}