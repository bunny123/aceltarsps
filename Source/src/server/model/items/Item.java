package server.model.items;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import server.Config;
import server.Server;

public class Item {

	public static boolean playerCape(int itemId) {
		String[] data = {
			"cloak", "ava's", "cape", "accumulator", "master", "Ava's"
		};
		String item = getItemName(itemId);
		if (item == null) {
			return false;
		}
		boolean item1 = false;
		for(int i = 0; i < data.length; i++ ) {
			if(item.endsWith(data[i]) || item.contains(data[i])) {
				item1 = true;
			}
		}
		return item1;
	}

	public static boolean playerBoots(int itemId) {
		String[] data = {
			"Shoes", "shoes", "boots", "Boots"
		};
		String item = getItemName(itemId);
		if (item == null) {
			return false;
		}
		boolean item1 = false;
		for(int i = 0; i < data.length; i++ ) {
			if(item.endsWith(data[i]) || item.contains(data[i])) {
				item1 = true;
			}
		}
		return item1;
	}

	public static boolean playerGloves(int itemId) {
		String[] data = {
			"Gloves", "gloves", "brace", "Brace", "glove", "Glove", "gauntlets", "Gauntlets", "vamb"
		};
		String item = getItemName(itemId);
		if (item == null) {
			return false;
		}
		boolean item1 = false;
		for(int i = 0; i < data.length; i++ ) {
			if(item.endsWith(data[i]) || item.contains(data[i])) {
				item1 = true;
			}
		}
		return item1;
	}

	public static boolean playerShield(int itemId) {
		String[] data = {
			"kiteshield", "book", "Kiteshield", "shield", "Shield", "Kite", "kite", "defender", "ket-xil", "lantern", "Tome of frost", "of balance"
		};
		String item = getItemName(itemId);
		if (item == null) {
			return false;
		}
		boolean item1 = false;
		for(int i = 0; i < data.length; i++ ) {
			if(item.endsWith(data[i]) || item.contains(data[i])) {
				item1 = true;
			}
		}
		return item1;
	}

	public static boolean playerAmulet(int itemId) {
		String[] data = {
			"amulet", "Amulet", "necklace", "Necklace", "Pendant", "pendant", "Symbol", "symbol", "stole", "scarf"
		};
		String item = getItemName(itemId);
		if (item == null) {
			return false;
		}
		boolean item1 = false;
		for(int i = 0; i < data.length; i++ ) {
			if(item.endsWith(data[i]) || item.contains(data[i])) {
				item1 = true;
			}
		}
		return item1;
	}

	public static boolean playerArrows(int itemId) {
		String[] data = {
			"Arrows", "arrows", "Arrow", "arrow", "Bolts", "bolts", "Bolt", "bolt", "shot"
		};
		String item = getItemName(itemId);
		if (item == null) {
			return false;
		}
		boolean item1 = false;
		for(int i = 0; i < data.length; i++ ) {
			if(item.endsWith(data[i]) || item.contains(data[i])) {
				item1 = true;
			}
		}
		return item1;
	}

	public static boolean playerRings(int itemId) {
		String[] data = {
			"ring", "rings", "Ring", "Rings",
		};
		String item = getItemName(itemId);
		if (item == null) {
			return false;
		}
		boolean item1 = false;
		for(int i = 0; i < data.length; i++ ) {
			if(item.endsWith(data[i]) || item.contains(data[i])) {
				item1 = true;
			}
		}
		return item1;
	}

