package com.estebakos.letscook.base

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.annotation.StringRes
import kotlinx.android.synthetic.main.activity_base.*

open class SecondLevelActivity(@StringRes private val title: Int) : BaseActivity(title, true) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }

        return super.onOptionsItemSelected(item)

    }

}