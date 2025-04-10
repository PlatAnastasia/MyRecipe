package com.project.myrecipe.di

import com.project.myrecipe.repositories.RecipeRepo
import com.project.myrecipe.repositories.RecipeRepoImpl
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
    fun provideRecipeRepository(impl: RecipeRepoImpl): RecipeRepo = impl
}