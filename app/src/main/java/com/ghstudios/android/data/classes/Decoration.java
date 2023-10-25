package com.ghstudios.android.data.classes;
/*
 * Class for Decoration
 *
 * Note: Subclass of Item
 */
public class Decoration extends Item{

	private int num_slots;			// Number of slots required
	private String slots_string;	// Number of slots as unicode display
	private long skill_1_id;		// Id of SkillTree 1
	private String skill_1_name;	// Name of SkillTree 1
	private int skill_1_point;		// # of points for SkillTree 1
	private long skill_2_id;		// Id of SkillTree 2
	private String skill_2_name;	// Name of SkillTree 2
	private int skill_2_point;		// # of points for SkillTree 2
	private long skill_3_id;		// Id of SkillTree 3
	private String skill_3_name;	// Name of SkillTree 3
	private int skill_3_point;		// # of points for SkillTree 3
	private long skill_4_id;		// Id of SkillTree 4
	private String skill_4_name;	// Name of SkillTree 4
	private int skill_4_point;		// # of points for SkillTree 4

	/* Default Constructor */
	public Decoration() {
		super();					// Initialized variables in Item

		this.num_slots = -1;
		this.skill_1_id = -1;
		this.skill_1_name = "";
		this.skill_1_point = -1;
		this.skill_2_id = -1;
		this.skill_2_name = "";
		this.skill_2_point = -1;
		this.skill_3_id = -1;
		this.skill_3_name = "";
		this.skill_3_point = -1;
		this.skill_4_id = -1;
		this.skill_4_name = "";
		this.skill_4_point = -1;

	}

	/* Getters and Setters */
	public int getNumSlots() {
		return num_slots;
	}

	public String getSlotsString() { return slots_string; }

	public void setNumSlots(int num_slots) {
		this.num_slots = num_slots;

		// Set the slot to view
		String slot = "";

		// Unicode Black Circle \u25CF
		switch (this.num_slots) {
			case 1:
				slot = "\u25CF";
				break;
			case 2:
				slot = "\u25CF\u25CF";
				break;
			case 3:
				slot = "\u25CF\u25CF\u25CF";
				break;
			default:
				slot = "error!!";
				break;
		}

		this.slots_string = slot;
	}

	public long getSkill1Id() {
		return skill_1_id;
	}

	public void setSkill1Id(long skill_1_id) {
		this.skill_1_id = skill_1_id;
	}

	public String getSkill1Name() {
		return skill_1_name;
	}

	public void setSkill1Name(String skill_1_name) {
		this.skill_1_name = skill_1_name;
	}

	public int getSkill1Point() {
		return skill_1_point;
	}

	public void setSkill1Point(int skill_1_point) {
		this.skill_1_point = skill_1_point;
	}

	public long getSkill2Id() {
		return skill_2_id;
	}

	public void setSkill2Id(long skill_2_id) {
		this.skill_2_id = skill_2_id;
	}

	public String getSkill2Name() {
		return skill_2_name;
	}

	public void setSkill2Name(String skill_2_name) {
		this.skill_2_name = skill_2_name;
	}

	public int getSkill2Point() {
		return skill_2_point;
	}

	public void setSkill2Point(int skill_2_point) {
		this.skill_2_point = skill_2_point;
	}

	public long getSkill3Id() {
		return skill_3_id;
	}

	public void setSkill3Id(long skill_3_id) {
		this.skill_3_id = skill_3_id;
	}

	public String getSkill3Name() {
		return skill_3_name;
	}

	public void setSkill3Name(String skill_3_name) {
		this.skill_3_name = skill_3_name;
	}

	public int getSkill3Point() {
		return skill_3_point;
	}

	public void setSkill3Point(int skill_3_point) {
		this.skill_3_point = skill_3_point;
	}

	public long getSkill4Id() {
		return skill_4_id;
	}

	public void setSkill4Id(long skill_4_id) {
		this.skill_4_id = skill_4_id;
	}

	public String getSkill4Name() {
		return skill_4_name;
	}

	public void setSkill4Name(String skill_4_name) {
		this.skill_4_name = skill_4_name;
	}

	public int getSkill4Point() {
		return skill_4_point;
	}

	public void setSkill4Point(int skill_4_point) {
		this.skill_4_point = skill_4_point;
	}

}
