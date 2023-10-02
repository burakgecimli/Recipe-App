package com.example.sisterslabprojectrecipes.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.sisterslabprojectrecipes.R
import com.example.sisterslabprojectrecipes.databinding.RecipeCardBinding
import com.example.sisterslabprojectrecipes.model.Recipe
import com.example.sisterslabprojectrecipes.util.itemNavigate

class RecipeAdapter(private val mContext: Context,
                    private val recipeList: List<Recipe>,
                    val viewModel: RecipeHomeViewModel) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    class RecipeViewHolder(val binding:RecipeCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(recipe: Recipe) {
            with(binding) {
                selectedRecipe = recipe
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val layoutInflater = LayoutInflater.from(mContext)
        val binding: RecipeCardBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.recipe_card, parent, false)
        return RecipeViewHolder(binding)
    }

    override fun getItemCount(): Int = recipeList.size

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipeList[position]
        holder.bind(recipe)


        //Bu şekilde gitmek mantıklı değil invoke ile geçiş yapılmalı.
        holder.binding.cardview.setOnClickListener {
            val navigate = RecipeHomeFragmentDirections.actionRecipeListFragmentToRecipeDetailFragment(recipe.id)
            Navigation.itemNavigate(it, navigate)
        }

    }

}



