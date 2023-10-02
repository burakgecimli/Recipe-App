package com.example.sisterslabprojectrecipes.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sisterslabprojectrecipes.model.RecipeDetailResponse
import com.example.sisterslabprojectrecipes.model.Recipe
import com.example.sisterslabprojectrecipes.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel  @Inject constructor (private var service : RecipeRepository): ViewModel() {

    var recipeDetail = MutableLiveData<RecipeDetailResponse?>()
    private var recipeUpdate = MutableLiveData<String?>()

    suspend fun recipeDetail(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = service.recipeDetail(id)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    recipeDetail.value = response.body()
                } else{
                    println("404 hata")
                }
            }
        }
    }
    suspend fun recipeUpdate(recipeId: Int, recipeName: String, recipe: String){
        val recipe = Recipe(recipeId,recipeName,recipe)
        viewModelScope.launch(Dispatchers.IO) {
            val response = service.recipeUpdate(recipe)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    recipeDetail(recipe.id)
                } else {
                    recipeUpdate.value = response.body()?.message
                }
            }
        }
    }
}