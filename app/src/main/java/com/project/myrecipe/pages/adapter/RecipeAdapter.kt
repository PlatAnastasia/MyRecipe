package com.project.myrecipe.pages.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.project.myrecipe.R
import com.project.myrecipe.databinding.ItemRecipeBinding
import com.project.myrecipe.pages.database.Recipe
import com.project.myrecipe.pages.domain.RecipeDomain

class RecipeAdapter(
    private val recipes: List<RecipeDomain>,
    private val onReadMoreClick: (RecipeDomain) -> Unit,
    private val onFavoriteClick: (RecipeDomain) -> Unit,
) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    private var recipeList = recipes

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val binding = ItemRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipeList[position]
        holder.bind(recipe)
    }

    override fun getItemCount(): Int = recipeList.size

    inner class RecipeViewHolder(private val binding: ItemRecipeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(recipe: RecipeDomain) {
            // Bind data
            binding.recipeTitle.text = recipe.title
            binding.recipeDescription.text = recipe.description

            // Handle favorite toggle state
            binding.imgFavouriteIndication.setImageResource(
                if (recipe.isFavourite) R.drawable.baseline_favorite_24 else R.drawable.baseline_favorite_border_24
            )

            // Handle favorite click
            binding.imgFavouriteIndication.setOnClickListener {
                onFavoriteClick(recipe)
            }

            // Handle Read More click
            binding.readMoreButton.setOnClickListener {
                onReadMoreClick(recipe)
            }


        }
    }

}
