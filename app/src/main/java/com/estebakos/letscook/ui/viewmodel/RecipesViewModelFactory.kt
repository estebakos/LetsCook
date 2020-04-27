package com.estebakos.letscook.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.estebakos.letscook.domain.usecase.GetRecipesUseCase
import com.estebakos.letscook.domain.usecase.SearchRecipeUseCase
import javax.inject.Inject

class RecipesViewModelFactory @Inject constructor(
    private val getRecipesUseCase: GetRecipesUseCase,
    private val searchRecipeUseCase: SearchRecipeUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RecipesViewModel::class.java)) {
            return RecipesViewModel(
                getRecipesUseCase,
                searchRecipeUseCase
            ) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}