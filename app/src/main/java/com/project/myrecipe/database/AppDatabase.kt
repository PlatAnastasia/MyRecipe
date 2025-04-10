package com.project.myrecipe.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(version = 1, entities = [Recipe::class], exportSchema = false)
abstract class DB:RoomDatabase(){
    abstract fun getDbDao(): RecipesDao
}