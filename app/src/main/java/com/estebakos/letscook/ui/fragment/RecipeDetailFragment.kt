package com.estebakos.letscook.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.estebakos.letscook.R
import com.estebakos.letscook.base.BaseFragment
import com.estebakos.letscook.ui.model.RecipeDetailUI
import com.estebakos.letscook.ui.model.RecipeUI
import com.estebakos.letscook.ui.viewmodel.RecipeDetailViewModel
import com.estebakos.letscook.ui.viewmodel.RecipeDetailViewModelFactory
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_recipe_detail.*
import javax.inject.Inject

class RecipeDetailFragment : BaseFragment(R.layout.fragment_recipe_detail) {

    @Inject
    lateinit var recipeDetailViewModelFactory: RecipeDetailViewModelFactory
    private val recipeDetailViewModel: RecipeDetailViewModel by viewModels { recipeDetailViewModelFactory }
    private var recipeUI: RecipeUI? = null

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recipeUI = arguments?.getParcelable(RECIPE_EXTRA)
        observeLiveData()
        recipeDetailViewModel.loadRecipe(recipeUI!!.id)
    }

    private fun observeLiveData() {
        recipeDetailViewModel.recipeDetail.observe(
            requireActivity(),
            Observer(::onRecipeDetailSuccess)
        )
        recipeDetailViewModel.loading.observe(requireActivity(), Observer(::onLoadingStateReceived))
        recipeDetailViewModel.error.observe(requireActivity(), Observer(::onErrorReceived))
        recipeDetailViewModel.empty.observe(requireActivity(), Observer(::onErrorReceived))
    }

    private fun onLoadingStateReceived(isLoading: Boolean) {
        layout_detail_loading.apply {
            visibility = if (isLoading) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }
    }

    private fun onErrorReceived(success: Boolean) {
        showAlertDialog(getString(R.string.connection_error))
    }

    private fun onRecipeDetailSuccess(recipe: RecipeDetailUI) {
        Glide.with(this).load(recipe.image).into(iv_recipe_detail)
        tv_recipe_detail_title.text = recipe.title
        tv_recipe_instructions.text = recipe.instructions
        fillStars(recipe.rating)
    }

    private fun fillStars(rating: Int) {
        when (rating) {
            1 -> Glide.with(this).load(R.drawable.fill_star).into(iv_star_1)
            2 -> {
                Glide.with(this).load(R.drawable.fill_star).into(iv_star_1)
                Glide.with(this).load(R.drawable.fill_star).into(iv_star_2)
            }
            3 -> {
                Glide.with(this).load(R.drawable.fill_star).into(iv_star_1)
                Glide.with(this).load(R.drawable.fill_star).into(iv_star_2)
                Glide.with(this).load(R.drawable.fill_star).into(iv_star_3)
            }
            4 -> {
                Glide.with(this).load(R.drawable.fill_star).into(iv_star_1)
                Glide.with(this).load(R.drawable.fill_star).into(iv_star_2)
                Glide.with(this).load(R.drawable.fill_star).into(iv_star_3)
                Glide.with(this).load(R.drawable.fill_star).into(iv_star_4)
            }
            5 -> {
                Glide.with(this).load(R.drawable.fill_star).into(iv_star_1)
                Glide.with(this).load(R.drawable.fill_star).into(iv_star_2)
                Glide.with(this).load(R.drawable.fill_star).into(iv_star_3)
                Glide.with(this).load(R.drawable.fill_star).into(iv_star_4)
                Glide.with(this).load(R.drawable.fill_star).into(iv_star_5)
            }
            else -> {
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(bundle: Bundle?): RecipeDetailFragment {
            val recipeDetail = RecipeDetailFragment()
            recipeDetail.arguments = bundle
            return recipeDetail
        }

        const val RECIPE_EXTRA = "RecipeExtra"
    }
}