	public static boolean playerHats(int itemId) {
		String[] data = {
			"boater", "cowl", "peg", "coif", "helm", "afro", "Afro",
			"coif", "mask", "hat", "headband", "hood", 
			"disguise", "cavalier", "hood", "full helm", "tiara",
			"helmet", "Hat", "ears", "partyhat", "helm(t)",
			"helm(g)", "beret", "facemask", "sallet", "Void", "Void ranger helm", "Void Ranger Helm",
			"hat(g)", "hat(t)", "bandana", "Helm", "hood", "coif", "mitre", "cap", "wig", "PartyHat","h'ween mask", "Cowl", "pernix Cowl", "Slayer h", "helmet", "Full", "full","Slayer Helmet", "Slayer helm", "Slayer helmet (e)", "Slayer helmet (charged)", "full slayer helm", "Slayer helmet", "helm", "Helmet", "Slayer Helmet (e)", "Full slayer helmet", "Full Slayer Helmet", "full", "slayer", "helmet", "(e)", "cowl", "pernix cowl", "pernix"
		};
		String item = getItemName(itemId);
		if (item == null) {
			return false;
		}
		boolean item1 = false;
		for(int i = 0; i < data.length; i++ ) {
			if((item.endsWith(data[i]) || item.contains(data[i])) && itemId != 4214) {
				item1 = true;
			}
		}
		return item1;
	}

	public static boolean playerLegs(int itemId) {
		String[] data = {
			"tassets", "chaps", "bottoms", "gown", "trousers", 
			"platelegs", "robe", "tasset", "plateskirt", "legs", "leggings", 
			"shorts", "Skirt", "skirt", "cuisse", "Trousers", "chaps", "Pernix Chaps"
		};
		String item = getItemName(itemId);
		if (item == null) {
			return false;
		}
		boolean item1 = false;
		for(int i = 0; i < data.length; i++ ) {
			if((item.endsWith(data[i]) || item.contains(data[i])) && (!item.contains("top") && (!item.contains("robe (g)") && (!item.contains("robe (t)"))))) {
				item1 = true;
			}
		}
		return item1;
	}

	public static boolean playerBody(int itemId) {
		String[] data = {
			"plate", "body", "top", "Varrock armour", "Wizard robe", "Priest gown", "apron", "shirt", 
			"platebody", "robetop", "body(g)", "body(t)", 
			"Wizard robe (g)", "Wizard robe (t)", "body", "brassard", "blouse", 
			"tunic", "leathertop", "Saradomin plate", "chainbody", 
			"hauberk", "Shirt", "torso", "chestplate", "s Chainbody","agile_top","Ring_top", "Varrock", "Pernix Body", "Body", "pernix"
		};
		String item = getItemName(itemId);
		if (item == null) {
			return false;
		}
		boolean item1 = false;
		for(int i = 0; i < data.length; i++ ) {
			if(item.endsWith(data[i]) || item.contains(data[i])) {
				item1 = true;
			}
		}
		return item1;
	}

	private static String[] fullbody = {
		"top", "chestplate", "Wizard robe", "shirt","platebody","Ahrims robetop",
		"Karils leathertop","brassard","Robe top","robetop",
		"platebody (t)","platebody (g)","chestplate",
		"torso", "hauberk", "Dragon chainbody", "Vesta", "Morrigan","agile_top","Ring_top", "Varrock", "Dragon Platebody", "Pernix Body", "pernix body", "pernix Body", "Pernix Body", "body", "dragon platebody", "Dragon Platebody"
	};

	private static String[] fullhat = {
		"med helm", "Dharok's helm", "hood", "Initiate helm",
		"Helm of neitiznot","Armadyl helmet","Berserker helm", 
		"Archer helm", "Farseer helm", "Warrior helm", "Void", "hood", "Slayer Helmet", "Slayer helm", "Slayer helmet (e)", "Slayer helmet (charged)", "full slayer helm", "Slayer helmet"
	};

	private static String[] fullmask = {
		"full helm", "mask", "Verac's helm", "Guthan's helm", "Karil's coif", "mask", "Torag's helm", "sallet", "Saradomin helm", "Mask", "Jack lant Mask", "Dragon Mask", "Red Dragon Mask", "full", "slayer", "helmet", "(e)", "hood", "morrigan's coif", "coif", "Herblore hood", "h'ween", "cowl", "pernix cowl", "pernix"
	};

	private static String[] scroll = {"scroll", "Scroll"};

