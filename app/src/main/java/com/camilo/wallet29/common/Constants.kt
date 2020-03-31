package com.camilo.wallet29.common

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.maltaisn.icondialog.data.Icon

object Constants {
    var spinnerItemPosition = 0

    lateinit var icons: MutableMap<Int, Icon>

    fun changeDrawableColor(context: Context, drawable: Drawable?): Drawable? {
        drawable?.colorFilter = PorterDuffColorFilter(
            ContextCompat.getColor(context, android.R.color.white),
            PorterDuff.Mode.SRC_IN
        )
        return drawable
    }

}