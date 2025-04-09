package com.project.myrecipe.pages.Recipes

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.myrecipe.pages.AddRecipe.InsertRecipe
import com.project.myrecipe.pages.domain.RecipeDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    private val RecipesInteractor: RecipesInteractor
) : ViewModel() {

    private val _recipe = MutableStateFlow<List<RecipeDomain>>(emptyList())
    val recipe: StateFlow<List<RecipeDomain>> = _recipe


    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error


    fun getAllRecipes() {
        viewModelScope.launch {
            RecipesInteractor.getAllRecipes().collect { result ->
                when (result) {
                    is GetRecipes.Empty -> {
                        _recipe.value = emptyList()
                        _error.value = result.error
                    }
                    is GetRecipes.Success -> {
                        _recipe.value = result.recipe
                        _error.value = null
                    }
                }
            }
        }
    }

    fun updateFavouriteRecipe(id:Int,isFav:Boolean){
        viewModelScope.launch {
            val newState = !isFav
            RecipesInteractor.updateFavouriteRecipe(id,newState).collect { result ->
                when (result) {
                    UpdateFav.Empty -> TODO()
                    is UpdateFav.Success -> {
                        _recipe.value = result.recipe
                    }
                }
            }
        }
    }

    fun updateImage(id:Int,image:String){
        viewModelScope.launch {
            RecipesInteractor.updateImage(id,image).collect { result ->
                when (result) {
                    UpdateImage.Empty -> TODO()
                    is UpdateImage.Success -> { _recipe.value = result.recipe}
                }
            }
        }
    }


}