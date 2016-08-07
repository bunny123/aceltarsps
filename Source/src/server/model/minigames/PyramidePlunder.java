package server.model.minigames;

import server.model.npcs.NPCHandler;
import server.model.players.Client;
import server.util.Misc;
import server.Config;
import server.event.Task;
import server.Server;

/**
 * PyramidePlunder class
 * @author Aintaro
 */

public class PyramidePlunder {
	private static final int 	CLOSED_URN1 = 16501, CLOSED_URN2 = 16502, CLOSED_URN3 = 16503, 
						SNAKE_URN1 = 16509, SNAKE_URN2 = 16510, SNAKE_URN3 = 16511,
						EMPTY_URN1 = 16505, EMPTY_URN2 = 16506, EMPTY_URN3 = 16507,
						GOLDEN_SCARAB = 9028, STONE_SCARAB = 9030, POTTERY_SCARAB = 9032,
						GOLDEN_STATUETTE = 9034, STONE_STATUETTE = 9036, POTTERY_STATUETTE = 9038,
						GOLDEN_SEAL = 9040, STONE_SEAL = 9042,
						PHARAO_SCEPTRE_UNCHARGED = 9050, PHARAO_SCEPTRE_ONE_CHARGE = 9048, PHARAO_SCEPTRE_TWO_CHARGE = 9046, PHARAO_SCEPTRE_THREE_CHARGE = 9044,
						GRAND_GOLD_CHEST_CLOSED = 16473, GRAND_GOLD_CHEST_OPEN = 16474;
					
						
	private static int[][] urnData = {
		{CLOSED_URN1, SNAKE_URN1, EMPTY_URN1},
		{CLOSED_URN2, SNAKE_URN2, EMPTY_URN2},
		{CLOSED_URN3, SNAKE_URN3, EMPTY_URN3},
		{GRAND_GOLD_CHEST_CLOSED, -1, GRAND_GOLD_CHEST_OPEN},
	};
	
	private static int[] TIER1_ITEMS = {POTTERY_SCARAB, POTTERY_STATUETTE, STONE_SEAL};
	
	private static int[] TIER2_ITEMS = {STONE_SCARAB, STONE_STATUETTE, STONE_SEAL};
	
	private static int[] TIER3_ITEMS = {GOLDEN_SCARAB, GOLDEN_STATUETTE, GOLDEN_SEAL, STONE_SCARAB, STONE_STATUETTE, STONE_SEAL, STONE_SCARAB, STONE_STATUETTE, STONE_SEAL};
	
	private static int[] TIER4_ITEMS = {GOLDEN_SCARAB, GOLDEN_STATUETTE, GOLDEN_SEAL, PHARAO_SCEPTRE_THREE_CHARGE, 
	GOLDEN_SEAL, GOLDEN_SEAL, GOLDEN_SCARAB, GOLDEN_SEAL, GOLDEN_SEAL, GOLDEN_SCARAB, GOLDEN_SCARAB, GOLDEN_SCARAB,
	GOLDEN_SCARAB, GOLDEN_SCARAB, GOLDEN_SEAL, GOLDEN_SEAL, GOLDEN_SCARAB, GOLDEN_SCARAB, GOLDEN_SCARAB, PHARAO_SCEPTRE_THREE_CHARGE,
	GOLDEN_SEAL, GOLDEN_SEAL, GOLDEN_SCARAB, GOLDEN_SCARAB, GOLDEN_SCARAB, PHARAO_SCEPTRE_THREE_CHARGE, GOLDEN_SEAL, GOLDEN_SEAL, GOLDEN_SCARAB, GOLDEN_SCARAB, GOLDEN_SCARAB,
	GOLDEN_SEAL, GOLDEN_SEAL, GOLDEN_SCARAB, GOLDEN_SCARAB, GOLDEN_SCARAB, GOLDEN_SEAL, GOLDEN_SEAL, GOLDEN_SCARAB, GOLDEN_SCARAB, GOLDEN_SCARAB, PHARAO_SCEPTRE_THREE_CHARGE, PHARAO_SCEPTRE_THREE_CHARGE};
	
	
	public static int randomTier1() {
		return TIER1_ITEMS[(int) (Math.random() * TIER1_ITEMS.length)];
	}
	
