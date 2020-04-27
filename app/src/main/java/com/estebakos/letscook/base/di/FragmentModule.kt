package com.estebakos.letscook.base.di

import com.estebakos.letscook.ui.fragment.RecipeDetailFragment
import com.estebakos.letscook.ui.fragment.RecipesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun bindRecipesFragment(): RecipesFragment

    @ContributesAndroidInjector
    abstract fun bindRecipeDetailFragment(): RecipeDetailFragment
}