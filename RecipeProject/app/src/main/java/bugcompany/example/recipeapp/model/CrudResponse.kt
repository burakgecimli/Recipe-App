package com.example.sisterslabprojectrecipes.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class CrudResponse(
    @SerializedName("status")
    var status: Int,
    @SerializedName("message")
    var message: String
) : Serializable
