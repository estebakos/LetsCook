package com.estebakos.letscook.data

import com.estebakos.letscook.base.Output
import com.estebakos.letscook.data.local.datasource.RecipesLocalDataSource
import com.estebakos.letscook.data.remote.datasource.RecipesRemoteDataSource
import com.estebakos.letscook.domain.repository.RecipesRepository
import com.estebakos.letscook.ui.model.RecipeDetailUI
import com.estebakos.letscook.ui.model.RecipeUI
import javax.inject.Inject

class RecipesRepositoryImpl @Inject constructor(
    private val recipesRemoteDataSource: RecipesRemoteDataSource,
    private val recipesLocalDataSource: RecipesLocalDataSource
) : RecipesRepository {
    override suspend fun getRecipes(): Output<List<RecipeUI>> {
        return recipesRemoteDataSource.getRecipes()
    }

    override suspend fun getRecipeDetail(id: Int): Output<RecipeDetailUI> {
        return recipesRemoteDataSource.getRecipeDetail(id)
    }

    override suspend fun getLocalRecipeList(): Output<List<RecipeUI>> {
        return recipesLocalDataSource.getRecipeList()
    }

    override suspend fun getLocalRecipesByQuery(query: String): Output<List<RecipeUI>> {
        return recipesLocalDataSource.searchByQuery(query)
    }

    override suspend fun insertRecipeList(recipes: List<RecipeUI>) {
        recipesLocalDataSource.insertItems(recipes)
    }

}