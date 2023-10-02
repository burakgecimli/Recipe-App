package com.example.sisterslabprojectrecipes.di

import com.example.sisterslabprojectrecipes.repository.RecipeRepository
import com.example.sisterslabprojectrecipes.data.remote.ApiUtils
import com.example.sisterslabprojectrecipes.data.remote.RecipeApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideRecipeRepository(service: RecipeApiService) : RecipeRepository{
        return RecipeRepository(service)
    }

    @Provides
    @Singleton
    fun provideRecipeDao() : RecipeApiService {
        return ApiUtils.getRecipeService()
    }
}