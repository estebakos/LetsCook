package com.estebakos.letscook.data.remote.model

import com.squareup.moshi.Json

data class RecipeListResponse(
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "title")
    val title: String
)