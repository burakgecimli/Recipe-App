package com.example.sisterslabprojectrecipes.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.sisterslabprojectrecipes.databinding.FragmentRecipeDetailBinding

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class RecipeDetailFragment : Fragment() {
    private var _binding: FragmentRecipeDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: RecipeDetailViewModel
    private val recipeDetailArgs: RecipeDetailFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecipeDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val detailViewModel: RecipeDetailViewModel by viewModels()
        viewModel = detailViewModel


        binding.apply {
            viewModel = detailViewModel
            recipeDetailFragmentToolbar = "Recipe Description"
        }

        val recipeId = recipeDetailArgs.recipeId

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.recipeDetail(recipeId)
        }

        viewModel.recipeDetail.observe(viewLifecycleOwner) {
            binding.recipeModel = it?.recipe
        }

        binding.buttonUpdate.setOnClickListener {
            val recipeName = binding.editTextAddName.text.toString()
            val recipeDescription = binding.editTextAddContent.text.toString()

            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.recipeUpdate(recipeDetailArgs.recipeId, recipeName, recipeDescription)
            }

            viewModel.recipeDetail.observe(viewLifecycleOwner) { recipe ->
                if (recipe != null) {
                    Toast.makeText(
                        requireContext(),
                        "Recipe Uptaded Succesfully",
                        Toast.LENGTH_SHORT
                    ).show()
                    val action =
                        RecipeDetailFragmentDirections.actionRecipeDetailFragmentToRecipeListFragment()
                    Navigation.findNavController(it).navigate(action)
                } else {
                    Toast.makeText(requireContext(), "Recipe Failed Update", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}


