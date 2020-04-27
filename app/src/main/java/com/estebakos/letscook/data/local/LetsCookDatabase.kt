package com.estebakos.letscook.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.estebakos.letscook.data.local.dao.RecipesDao
import com.estebakos.letscook.data.local.entity.RecipeEntity

@Database(
    entities = [RecipeEntity::class],
    version = 1
)
abstract class LetsCookDatabase : RoomDatabase() {
    abstract fun recipesDao(): RecipesDao
}