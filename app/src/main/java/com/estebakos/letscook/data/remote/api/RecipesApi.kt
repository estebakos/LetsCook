package com.estebakos.letscook.data.remote.api

import com.estebakos.letscook.data.remote.model.RecipeDetailResponse
import com.estebakos.letscook.data.remote.model.RecipeListResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface RecipesApi {

    @GET("recipes/{id}")
    suspend fun getRecipeDetail(
        @Path("id") id: Int
    ): RecipeDetailResponse

    @GET("recipes")
    suspend fun getRecipes(
    ): List<RecipeListResponse>
}