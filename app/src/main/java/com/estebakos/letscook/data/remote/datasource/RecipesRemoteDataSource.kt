package com.estebakos.letscook.data.remote.datasource

import com.estebakos.letscook.base.Output
import com.estebakos.letscook.data.RecipesDataMapper
import com.estebakos.letscook.data.remote.api.RecipesApi
import com.estebakos.letscook.ui.model.RecipeDetailUI
import com.estebakos.letscook.ui.model.RecipeUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RecipesRemoteDataSource @Inject constructor(
    private val recipesApi: RecipesApi
) {

    suspend fun getRecipes(): Output<List<RecipeUI>> =
        try {
            val recipesResponse = withContext(Dispatchers.IO) {
                recipesApi.getRecipes()
            }

            val recipeList = RecipesDataMapper.RecipeListRemoteToUI.map(recipesResponse)
            Output.Success(recipeList)
        } catch (e: Exception) {
            Output.Error(e)
        }

    suspend fun getRecipeDetail(id: Int): Output<RecipeDetailUI> =
        try {
            val recipeDetailResponse = withContext(Dispatchers.IO) {
                recipesApi.getRecipeDetail(id)
            }

            val recipeDetail = RecipesDataMapper.RecipeDetailRemoteToUI.map(recipeDetailResponse)
            Output.Success(recipeDetail)
        } catch (e: Exception) {
            Output.Error(e)
        }
}