	public static int randomTier2() {
		return TIER2_ITEMS[(int) (Math.random() * TIER2_ITEMS.length)];
	}
	
	public static int randomTier3() {
		return TIER3_ITEMS[(int) (Math.random() * TIER3_ITEMS.length)];
	}
	
	public static int randomTier4() {
		return TIER4_ITEMS[(int) (Math.random() * TIER4_ITEMS.length)];
	}
	
	/**
	* this method calculates the chance to receive high value items
	*/
	
	private static int calculateChance(int chance) {
		if (chance >= 0 && chance < 50) {
			return randomTier1();
		} else if (chance >= 50 && chance < 70) {
			return randomTier2();
		} else if (chance >= 70 && chance <= 86) {
			return randomTier3();
		} else if (chance > 86) {
			return randomTier4();
		}
		return -1;
	}
	
	/**
	* checks if the player is inside the pyramid plunder minigame
	* or outside before giving the player the options.
	*/
	
	private static void talkMummy(Client c) {
		if (c.heightLevel == 0 & c.isInPyramidePlunder == false) {
			c.getDH().sendDialogues(99, 4476);
		} else if (c.heightLevel == 0 && c.isInPyramidePlunder) {
			c.getDH().sendDialogues(103, 4476);
		} else if (c.heightLevel == 4 && c.isInPyramidePlunder) {
			c.getDH().sendDialogues(103, 4476);
		} else if (c.heightLevel == 8 && c.isInPyramidePlunder) {
			c.getDH().sendDialogues(103, 4476);
		} else if (c.heightLevel == 12 && c.isInPyramidePlunder) {
			c.getDH().sendDialogues(103, 4476);
		}
	}
	
	/**
	* this method is called when a player clicks on a
	* pyramid plunder object
	* @param objectId : objectId
	* @param objectX : objectX
	* @param objectY : objectY
	* @param objectH : object Height
	* @param face: not really used at the moment
	* @param urnThievingReq: the thieving level required to search the object
	*/
	
	private static void searchUrn(Client c, int objectId, int objectX, int objectY, int objectH, int face, int urnThievingReq) {
		for (int[] getUrnData : urnData) {
			if (objectId == getUrnData[0]) {
				if (c.playerLevel[c.playerThieving] >= urnThievingReq) {
					if (Misc.random(c.playerLevel[c.playerThieving]) < 4 * c.heightLevel && objectId != GRAND_GOLD_CHEST_CLOSED) {	
						failAction(c);
					} else {
						succesAction(c, urnThievingReq);
						c.getPA().addSkillXP((88 + c.heightLevel) * Config.THIEVING_EXPERIENCE, c.playerThieving); //max of 3k exp
						c.getPA().object(getUrnData[2], objectX, objectY, face, 10);
					}
				} else {
					c.sendMessage("You need atleast " + urnThievingReq + " thieving to investigate this room.");
					break;
				}
			}
		}
	}
	
	/**
	* Player succesfully investigates an urn
	*/
	
	private static void succesAction(final Client c, final int thievingReq) {
		c.getPlayerAction().setAction(true);
		c.getPlayerAction().canWalk(false);
		c.startAnimation(4340);
		Server.getTaskScheduler().schedule(new Task(1) {
			@Override
			protected void execute() {
				if (c.disconnected) {
					stop();
					return;
				}
				c.startAnimation(4342);
				c.getItems().addItem(calculateChance(thievingReq), 1);
				updateProgress(c);
				updatePyramidePlunderInterface(c);
				c.getPlayerAction().setAction(false);
				c.getPlayerAction().canWalk(true);
				this.stop();
			}
		});
	}
	
