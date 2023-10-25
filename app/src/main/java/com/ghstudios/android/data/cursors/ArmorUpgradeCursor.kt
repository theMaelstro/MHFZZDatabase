package com.ghstudios.android.data.cursors

import com.ghstudios.android.data.classes.Item
import com.ghstudios.android.data.classes.ItemTypeConverter
import com.ghstudios.android.data.database.S

import android.database.Cursor
import android.database.CursorWrapper
import com.ghstudios.android.data.util.getInt
import com.ghstudios.android.data.util.getLong
import com.ghstudios.android.data.util.getString

class ArmorUpgradeCursor(c: Cursor) : CursorWrapper(c) {
    val item: Item
        get() {
            val items = Item().apply {
                id = getLong(S.COLUMN_ARMOR_UPGRADE_DESCENDANT_ID)
                name = getString("name")
                fileLocation = getString("icon_name")
                iconColor = getInt("icon_color")
            }
            return items
        }
}