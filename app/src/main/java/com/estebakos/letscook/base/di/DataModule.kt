package com.estebakos.letscook.base.di

import com.estebakos.letscook.data.RecipesRepositoryImpl
import com.estebakos.letscook.data.local.datasource.RecipesLocalDataSource
import com.estebakos.letscook.data.remote.datasource.RecipesRemoteDataSource
import com.estebakos.letscook.domain.repository.RecipesRepository
import dagger.Module
import dagger.Provides

@Module(includes = [NetworkModule::class])
object DataModule {

    @Provides
    @JvmStatic
    internal fun providesRecipesRepository(
        recipesRemoteDataSource: RecipesRemoteDataSource,
        recipesLocalDataSource: RecipesLocalDataSource
    ): RecipesRepository =
        RecipesRepositoryImpl(recipesRemoteDataSource, recipesLocalDataSource)
}