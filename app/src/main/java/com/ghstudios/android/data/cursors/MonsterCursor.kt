package com.ghstudios.android.data.cursors

import android.database.Cursor
import android.database.CursorWrapper

import com.ghstudios.android.data.classes.Monster
import com.ghstudios.android.data.classes.MonsterClassConverter
import com.ghstudios.android.data.classes.QuestHub
import com.ghstudios.android.data.database.S
import com.ghstudios.android.data.util.getInt
import com.ghstudios.android.data.util.getLong
import com.ghstudios.android.data.util.getString
import com.ghstudios.android.data.classes.MonsterType

/**
 * A convenience class to wrap a cursor that returns rows from the "monsters"
 * table. The [] method will give you a Monster instance
 * representing the current row.
 */
class MonsterCursor(c: Cursor) : CursorWrapper(c) {

    /**
     * Returns a Monster object configured for the current row
     */
    val monster: Monster
        get() {
            return Monster().apply {
                id = getLong(S.COLUMN_MONSTERS_ID)
                name = getString(S.COLUMN_MONSTERS_NAME,"")
                jpnName = getString(S.COLUMN_MONSTERS_JPN_NAME,"")
                monsterClass = MonsterClassConverter.deserialize(getInt(S.COLUMN_MONSTERS_CLASS))
                //monsterClassType = getString(S.COLUMN_MONSTERS_CLASS_TYPE,"Unknown")
                monsterClassType = MonsterType.from(getString(S.COLUMN_MONSTERS_CLASS_TYPE)!!)
                sizeMin = getString(S.COLUMN_MONSTERS_SIZE_MIN,"Unknown")
                sizeMax = getString(S.COLUMN_MONSTERS_SIZE_MAX,"Unknown")
                fileLocation = getString(S.COLUMN_MONSTERS_FILE_LOCATION,"")
                metadata = getInt(S.COLUMN_MONSTERS_METADATA)
            }
        }
}