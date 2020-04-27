package com.estebakos.letscook.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.estebakos.letscook.data.local.entity.RecipeEntity

@Dao
interface RecipesDao {
    @Query("SELECT * FROM recipe_entity")
    suspend fun getAll(): List<RecipeEntity>

    @Query("SELECT * FROM recipe_entity WHERE title LIKE :query")
    suspend fun findByQuery(query: String): List<RecipeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(item: List<RecipeEntity>)

    @Query("DELETE FROM recipe_entity")
    suspend fun deleteAll()
}