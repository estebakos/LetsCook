package com.estebakos.letscook.ui.activity

import android.os.Bundle
import com.estebakos.letscook.R
import com.estebakos.letscook.base.SecondLevelActivity
import com.estebakos.letscook.ui.fragment.RecipeDetailFragment
import dagger.android.AndroidInjection

class RecipeDetailActivity : SecondLevelActivity(R.string.recipe_detail_title) {

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        val extras = intent.extras
        addFragment(RecipeDetailFragment.newInstance(extras))
    }

}
