package com.camilo.wallet29.local_data

import androidx.room.TypeConverter
import java.util.*

class Converters {
    @TypeConverter
    fun toCalendar(l: Long?): Calendar? = if(l == null) null else Calendar.getInstance().apply { timeInMillis = l }

    @TypeConverter
    fun fromCalendar(c: Calendar?): Long? = c?.time?.time
}