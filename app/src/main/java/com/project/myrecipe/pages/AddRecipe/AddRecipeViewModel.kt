package com.project.myrecipe.pages.AddRecipe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.myrecipe.pages.database.Recipe
import com.project.myrecipe.pages.domain.RecipeDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddRecipeViewModel @Inject constructor(
    private val AddRecipeInteractor: AddRecipeInteractor
) : ViewModel() {

    private val _recipe = MutableStateFlow<RecipeDomain?>(null)
    val recipe: StateFlow<RecipeDomain?> = _recipe

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun saveRecipe(title:String,descr:String?,ingr:String,isFavourite:Boolean) {
        viewModelScope.launch {
            if (title != null) {
                AddRecipeInteractor.saveRecipe(title,descr,ingr,isFavourite).collect {
                    when (it) {
                        is InsertRecipe.Success -> {
                            _error.value = null
                            _recipe.value = it.recipe
                        }

                        is InsertRecipe.Empty -> {
                            _error.value = it.error
                            _recipe.value = null
                        }

                    }
                }
            }
        }

    }

    fun getRecipeUsingID(id:Int) {
        viewModelScope.launch {
                AddRecipeInteractor.getRecipeUsingID(id).collect {
                    when (it) {
                        is GetRecipe.Empty -> {
                            _error.value = it.error
                            _recipe.value = null
                        }
                        is GetRecipe.Success -> {
                            _recipe.value = it.recipe
                            _error.value = null
                        }
                   }
                }

        }

    }

}