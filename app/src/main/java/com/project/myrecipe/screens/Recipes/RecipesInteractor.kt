package com.project.myrecipe.screens.Recipes

import android.util.Log
import com.project.myrecipe.domain.RecipeDomain
import com.project.myrecipe.domain.toDomainList
import com.project.myrecipe.generalFunctions.safeAsync
import com.project.myrecipe.repositories.RecipeRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface RecipesInteractor {

    fun getAllRecipes(): Flow<GetRecipes>

    fun updateFavouriteRecipe(id:Int,isFav:Boolean): Flow<UpdateFav>

}

class RecipesInteractorImpl @Inject constructor(
    private val recipeRepo: RecipeRepo
):
    RecipesInteractor {
    override fun getAllRecipes(): Flow<GetRecipes> = flow {
            val recipesList = recipeRepo.getRecipes()
            Log.d("recipe",recipesList.toString())
            if (recipesList.isNotEmpty()) {
                emit(GetRecipes.Success(recipesList.toDomainList()))
            } else {
                emit(GetRecipes.Empty("Δεν υπάρχουν διαθέσιμες συνταγές!"))
            }
        }.safeAsync{
        GetRecipes.Empty(it.localizedMessage)
        }

    override fun updateFavouriteRecipe(id: Int, isFav: Boolean): Flow<UpdateFav> = flow <UpdateFav>{
        recipeRepo.updateFavorite(id,isFav)
        val updatedList= recipeRepo.getRecipes()
        emit(UpdateFav.Success(updatedList.toDomainList()))
    }.safeAsync{
        UpdateFav.Empty
    }

}




sealed class  GetRecipes{
    data class Success(val recipe: List<RecipeDomain>): GetRecipes()
    data class Empty(val error:String): GetRecipes()
}

sealed class  UpdateFav{
    data class Success(val recipe: List<RecipeDomain>): UpdateFav()
    data object Empty: UpdateFav()
}

sealed class  UpdateImage{
    data class Success(val recipe: List<RecipeDomain>): UpdateImage()
    data object Empty: UpdateImage()
}