package com.ghstudios.android.loader;

import android.content.Context;
import android.database.Cursor;

import com.ghstudios.android.data.DataManager;

public class HuntingRewardListCursorLoader extends SQLiteCursorLoader {
	public static String FROM_ITEM = "item";
	public static String FROM_MONSTER = "monster";

	public static String RANK_EX = "EX";
	public static String RANK_LR = "LR";
	public static String RANK_HR = "HR";
	public static String RANK_GOU = "GOU";
	public static String RANK_G = "G";
	public static String RANK_Z1 = "Z1";
	public static String RANK_Z2 = "Z2";
	public static String RANK_Z3 = "Z3";
	public static String RANK_Z4 = "Z4";
	
	private String from;	// "item" or "monster"
	private long id; 		// Item or Monster id
	private String rank; 	// (For Monsters only) "LR", "HR", "G", or null

	public HuntingRewardListCursorLoader(Context context, String from, long id, String rank) {
		super(context);
		this.from = from;
		this.id = id;
		this.rank = rank;
	}

	@Override
	protected Cursor loadCursor() {
		if (from.equals(FROM_ITEM)) {
			// Query the list of hunting reward based on item
			return DataManager.get().queryHuntingRewardItem(id);
		}
		else if(from.equals(FROM_MONSTER)) {
			// Query the list of hunting reward based on monster and rank
			return DataManager.get().queryHuntingRewardMonsterRank(id, rank);
		}
		else {
			return null;
		}
	}
}
