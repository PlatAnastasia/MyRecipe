package com.project.myrecipe.screens.Favourites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.myrecipe.domain.RecipeDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(
    private val FaivouritesInteractor: FaivouritesInteractor
) : ViewModel() {

    private val _recipe = MutableStateFlow<List<RecipeDomain>>(emptyList())
    val recipe: StateFlow<List<RecipeDomain>> = _recipe


    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun updateFavouriteRecipe(id:Int,isFav:Boolean){
        viewModelScope.launch {
            val newState = !isFav
            FaivouritesInteractor.updateFavouriteRecipe(id,newState).collect { result ->
                when (result) {
                    UpdateFavRecipe.Empty -> {}
                    is UpdateFavRecipe.Success -> {
                        _recipe.value = result.favRecipes
                    }
                }
            }
        }
    }
    fun getFavRecipes(){
        viewModelScope.launch {
            FaivouritesInteractor.getFavRecipes().collect(){
                when(it){
                    is GetFavourites.Empty -> {
                        _recipe.value = emptyList()
                        _error.value = it.error
                    }
                    is GetFavourites.Success -> {
                        _recipe.value = it.favRecipes
                        _error.value = null
                    }
                }
            }
        }
    }

}