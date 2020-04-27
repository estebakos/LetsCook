package com.estebakos.letscook.base

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.estebakos.letscook.R

open class BaseFragment(@LayoutRes layout: Int) : Fragment(layout) {

    fun showAlertDialog(message: String) {
        val dialogBuilder = AlertDialog.Builder(requireActivity()).apply {
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