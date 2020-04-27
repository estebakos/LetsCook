package com.estebakos.letscook.base.di

import com.estebakos.letscook.ui.activity.RecipeDetailActivity
import com.estebakos.letscook.ui.activity.RecipesActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun bindRecipesActivity(): RecipesActivity

    @ContributesAndroidInjector
    abstract fun bindRecipeDetailActivity(): RecipeDetailActivity
}