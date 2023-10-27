package com.ghstudios.android.data.cursors;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.ghstudios.android.data.classes.Cuff;
import com.ghstudios.android.data.classes.ItemType;
import com.ghstudios.android.data.database.S;

/**
 * A convenience class to wrap a cursor that returns rows from the "cuffs"
 * table. The {@link getCuff()} method will give you a Cuff instance
 * representing the current row.
 */
public class CuffCursor extends CursorWrapper {

    public CuffCursor(Cursor c) {
        super(c);
    }

    /**
     * Returns a Cuff object configured for the current row, or null if the
     * current row is invalid.
     */
    public Cuff getCuff() {
        if (isBeforeFirst() || isAfterLast())
            return null;

        Cuff cuff = new Cuff();

        long cuffId = getLong(getColumnIndex("_id"));
        String name = getString(getColumnIndex("item_name"));
        String jpnName = getString(getColumnIndex(S.COLUMN_ITEMS_JPN_NAME));
        int rarity = getInt(getColumnIndex(S.COLUMN_ITEMS_RARITY));
        int carry_capacity = getInt(getColumnIndex(S.COLUMN_ITEMS_CARRY_CAPACITY));
        int buy = getInt(getColumnIndex(S.COLUMN_ITEMS_BUY));
        int sell = getInt(getColumnIndex(S.COLUMN_ITEMS_SELL));
        String description = getString(getColumnIndex(S.COLUMN_ITEMS_DESCRIPTION));
        String fileLocation = getString(getColumnIndex(S.COLUMN_ITEMS_ICON_NAME));
        int color = getInt(getColumnIndex(S.COLUMN_ITEMS_ICON_COLOR));

        int num_slots = getInt(getColumnIndex(S.COLUMN_CUFFS_NUM_SLOTS));

        long skill_1_id = getLong(getColumnIndex("skill_1_id"));
        String skill_1_name = getString(getColumnIndex("skill_1_name"));
        int skill_1_point = getInt(getColumnIndex("skill_1_point_value"));

        long skill_2_id = getLong(getColumnIndex("skill_2_id"));
        String skill_2_name = getString(getColumnIndex("skill_2_name"));
        int skill_2_point = getInt(getColumnIndex("skill_2_point_value"));

        cuff.setId(cuffId);
        cuff.setName(name);
        cuff.setJpnName(jpnName);
        cuff.setType(ItemType.CUFF);
        cuff.setRarity(rarity);
        cuff.setCarryCapacity(carry_capacity);
        cuff.setBuy(buy);
        cuff.setSell(sell);
        cuff.setDescription(description);
        cuff.setFileLocation(fileLocation);
        cuff.setIconColor(color);

        cuff.setNumSlots(num_slots);

        cuff.setSkill1Id(skill_1_id);
        cuff.setSkill1Name(skill_1_name);
        cuff.setSkill1Point(skill_1_point);

        cuff.setSkill2Id(skill_2_id);
        cuff.setSkill2Name(skill_2_name);
        cuff.setSkill2Point(skill_2_point);

        return cuff;
    }

}