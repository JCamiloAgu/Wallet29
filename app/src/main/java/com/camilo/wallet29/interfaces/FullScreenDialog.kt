package com.camilo.wallet29.interfaces

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.core.graphics.ColorUtils
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.camilo.wallet29.R
import com.camilo.wallet29.common.Constants
import com.camilo.wallet29.ui.icon_picker.AppIcons
import com.github.dhaval2404.colorpicker.MaterialColorPickerDialog
import com.github.dhaval2404.colorpicker.model.ColorSwatch
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.maltaisn.icondialog.IconDialog
import com.maltaisn.icondialog.IconDialogSettings
import com.maltaisn.icondialog.data.Icon
import com.maltaisn.icondialog.pack.IconPack
import kotlinx.android.synthetic.main.add_element_dialog_header.view.*


abstract class FullScreenDialog : DialogFragment(), IconDialog.Callback {
    val TAG = "full_screen_dialog"

    var toolbar: Toolbar? = null

    abstract var color: Int
    abstract var iconId: Int

    private var tempIconId: Int? = null

    override val iconDialogIconPack: IconPack?
        get() = (activity!!.application as AppIcons).iconPack


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AppTheme_FullScreenDialog)
    }

    companion object {
        private const val ICON_DIALOG_TAG = "icon-dialog"
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
            // If dialog is already added to fragment manager, get it. If not, create a new instance.
            val iconDialog = childFragmentManager.findFragmentByTag(ICON_DIALOG_TAG) as IconDialog?
                ?: IconDialog.newInstance(IconDialogSettings())

            iconDialog.show(childFragmentManager, ICON_DIALOG_TAG)
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

    private fun showColorPicker() {
        MaterialColorPickerDialog
            .Builder(activity!!)
            // Pass Activity Instance
            .setTitle(getString(R.string.dialog_color_picker_title))               // Change Dialog Title
            .setColorSwatch(ColorSwatch._900)
            .setColorListener { mColor, _ ->
                color = mColor
                iconId = tempIconId!!
                changeFabIcon()
                changeColors()
            }
            .show()

    }

    open fun changeFabIcon(fab: FloatingActionButton? = null) {
        val drawable = Constants.changeDrawableColor(
            context!!,
            Constants.icons[iconId]!!.drawable
        )

        if (fab == null)
            view!!.fabCustomizeElement.setImageDrawable(drawable)
        else
            fab.setImageDrawable(drawable)
    }

    open fun changeColors() {
        view!!.apply {
            addElementDialogToolbar!!.setBackgroundColor(color)
            addElementDialogBelowToolbar!!.setBackgroundColor(color)
            edtNameElement!!.setBackgroundColor(color)
            edtNameElement!!.editText!!.setBackgroundColor(color)
        }

        if (dialog != null) {
            val finalColor = ColorUtils.blendARGB(color, Color.BLACK, 0.2f)
            dialog!!.window?.statusBarColor = ColorUtils.setAlphaComponent(finalColor, 255)
        }
    }

    abstract fun menuItemClick()

    abstract fun toolbarNavigationItemClick()

    abstract fun isAllInputsCorrect(): Boolean

    abstract fun insert()

    abstract fun update()

    override fun onIconDialogIconsSelected(dialog: IconDialog, icons: List<Icon>) {
        tempIconId = icons.map { it.id }[0]
        showColorPicker()
    }


}
