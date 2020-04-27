package com.estebakos.letscook.domain.repository

import com.estebakos.letscook.base.Output
import com.estebakos.letscook.ui.model.RecipeDetailUI
import com.estebakos.letscook.ui.model.RecipeUI

interface RecipesRepository {

    suspend fun getRecipes(): Output<List<RecipeUI>>
    suspend fun getRecipeDetail(id: Int): Output<RecipeDetailUI>
    suspend fun getLocalRecipeList(): Output<List<RecipeUI>>
    suspend fun getLocalRecipesByQuery(query: String): Output<List<RecipeUI>>
    suspend fun insertRecipeList(recipes: List<RecipeUI>)
}