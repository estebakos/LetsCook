package com.estebakos.letscook.base.di

import android.content.Context
import androidx.room.Room
import com.estebakos.letscook.data.local.LetsCookDatabase
import com.estebakos.letscook.data.local.dao.RecipesDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object CacheModule {

    @Singleton
    @Provides
    @JvmStatic
    fun provideRoomDatabase(context: Context): LetsCookDatabase {
        return Room.databaseBuilder(context, LetsCookDatabase::class.java, "lets-cook.db")
            .build()
    }

    @Singleton
    @Provides
    fun provideStorageInfoDao(database: LetsCookDatabase): RecipesDao {
        return database.recipesDao()
    }
}