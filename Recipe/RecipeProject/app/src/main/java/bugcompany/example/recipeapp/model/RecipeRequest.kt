package com.example.sisterslabprojectrecipes.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RecipeRequest(
    @SerializedName
        ("name") var name: String, @SerializedName
        ("description") var description: String
) : Serializable {
}
