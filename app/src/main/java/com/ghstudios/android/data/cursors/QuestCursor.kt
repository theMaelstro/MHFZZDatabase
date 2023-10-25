package com.ghstudios.android.data.cursors

import android.database.Cursor
import android.database.CursorWrapper

import com.ghstudios.android.data.classes.Location
import com.ghstudios.android.data.classes.Quest
import com.ghstudios.android.data.classes.QuestHub
import com.ghstudios.android.data.database.S
import com.ghstudios.android.data.util.getInt
import com.ghstudios.android.data.util.getLong
import com.ghstudios.android.data.util.getString

/**
 * A convenience class to wrap a cursor that returns rows from the "quests"
 * table. The [] method will give you a Quest instance
 * representing the current row.
 */
class QuestCursor(c: Cursor) : CursorWrapper(c) {

    /**
     * Returns a Quest object configured for the current row, or null if the
     * current row is invalid.
     */
    //0=Normal,1=Key,2=Urgent
    val quest: Quest
        get() {
            val quest = Quest().apply {
                id = getLong(S.COLUMN_QUESTS_ID)
                name = getString("q" + S.COLUMN_QUESTS_NAME) ?: ""
                jpnName = getString("q" + S.COLUMN_QUESTS_JPN_NAME) ?: ""
                goal = getString(S.COLUMN_QUESTS_GOAL)
                hub = QuestHub.from(getString(S.COLUMN_QUESTS_HUB)!!)
                type = getInt(S.COLUMN_QUESTS_TYPE)
                stars = getString(S.COLUMN_QUESTS_STARS)
                timeLimit = getInt(S.COLUMN_QUESTS_TIME_LIMIT)
                fee = getInt(S.COLUMN_QUESTS_FEE)
                reward = getInt(S.COLUMN_QUESTS_REWARD)
                hrp = getInt(S.COLUMN_QUESTS_HRP)
                subGoalA = getString(S.COLUMN_QUESTS_SUB_A_GOAL)
                subRewardA = getInt(S.COLUMN_QUESTS_SUB_A_REWARD)
                subHrpA = getInt(S.COLUMN_QUESTS_SUB_A_HRP)
                subGoalB = getString(S.COLUMN_QUESTS_SUB_B_GOAL)
                subRewardB = getInt(S.COLUMN_QUESTS_SUB_B_REWARD)
                subHrpB = getInt(S.COLUMN_QUESTS_SUB_B_HRP)
                goalType = getInt(S.COLUMN_QUESTS_GOAL_TYPE)
                hunterType = getInt(S.COLUMN_QUESTS_HUNTER_TYPE)
                flavor = getString(S.COLUMN_QUESTS_FLAVOR)
                hirer = getString(S.COLUMN_QUESTS_HIRER)
                metadata = getInt(S.COLUMN_QUESTS_METADATA)
                rank = getString(S.COLUMN_QUESTS_RANK)
                permitMonsterId = getInt(S.COLUMN_QUESTS_PERMIT_MONSTER_ID)
            }

            if(quest.name.isEmpty()) quest.name = quest.jpnName

            quest.location = Location().apply {
                id = getLong(S.COLUMN_QUESTS_LOCATION_ID)
                name = getString("l" + S.COLUMN_LOCATIONS_NAME)
                fileLocation = getString(S.COLUMN_LOCATIONS_MAP)

            }

            return quest
        }

}