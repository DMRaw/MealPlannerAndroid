package com.dmraw.mealplanner

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.dmraw.mealplanner.databinding.FragmentRecipeModifyBinding

class RecipeModifyFragment : Fragment() {

    private var binding: FragmentRecipeModifyBinding? = null
    private val sharedViewModel: RecipeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentRecipeModifyBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            fragment = this@RecipeModifyFragment
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    fun saveRecipe() {
        if (checkModelForSaving()) {

            sharedViewModel.setName(binding?.recipeNameEditText?.text.toString())
            sharedViewModel.setUri(binding?.sourceEditText?.text.toString())
            sharedViewModel.setImageUri(binding?.sourcePictureEditText?.text.toString())
            sharedViewModel.setVideoUri(binding?.sourceVideoEditText?.text.toString())
            sharedViewModel.setIngrediants(binding?.ingredients?.text.toString())
            sharedViewModel.setSteps(binding?.recipeSteps?.text.toString())
            sharedViewModel.setSaveModel(true)

            val action = RecipeModifyFragmentDirections.actionRecipeModifyFragmentToRecipeFragment()
            findNavController().navigate(action)
        }
    }

    private fun checkModelForSaving() : Boolean {
        var readyToSave = true

        if (sharedViewModel.equals("")) {
            Toast.makeText(
                this.context,
                getString(R.string.recipe_name_not_filled),
                Toast.LENGTH_SHORT
            ).show()

            readyToSave = false
        }

        return readyToSave
    }
}