	/**
	* Player fails at investigating an urn
	*/
	
	private static void failAction(final Client c)  {
		c.getPlayerAction().setAction(true);
		c.getPlayerAction().canWalk(false);
		c.startAnimation(4340);
		Server.getTaskScheduler().schedule(new Task(1) {
			@Override
			protected void execute() {
				if (c.disconnected) {
					stop();
					return;
				}
				if (c.poisonDamage > 0) {
				c.getPlayerAction().setAction(false);
				c.getPlayerAction().canWalk(true);
				c.startAnimation(4341);
//				c.setHitDiff(6);
				c.setHitUpdateRequired(true);
				c.getPA().refreshSkill(3);
				c.playerLevel[3] -= 6;
				c.forcedChat("Ouch !");
				} else {
					c.getPlayerAction().setAction(false);
					c.getPlayerAction().canWalk(true);
					c.startAnimation(4341);
					c.getPA().appendPoison(12);
					c.getPA().refreshSkill(3);
					c.sendMessage("You have been poisoned.");
				}
				
				this.stop();
			}
		});
	}
	
	/**
	* When a player loot something we 
	* decrease the roomProgress
	*/
	
	private static void updateProgress(Client c) {
		c.roomProgress -= 8;
	}
	
	/**
	* Update the percentage on the interface
	*/
	
	private static void updatePyramidePlunderInterface(Client c) {
		if (c.roomProgress < 0)  {
			c.roomProgress = 0;
			c.getPA().sendFrame126("@red@Pyramide Plunder", 6569);
			c.getPA().sendFrame126(c.roomProgress + "%", 6572);
			c.getPA().sendFrame126("Loot left !", 6664);
			c.getPA().walkableInterface(6568);
		} else {
			c.getPA().sendFrame126("@red@Pyramide Plunder", 6569);
			c.getPA().sendFrame126(c.roomProgress + "%", 6572);
			c.getPA().sendFrame126("Loot left !", 6664);
			c.getPA().walkableInterface(6568);
		}
	}
	
	public static boolean pyramidePlunderIllegal(Client c) {
		if (c.getX() >= 3300 && c.getX() <= 3311 && c.getY() >=  9192 && c.getY() <= 9198 && c.isInPyramidePlunder == false) {
			return true;
		}
		return false;
	}
	
	/**
	* first click objects in the pyramid plunder
	*/
	
	public static boolean pyramidePlunderObjects1(Client c, int objectId) {
		switch (objectId) {
			case CLOSED_URN1:
				if (c.heightLevel == 0) {
					searchUrn(c, c.objectId, c.objectX, c.objectY, c.heightLevel, 1, 60);
				} else if (c.heightLevel == 4) {
					searchUrn(c, c.objectId, c.objectX, c.objectY, c.heightLevel, 1, 75);
				} else if (c.heightLevel == 8) {
					searchUrn(c, c.objectId, c.objectX, c.objectY, c.heightLevel, 1, 85);
				} else if (c.heightLevel == 12) {
					searchUrn(c, c.objectId, c.objectX, c.objectY, c.heightLevel, 1, 91);
				}
				return true;
				
			case CLOSED_URN2:
				if (c.heightLevel == 0) {
					searchUrn(c, c.objectId, c.objectX, c.objectY, c.heightLevel, 1, 60);
				} else if (c.heightLevel == 4) {
					searchUrn(c, c.objectId, c.objectX, c.objectY, c.heightLevel, 1, 75);
				}  else if (c.heightLevel == 8) {
					searchUrn(c, c.objectId, c.objectX, c.objectY, c.heightLevel, 1, 85);
				} else if (c.heightLevel == 12) {
					searchUrn(c, c.objectId, c.objectX, c.objectY, c.heightLevel, 1, 91);
				}
				return true;
				
			case CLOSED_URN3:
				if (c.heightLevel == 0) {
					searchUrn(c, c.objectId, c.objectX, c.objectY, c.heightLevel, 1, 60);
				} else if (c.heightLevel == 4) {
					searchUrn(c, c.objectId, c.objectX, c.objectY, c.heightLevel, 1, 75);
				}  else if (c.heightLevel == 8) {
					searchUrn(c, c.objectId, c.objectX, c.objectY, c.heightLevel, 1, 85);
				} else if (c.heightLevel == 12) {
					searchUrn(c, c.objectId, c.objectX, c.objectY, c.heightLevel, 1, 91);
				}
				return true;
			
			case GRAND_GOLD_CHEST_CLOSED:
				if (c.heightLevel == 0) {
					searchUrn(c, c.objectId, c.objectX, c.objectY, c.heightLevel, 1, 60);
				} else if (c.heightLevel == 4) {
					searchUrn(c, c.objectId, c.objectX, c.objectY, c.heightLevel, 1, 75);
				}  else if (c.heightLevel == 8) {
					searchUrn(c, c.objectId, c.objectX, c.objectY, c.heightLevel, 1, 85);
				} else if (c.heightLevel == 12) {
					searchUrn(c, c.objectId, c.objectX, c.objectY, c.heightLevel, 1, 91);
				}
				return true;
		}
		return false;
	}
	
