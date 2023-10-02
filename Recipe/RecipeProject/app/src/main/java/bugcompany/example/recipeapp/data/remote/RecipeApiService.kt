package com.example.sisterslabprojectrecipes.data.remote

import com.example.sisterslabprojectrecipes.common.Constans.ADD_RECIPES
import com.example.sisterslabprojectrecipes.common.Constans.GET_DETAIL_RECIPES
import com.example.sisterslabprojectrecipes.common.Constans.GET_RECIPES
import com.example.sisterslabprojectrecipes.common.Constans.SEARCH_RECIPES
import com.example.sisterslabprojectrecipes.common.Constans.UPDATE_RECIPES
import com.example.sisterslabprojectrecipes.model.CrudResponse
import com.example.sisterslabprojectrecipes.model.RecipeDetailResponse
import com.example.sisterslabprojectrecipes.model.RecipeRequest
import com.example.sisterslabprojectrecipes.model.Recipe
import com.example.sisterslabprojectrecipes.model.RecipeListModel
import com.example.sisterslabprojectrecipes.model.RecipeResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface RecipeApiService {

    @GET(GET_RECIPES)
    suspend fun recipes(): Response<RecipeResponse>

    @GET(SEARCH_RECIPES)
    suspend fun recipeSearch(@Query("query") query: String): Response<RecipeListModel>

    @GET(GET_DETAIL_RECIPES)
    suspend fun recipeDetail(@Query("id") id: Int): Response<RecipeDetailResponse>

    @POST(ADD_RECIPES)
    suspend fun addRecipe(@Body request: RecipeRequest): Response<CrudResponse>

    @POST(UPDATE_RECIPES)
    suspend fun recipeUpdate(@Body request: Recipe): Response<CrudResponse>

}