package com.ghstudios.android.data.cursors

import android.database.Cursor
import android.database.CursorWrapper
import com.ghstudios.android.data.classes.Bento
import com.ghstudios.android.data.classes.Item
import com.ghstudios.android.data.classes.StoreOffer
import com.ghstudios.android.data.database.S
import com.ghstudios.android.data.util.getInt
import com.ghstudios.android.data.util.getLong
import com.ghstudios.android.data.util.getString

class StoreOfferCursor(c: Cursor) : CursorWrapper(c) {
    val storeoffer: StoreOffer
        get() {
            val storeoffers = StoreOffer().apply {
                cost = getString(S.COLUMN_SHOP_LIST_COST).toString()
                store = getString(S.COLUMN_SHOP_LIST_STORE)!!
                quantity = getString(S.COLUMN_SHOP_LIST_QUANTITY).toString()
                notes = getString(S.COLUMN_SHOP_LIST_NOTES).toString()
            }
            storeoffers.product = Item().apply {
                id = getLong(S.COLUMN_SHOP_LIST_ITEM_ID)
                name = getString("item_name")
                fileLocation = getString("item_icon_name")
                iconColor = getInt("item_icon_color")
            }
            return storeoffers
        }
}