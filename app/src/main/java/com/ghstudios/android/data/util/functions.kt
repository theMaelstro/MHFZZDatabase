package com.ghstudios.android.data.util

import com.ghstudios.android.AppSettings
import android.util.Log

/**
 * Computes the column name to allow the function to work in a specific locale
 */
fun localizeColumn(locale: String, columnName: String) = when(locale) {
    "en" -> columnName
    else -> "${columnName}_$locale"
}

fun getBloated (): Boolean {
    var bloated : Boolean = AppSettings.isBloatedEnabled
    return bloated
}

fun getElementTrueRaw (): Boolean {
    var elementTrueRaw: Boolean = AppSettings.isElementTrueRawEnabled
    return elementTrueRaw
}


/**
 * Returns the localized form of the base column name for the current locale
 */
fun localizeColumn(columnName: String) = localizeColumn(AppSettings.dataLocale, columnName)
