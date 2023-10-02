package com.example.sisterslabprojectrecipes.add

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sisterslabprojectrecipes.model.CrudResponse
import com.example.sisterslabprojectrecipes.model.RecipeRequest
import com.example.sisterslabprojectrecipes.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel


class AddViewModel  @Inject constructor(private var service : RecipeRepository): ViewModel() {

    val recipeAdd = MutableLiveData<CrudResponse>()

    suspend fun addRecipes(foodName: String, recipe: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = service.addRecipe(RecipeRequest(foodName, recipe))
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    recipeAdd.value = response.body()
                } else {

                }
            }
        }
    }
}