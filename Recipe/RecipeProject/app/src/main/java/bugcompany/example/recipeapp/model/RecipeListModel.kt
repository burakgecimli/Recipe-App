package com.example.sisterslabprojectrecipes.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class RecipeListModel(
    @SerializedName("recipes")
    var recipes: List<Recipe>,
    @SerializedName("status")
    var status: Int
) : Serializable
