package com.dmraw.mealplanner

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.dmraw.mealplanner.databinding.FragmentRecipeBinding

class RecipeFragment : Fragment() {

    private var binding: FragmentRecipeBinding? = null
    private val sharedViewModel : RecipeViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentRecipeBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.recipe_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_edit_recipe -> {
                val action = RecipeFragmentDirections.actionRecipeFragmentToRecipeModifyFragment()
                findNavController().navigate(action)

                return true
            }
            android.R.id.home -> {
                findNavController().navigate(R.id.recipeBookFragment)

                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}