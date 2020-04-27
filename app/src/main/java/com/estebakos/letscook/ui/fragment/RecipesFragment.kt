package com.estebakos.letscook.ui.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.estebakos.letscook.R
import com.estebakos.letscook.base.BaseFragment
import com.estebakos.letscook.ui.activity.RecipeDetailActivity
import com.estebakos.letscook.ui.adapter.RecipesRecyclerViewAdapter
import com.estebakos.letscook.ui.model.RecipeUI
import com.estebakos.letscook.ui.viewmodel.RecipesViewModel
import com.estebakos.letscook.ui.viewmodel.RecipesViewModelFactory
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_recipes.*
import javax.inject.Inject

class RecipesFragment : BaseFragment(R.layout.fragment_recipes) {

    @Inject
    lateinit var recipesViewModelFactory: RecipesViewModelFactory
    private val recipesViewModel: RecipesViewModel by activityViewModels { recipesViewModelFactory }
    private lateinit var recipesAdapter: RecipesRecyclerViewAdapter

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeLiveData()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_recipes.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
        }
    }

    override fun onResume() {
        super.onResume()
        recipesViewModel.loadRecipes()
    }

    private fun observeLiveData() {
        recipesViewModel.recipeList.observe(requireActivity(), Observer(::onRecipesSuccess))
        recipesViewModel.loading.observe(requireActivity(), Observer(::onLoadingStateReceived))
        recipesViewModel.error.observe(requireActivity(), Observer(::onErrorReceived))
        recipesViewModel.empty.observe(requireActivity(), Observer(::onErrorReceived))
    }

    private fun goToRecipeDetail(recipe: RecipeUI) {
        val intent = Intent(requireActivity(), RecipeDetailActivity::class.java)
        intent.putExtra(RecipeDetailFragment.RECIPE_EXTRA, recipe)
        startActivity(intent)
    }

    private fun onLoadingStateReceived(isLoading: Boolean) {
        layout_loading.apply {
            visibility = if (isLoading) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }
    }

    private fun onRecipesSuccess(recipes: List<RecipeUI>) {
        recipesAdapter = RecipesRecyclerViewAdapter(recipes) {
            goToRecipeDetail(it)
        }

        rv_recipes.apply {
            adapter = recipesAdapter
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    private fun onErrorReceived(success: Boolean) {
        showAlertDialog(getString(R.string.connection_error))
    }

    companion object {
        @JvmStatic
        fun newInstance(bundle: Bundle?): RecipesFragment {
            val recipes = RecipesFragment()
            recipes.arguments = bundle
            return recipes
        }
    }
}