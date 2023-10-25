package com.ghstudios.android.data.cursors

import android.database.Cursor
import android.database.CursorWrapper
import com.ghstudios.android.data.classes.Bento
import com.ghstudios.android.data.classes.Item
import com.ghstudios.android.data.database.S
import com.ghstudios.android.data.util.getInt
import com.ghstudios.android.data.util.getLong
import com.ghstudios.android.data.util.getString

class BentoCursor(c: Cursor) : CursorWrapper(c) {
    val bento: Bento
        get() {
            val bentos = Bento().apply {
                //id = getLong(S.COLUMN_BENTO_ID)
                //name = getString(S.COLUMN_BENTO_NAME,"")

                effect1 = getLong(S.COLUMN_BENTO_EFFECT_1)
                effect2 = getLong(S.COLUMN_BENTO_EFFECT_2)
                effect3 = getLong(S.COLUMN_BENTO_EFFECT_3)
                effect4 = getLong(S.COLUMN_BENTO_EFFECT_4)
                fileLocation = getString(S.COLUMN_ITEMS_ICON_NAME)
                //fileLocation = getString(S.COLUMN_BENTO_ICON,"")
                //fileLocation = ""
            }
            bentos.ingredient1 = Item().apply {
                id = getLong(S.COLUMN_BENTO_INGREDIENT_1_ID)
                name = getString("item_1_name")
                fileLocation = getString("item_1_icon_name")
                iconColor = getInt("item_1_icon_color")
            }
            bentos.ingredient2 = Item().apply {
                id = getLong(S.COLUMN_BENTO_INGREDIENT_2_ID)
                name = getString("item_2_name")
                fileLocation = getString("item_2_icon_name")
                iconColor = getInt("item_2_icon_color")
            }
            bentos.id = getLong(S.COLUMN_ITEMS_ID)
            bentos.name = getString(S.COLUMN_ITEMS_NAME)
            return bentos
        }
}