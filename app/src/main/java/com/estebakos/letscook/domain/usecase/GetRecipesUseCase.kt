package com.estebakos.letscook.domain.usecase

import com.estebakos.letscook.base.Output
import com.estebakos.letscook.domain.repository.RecipesRepository
import com.estebakos.letscook.ui.model.RecipeUI
import java.io.IOException
import javax.inject.Inject

class GetRecipesUseCase @Inject constructor(private val recipesRepository: RecipesRepository) {

    suspend fun execute(): Output<List<RecipeUI>> {
        var recipeListOutput: Output<List<RecipeUI>> = Output.Success((listOf()))

        recipesRepository.getRecipes().let { output ->
            recipeListOutput = if (output is Output.Success) {
                recipesRepository.insertRecipeList(output.data)
                Output.Success(output.data)
            } else {
                Output.Error(IOException())
            }
        }

        if (recipeListOutput is Output.Error) {
            recipesRepository.getLocalRecipeList().let { output ->
                recipeListOutput = if (output is Output.Success) {
                    Output.Success(output.data)
                } else {
                    Output.Error(IOException())
                }
            }
        }

        return recipeListOutput
    }
}