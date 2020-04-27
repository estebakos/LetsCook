package com.estebakos.letscook.data.remote.model

import com.squareup.moshi.Json

data class RecipeDetailResponse(
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "title")
    val title: String,
    @field:Json(name = "rating")
    val rating: Int,
    @field:Json(name = "image")
    val image: String,
    @field:Json(name = "instructions")
    val instructions: String
)