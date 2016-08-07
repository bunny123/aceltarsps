package server.model.players;

import server.model.players.Client;

public class prayStat {

	/*Bonuses:
	* 0 = attack
	* 1 = strength
	* 2 = defence
	* 3 = ranged
	* 4 = magic
	*/
	
	/*
	 * Sets A Bonus
	 */
	public static int setBonus(Client p, int type, int toIncrease) {
		return p.bonus[type] = toIncrease;
	}
	
	/*
	 * Resets The Bonus
	 */
	public static int resetBonus(Client p, int type) {
		return p.bonus[type] = 0;
	}
	
	/*
	 * Gets the bonuses
	 */
	public static int getBonus(Client p, int type) {
		return p.bonus[type];
	}

	/*
	 * Bonus Display
	 */
	public static void displayBonus(Client p, int type){
		p.getPA().sendFrame126(""+getBonus(p,type)+"",frameForType(type));
	}
	
	/* 
	 * Handles Frame ID's.
 	 */
	public static int frameForType(int type) {
		switch (type){
			case 0://Defence
				return 690;

			case 1://Attack
				return 691;

			case 2://Strength
				return 692;

			case 3://Range
				return 693;
			case 4://Magic
				return 694;
		}
		return -1;
	}
	
	/*
	 * Decreasing when cursing somebody.
	 */
	public static int decreaseBonus(Client p, int type, int toDecrease) {
		return p.bonus[type] -= toDecrease;
	}
}