package com.camilo.wallet29.interfaces

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.camilo.wallet29.R
import com.github.dhaval2404.colorpicker.MaterialColorPickerDialog
import com.github.dhaval2404.colorpicker.model.ColorSwatch
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.add_element_dialog_header.view.*


abstract class FullScreenDialog : DialogFragment() {
    val TAG = "full_screen_dialog"

    var toolbar: Toolbar? = null

    abstract var color: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AppTheme_FullScreenDialog)
    }

    abstract fun display(fragmentManager: FragmentManager?): FullScreenDialog?

    override fun onStart() {
        super.onStart()
        val dialog = dialog
        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            dialog.window!!.setLayout(width, height)
            dialog.window?.setWindowAnimations(R.style.AppTheme_Slide)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar?.setNavigationOnClickListener { _ -> toolbarNavigationItemClick() }
        toolbar?.title = "Agregar cuenta"
        toolbar?.setTitleTextColor(Color.WHITE)
        toolbar!!.setOnMenuItemClickListener {
            menuItemClick()
            true
        }
        view.fabCustomizeElement.setOnClickListener {
            fabCustomizeClick()
        }
    }

    fun showDialogDiscardChanges() {
        MaterialAlertDialogBuilder(context!!).apply {
            setTitle(R.string.dialog_title_discard_changes)
            setMessage(R.string.alert_dialog_dismiss_with_changes)
            setPositiveButton(getString(R.string.dialog_neg_btn_discard)) { confirmDialog, _ ->
                confirmDialog.dismiss()
                dismiss()
            }
            setNegativeButton(android.R.string.cancel) { confirmDialog, _ -> confirmDialog.dismiss() }
            create()
        }.show()
    }

    private fun fabCustomizeClick() {
        MaterialColorPickerDialog
            .Builder(activity!!)                    // Pass Activity Instance
            .setTitle(getString(R.string.dialog_color_picker_title))               // Change Dialog Title
            .setColorSwatch(ColorSwatch._900)
            .setColorListener { mColor, _ ->
                color = mColor
                changeColors(mColor)
            }
            .show()
    }

    open fun changeColors(color: Int) {
        view!!.apply {
            addElementDialogToolbar!!.setBackgroundColor(color)
            addElementDialogBelowToolbar!!.setBackgroundColor(color)
            edtNameElement!!.setBackgroundColor(color)
            edtNameElement!!.editText!!.setBackgroundColor(color)
        }

    }

    abstract fun menuItemClick()

    abstract fun toolbarNavigationItemClick()

    abstract fun isAllInputsCorrect(): Boolean

    abstract fun insert()

}
