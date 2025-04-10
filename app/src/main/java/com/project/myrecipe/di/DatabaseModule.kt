package com.project.myrecipe.di

import android.content.Context
import androidx.room.Room
import com.project.myrecipe.database.DB
import com.project.myrecipe.database.RecipesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): DB {
        return Room.databaseBuilder(appContext, DB::class.java,"sqlite_recipes").build()
    }

    @Provides
    @Singleton
    fun provideDao(appDatabase: DB): RecipesDao {
        return appDatabase.getDbDao()
    }
}