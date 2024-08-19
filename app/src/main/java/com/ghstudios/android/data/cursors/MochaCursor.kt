package com.ghstudios.android.data.cursors

import android.database.Cursor
import android.database.CursorWrapper
import com.ghstudios.android.data.classes.Mocha
import com.ghstudios.android.data.classes.Item
import com.ghstudios.android.data.database.S
import com.ghstudios.android.data.util.getInt
import com.ghstudios.android.data.util.getLong
import com.ghstudios.android.data.util.getString

class MochaCursor(c: Cursor) : CursorWrapper(c) {
    val mocha: Mocha
        get() {
            val mochas = Mocha()
            mochas.pot = Item().apply {
                id = getLong(S.COLUMN_MOCHA_POT)
                name = getString("pot_name")
                fileLocation = getString("pot_icon_name")
                iconColor = getInt("pot_icon_color")
            }
            mochas.input = Item().apply {
                id = getLong(S.COLUMN_MOCHA_INPUT)
                name = getString("input_name")
                fileLocation = getString("input_icon_name")
                iconColor = getInt("input_icon_color")
            }

            mochas.item1 = Item().apply {
                id = getLong(S.COLUMN_MOCHA_RESULT_1)
                name = getString("item_1_name")
                fileLocation = getString("item_1_icon_name")
                iconColor = getInt("item_1_icon_color")
            }
            mochas.item2 = Item().apply {
                id = getLong(S.COLUMN_MOCHA_RESULT_2)
                name = getString("item_2_name")
                fileLocation = getString("item_2_icon_name")
                iconColor = getInt("item_2_icon_color")
            }
            mochas.item3 = Item().apply {
                id = getLong(S.COLUMN_MOCHA_RESULT_3)
                name = getString("item_3_name")
                fileLocation = getString("item_3_icon_name")
                iconColor = getInt("item_3_icon_color")
            }
            mochas.item4 = Item().apply {
                id = getLong(S.COLUMN_MOCHA_RESULT_4)
                name = getString("item_4_name")
                fileLocation = getString("item_4_icon_name")
                iconColor = getInt("item_4_icon_color")
            }
            mochas.item5 = Item().apply {
                id = getLong(S.COLUMN_MOCHA_RESULT_5)
                name = getString("item_5_name")
                fileLocation = getString("item_5_icon_name")
                iconColor = getInt("item_5_icon_color")
            }
            mochas.item6 = Item().apply {
                id = getLong(S.COLUMN_MOCHA_RESULT_6)
                name = getString("item_6_name")
                fileLocation = getString("item_6_icon_name")
                iconColor = getInt("item_6_icon_color")
            }

            mochas.id = getLong(S.COLUMN_MOCHA_ID)
            return mochas
        }
}