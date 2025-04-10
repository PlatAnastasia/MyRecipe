package com.project.myrecipe.screens.Favourites

import com.project.myrecipe.domain.RecipeDomain
import com.project.myrecipe.domain.toDomainList
import com.project.myrecipe.generalFunctions.safeAsync
import com.project.myrecipe.repositories.RecipeRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface FaivouritesInteractor {

 fun getFavRecipes():Flow<GetFavourites>

    fun updateFavouriteRecipe(id:Int,isFav:Boolean): Flow<UpdateFavRecipe>

}

class FaivouritesInteractorImpl @Inject constructor(
    private val recipeRepo: RecipeRepo
):
    FaivouritesInteractor {
    override fun getFavRecipes(): Flow<GetFavourites> = flow<GetFavourites> {
       val list = recipeRepo.getFavRecipes()
        if (list.isNotEmpty()){
            emit(GetFavourites.Success(list.toDomainList()))
        }else{
            emit(GetFavourites.Empty("Δεν υπάρχουν αγαπημένες συνταγές!"))
        }
    }.safeAsync {
        GetFavourites.Empty(it.localizedMessage)
    }

    override fun updateFavouriteRecipe(id: Int, isFav: Boolean): Flow<UpdateFavRecipe> = flow<UpdateFavRecipe>{
        recipeRepo.updateFavorite(id,isFav)
        val updatedList= recipeRepo.getFavRecipes()
        emit(UpdateFavRecipe.Success(updatedList.toDomainList()))
    }.safeAsync {
        UpdateFavRecipe.Empty
    }


}

sealed class UpdateFavRecipe(){
    data class Success(val favRecipes:List<RecipeDomain>): UpdateFavRecipe()
    data object Empty: UpdateFavRecipe()
}
sealed class GetFavourites(){
    data class Success(val favRecipes:List<RecipeDomain>): GetFavourites()
    data class Empty(val error:String): GetFavourites()
}