package com.project.myrecipe.pages.repositories

import com.project.myrecipe.pages.database.Recipe
import com.project.myrecipe.pages.database.RecipesDao
import com.project.myrecipe.pages.database.Ιngredients
import javax.inject.Inject

interface RecipeRepo{
    suspend fun saveRecipe(recipe: Recipe)
//    suspend fun saveIngredients(ingr: Ιngredients)
}
class RecipeRepoImpl @Inject constructor(
    private val dao: RecipesDao
): RecipeRepo{
    override suspend fun saveRecipe( recipe: Recipe) {
        try {
            dao.insertNewRecipe(recipe)
        } catch (_: Exception) {
            null
        }
    }


//    override suspend fun saveIngredients(ingr: Ιngredients) {
//        try {
//            dao.insertIngredients(ingr)
//        } catch (_: Exception) {
//            null
//        }
//    }

}