package com.estebakos.letscook.base

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.estebakos.letscook.R
import kotlinx.android.synthetic.main.activity_base.*

open class BaseActivity(
    @StringRes private val title: Int,
    secondLevelActivity: Boolean? = false
) :
    AppCompatActivity(
        if (secondLevelActivity!!) {
            R.layout.activity_second_level
        } else {
            R.layout.activity_base
        }
    ) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        setTitle()
    }

    private fun setTitle() {
        tv_toolbar_title.text = getString(title)
    }

    fun replaceFragment(fragment: Fragment, @IdRes containerId: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        with(transaction) {
            setCustomAnimations(
                R.anim.slide_in_right, R.anim.slide_out_left,
                R.anim.slide_in_left, R.anim.slide_out_right
            )
            replace(containerId, fragment)
            commit()
        }
    }

    fun addFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        with(transaction) {
            add(R.id.container, fragment)
            commit()
        }
    }

    fun showAlertDialog(message: String) {
        val dialogBuilder = AlertDialog.Builder(this).apply {
            setMessage(message)
                .setCancelable(false)
                .setPositiveButton(getString(R.string.dialog_ok_button)) { dialog, _ ->
                    dialog.dismiss()
                }
        }

        val alert = dialogBuilder.create()
        alert.setTitle(getString(R.string.app_name))
        alert.show()
    }

}