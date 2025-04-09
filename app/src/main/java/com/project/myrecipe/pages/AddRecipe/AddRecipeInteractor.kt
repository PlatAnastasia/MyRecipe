package com.project.myrecipe.pages.AddRecipe

import android.util.Log
import com.project.myrecipe.pages.database.Recipe
import com.project.myrecipe.pages.domain.RecipeDomain
import com.project.myrecipe.pages.domain.toDomain
import com.project.myrecipe.pages.generalFunctions.safeAsync
import com.project.myrecipe.pages.repositories.RecipeRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface AddRecipeInteractor {
   fun saveRecipe(title:String,descr:String?,ingr:String,isFavourite:Boolean): Flow<InsertRecipe>

   fun getRecipeUsingID(id:Int): Flow<GetRecipe>
}

class AddRecipeInteractorImpl @Inject constructor(
    private val recipeRepo: RecipeRepo
):
    AddRecipeInteractor{
    override fun saveRecipe(title:String,descr:String?,ingr:String,isFavourite:Boolean): Flow<InsertRecipe> = flow<InsertRecipe> {
       if (!title.isNullOrEmpty() ){
           val recipe= Recipe(title,descr,isFavourite,ingr,null)
           recipeRepo.saveRecipe(recipe)
           emit(InsertRecipe.Success(recipe.toDomain()))
       }else{
           emit(InsertRecipe.Empty("Ο τιτλος δεν μπορεί να είναι κενός!"))
       }

    }.safeAsync{
        InsertRecipe.Empty(it.localizedMessage)
    }

    override fun getRecipeUsingID(id: Int): Flow<GetRecipe> = flow<GetRecipe> {
        val recipe = recipeRepo.getRecipeUsingID(id)
           if(recipe!=null){
               emit(GetRecipe.Success(recipe.toDomain()))
           }else{
               emit(GetRecipe.Empty("Δεν βρέθηκε συνταγή!"))
           }

    }.safeAsync{
        GetRecipe.Empty(it.localizedMessage)
    }
}

sealed class  InsertRecipe{
    data class Success(val recipe: RecipeDomain):InsertRecipe()
    data class Empty(val error:String):InsertRecipe()
}

sealed class  GetRecipe{
    data class Success(val recipe: RecipeDomain):GetRecipe()
    data class Empty(val error:String):GetRecipe()
}