package com.example.sisterslabprojectrecipes.model

import com.google.gson.annotations.SerializedName

data class RecipeDetailResponse (@SerializedName("recipe") var recipe:Recipe,
                                 @SerializedName("status") var status:Int,
                                 @SerializedName("message")var message:String){
}