package com.project.myrecipe.pages.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface RecipesDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewRecipe(recipe:Recipe)

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertIngredients(ingr:Ιngredients)
}