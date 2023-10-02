package com.example.sisterslabprojectrecipes.data.remote

import com.example.sisterslabprojectrecipes.common.Constans.BASE_URL

class ApiUtils {
    companion object {

        fun getRecipeService(): RecipeApiService {
            return RetrofitCreate.getClient(BASE_URL).create(RecipeApiService::class.java)
        }
    }
}