package com.project.myrecipe.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RecipesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewRecipe(recipe: Recipe)

    @Query("SELECT * from recipes")
    fun getRecipes():List<Recipe>

    @Query("SELECT * from recipes where id =:id ")
    fun getRecipeUsingID(id:Int): Recipe

    @Query("UPDATE recipes SET isFavourite = :isFavorite WHERE id = :recipeId")
    suspend fun updateFavorite(recipeId: Int, isFavorite: Boolean)

    @Query("UPDATE recipes SET image = :image WHERE id = :recipeId")
    suspend fun updateImage(recipeId: Int, image: String)

    @Query("SELECT * FROM recipes WHERE isFavourite = 1")
    suspend fun getFavRecipes(): List<Recipe>



}