	public static boolean isScroll(int itemId) {
		String summonScroll = getItemName(itemId);
		if (summonScroll == null)
			return false;
		for (int i = 0; i < scroll.length; i++) {
			if (summonScroll.endsWith(scroll[i]) && itemId != 2720 && itemId != 2804 && itemId != 2789) {
				return true;
			}
		}
		return false;
	}

	public static boolean isFullBody(int itemId) {
		String weapon = getItemName(itemId);
		if (weapon == null)
			return false;
		for (int i = 0; i < fullbody.length; i++) {
			if (weapon.endsWith(fullbody[i]) && itemId != 2503 && itemId != 2501) {
				return true;
			}
		}
		return false;
	}

	public static boolean isFullHelm(int itemId) {
		String weapon = getItemName(itemId);
			if (weapon == null)
				return false;
		for (int i = 0; i < fullhat.length; i++) {
			if (weapon.endsWith(fullhat[i]) && itemId != 2631) {
				return true;
			}
		}
		return false;
	}

	public static boolean isFullMask(int itemId) {
		String weapon = getItemName(itemId);
			if (weapon == null)
				return false;
		for (int i = 0; i < fullmask.length; i++) {
			if (weapon.endsWith(fullmask[i]) && itemId != 2631) {
				return true;
			}
		}
		return false;
	}
	
	public static String getItemName(int id) {
		for (int j = 0; j < Server.itemHandler.ItemList.length; j++) {
			if (Server.itemHandler.ItemList[j] != null)
				if (Server.itemHandler.ItemList[j].itemId == id)
					return Server.itemHandler.ItemList[j].itemName;	
		}
		return null;
	}
	
	
	public static boolean[] itemStackable = new boolean[Config.ITEM_LIMIT];
	public static boolean[] itemIsNote = new boolean[Config.ITEM_LIMIT];
	public static int[] targetSlots = new int[Config.ITEM_LIMIT];
	static {
		int counter = 0;
		int c;
		
		try {
			FileInputStream dataIn = new FileInputStream(new File("./Data/data/stackable.dat"));
			while ((c = dataIn.read()) != -1) {
				if (c == 0) {
					itemStackable[counter] = false;
				} else {
					itemStackable[counter] = true;
				}
				counter++;
			}
			//itemStackable[995] = true;
			itemStackable[19475] = false;
			itemStackable[19576] = false;
			dataIn.close();
		} catch (IOException e) {
			System.out.println("Critical error while loading stackabledata! Trace:");
			e.printStackTrace();
		}

		counter = 0;
		
		try {
			FileInputStream dataIn = new FileInputStream(new File("./Data/data/notes.dat"));
			while ((c = dataIn.read()) != -1) {
				if (c == 0) {
					itemIsNote[counter] = true;
				} else {
					itemIsNote[counter] = false;
				}
				counter++;
			}

				int[] stackableItems = {18016, 18831, 13879, 13880, 13881, 13882,15243, 19152, 19157, 19162, 19467, 18201, 4561, 13280, 13883 }; //put your stackable items here
			for (int i = 0; i < stackableItems.length; i++) {
				for (int j = 0; j < scroll.length; j++) {
					itemStackable[stackableItems[i]] = true;
					itemStackable[stackableItems[j]] = true;
				}
			}

			dataIn.close();
		} catch (IOException e) {
			System.out.println("Critical error while loading notedata! Trace:");
			e.printStackTrace();
		}
		
		counter = 0;
		try {
			FileInputStream dataIn = new FileInputStream(new File("./Data/data/equipment.dat"));
			while ((c = dataIn.read()) != -1) {
				targetSlots[counter++] = c;
			}
			dataIn.close();
		} catch (IOException e) {
			System.out.println("Critical error while loading notedata! Trace:");
			e.printStackTrace();
		}
	}
	
	public static final boolean itemIsNoteable(int itemID) {
		return itemIsNote[itemID];
	}
}