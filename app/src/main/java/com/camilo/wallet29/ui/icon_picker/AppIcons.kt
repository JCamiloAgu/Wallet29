package com.camilo.wallet29.ui.icon_picker

import android.app.Application
import com.camilo.wallet29.common.Constants
import com.maltaisn.icondialog.data.Icon
import com.maltaisn.icondialog.pack.IconPack
import com.maltaisn.icondialog.pack.IconPackLoader
import com.maltaisn.iconpack.defaultpack.createDefaultIconPack

class AppIcons : Application() {

    var iconPack: IconPack? = null

    override fun onCreate() {
        super.onCreate()

        // Load the icon pack on application start.
        loadIconPack()
    }

    private fun loadIconPack() {
        // Create an icon pack loader with application context.
        val loader = IconPackLoader(this)

        // Create an icon pack and load all drawables.
        val iconPack = createDefaultIconPack(loader)
        iconPack.loadDrawables(loader.drawableLoader)

       Constants.icons = iconPack.icons

        this.iconPack = iconPack
    }
}