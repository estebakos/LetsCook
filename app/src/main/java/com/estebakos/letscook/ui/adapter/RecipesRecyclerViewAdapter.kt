package com.estebakos.letscook.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.estebakos.letscook.R
import com.estebakos.letscook.ui.model.RecipeUI
import kotlinx.android.synthetic.main.item_recipe.view.*

class RecipesRecyclerViewAdapter(
    private val welcomeList: List<RecipeUI>,
    private val listener: (RecipeUI) -> Unit
) : RecyclerView.Adapter<RecipesRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recipe, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = welcomeList[position]
        holder.run {
            tvTitle.text = item.title
            itemView.setOnClickListener {
                listener(item)
            }
        }
    }

    override fun getItemCount(): Int = welcomeList.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.tv_recipe_title
    }
}