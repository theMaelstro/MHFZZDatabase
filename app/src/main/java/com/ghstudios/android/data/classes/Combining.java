package com.ghstudios.android.data.classes;

/*
 * Class for Combining
 *
 * This is for the Combining List, where two Items are used to
 * create another Item
 */
public class Combining {

	private long id;				// Combining id
	private Item created_item;		// Created Item
	private Item item_1;			// Component Item #1
	private Item item_2;			// Component Item #2
	private Item item_3;			// Component Item #3
	private int amount_made_min;	// Minimum possible amount made
	private int amount_made_max;	// Maximum possible amount made
	private int percentage;			// Percentage of success
	private int combiner_fee;			// Combiner fee
	private int alchemy;			// Alchemy skill perquisite
	
	/* Default Constructor */
	public Combining() {
		this.id = -1;
		this.created_item = null;
		this.item_1 = null;
		this.item_2 = null;
		this.item_3 = null;
		this.amount_made_min = -1;
		this.amount_made_max = -1;
		this.percentage = -1;
		this.combiner_fee = -1;
		this.alchemy = -1;
	}
	
	/* Getters and Setters */
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public Item getCreatedItem() {
		return created_item;
	}
	
	public void setCreatedItem(Item created_item) {
		this.created_item = created_item;
	}
	
	public Item getItem1() {
		return item_1;
	}
	
	public void setItem1(Item item_1) {
		this.item_1 = item_1;
	}
	
	public Item getItem2() {
		return item_2;
	}
	
	public void setItem2(Item item_2) {
		this.item_2 = item_2;
	}

	public Item getItem3() {
		return item_3;
	}

	public void setItem3(Item item_3) {
		this.item_3 = item_3;
	}
	
	public int getAmountMadeMin() {
		return amount_made_min;
	}
	
	public void setAmountMadeMin(int amount_made_min) {
		this.amount_made_min = amount_made_min;
	}
	
	public int getAmountMadeMax() {
		return amount_made_max;
	}
	
	public void setAmountMadeMax(int amount_made_max) {
		this.amount_made_max = amount_made_max;
	}
	
	public int getPercentage() {
		return percentage;
	}
	
	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}

	public int getCombinerFee() {
		return combiner_fee;
	}

	public void setCombinerFee(int combiner_fee) {
		this.combiner_fee = combiner_fee;
	}

	public int getAlchemy() {
		return alchemy;
	}

	public void setAlchemy(int alchemy) {
		this.alchemy = alchemy;
	}
	
}
