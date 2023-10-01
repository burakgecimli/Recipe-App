package com.example.sisterslabprojectrecipes.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sisterslabprojectrecipes.model.Recipe
import com.example.sisterslabprojectrecipes.model.RecipeListModel
import com.example.sisterslabprojectrecipes.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class RecipeHomeViewModel @Inject constructor(var rrepo : RecipeRepository) : ViewModel() {
    var recipesList = MutableLiveData<List<Recipe>>()
    var recipeSearch = MutableLiveData<RecipeListModel>()

    //Arka planda işlemler için
    fun getRecipes() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = rrepo.getRecipes()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    recipesList.value = response.body()?.recipes
                } else {
                }
            }
        }
    }

    suspend fun recipeSearch(searchWord: String) {
        viewModelScope.launch {
            val response = rrepo.recipeSearch(searchWord)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    recipeSearch.value = response.body()
                } else {
                }
            }
        }
    }
}