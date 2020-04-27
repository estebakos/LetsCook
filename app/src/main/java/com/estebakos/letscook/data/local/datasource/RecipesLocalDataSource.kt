package com.estebakos.letscook.data.local.datasource

import com.estebakos.letscook.base.Output
import com.estebakos.letscook.data.RecipesDataMapper
import com.estebakos.letscook.data.local.dao.RecipesDao
import com.estebakos.letscook.ui.model.RecipeUI
import java.io.IOException
import javax.inject.Inject

class RecipesLocalDataSource @Inject constructor(
    private val dao: RecipesDao
) {

    suspend fun getRecipeList(): Output<List<RecipeUI>> =
        try {
            val itemDomain = RecipesDataMapper.RecipeListCacheToUI.map(dao.getAll())
            Output.Success(itemDomain)
        } catch (e: Throwable) {
            Output.Error(IOException("Exception ${e.message}"))
        }

    suspend fun searchByQuery(query: String): Output<List<RecipeUI>> =
        try {
            val itemDomain = RecipesDataMapper.RecipeListCacheToUI.map(dao.findByQuery(query))
            Output.Success(itemDomain)
        } catch (e: Throwable) {
            Output.Error(IOException("Exception ${e.message}"))
        }

    suspend fun insertItems(item: List<RecipeUI>) {
        try {
            dao.deleteAll()
            dao.insertAll(RecipesDataMapper.RecipeListUIToCache.map(item))
        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }
}