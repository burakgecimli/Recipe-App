package com.example.sisterslabprojectrecipes.repository

import com.example.sisterslabprojectrecipes.model.Recipe
import com.example.sisterslabprojectrecipes.model.RecipeRequest
import com.example.sisterslabprojectrecipes.data.remote.RecipeApiService


class RecipeRepository (private var service : RecipeApiService){

    suspend fun getRecipes() = service.recipes()

    suspend fun addRecipe(request: RecipeRequest) = service.addRecipe(request)

    suspend fun recipeUpdate(recipe: Recipe) = service.recipeUpdate(recipe)

    suspend fun recipeDetail(id:Int) = service.recipeDetail(id)

    suspend fun recipeSearch(searchWord:String) = service.recipeSearch(searchWord)

}
