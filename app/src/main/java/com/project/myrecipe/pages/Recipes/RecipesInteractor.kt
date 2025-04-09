package com.project.myrecipe.pages.Recipes

import android.util.Log
import com.project.myrecipe.pages.AddRecipe.InsertRecipe
import com.project.myrecipe.pages.domain.RecipeDomain
import com.project.myrecipe.pages.domain.toDomainList
import com.project.myrecipe.pages.generalFunctions.safeAsync
import com.project.myrecipe.pages.repositories.RecipeRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface RecipesInteractor {

    fun getAllRecipes(): Flow<GetRecipes>

    fun updateFavouriteRecipe(id:Int,isFav:Boolean): Flow<UpdateFav>
    fun   updateImage(id: Int,image:String): Flow<UpdateImage>
}

class RecipesInteractorImpl @Inject constructor(
    private val recipeRepo: RecipeRepo
):
    RecipesInteractor{
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

    override fun updateImage(id: Int, image: String): Flow<UpdateImage> = flow<UpdateImage> {
        recipeRepo.updateImage(id,image)
        val updatedList= recipeRepo.getRecipes()
        emit(UpdateImage.Success(updatedList.toDomainList()))
    }.safeAsync{
        UpdateImage.Empty
    }


}




sealed class  GetRecipes{
    data class Success(val recipe: List<RecipeDomain>):GetRecipes()
    data class Empty(val error:String):GetRecipes()
}

sealed class  UpdateFav{
    data class Success(val recipe: List<RecipeDomain>):UpdateFav()
    data object Empty:UpdateFav()
}

sealed class  UpdateImage{
    data class Success(val recipe: List<RecipeDomain>):UpdateImage()
    data object Empty:UpdateImage()
}