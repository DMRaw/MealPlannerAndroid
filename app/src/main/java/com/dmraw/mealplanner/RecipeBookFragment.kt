package com.dmraw.mealplanner

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dmraw.mealplanner.databinding.FragmentRecipeBookBinding

class RecipeBookFragment : Fragment() {
    val recipeBook : RecipeBookViewModel by viewModels()
    private var recipeAdapter = RecipeAdapter(recipeBook.book.value!!)
    private val sharedViewModel : RecipeViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentRecipeBookBinding.inflate(inflater)

        binding.lifecycleOwner = this
        binding.recipeBookRecycler.layoutManager = LinearLayoutManager(this.context)
        binding.recipeBookRecycler.adapter = recipeAdapter
        binding.recipeBook = recipeBook

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        if (sharedViewModel.saveModel) {

            sharedViewModel.setSaveModel(false)
            if (sharedViewModel.id == -1) {
                val position = recipeBook.addRecipe(sharedViewModel.copy())
                recipeAdapter.notifyItemInserted(position)
            } else {
                recipeBook.modifyRecipe(sharedViewModel.id,sharedViewModel)
                recipeAdapter.notifyItemChanged(sharedViewModel.id)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.recipe_book_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {

            R.id.action_add_recipe -> {
                sharedViewModel.reset()
                val action = RecipeBookFragmentDirections.actionRecipeBookFragmentToRecipeModifyFragment()
                findNavController().navigate(action)

                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}