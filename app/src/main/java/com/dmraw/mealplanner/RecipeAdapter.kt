package com.dmraw.mealplanner

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView

class RecipeAdapter (val recipes : MutableList<RecipeViewModel>) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    class RecipeViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val card = view.findViewById<CardView>(R.id.item_recipe)
        val name = view.findViewById<TextView>(R.id.text_recipe_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeAdapter.RecipeViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.item_recipe, parent, false)

        layout.accessibilityDelegate = Accessibility
        return RecipeViewHolder(layout)
    }

    override fun onBindViewHolder(holder: RecipeAdapter.RecipeViewHolder, position: Int) {
        val item = recipes.get(position)

        holder.card.setOnClickListener{
            holder.view.findNavController().navigate(R.id.action_recipeBookFragment_to_recipeFragment)
        }
        holder.name.text = item.name.value
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    companion object Accessibility : View.AccessibilityDelegate() {

    }
}