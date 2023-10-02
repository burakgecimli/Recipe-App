package com.example.sisterslabprojectrecipes.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.sisterslabprojectrecipes.R
import com.example.sisterslabprojectrecipes.databinding.FragmentRecipeDetailBinding
import com.example.sisterslabprojectrecipes.databinding.FragmentRecipeHomeBinding
import com.example.sisterslabprojectrecipes.util.fabNavigate
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class RecipeHomeFragment : Fragment(), SearchView.OnQueryTextListener {
    private var _binding: FragmentRecipeHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var recipeHomeViewModel: RecipeHomeViewModel
    private lateinit var adapter: RecipeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipeHomeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.floatActionBarComment.setOnClickListener {
            findNavController().navigate(R.id.action_recipeListFragment_to_signInUpFragment)
        }

        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        val homeViewModel: RecipeHomeViewModel by viewModels()
        recipeHomeViewModel = homeViewModel

        binding.recipeListFragment = this

        recipeHomeViewModel.getRecipes()

        recipeHomeViewModel.recipesList.observe(viewLifecycleOwner) { recipe ->
            adapter = RecipeAdapter(requireContext(), recipe, recipeHomeViewModel)
            binding.racipeListRV.adapter = adapter
            adapter.notifyDataSetChanged()
        }

        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.toolbar_menu, menu)

                val item = menu.findItem(R.id.action_search)
                val searchView = item.actionView as SearchView
                searchView.setOnQueryTextListener(this@RecipeHomeFragment)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return false
            }
        })
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        viewLifecycleOwner.lifecycleScope.launch {
            if (query != null) {
                recipeHomeViewModel.recipeSearch(query)
                recipeHomeViewModel.recipeSearch.observe(viewLifecycleOwner) {recipe->
                    adapter = RecipeAdapter(requireContext(), recipe.recipes, recipeHomeViewModel)
                    binding.racipeListRV.adapter = adapter
                    adapter.notifyDataSetChanged()
                }
            }
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        viewLifecycleOwner.lifecycleScope.launch {
            if (newText != null) {
                recipeHomeViewModel.recipeSearch(newText)
                recipeHomeViewModel.recipeSearch.observe(viewLifecycleOwner) {
                    adapter = RecipeAdapter(requireContext(), it.recipes, recipeHomeViewModel)
                    binding.racipeListRV.adapter = adapter
                    adapter.notifyDataSetChanged()
                }
            }
        }
        return true
    }

    fun floatActionBarClick(it: View) {
        Navigation.fabNavigate(R.id.action_recipeListFragment_to_addFragment, it)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}