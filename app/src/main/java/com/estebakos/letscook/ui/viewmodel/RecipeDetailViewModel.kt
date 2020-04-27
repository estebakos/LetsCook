package com.estebakos.letscook.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.estebakos.letscook.base.Output
import com.estebakos.letscook.domain.usecase.GetRecipeDetailUseCase
import com.estebakos.letscook.ui.model.RecipeDetailUI
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


class RecipeDetailViewModel @Inject constructor(
    private val getRecipeDetailUseCase: GetRecipeDetailUseCase
) : ViewModel() {

    private val recipeDetailLiveData: MutableLiveData<RecipeDetailUI> = MutableLiveData()
    private val emptyItemsLiveData: MutableLiveData<Boolean> = MutableLiveData()
    private val errorLiveData: MutableLiveData<Boolean> = MutableLiveData()
    private val loadingLiveData: MutableLiveData<Boolean> = MutableLiveData()

    val recipeDetail: LiveData<RecipeDetailUI>
        get() = recipeDetailLiveData

    val empty: LiveData<Boolean>
        get() = emptyItemsLiveData

    val error: LiveData<Boolean>
        get() = errorLiveData

    val loading: LiveData<Boolean>
        get() = loadingLiveData

    fun loadRecipe(id: Int) {
        loadingLiveData.postValue(true)
        viewModelScope.launch {
            val output = getRecipeDetailUseCase.execute(id)
            if (output is Output.Success) {
                onGetRecipeDetailSuccess(output.data)
            } else {
                onError()
            }
        }
    }

    private fun onGetRecipeDetailSuccess(recipe: RecipeDetailUI) {
        recipeDetailLiveData.postValue(recipe)
        loadingLiveData.postValue(false)
    }

    private fun onError() {
        viewModelScope.launch {
            delay(300)
            loadingLiveData.postValue(false)
        }.invokeOnCompletion {
            errorLiveData.postValue(true)
        }
    }
}