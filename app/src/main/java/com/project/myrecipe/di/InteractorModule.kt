package com.project.myrecipe.di

import com.project.myrecipe.screens.AddRecipe.AddRecipeInteractor
import com.project.myrecipe.screens.AddRecipe.AddRecipeInteractorImpl
import com.project.myrecipe.screens.Favourites.FaivouritesInteractor
import com.project.myrecipe.screens.Favourites.FaivouritesInteractorImpl
import com.project.myrecipe.screens.Recipes.RecipesInteractor
import com.project.myrecipe.screens.Recipes.RecipesInteractorImpl

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
class InteractorModule {


    @Provides

    fun provideAddRecipeInteractor(impl: AddRecipeInteractorImpl): AddRecipeInteractor = impl


    @Provides

    fun provideRecipesInteractor(impl: RecipesInteractorImpl): RecipesInteractor = impl

    @Provides

    fun provideFavouritesInteractor(impl: FaivouritesInteractorImpl): FaivouritesInteractor = impl

}