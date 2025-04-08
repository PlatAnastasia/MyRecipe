package com.project.myrecipe.pages.di

import com.project.myrecipe.pages.AddRecipe.AddRecipeInteractor
import com.project.myrecipe.pages.AddRecipe.AddRecipeInteractorImpl
import com.project.myrecipe.pages.mainMenu.MainMenuInteractor
import com.project.myrecipe.pages.mainMenu.MainMenuInteractorImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class InteractorModule {

    @Provides
    fun provideMainMenuInteractor(impl: MainMenuInteractorImpl): MainMenuInteractor = impl

    @Provides
    fun provideAddRecipeInteractor(impl: AddRecipeInteractorImpl): AddRecipeInteractor = impl


//    @Provides
//    fun provideRecipesInteractor(impl: RecipesInteractorImpl):RecipesInteractor = impl
//
//    @Provides
//    fun provideFavouritesInteractor(impl: FaivouritesInteractorImpl):FaivouritesInteractor = impl

}