	/**
	* second click object in the pyramid plunder
	*/
	
	public static boolean pyramidePlunderObjects2(Client c, int objectId) {
		switch (objectId) {
			case CLOSED_URN1:
				for (int[] getUrnData : urnData) {
					if (objectId == getUrnData[0]) {
						c.getPA().object(getUrnData[1], c.objectX, c.objectY, 1, 10);
						updateProgress(c);
						break;
					}
				}
				return true;
				
			case CLOSED_URN2:
				for (int[] getUrnData : urnData) {
					if (objectId == getUrnData[0]) {
						c.getPA().object(getUrnData[1], c.objectX, c.objectY, 1, 10);
						updateProgress(c);
						break;
					}
				}
				return true;
				
			case CLOSED_URN3:
				for (int[] getUrnData : urnData) {
					if (objectId == getUrnData[0]) {
						c.getPA().object(getUrnData[1], c.objectX, c.objectY, 1, 10);
						updateProgress(c);
						break;
					}
				}
				return true;
			
			case GRAND_GOLD_CHEST_CLOSED:
				for (int[] getUrnData : urnData) {
					if (objectId == getUrnData[0]) {
						c.getPA().object(getUrnData[1], c.objectX, c.objectY, 1, 10);
						updateProgress(c);
						break;
					}
				}
				return true;
		}
		return false;
	}
	
	/**
	* Recharge sceptre option
	*/
	
