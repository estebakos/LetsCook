package com.estebakos.letscook.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.estebakos.letscook.domain.usecase.GetRecipeDetailUseCase
import javax.inject.Inject

class RecipeDetailViewModelFactory @Inject constructor(
    private val getRecipeDetailUseCase: GetRecipeDetailUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RecipeDetailViewModel::class.java)) {
            return RecipeDetailViewModel(
                getRecipeDetailUseCase
            ) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}