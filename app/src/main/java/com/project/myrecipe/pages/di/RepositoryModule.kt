package com.project.myrecipe.pages.di

import com.project.myrecipe.pages.repositories.RecipeRepo
import com.project.myrecipe.pages.repositories.RecipeRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideRecipeRepository(impl:RecipeRepoImpl):RecipeRepo = impl
}