	public static void rechargeSceptre(Client c) {
		if (c.getItems().playerHasItem(GOLDEN_SCARAB, 6)) {
			if (c.getItems().playerHasItem(PHARAO_SCEPTRE_UNCHARGED, 1)) {
				c.getItems().deleteItem(PHARAO_SCEPTRE_UNCHARGED, 1);
				c.getItems().deleteItem2(GOLDEN_SCARAB, 6);
				c.getItems().addItem(PHARAO_SCEPTRE_THREE_CHARGE, 1);
				c.getPA().closeAllWindows();
			} else if (c.getItems().playerHasItem(PHARAO_SCEPTRE_ONE_CHARGE, 1)) {
				c.getItems().deleteItem(PHARAO_SCEPTRE_ONE_CHARGE, 1);
				c.getItems().deleteItem2(GOLDEN_SCARAB, 6);
				c.getItems().addItem(PHARAO_SCEPTRE_THREE_CHARGE, 1);
				c.getPA().closeAllWindows();
			} else if (c.getItems().playerHasItem(PHARAO_SCEPTRE_TWO_CHARGE, 1)) {
				c.getItems().deleteItem(PHARAO_SCEPTRE_TWO_CHARGE, 1);
				c.getItems().deleteItem2(GOLDEN_SCARAB, 6);
				c.getItems().addItem(PHARAO_SCEPTRE_THREE_CHARGE, 1);
				c.getPA().closeAllWindows();
			} else {
				c.getDH().sendStatement("You need atleast one uncharged sceptre.");
				c.nextChat = 0;
			}
		} else if ( c.getItems().playerHasItem(GOLDEN_STATUETTE, 6)) {
			if (c.getItems().playerHasItem(PHARAO_SCEPTRE_UNCHARGED, 1)) {
				c.getItems().deleteItem(PHARAO_SCEPTRE_UNCHARGED, 1);
				c.getItems().deleteItem2(GOLDEN_STATUETTE, 6);
				c.getItems().addItem(PHARAO_SCEPTRE_THREE_CHARGE, 1);
				c.getPA().closeAllWindows();
			} else if (c.getItems().playerHasItem(PHARAO_SCEPTRE_ONE_CHARGE, 1)) {
				c.getItems().deleteItem(PHARAO_SCEPTRE_ONE_CHARGE, 1);
				c.getItems().deleteItem2(GOLDEN_STATUETTE, 6);
				c.getItems().addItem(PHARAO_SCEPTRE_THREE_CHARGE, 1);
				c.getPA().closeAllWindows();
			} else if (c.getItems().playerHasItem(PHARAO_SCEPTRE_TWO_CHARGE, 1)) {
				c.getItems().deleteItem(PHARAO_SCEPTRE_TWO_CHARGE, 1);
				c.getItems().deleteItem2(GOLDEN_STATUETTE, 6);
				c.getItems().addItem(PHARAO_SCEPTRE_THREE_CHARGE, 1);
				c.getPA().closeAllWindows();
			} else {
				c.getDH().sendStatement("You need atleast one uncharged sceptre.");
				c.nextChat = 0;
			}
		} else if ( c.getItems().playerHasItem(GOLDEN_SEAL, 6)) {
			if (c.getItems().playerHasItem(PHARAO_SCEPTRE_UNCHARGED, 1)) {
				c.getItems().deleteItem(PHARAO_SCEPTRE_UNCHARGED, 1);
				c.getItems().deleteItem2(GOLDEN_SEAL, 6);
				c.getItems().addItem(PHARAO_SCEPTRE_THREE_CHARGE, 1);
				c.getPA().closeAllWindows();
			} else if (c.getItems().playerHasItem(PHARAO_SCEPTRE_ONE_CHARGE, 1)) {
				c.getItems().deleteItem(PHARAO_SCEPTRE_ONE_CHARGE, 1);
				c.getItems().deleteItem2(GOLDEN_SEAL, 6);
				c.getItems().addItem(PHARAO_SCEPTRE_THREE_CHARGE, 1);
				c.getPA().closeAllWindows();
			} else if (c.getItems().playerHasItem(PHARAO_SCEPTRE_TWO_CHARGE, 1)) {
				c.getItems().deleteItem(PHARAO_SCEPTRE_TWO_CHARGE, 1);
				c.getItems().deleteItem2(GOLDEN_SEAL, 6);
				c.getItems().addItem(PHARAO_SCEPTRE_THREE_CHARGE, 1);
				c.getPA().closeAllWindows();
			} else {
				c.getDH().sendStatement("You need atleast one uncharged sceptre.");
				c.nextChat = 0;
			}
		} else {
			c.getDH().sendStatement("You need atleast 6 golden jewellery to recharge your sceptre.");
			c.nextChat = 0;
		}
	}
	
	/**
	* spawns the objects for the player room1
	*/
	
