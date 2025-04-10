package com.project.myrecipe.repositories


import com.project.myrecipe.database.Recipe
import com.project.myrecipe.database.RecipesDao
import javax.inject.Inject

interface RecipeRepo{
    suspend fun getFavRecipes():List<Recipe>
    suspend fun saveRecipe(recipe: Recipe)
    suspend fun getRecipes():List<Recipe>
    suspend fun updateFavorite(id:Int,isFavourite:Boolean)
      suspend fun getRecipeUsingID(id: Int): Recipe?
    suspend fun updateImage(id:Int,image:String)
}
class RecipeRepoImpl @Inject constructor(
    private val dao: RecipesDao
): RecipeRepo {
    override suspend fun getFavRecipes(): List<Recipe> {
      return  try {
          dao.getFavRecipes()
        }catch (ex: Exception) {
            emptyList()
        }
    }

    override suspend fun saveRecipe( recipe: Recipe) {
        try {
            dao.insertNewRecipe(recipe)
        } catch (ex: Exception) {
            null
        }
    }

    override suspend fun getRecipes(): List<Recipe> {
       return try {
      dao.getRecipes()
        } catch (ex: Exception) {
            emptyList()
        }
    }

    override suspend fun updateFavorite(id:Int,isFavourite: Boolean) {
        try {
            dao.updateFavorite(id,isFavourite)
        }catch (_:Exception){
            null
        }
    }

    override suspend fun getRecipeUsingID(id: Int): Recipe? {
        return try {
            dao.getRecipeUsingID(id)
        }catch (ex:Exception){
           null
        }
    }

    override suspend fun updateImage(id: Int, image: String) {
        try {
            dao.updateImage(id,image)
        }catch (_:Exception){
            null
        }
    }


}