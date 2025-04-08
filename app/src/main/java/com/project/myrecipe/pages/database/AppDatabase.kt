package com.project.myrecipe.pages.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(version = 1, entities = [], exportSchema = false)
abstract class DB:RoomDatabase(){
    abstract fun getDbDao():RecipesDao
}