	public static void createRoom1(Client c) {
		c.roomProgress = 100;
		cleanRoom(c);
		//small urn
		c.getPA().object(CLOSED_URN1, 3302, 9192, 1, 10);
		c.getPA().object(CLOSED_URN1, 3304, 9192, 1, 10);
		c.getPA().object(CLOSED_URN1, 3306, 9192, 1, 10);
		c.getPA().object(CLOSED_URN1, 3308, 9192, 1, 10);
		//med urn
		c.getPA().object(CLOSED_URN2, 3311, 9194, 1, 10);
		c.getPA().object(CLOSED_URN2, 3302, 9195, 1, 10);
		c.getPA().object(CLOSED_URN2, 3302, 9196, 1, 10);
		c.getPA().object(CLOSED_URN2, 3302, 9197, 1, 10);
		//giant urn
		c.getPA().object(CLOSED_URN3, 3304, 9198, 1, 10);
		c.getPA().object(CLOSED_URN3, 3302, 9198, 1, 10);
		c.getPA().object(CLOSED_URN3, 3300, 9195, 1, 10);
		//grand chest
		c.getPA().object(GRAND_GOLD_CHEST_CLOSED, 3304, 9195, 1, 10);
		c.getPA().object(GRAND_GOLD_CHEST_CLOSED, 3305, 9195, 1, 10);
	}
	
	/**
	* spawns the objects for the player room2
	*/
	
	public static void createRoom2(Client c) {
		c.roomProgress = 100;
		cleanRoom(c);
		//small urn
		c.getPA().object(CLOSED_URN1, 3302, 9192, 1, 10);
		c.getPA().object(CLOSED_URN1, 3303, 9192, 1, 10);
		c.getPA().object(CLOSED_URN1, 3305, 9192, 1, 10);
		c.getPA().object(CLOSED_URN1, 3306, 9192, 1, 10);
		//med urn
		c.getPA().object(CLOSED_URN2, 3311, 9196, 1, 10);
		c.getPA().object(CLOSED_URN2, 3304, 9195, 1, 10);
		c.getPA().object(CLOSED_URN2, 3308, 9196, 1, 10);
		c.getPA().object(CLOSED_URN2, 3308, 9197, 1, 10);
		//giant urn
		c.getPA().object(CLOSED_URN3, 3304, 9198, 1, 10);
		c.getPA().object(CLOSED_URN3, 3302, 9198, 1, 10);
		c.getPA().object(CLOSED_URN3, 3300, 9195, 1, 10);
		//grand chest
		c.getPA().object(GRAND_GOLD_CHEST_CLOSED, 3304, 9195, 1, 10);
		c.getPA().object(GRAND_GOLD_CHEST_CLOSED, 3305, 9195, 1, 10);
	}
	
	/**
	* spawns the objects for the player room3
	*/
	
	public static void createRoom3(Client c) {
		c.roomProgress = 100;
		cleanRoom(c);
		//small urn
		c.getPA().object(CLOSED_URN1, 3304, 9192, 1, 10);
		c.getPA().object(CLOSED_URN1, 3310, 9192, 1, 10);
		c.getPA().object(CLOSED_URN1, 3307, 9192, 1, 10);
		c.getPA().object(CLOSED_URN1, 3309, 9192, 1, 10);
		//med urn
		c.getPA().object(CLOSED_URN2, 3311, 9196, 1, 10);
		c.getPA().object(CLOSED_URN2, 3307, 9195, 1, 10);
		c.getPA().object(CLOSED_URN2, 3308, 9196, 1, 10);
		c.getPA().object(CLOSED_URN2, 3308, 9193, 1, 10);
		//giant urn
		c.getPA().object(CLOSED_URN3, 3304, 9198, 1, 10);
		c.getPA().object(CLOSED_URN3, 3300, 9193, 1, 10);
		c.getPA().object(CLOSED_URN3, 3300, 9192, 1, 10);
		//grand chest
		c.getPA().object(GRAND_GOLD_CHEST_CLOSED, 3304, 9195, 1, 10);
		c.getPA().object(GRAND_GOLD_CHEST_CLOSED, 3305, 9195, 1, 10);
	}
	
	/**
	* spawns the objects for the player room4
	*/
	
