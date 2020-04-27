package com.estebakos.letscook.ui.activity

import android.os.Bundle
import android.widget.SearchView
import androidx.activity.viewModels
import com.estebakos.letscook.R
import com.estebakos.letscook.base.BaseActivity
import com.estebakos.letscook.ui.fragment.RecipesFragment
import com.estebakos.letscook.ui.viewmodel.RecipesViewModel
import com.estebakos.letscook.ui.viewmodel.RecipesViewModelFactory
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_base.*
import javax.inject.Inject

class RecipesActivity : BaseActivity(R.string.recipe_list_title) {

    @Inject
    lateinit var recipesViewModelFactory: RecipesViewModelFactory
    private val recipesViewModel: RecipesViewModel by viewModels { recipesViewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        val extras = intent.extras
        setSearchViewListener()
        addFragment(RecipesFragment.newInstance(extras))
    }

    private fun setSearchViewListener() {
        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrEmpty() && query.length >= 3) {
                    recipesViewModel.searchRecipe(query)
                }

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrEmpty()) {
                    recipesViewModel.loadRecipes()
                }
                return false
            }

        })
    }
}
