package com.example.sisterslabprojectrecipes.util

import android.view.View
import androidx.navigation.NavDirections
import androidx.navigation.Navigation

fun Navigation.fabNavigate(id: Int, it: View) {
    findNavController(it).navigate(id)
}

fun Navigation.itemNavigate(it: View, id: NavDirections) {
    findNavController(it).navigate(id)
}

