package com.estebakos.letscook.domain.usecase

import com.estebakos.letscook.base.Output
import com.estebakos.letscook.domain.repository.RecipesRepository
import com.estebakos.letscook.ui.model.RecipeDetailUI
import javax.inject.Inject

class GetRecipeDetailUseCase @Inject constructor(private val recipesRepository: RecipesRepository) {

    suspend fun execute(id: Int): Output<RecipeDetailUI> = recipesRepository.getRecipeDetail(id)
}