	public static void createRoom4(Client c) {
		c.roomProgress = 100;
		cleanRoom(c);
		//small urn
		c.getPA().object(CLOSED_URN1, 3310, 9198, 1, 10);
		c.getPA().object(CLOSED_URN1, 3309, 9198, 1, 10);
		c.getPA().object(CLOSED_URN1, 3308, 9198, 1, 10);
		c.getPA().object(CLOSED_URN1, 3311, 9198, 1, 10);
		//med urn
		c.getPA().object(CLOSED_URN2, 3310, 9192, 1, 10);
		c.getPA().object(CLOSED_URN2, 3309, 9192, 1, 10);
		c.getPA().object(CLOSED_URN2, 3308, 9192, 1, 10);
		c.getPA().object(CLOSED_URN2, 3307, 9192, 1, 10);
		//giant urn
		c.getPA().object(CLOSED_URN3, 3304, 9198, 1, 10);
		c.getPA().object(CLOSED_URN3, 3300, 9193, 1, 10);
		c.getPA().object(CLOSED_URN3, 3300, 9192, 1, 10);
		//grand chest
		c.getPA().object(GRAND_GOLD_CHEST_CLOSED, 3304, 9195, 1, 10);
		c.getPA().object(GRAND_GOLD_CHEST_CLOSED, 3305, 9195, 1, 10);
	}
	
	/**
	* removes all the objects in the remove
	* before spawning them
	*/
	
	private static void cleanRoom(Client c) {
		for (int i = 3300; i < 3312; i++) {
			for (int z = 9192; z < 9199; z++) {
				c.getPA().object(-1, i, z, 1, 10);
			}
		}
	}
	
	/**
	* this method is called when a player wants to
	* start the pyramid plunder minigame
	*/
	
	public static void startPyramidePlunder(final Client c) {
		c.getPA().movePlayer(3306, 9198, 0);
		antiBotInterface(c);
		c.isInPyramidePlunder = true;
		Server.getTaskScheduler().schedule(new Task(4) {
			@Override
			protected void execute() {
				if (c.disconnected) {
					stop();
					return;
				}
				if (c.isInPyramidePlunder) {
					updatePyramidePlunderInterface(c);
					createRoom1(c);
				} else {
					endPyramidePlunder(c);
				}
				this.stop();
			}
		});
	}
	
	/**
	* simple antibot interface (not really) xd
	* makes it look smoother
	*/
	
	public static void antiBotInterface(Client c) {
		c.getPA().sendFrame126("Do you want to start your room?", 15528);
		c.getPA().showInterface(18299);
	}
	
	/**
	* when a player wants to move to the next room
	* this method is called
	*/
	
	public static void nextRoom(Client c) {
		switch(c.heightLevel) {
			case 0:
				createRoom2(c);
				c.getPA().movePlayer(c.getX(), c.getY(), 4);
				c.getPA().closeAllWindows();
				updatePyramidePlunderInterface(c);
				break;
			case 4:
				createRoom3(c);
				c.getPA().movePlayer(c.getX(), c.getY(), 8);
				c.getPA().closeAllWindows();
				updatePyramidePlunderInterface(c);
				break;
			case 8:
				createRoom4(c);
				c.getPA().movePlayer(c.getX(), c.getY(), 12);
				c.getPA().closeAllWindows();
				updatePyramidePlunderInterface(c);
			case 12:
				c.getDH().sendDialogues(105, 4476);
				break;
		}
	}
	
	/**
	* this method is called when a player 
	* logout or leaves the pyramid plunder minigame
	*/
	
	public static void endPyramidePlunder(Client c) {
		c.isInPyramidePlunder = false;
		c.getPA().closeAllWindows();
		c.roomProgress = 100;
		c.getPA().movePlayer(Config.EDGEVILLE_X, Config.EDGEVILLE_Y, 0);
		c.getPA().walkableInterface(-1);
	}
	
}