package com.example.sisterslabprojectrecipes.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.sisterslabprojectrecipes.databinding.FragmentAddBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class AddFragment : Fragment() {

    private  var _binding: FragmentAddBinding?=null
    private val binding get() = _binding!!
    private lateinit var viewModel: AddViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            val addViewModel: AddViewModel by viewModels()
            viewModel = addViewModel
            viewModel = viewModel
            addFragmentToolbarName = "Recipe Add"
        }

        binding.buttonUpdate.setOnClickListener {
            val recipeName = binding.editTextAddName.text.toString().trim()
            val recipeDescription = binding.editTextAddName.text.toString().trim()
            if (recipeName.isEmpty()) {
                showToast()
                if (recipeDescription.isEmpty()) {
                    showToast()
                } else {
                    viewLifecycleOwner.lifecycleScope.launch {
                        viewModel.addRecipes(recipeName, recipeDescription)
                    }
                    viewModel.recipeAdd.observe(viewLifecycleOwner) { recipe ->
                        if (recipe != null) {
                            Toast.makeText(
                                requireContext(),
                                "Recipe Added Successfully",
                                Toast.LENGTH_SHORT
                            ).show()
                            val action =
                                AddFragmentDirections.actionAddFragmentToRecipeListFragment()
                            Navigation.findNavController(it).navigate(action)
                        } else {
                            Toast.makeText(
                                requireContext(),
                                "Adding Recipe Failed",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }
                    }
                }
            }
        }

    }
    private fun showToast() {
        Toast.makeText(requireContext(), "Empty Value", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
