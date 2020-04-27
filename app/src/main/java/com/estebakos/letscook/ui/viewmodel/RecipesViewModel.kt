package com.estebakos.letscook.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.estebakos.letscook.base.Output
import com.estebakos.letscook.domain.usecase.GetRecipesUseCase
import com.estebakos.letscook.domain.usecase.SearchRecipeUseCase
import com.estebakos.letscook.ui.model.RecipeUI
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


class RecipesViewModel @Inject constructor(
    private val getRecipesUseCase: GetRecipesUseCase,
    private val searchRecipeUseCase: SearchRecipeUseCase
) : ViewModel() {

    private val recipeListLiveData: MutableLiveData<List<RecipeUI>> = MutableLiveData()
    private val emptyItemsLiveData: MutableLiveData<Boolean> = MutableLiveData()
    private val errorLiveData: MutableLiveData<Boolean> = MutableLiveData()
    private val loadingLiveData: MutableLiveData<Boolean> = MutableLiveData()

    val recipeList: LiveData<List<RecipeUI>>
            get() = recipeListLiveData

    val empty: LiveData<Boolean>
        get() = emptyItemsLiveData

    val error: LiveData<Boolean>
        get() = errorLiveData

    val loading: LiveData<Boolean>
        get() = loadingLiveData

    fun loadRecipes() {
        loadingLiveData.postValue(true)
        viewModelScope.launch {
            val output = getRecipesUseCase.execute()
            if (output is Output.Success) {
                onGetRecipeListSuccess(output.data)
            } else {
                onError()
            }
        }
    }

    fun searchRecipe(query: String) {
        loadingLiveData.postValue(true)
        viewModelScope.launch {
            val output = searchRecipeUseCase.execute(query)
            if (output is Output.Success) {
                onGetRecipeListSuccess(output.data)
            } else {
                onError()
            }
        }
    }

    private fun onGetRecipeListSuccess(recipeList: List<RecipeUI>) {
        if (recipeList.isEmpty()) {
            emptyItemsLiveData.postValue(true)
        } else {
            recipeListLiveData.postValue(recipeList)
        }

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