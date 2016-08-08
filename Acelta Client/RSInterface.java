// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

public final class RSInterface {

	public void swapInventoryItems(int i, int j) {
		int k = inv[i];
		inv[i] = inv[j];
		inv[j] = k;
		k = invStackSizes[i];
		invStackSizes[i] = invStackSizes[j];
		invStackSizes[j] = k;
	}

	public static void unpack(StreamLoader streamLoader, TextDrawingArea textDrawingAreas[], StreamLoader streamLoader_1) {
		aMRUNodes_238 = new MRUNodes(50000);
		Stream stream = new Stream(streamLoader.getDataForName("data"));
		int i = -1;
		int j = stream.readUnsignedWord();
		interfaceCache = new RSInterface[50000];
		while(stream.currentOffset < stream.buffer.length) {
			int k = stream.readUnsignedWord();
			if(k == 65535) {
				i = stream.readUnsignedWord();
				k = stream.readUnsignedWord();
			}
			RSInterface rsInterface = interfaceCache[k] = new RSInterface();
			rsInterface.id = k;
			rsInterface.parentID = i;
			rsInterface.type = stream.readUnsignedByte();
			rsInterface.atActionType = stream.readUnsignedByte();
			rsInterface.contentType = stream.readUnsignedWord();
			rsInterface.width = stream.readUnsignedWord();
			rsInterface.height = stream.readUnsignedWord();
			rsInterface.aByte254 = (byte) stream.readUnsignedByte();
			rsInterface.mOverInterToTrigger = stream.readUnsignedByte();
			if(rsInterface.mOverInterToTrigger != 0)
				rsInterface.mOverInterToTrigger = (rsInterface.mOverInterToTrigger - 1 << 8) + stream.readUnsignedByte();
			else
				rsInterface.mOverInterToTrigger = -1;
			int i1 = stream.readUnsignedByte();
			if(i1 > 0) {
				rsInterface.anIntArray245 = new int[i1];
				rsInterface.anIntArray212 = new int[i1];
				for(int j1 = 0; j1 < i1; j1++) {
					rsInterface.anIntArray245[j1] = stream.readUnsignedByte();
					rsInterface.anIntArray212[j1] = stream.readUnsignedWord();
				}

			}
			int k1 = stream.readUnsignedByte();
			if(k1 > 0) {
				rsInterface.valueIndexArray = new int[k1][];
				for(int l1 = 0; l1 < k1; l1++) {
					int i3 = stream.readUnsignedWord();
					rsInterface.valueIndexArray[l1] = new int[i3];
					for(int l4 = 0; l4 < i3; l4++)
						rsInterface.valueIndexArray[l1][l4] = stream.readUnsignedWord();

				}

			}
			if(rsInterface.type == 0) {
				rsInterface.drawsTransparent = false;
				rsInterface.scrollMax = stream.readUnsignedWord();
				rsInterface.isMouseoverTriggered = stream.readUnsignedByte() == 1;
				int i2 = stream.readUnsignedWord();
				rsInterface.children = new int[i2];
				rsInterface.childX = new int[i2];
				rsInterface.childY = new int[i2];
				for(int j3 = 0; j3 < i2; j3++) {
					rsInterface.children[j3] = stream.readUnsignedWord();
					rsInterface.childX[j3] = stream.readSignedWord();
					rsInterface.childY[j3] = stream.readSignedWord();
				}
			}
			if(rsInterface.type == 1) {
				stream.readUnsignedWord();
				stream.readUnsignedByte();
			}
			if(rsInterface.type == 2) {
				rsInterface.inv = new int[rsInterface.width * rsInterface.height];
				rsInterface.invStackSizes = new int[rsInterface.width * rsInterface.height];
				rsInterface.aBoolean259 = stream.readUnsignedByte() == 1;
				rsInterface.isInventoryInterface = stream.readUnsignedByte() == 1;
				rsInterface.usableItemInterface = stream.readUnsignedByte() == 1;
				rsInterface.aBoolean235 = stream.readUnsignedByte() == 1;
				rsInterface.invSpritePadX = stream.readUnsignedByte();
				rsInterface.invSpritePadY = stream.readUnsignedByte();
				rsInterface.spritesX = new int[20];
				rsInterface.spritesY = new int[20];
				rsInterface.sprites = new Sprite[20];
				for(int j2 = 0; j2 < 20; j2++) {
					int k3 = stream.readUnsignedByte();
					if(k3 == 1) {
						rsInterface.spritesX[j2] = stream.readSignedWord();
						rsInterface.spritesY[j2] = stream.readSignedWord();
						String s1 = stream.readString();
						if(streamLoader_1 != null && s1.length() > 0) {
							int i5 = s1.lastIndexOf(",");
							rsInterface.sprites[j2] = method207(Integer.parseInt(s1.substring(i5 + 1)), streamLoader_1, s1.substring(0, i5));
						}
					}
				}
				rsInterface.actions = new String[5];
				for(int l3 = 0; l3 < 5; l3++) {
					rsInterface.actions[l3] = stream.readString();
					if(rsInterface.actions[l3].length() == 0)
						rsInterface.actions[l3] = null;
					if(rsInterface.parentID == 1644)
						rsInterface.actions[2] = "Operate";
				}
			}
			if(rsInterface.type == 3)
				rsInterface.aBoolean227 = stream.readUnsignedByte() == 1;
			if(rsInterface.type == 4 || rsInterface.type == 1) {
				rsInterface.centerText = stream.readUnsignedByte() == 1;
				int k2 = stream.readUnsignedByte();
				if(textDrawingAreas != null)
					rsInterface.textDrawingAreas = textDrawingAreas[k2];
				rsInterface.textShadow = stream.readUnsignedByte() == 1;
			}
			if(rsInterface.type == 4) {
				rsInterface.message = stream.readString();
				rsInterface.aString228 = stream.readString();
			}
			if(rsInterface.type == 1 || rsInterface.type == 3 || rsInterface.type == 4)
				rsInterface.textColor = stream.readDWord();
			if(rsInterface.type == 3 || rsInterface.type == 4) {
				rsInterface.anInt219 = stream.readDWord();
				rsInterface.anInt216 = stream.readDWord();
				rsInterface.anInt239 = stream.readDWord();
			}
			if(rsInterface.type == 5) {
				rsInterface.drawsTransparent = false;
				String s = stream.readString();
				if(streamLoader_1 != null && s.length() > 0) {
					int i4 = s.lastIndexOf(",");
					rsInterface.sprite1 = method207(Integer.parseInt(s.substring(i4 + 1)), streamLoader_1, s.substring(0, i4));
				}
				s = stream.readString();
				if(streamLoader_1 != null && s.length() > 0) {
					int j4 = s.lastIndexOf(",");
					rsInterface.sprite2 = method207(Integer.parseInt(s.substring(j4 + 1)), streamLoader_1, s.substring(0, j4));
				}
			}
			if(rsInterface.type == 6) {
				int l = stream.readUnsignedByte();
				if(l != 0) {
					rsInterface.anInt233 = 1;
					rsInterface.mediaID = (l - 1 << 8) + stream.readUnsignedByte();
				}
				l = stream.readUnsignedByte();
				if(l != 0) {
					rsInterface.anInt255 = 1;
					rsInterface.anInt256 = (l - 1 << 8) + stream.readUnsignedByte();
				}
				l = stream.readUnsignedByte();
				if(l != 0)
					rsInterface.anInt257 = (l - 1 << 8) + stream.readUnsignedByte();
				else
					rsInterface.anInt257 = -1;
				l = stream.readUnsignedByte();
				if(l != 0)
					rsInterface.anInt258 = (l - 1 << 8) + stream.readUnsignedByte();
				else
					rsInterface.anInt258 = -1;
				rsInterface.modelZoom = stream.readUnsignedWord();
				rsInterface.modelRotation1 = stream.readUnsignedWord();
				rsInterface.modelRotation2 = stream.readUnsignedWord();
			}
			if(rsInterface.type == 7) {
				rsInterface.inv = new int[rsInterface.width * rsInterface.height];
				rsInterface.invStackSizes = new int[rsInterface.width * rsInterface.height];
				rsInterface.centerText = stream.readUnsignedByte() == 1;
				int l2 = stream.readUnsignedByte();
				if(textDrawingAreas != null)
					rsInterface.textDrawingAreas = textDrawingAreas[l2];
				rsInterface.textShadow = stream.readUnsignedByte() == 1;
				rsInterface.textColor = stream.readDWord();
				rsInterface.invSpritePadX = stream.readSignedWord();
				rsInterface.invSpritePadY = stream.readSignedWord();
				rsInterface.isInventoryInterface = stream.readUnsignedByte() == 1;
				rsInterface.actions = new String[5];
				for(int k4 = 0; k4 < 5; k4++) {
					rsInterface.actions[k4] = stream.readString();
					if(rsInterface.actions[k4].length() == 0)
						rsInterface.actions[k4] = null;
				}

			}
			if(rsInterface.atActionType == 2 || rsInterface.type == 2) {
				rsInterface.selectedActionName = stream.readString();
				rsInterface.spellName = stream.readString();
				rsInterface.spellUsableOn = stream.readUnsignedWord();
			}

			if(rsInterface.type == 8)
				rsInterface.message = stream.readString();

			if(rsInterface.atActionType == 1 || rsInterface.atActionType == 4 || rsInterface.atActionType == 5 || rsInterface.atActionType == 6) {
				rsInterface.tooltip = stream.readString();
				if(rsInterface.tooltip.length() == 0) {
					if(rsInterface.atActionType == 1)
						rsInterface.tooltip = "Ok";
					if(rsInterface.atActionType == 4)
						rsInterface.tooltip = "Select";
					if(rsInterface.atActionType == 5)
						rsInterface.tooltip = "Select";
					if(rsInterface.atActionType == 6)
						rsInterface.tooltip = "Continue";
				}
			}
		}
		aClass44 = streamLoader;
		//Starter(textDrawingAreas);
		//StarterInfomation(textDrawingAreas);
		bobInterface(textDrawingAreas);
		godWars(textDrawingAreas);
		quickCurses(textDrawingAreas);
		quickPrayers(textDrawingAreas);
		prayerTab(textDrawingAreas);
		curseTab(textDrawingAreas);
		//Curses(textDrawingAreas);
		emoteTab();
		notesTab(textDrawingAreas);
		optionTab(textDrawingAreas);
		achievement(textDrawingAreas);
		questTab(textDrawingAreas);
		skillInterface(textDrawingAreas);
		clanChatTab(textDrawingAreas);
		FightCave(textDrawingAreas);
		Sidebar0(textDrawingAreas);
		friendsTab(textDrawingAreas);
        ignoreTab(textDrawingAreas);
		//SettingsTab(textDrawingAreas);
		Pestpanel(textDrawingAreas);
		Pestpanel2(textDrawingAreas);
		equipmentScreen(textDrawingAreas);
		magicTab(textDrawingAreas);
		ancientMagicTab(textDrawingAreas);
		configureLunar(textDrawingAreas);
		SettingsTab(textDrawingAreas);
		createClan(textDrawingAreas); 
		buyRXPShop1(textDrawingAreas); 
		bossinterface(textDrawingAreas);
		Crafting(textDrawingAreas);
		Shop(textDrawingAreas);
		pouches(textDrawingAreas);
		SummonTab(textDrawingAreas);
		summoningTab(textDrawingAreas);
		petSummoningTab(textDrawingAreas);
		scrolls(textDrawingAreas);
		skilllevel(textDrawingAreas);
		Sell(textDrawingAreas);
		Buy(textDrawingAreas);
		bountyHunter(textDrawingAreas);
		Bank();
		constructLunar();
		aMRUNodes_238 = null;
	}
	public static void bountyHunter(TextDrawingArea[] tda) {
		RSInterface tab = addInterface(25347);
		tab.totalChildren(5);

		addText(25349, "Target:", 0xffcccc, false, true, -1, tda, 1);
		addText(25350, "Username Here", 0xff6666, true, true, -1, tda, 2);
		addText(25351, "Pickup penalty:", 0xff3333, false, true, -1, tda, 1);
		addText(25352, "Sec here", 0xff6666, true, true, -1, tda, 2);
		
		addTransparentSprite(25348, 0, "Other/bh", 128);

		tab.child(0,25348,332,3);
		tab.child(1,25349,340,13);
		tab.child(2,25350,452,13);
		tab.child(3,25351,340,34);
		tab.child(4,25352,470,34);
	}
	private static final int WHITE_TEXT = 0xFFFFFF;
	private static final int GREY_TEXT = 0xB9B855;
	private static final int ORANGE_TEXT = 0xFF981F;
	
	private static void summoningTab(TextDrawingArea[] tda) {
		final String dir = "Interfaces/Tab/SPRITE";
		RSInterface rsi = addTabInterface(18017);
		addButton(18018, 0, dir, 143, 13, "Cast special", 1); 
		addText(18019, "S P E C I A L  M O V E", tda, 0, WHITE_TEXT, false, false);
		addSprite(18020, 1, dir);
		addFamiliarHead(18021, 75, 50, 875);
		addSprite(18022, 2, dir);
		addConfigButton(18023, 18017, 4, 3, dir, 30, 31, "Cast special", 0, 1, 330);
		addText(18024, "0", tda, 0, ORANGE_TEXT, false, true);
		addSprite(18025, 5, dir);
		addConfigButton(18026, 18017, 7, 6, dir, 29, 39, "Attack", 0, 1, 333);
		addSprite(18027, 8, dir);
		addText(18028, "", tda, 2, ORANGE_TEXT, true, false);
		addHoverButton(18029, dir, 9, 38, 38, "Withdraw BoB", -1, 18030, 1);
		addHoveredButton(18030, dir, 10, 38, 38, 18031);
		addHoverButton(18032, dir, 11, 38, 38, "Renew familiar", -1, 18033, 1);
		addHoveredButton(18033, dir, 12, 38, 38, 18034);
		addHoverButton(18035, dir, 13, 38, 38, "Call follower", -1, 18036, 1);
		addHoveredButton(18036, dir, 14, 38, 38, 18037);
		addHoverButton(18038, dir, 15, 38, 38, "Dismiss familiar", -1, 18039, 1);
		addHoveredButton(18039, dir, 16, 38, 38, 18040);
		addHead2(23027, 50, 50, 800);
		addSprite(18041, 17, dir);
		addSprite(18042, 18, dir);
		addText(18043, "", tda, 0, GREY_TEXT, false, true);
		addSprite(18044, 19, dir);
		addText(18045, "", tda, 0, GREY_TEXT, false, true);
		setChildren(25, rsi);
		setBounds(18018, 23, 10, 0, rsi);
		setBounds(18019, 43, 12, 1, rsi);
		setBounds(18020, 10, 32, 2, rsi);
		setBounds(18021, 63, 60, 3, rsi);
		setBounds(18022, 11, 32, 4, rsi);
		setBounds(18023, 14, 35, 5, rsi);
		setBounds(18024, 25, 69, 6, rsi);
		setBounds(18025, 138, 32, 7, rsi);
		setBounds(18026, 143, 36, 8, rsi);
		setBounds(18027, 12, 144, 9, rsi);
		setBounds(18028, 93, 146, 10, rsi);
		setBounds(18029, 23, 168, 11, rsi);
		setBounds(18030, 23, 168, 12, rsi);
		setBounds(18032, 75, 168, 13, rsi);
		setBounds(18033, 75, 168, 14, rsi);
		setBounds(18035, 23, 213, 15, rsi);
		setBounds(18036, 23, 213, 16, rsi);
		setBounds(18038, 75, 213, 17, rsi);
		setBounds(18039, 75, 213, 18, rsi);
		setBounds(18041, 130, 168, 19, rsi);
		setBounds(18042, 153, 170, 20, rsi);
		setBounds(18043, 148, 198, 21, rsi);
		setBounds(18044, 149, 213, 22, rsi);
		setBounds(18045, 145, 241, 23, rsi);
		setBounds(23027, 75, 55, 24, rsi);
	}
	
	private static void petSummoningTab(TextDrawingArea[] tda) {
		final String dir = "Interfaces/Tab/SPRITE";
		RSInterface rsi = addTabInterface(19017);
		addSprite(19018, 1, dir);
		addFamiliarHead(19019, 75, 50, 900);
		addSprite(19020, 8, dir);
		addText(19021, "None", tda, 2, ORANGE_TEXT, true, false);
		addHoverButton(19022, dir, 13, 38, 38, "Call follower", -1, 19023, 1);
		addHoveredButton(19023, dir, 14, 38, 38, 19024);
		addHoverButton(19025, dir, 15, 38, 38, "Dismiss familiar", -1, 19026, 1);
		addHoveredButton(19026, dir, 16, 38, 38, 19027);
		addSprite(19028, 17, dir);
		addSprite(19029, 20, dir);
		addText(19030, "0%", tda, 0, GREY_TEXT, false, true);
		addSprite(19031, 21, dir);
		addText(19032, "0%", tda, 0, WHITE_TEXT, false, true);
		setChildren(13, rsi);
		setBounds(19018, 10, 32, 0, rsi);
		setBounds(19019, 65, 65, 1, rsi);
		setBounds(19020, 12, 145, 2, rsi);
		setBounds(19021, 93, 147, 3, rsi);
		setBounds(19022, 23, 213, 4, rsi);
		setBounds(19023, 23, 213, 5, rsi);
		setBounds(19025, 75, 213, 6, rsi);
		setBounds(19026, 75, 213, 7, rsi);
		setBounds(19028, 130, 168, 8, rsi);
		setBounds(19029, 148, 170, 9, rsi);
		setBounds(19030, 152, 198, 10, rsi);
		setBounds(19031, 149, 220, 11, rsi);
		setBounds(19032, 155, 241, 12, rsi);
	}
	
	private static void addFamiliarHead(int interfaceID, int width, int height, int zoom) {
       	 	RSInterface rsi = addTabInterface(interfaceID);
        	rsi.type = 6;
		rsi.anInt233 = 2;
		rsi.mediaID = 4000;
        	rsi.modelZoom = zoom;
        	rsi.modelRotation1 = 40;
        	rsi.modelRotation2 = 1800;
        	rsi.height = height;
        	rsi.width = width;
    	}
	public static void Buy(TextDrawingArea[] TDA) {
	RSInterface rsinterface = addTabInterface(24600);
	addSprite(24601, 0, "Interfaces/GrandExchange/buy");
	addHoverButton(24602, "Interfaces/GrandExchange/close", 1, 16, 16, "Close", 0, 24603, 1);
        addHoveredButton(24603, "Interfaces/GrandExchange/close", 2, 16, 16, 24604);
	addHoverButton(24606, "Interfaces/GrandExchange/sprite", 1, 13, 13, "Decrease Quantity", 0, 24607, 1);
        addHoveredButton(24607, "Interfaces/GrandExchange/sprite", 3, 13, 13, 24608);
	addHoverButton(24610, "Interfaces/GrandExchange/sprite", 2, 13, 13, "Increase Quantity", 0, 24611, 1);
        addHoveredButton(24611, "Interfaces/GrandExchange/sprite", 4, 13, 13, 24612);
	addHoverButton(24614, "Interfaces/GrandExchange/sprite", 5, 35, 25, "Add 1", 0, 24615, 1);
        addHoveredButton(24615, "Interfaces/GrandExchange/sprite", 6, 35, 25, 24616);
	addHoverButton(24618, "Interfaces/GrandExchange/sprite", 7, 35, 25, "Add 10", 0, 24619, 1);
        addHoveredButton(24619, "Interfaces/GrandExchange/sprite", 8, 35, 25, 24620);
	addHoverButton(24622, "Interfaces/GrandExchange/sprite", 9, 35, 25, "Add 100", 0, 24623, 1);
        addHoveredButton(24623, "Interfaces/GrandExchange/sprite", 10, 35, 25, 24624);
	addHoverButton(24626, "Interfaces/GrandExchange/sprite", 11, 35, 25, "Add 1000", 0, 24627, 1);
        addHoveredButton(24627, "Interfaces/GrandExchange/sprite", 12, 35, 25, 24628);
	addHoverButton(24630, "Interfaces/GrandExchange/sprite", 13, 35, 25, "Edit Quantity", 0, 24631, 1);
        addHoveredButton(24631, "Interfaces/GrandExchange/sprite", 14, 35, 25, 24632);
	addHoverButton(24634, "Interfaces/GrandExchange/sprite", 15, 35, 25, "Decrease Price", 0, 24635, 1);
        addHoveredButton(24635, "Interfaces/GrandExchange/sprite", 16, 35, 25, 24636);
	addHoverButton(24638, "Interfaces/GrandExchange/sprite", 17, 35, 25, "Offer Guild Price", 0, 24639, 1);
        addHoveredButton(24639, "Interfaces/GrandExchange/sprite", 18, 35, 25, 24640);
	addHoverButton(24642, "Interfaces/GrandExchange/sprite", 13, 35, 25, "Edit Price", 0, 24643, 1);
        addHoveredButton(24643, "Interfaces/GrandExchange/sprite", 14, 35, 25, 24644);
	addHoverButton(24646, "Interfaces/GrandExchange/sprite", 19, 35, 25, "Increase Price", 0, 24647, 1);
        addHoveredButton(24647, "Interfaces/GrandExchange/sprite", 20, 35, 25, 24648);
	addHoverButton(24650, "Interfaces/GrandExchange/sprite", 21, 120, 43, "Confrim Offer", 0, 24651, 1);
        addHoveredButton(24651, "Interfaces/GrandExchange/sprite", 22, 120, 43, 24652);
	addHoverButton(24654, "Interfaces/GrandExchange/sprite", 23, 40, 36, "Choose Item", 0, 24655, 1);
        addHoveredButton(24655, "Interfaces/GrandExchange/sprite", 24, 40, 36, 24656);
	addHoverButton(24658, "Interfaces/GrandExchange/sprite", 25, 29, 23, "Back", 0, 24659, 1);
        addHoveredButton(24659, "Interfaces/GrandExchange/sprite", 26, 29, 23, 24660);
	addHoverButton(24662, "Interfaces/GrandExchange/sprite", 1, 13, 13, "Decrease Price", 0, 24663, 1);
        addHoveredButton(24663, "Interfaces/GrandExchange/sprite", 3, 13, 13, 24664);
	addHoverButton(24665, "Interfaces/GrandExchange/sprite", 2, 13, 13, "Increase Price", 0, 24666, 1);
        addHoveredButton(24666, "Interfaces/GrandExchange/sprite", 4, 13, 13, 24667); 
 	addText(24669, "Choose an item to exchange", TDA, 0, 0x96731A, false, true);
 	addText(24670, "Click the icone to the left to sreach for items.", TDA, 0, 0x958E60, false, true);
 	addText(24671, "0", TDA, 0, 0xB58338, false, true);
 	addText(24672, "1 gp", TDA, 0, 0xB58338, false, true);
 	addText(24673, "0 gp", TDA, 0, 0xB58338, false, true);
        rsinterface.totalChildren(40);
        rsinterface.child(0, 24601, 4, 23);
        rsinterface.child(1, 24602, 464, 33);
        rsinterface.child(2, 24603, 464, 33);
        rsinterface.child(3, 24606, 46, 184);
        rsinterface.child(4, 24607, 46, 184);
        rsinterface.child(5, 24610, 226, 184);
        rsinterface.child(6, 24611, 226, 184);
        rsinterface.child(7, 24614, 43, 208);
        rsinterface.child(8, 24615, 43, 208);
        rsinterface.child(9, 24618, 84, 208);
        rsinterface.child(10, 24619, 84, 208);
        rsinterface.child(11, 24622, 125, 208);
        rsinterface.child(12, 24623, 125, 208);
        rsinterface.child(13, 24626, 166, 208);
        rsinterface.child(14, 24627, 166, 208);
        rsinterface.child(15, 24630, 207, 208);
        rsinterface.child(16, 24631, 207, 208);
        rsinterface.child(17, 24634, 260, 208);
        rsinterface.child(18, 24635, 260, 208);
        rsinterface.child(19, 24638, 316, 208);
        rsinterface.child(20, 24639, 316, 208);
        rsinterface.child(21, 24642, 357, 208);
        rsinterface.child(22, 24643, 357, 208);
        rsinterface.child(23, 24646, 413, 208);
        rsinterface.child(24, 24647, 413, 208);
        rsinterface.child(25, 24650, 191, 273);
        rsinterface.child(26, 24651, 191, 273);
        rsinterface.child(27, 24654, 93, 95);
        rsinterface.child(28, 24655, 93, 95);
        rsinterface.child(29, 24658, 19, 284);
        rsinterface.child(30, 24659, 19, 284);
        rsinterface.child(31, 24662, 260, 184);
        rsinterface.child(32, 24663, 260, 184);
        rsinterface.child(33, 24665, 435, 184);
        rsinterface.child(34, 24666, 435, 184);
        rsinterface.child(35, 24669, 202, 71);
        rsinterface.child(36, 24670, 202, 98);
        rsinterface.child(37, 24671, 139, 185);
        rsinterface.child(38, 24672, 341, 185);
        rsinterface.child(39, 24673, 238, 246);
	}

public static void Sell(TextDrawingArea[] TDA) {
	RSInterface rsinterface = addTabInterface(24700);
	addSprite(24701, 0, "Interfaces/GrandExchange/sell");
	addHoverButton(24702, "Interfaces/GrandExchange/close", 1, 16, 16, "Close", 0, 24703, 1);
        addHoveredButton(24703, "Interfaces/GrandExchange/close", 2, 16, 16, 24704);
	addHoverButton(24706, "Interfaces/GrandExchange/sprite", 1, 13, 13, "Decrease Quantity", 0, 24707, 1);
        addHoveredButton(24707, "Interfaces/GrandExchange/sprite", 3, 13, 13, 24708);
	addHoverButton(24710, "Interfaces/GrandExchange/sprite", 2, 13, 13, "Increase Quantity", 0, 24711, 1);
        addHoveredButton(24711, "Interfaces/GrandExchange/sprite", 4, 13, 13, 24712);
	addHoverButton(24714, "Interfaces/GrandExchange/sprite", 5, 35, 25, "Sell 1", 0, 24715, 1);
        addHoveredButton(24715, "Interfaces/GrandExchange/sprite", 6, 35, 25, 24716);
	addHoverButton(24718, "Interfaces/GrandExchange/sprite", 7, 35, 25, "Sell 10", 0, 24719, 1);
        addHoveredButton(24719, "Interfaces/GrandExchange/sprite", 8, 35, 25, 24720);
	addHoverButton(24722, "Interfaces/GrandExchange/sprite", 9, 35, 25, "Sell 100", 0, 24723, 1);
        addHoveredButton(24723, "Interfaces/GrandExchange/sprite", 10, 35, 25, 24724);
	addHoverButton(24726, "Interfaces/GrandExchange/sprite", 29, 35, 25, "Sell All", 0, 24727, 1);
        addHoveredButton(24727, "Interfaces/GrandExchange/sprite", 30, 35, 25, 24728);
	addHoverButton(24730, "Interfaces/GrandExchange/sprite", 13, 35, 25, "Edit Quantity", 0, 24731, 1);
        addHoveredButton(24731, "Interfaces/GrandExchange/sprite", 14, 35, 25, 24732);
	addHoverButton(24734, "Interfaces/GrandExchange/sprite", 15, 35, 25, "Decrease Price", 0, 24735, 1);
        addHoveredButton(24735, "Interfaces/GrandExchange/sprite", 16, 35, 25, 24736);
	addHoverButton(24738, "Interfaces/GrandExchange/sprite", 17, 35, 25, "Offer Guild Price", 0, 24739, 1);
        addHoveredButton(24739, "Interfaces/GrandExchange/sprite", 18, 35, 25, 24740);
	addHoverButton(24742, "Interfaces/GrandExchange/sprite", 13, 35, 25, "Edit Price", 0, 24743, 1);
        addHoveredButton(24743, "Interfaces/GrandExchange/sprite", 14, 35, 25, 24744);
	addHoverButton(24746, "Interfaces/GrandExchange/sprite", 19, 35, 25, "Increase Price", 0, 24747, 1);
        addHoveredButton(24747, "Interfaces/GrandExchange/sprite", 20, 35, 25, 24748);
	addHoverButton(24750, "Interfaces/GrandExchange/sprite", 21, 120, 43, "Confrim Offer", 0, 24751, 1);
        addHoveredButton(24751, "Interfaces/GrandExchange/sprite", 22, 120, 43, 24752);
	addHoverButton(24758, "Interfaces/GrandExchange/sprite", 25, 29, 23, "Back", 0, 24759, 1);
        addHoveredButton(24759, "Interfaces/GrandExchange/sprite", 26, 29, 23, 24760);
	addHoverButton(24762, "Interfaces/GrandExchange/sprite", 1, 13, 13, "Decrease Price", 0, 24763, 1);
        addHoveredButton(24763, "Interfaces/GrandExchange/sprite", 3, 13, 13, 24764);
	addHoverButton(24765, "Interfaces/GrandExchange/sprite", 2, 13, 13, "Increase Price", 0, 24766, 1);
        addHoveredButton(24766, "Interfaces/GrandExchange/sprite", 4, 13, 13, 24767);
 	addText(24769, "Choose an item to exchange", TDA, 0, 0x96731A, false, true);
 	addText(24770, "Click the icone to the left to sreach for items.", TDA, 0, 0x958E60, false, true);
	addText(24771, "0", TDA, 0, 0xB58338, false, true);
 	addText(24772, "1 gp", TDA, 0, 0xB58338, false, true);
 	addText(24773, "0 gp", TDA, 0, 0xB58338, false, true);
        rsinterface.totalChildren(38);
        rsinterface.child(0, 24701, 4, 23);
        rsinterface.child(1, 24702, 464, 33);
        rsinterface.child(2, 24703, 464, 33);
        rsinterface.child(3, 24706, 46, 184);
        rsinterface.child(4, 24707, 46, 184);
        rsinterface.child(5, 24710, 226, 184);
        rsinterface.child(6, 24711, 226, 184);
        rsinterface.child(7, 24714, 43, 208);
        rsinterface.child(8, 24715, 43, 208);
        rsinterface.child(9, 24718, 84, 208);
        rsinterface.child(10, 24719, 84, 208);
        rsinterface.child(11, 24722, 125, 208);
        rsinterface.child(12, 24723, 125, 208);
        rsinterface.child(13, 24726, 166, 208);
        rsinterface.child(14, 24727, 166, 208);
        rsinterface.child(15, 24730, 207, 208);
        rsinterface.child(16, 24731, 207, 208);
        rsinterface.child(17, 24734, 260, 208);
        rsinterface.child(18, 24735, 260, 208);
        rsinterface.child(19, 24738, 316, 208);
        rsinterface.child(20, 24739, 316, 208);
        rsinterface.child(21, 24742, 357, 208);
        rsinterface.child(22, 24743, 357, 208);
        rsinterface.child(23, 24746, 413, 208);
        rsinterface.child(24, 24747, 413, 208);
        rsinterface.child(25, 24750, 191, 273);
        rsinterface.child(26, 24751, 191, 273);
        rsinterface.child(27, 24758, 19, 284);
        rsinterface.child(28, 24759, 19, 284);
        rsinterface.child(29, 24762, 260, 184);
        rsinterface.child(30, 24763, 260, 184);
        rsinterface.child(31, 24765, 435, 184);
        rsinterface.child(32, 24766, 435, 184);
        rsinterface.child(33, 24769, 202, 71);
        rsinterface.child(34, 24770, 202, 98);
        rsinterface.child(35, 24771, 139, 185);
        rsinterface.child(36, 24772, 341, 185);
        rsinterface.child(37, 24773, 238, 246);
	}
	public static void skilllevel(TextDrawingArea[] tda) {
	 RSInterface text = interfaceCache[7202];
	 RSInterface attack = interfaceCache[6247];
	 RSInterface defence = interfaceCache[6253];
	 RSInterface str = interfaceCache[6206];
	 RSInterface hits = interfaceCache[6216];
	 RSInterface rng = interfaceCache[4443];
	 RSInterface pray = interfaceCache[6242];
	 RSInterface mage = interfaceCache[6211];
	 RSInterface cook = interfaceCache[6226];
	 RSInterface wood = interfaceCache[4272];
	 RSInterface flet = interfaceCache[6231];
	 RSInterface fish = interfaceCache[6258];
	 RSInterface fire = interfaceCache[4282];
	 RSInterface craf = interfaceCache[6263];
	 RSInterface smit = interfaceCache[6221];
	 RSInterface mine = interfaceCache[4416];
	 RSInterface herb = interfaceCache[6237];
	 RSInterface agil = interfaceCache[4277];
	 RSInterface thie = interfaceCache[4261];
	 RSInterface slay = interfaceCache[12122];
	 RSInterface farm = interfaceCache[5267];
	 RSInterface rune = interfaceCache[4267];
	 RSInterface cons = interfaceCache[7267];
	 RSInterface hunt = interfaceCache[8267];
	 RSInterface summ = addInterface(9267);
	 RSInterface dung = addInterface(10267);
		addSprite(17878, 0, "Interfaces/skillchat/skill");
		addSprite(17879, 1, "Interfaces/skillchat/skill");
		addSprite(17880, 2, "Interfaces/skillchat/skill");
		addSprite(17881, 3, "Interfaces/skillchat/skill");
		addSprite(17882, 4, "Interfaces/skillchat/skill");
		addSprite(17883, 5, "Interfaces/skillchat/skill");
		addSprite(17884, 6, "Interfaces/skillchat/skill");
		addSprite(17885, 7, "Interfaces/skillchat/skill");
		addSprite(17886, 8, "Interfaces/skillchat/skill");
		addSprite(17887, 9, "Interfaces/skillchat/skill");
		addSprite(17888, 10, "Interfaces/skillchat/skill");
		addSprite(17889, 11, "Interfaces/skillchat/skill");
		addSprite(17890, 12, "Interfaces/skillchat/skill");
		addSprite(17891, 13, "Interfaces/skillchat/skill");
		addSprite(17892, 14, "Interfaces/skillchat/skill");
		addSprite(17893, 15, "Interfaces/skillchat/skill");
		addSprite(17894, 16, "Interfaces/skillchat/skill");
		addSprite(17895, 17, "Interfaces/skillchat/skill");
		addSprite(17896, 18, "Interfaces/skillchat/skill");
		addSprite(11897, 19, "Interfaces/skillchat/skill");	
		addSprite(17898, 20, "Interfaces/skillchat/skill");
		addSprite(17899, 21, "Interfaces/skillchat/skill");
		addSprite(17900, 22, "Interfaces/skillchat/skill");
		addSprite(17901, 23, "Interfaces/skillchat/skill");		
		addSprite(17902, 24, "Interfaces/skillchat/skill");
				setChildren(4, attack);
				setBounds(17878, 20, 30, 0, attack);
				setBounds(4268, 80, 15, 1, attack);
				setBounds(4269, 80, 45, 2, attack);
				setBounds(358, 95, 75, 3, attack);
				setChildren(4, defence);
				setBounds(17879, 20, 30, 0, defence);
				setBounds(4268, 80, 15, 1, defence);
				setBounds(4269, 80, 45, 2, defence);
				setBounds(358, 95, 75, 3, defence);
				setChildren(4, str);
				setBounds(17880, 20, 30, 0, str);
				setBounds(4268, 80, 15, 1, str);
				setBounds(4269, 80, 45, 2, str);
				setBounds(358, 95, 75, 3, str);
				setChildren(4, hits);
				setBounds(17881, 20, 30, 0, hits);
				setBounds(4268, 80, 15, 1, hits);
				setBounds(4269, 80, 45, 2, hits);
				setBounds(358, 95, 75, 3, hits);
				setChildren(4, rng);
				setBounds(17882, 20, 30, 0, rng);
				setBounds(4268, 80, 15, 1, rng);
				setBounds(4269, 80, 45, 2, rng);
				setBounds(358, 95, 75, 3, rng);
				setChildren(4, pray);
				setBounds(17883, 20, 30, 0, pray);
				setBounds(4268, 80, 15, 1, pray);
				setBounds(4269, 80, 45, 2, pray);
				setBounds(358, 95, 75, 3, pray);
				setChildren(4, mage);
				setBounds(17884, 20, 30, 0, mage);
				setBounds(4268, 80, 15, 1, mage);
				setBounds(4269, 80, 45, 2, mage);
				setBounds(358, 95, 75, 3, mage);
				setChildren(4, cook);
				setBounds(17885, 20, 30, 0, cook);
				setBounds(4268, 80, 15, 1, cook);
				setBounds(4269, 80, 45, 2, cook);
				setBounds(358, 95, 75, 3, cook);
				setChildren(4, wood);
				setBounds(17886, 20, 30, 0, wood);
				setBounds(4268, 80, 15, 1, wood);
				setBounds(4269, 80, 45, 2, wood);
				setBounds(358, 95, 75, 3, wood);
				setChildren(4, flet);
				setBounds(17887, 20, 30, 0, flet);
				setBounds(4268, 80, 15, 1, flet);
				setBounds(4269, 80, 45, 2, flet);
				setBounds(358, 95, 75, 3, flet);
				setChildren(4, fish);
				setBounds(17888, 20, 30, 0, fish);
				setBounds(4268, 80, 15, 1, fish);
				setBounds(4269, 80, 45, 2, fish);
				setBounds(358, 95, 75, 3, fish);
				setChildren(4, fire);
				setBounds(17889, 20, 30, 0, fire);
				setBounds(4268, 80, 15, 1, fire);
				setBounds(4269, 80, 45, 2, fire);
				setBounds(358, 95, 75, 3, fire);
				setChildren(4, craf);
				setBounds(17890, 20, 30, 0, craf);
				setBounds(4268, 80, 15, 1, craf);
				setBounds(4269, 80, 45, 2, craf);
				setBounds(358, 95, 75, 3, craf);
				setChildren(4, smit);
				setBounds(17891, 20, 30, 0, smit);
				setBounds(4268, 80, 15, 1, smit);
				setBounds(4269, 80, 45, 2, smit);
				setBounds(358, 95, 75, 3, smit);
				setChildren(4, mine);
				setBounds(17892, 20, 30, 0, mine);
				setBounds(4268, 80, 15, 1, mine);
				setBounds(4269, 80, 45, 2, mine);
				setBounds(358, 95, 75, 3, mine);
				setChildren(4, herb);
				setBounds(17893, 20, 30, 0, herb);
				setBounds(4268, 80, 15, 1, herb);
				setBounds(4269, 80, 45, 2, herb);
				setBounds(358, 95, 75, 3, herb);
				setChildren(4, agil);
				setBounds(17894, 20, 30, 0, agil);
				setBounds(4268, 80, 15, 1, agil);
				setBounds(4269, 80, 45, 2, agil);
				setBounds(358, 95, 75, 3, agil);
				setChildren(4, thie);
				setBounds(17895, 20, 30, 0, thie);
				setBounds(4268, 80, 15, 1, thie);
				setBounds(4269, 80, 45, 2, thie);
				setBounds(358, 95, 75, 3, thie);
				setChildren(4, slay);
				setBounds(17896, 20, 30, 0, slay);
				setBounds(4268, 80, 15, 1, slay);
				setBounds(4269, 80, 45, 2, slay);
				setBounds(358, 95, 75, 3, slay);
				setChildren(3, farm);
				setBounds(4268, 80, 15, 0, farm);
				setBounds(4269, 80, 45, 1, farm);
				setBounds(358, 95, 75, 2, farm);
				setChildren(4, rune);
				setBounds(17898, 20, 30, 0, rune);
				setBounds(4268, 80, 15, 1, rune);
				setBounds(4269, 80, 45, 2, rune);
				setBounds(358, 95, 75, 3, rune);
				setChildren(3, cons);
				setBounds(4268, 80, 15, 0, cons);
				setBounds(4269, 80, 45, 1, cons);
				setBounds(358, 95, 75, 2, cons);
				setChildren(3, hunt);
				setBounds(4268, 80, 15, 0, hunt);
				setBounds(4269, 80, 45, 1, hunt);
				setBounds(358, 95, 75, 2, hunt);
				setChildren(4, summ);
				setBounds(17901, 20, 30, 0, summ);
				setBounds(4268, 80, 15, 1, summ);
				setBounds(4269, 80, 45, 2, summ);
				setBounds(358, 95, 75, 3, summ);
				setChildren(4, dung);
				setBounds(17902, 20, 30, 0, dung);
				setBounds(4268, 80, 15, 1, dung);
				setBounds(4269, 80, 45, 2, dung);
				setBounds(358, 95, 75, 3, dung);
	}
	private static String[] scrollNames = {"Howl","Dreadfowl Strike","Egg Spawn","Slime Spray","Stony Shell","Pester","Electric Lash","Venom Shot","Fireball Assault","Cheese Feast","Sandstorm","Generate Compost","Explode","Vampire Touch","Insane Ferocity","Multichop","Call of Arms","Call of Arms","Call of Arms","Call of Arms","Bronze Bull Rush","Unburden","Herbcall","Evil Flames","Petrifying gaze","Petrifying gaze","Petrifying gaze","Petrifying gaze","Petrifying gaze","Petrifying gaze","Petrifying gaze","Iron Bull Rush","Immense Heat","Thieving Fingers","Blood Drain","Tireless Run","Abyssal Drain","Dissolve","Steel Bull Rush","Fish Rain","Goad","Ambush","Rending","Doomsphere Device","Dust Cloud","Abyssal Stealth","Ophidian Incubation","Poisonous Blast","Mithril Bull Rush","Toad Bark","Testudo","Swallow Whole","Fruitfall","Famine","Arctic Blast","Rise from the Ashes","Volcanic Strength","Crushing Claw","Mantis Strike","Adamant Bull Rush","Inferno","Deadly Claw","Acorn Missile","Titan's Consitution","Titan's Consitution","Titan's onsitution","Regrowth","Spike Shot","Ebon Thunder","Swamp Plague","Rune Bull Rush","Healing Aura","Boil","Magic Focus","Essence Shipment","Iron Within","Winter Storage","Steel of Legends",};
	private static String[] pouchNames = {"Spirit Wolf", "Dreadfowl", "Spirit Spider", "Thorny Snail", "Granite Crab", "Spirit Mosquito", "Desert Wyrm", "Spirit Scorpian", "Spirit Tz-Kih", "Albino rat", "Spirit Kalphite", "Compost mound", "Giant Chinchompa", "Vampire Bat", "Honey badger", "Beaver", "Void Ravager", "Void Spinner", "Void Torcher", "Void Shifter", "Bronze minotaur", "Bull ant", "Macaw", "Evil Turnip", "Spirit Cockatrice", "Spirit Guthatrice", "Spirit Saratrice", "Spirit Zamatrice", "Spirit Pengatrice", "Spirit Coraxatrice", "Spirit Vulatrice", "Iron minotaur", "Pyrelord", "Magpie", "Bloated Leech", "Spirit terrorbird", "Abyssal Parasite", "Spirit Jelly", "Steel Minotaur", "Ibis","Spirit kyatt", "Spirit laurpia", "Spirit graahk", "Karamthulhu overlord", "Smoke Devil", "Abyssal Lurker", "Spirit cobra", "Stranger Plant", "Mithril minotaur", "Barker Toad", "War tortoise","Bunyip", "Fruit bat", "Ravenous Locust", "Artic Bear", "Pheonix", "Obsidian Golem", "Granite Lobster", "Praying mantis", "Forge regent", "Adamant minotaur", "Talon Beast", "Giant ent","Fire titan","Moss titan","Ice titan","Hydra","Spirit daggannoth","Lava titan","Swamp titan","Rune minotaur", "Unicorn Stallion", "Geyser titan", "Wolpertinger", "Abyssal Titan", "Iron titan","Pack Yack","Steel titan"};
	
	public static void scrolls(TextDrawingArea TDA[]) {
		RSInterface rsinterface = addInterface(38700);
		addButton(38701, 0, "Interfaces/Scrolls/SPRITE", "Pouches", 27640, 1,
				116, 20);
		addButton(38702, 1, "Interfaces/Scrolls/SPRITE", "Scrolls", 27640, 1,
				116, 20);
		RSInterface scroll = addTabInterface(38710);
		scroll.width = 430;
		scroll.height = 230;
		scroll.scrollMax = 550;
		// scroll.newScroller = true;
		int lastId = 38710;
		int lastImage = 4;
		for (int i = 0; i < 78; i++) {
			addHover(lastId + 1, 1, 0, lastId + 2, lastImage + 1,
					"Interfaces/Scrolls/Sprite", 46, 50, "Select "
							+ scrollNames[i] + " scroll");
			addHovered(lastId + 2, lastImage + 2, "Interfaces/Scrolls/Sprite",
					46, 50, lastId + 3);
			lastId += 3;
			lastImage += 2;
		}
		rsinterface.children = new int[7];
		rsinterface.childX = new int[7];
		rsinterface.childY = new int[7];
		rsinterface.children[0] = 39701;
		rsinterface.childX[0] = 14;
		rsinterface.childY[0] = 17;
		rsinterface.children[1] = 39702;
		rsinterface.childX[1] = 475;
		rsinterface.childY[1] = 30;
		rsinterface.children[2] = 39703;
		rsinterface.childX[2] = 475;
		rsinterface.childY[2] = 30;
		rsinterface.children[3] = 38701;
		rsinterface.childX[3] = 25;
		rsinterface.childY[3] = 30;
		rsinterface.children[4] = 38702;
		rsinterface.childX[4] = 128;
		rsinterface.childY[4] = 30;
		rsinterface.children[5] = 39707;
		rsinterface.childX[5] = 268;
		rsinterface.childY[5] = 30;
		rsinterface.children[6] = 38710;
		rsinterface.childX[6] = 35;
		rsinterface.childY[6] = 65;
		scroll.children = new int[156];
		scroll.childX = new int[156];
		scroll.childY = new int[156];
		int lastNumber = -1;
		int lastId2 = 38710;
		int lastX = -52;
		int lastY = 0;
		for (int i = 0; i < 78; i++) {
			scroll.children[lastNumber += 1] = lastId2 += 1;
			scroll.childX[lastNumber] = lastX += 52;
			scroll.childY[lastNumber] = lastY;
			scroll.children[lastNumber += 1] = lastId2 += 1;
			scroll.childX[lastNumber] = lastX;
			scroll.childY[lastNumber] = lastY;
			lastId2 += 1;
			if (lastX == 364) {
				lastX = -52;
				lastY += 55;
			}
		}
	}
	public static void pouches(TextDrawingArea TDA[]) {
		RSInterface rsinterface = addInterface(39700);
		addSprite(39701, 0, "Interfaces/Pouches/SPRITE");
		addHover(39702, 3, 0, 39703, 1, "Interfaces/Pouches/SPRITE", 17, 17,
				"Close Window");
		addHovered(39703, 2, "Interfaces/Pouches/SPRITE", 17, 17, 39704);
		addButton(39705, 3, "Interfaces/Pouches/SPRITE", "Pouches", 27640, 1,
				116, 20);
		addButton(39706, 4, "Interfaces/Pouches/SPRITE", "Scrolls", 27640, 1,
				116, 20);
		addText(39707, "Summoning Pouch Creation", TDA, 2, 0xff981f, false,
				true);
		RSInterface scroll = addTabInterface(39710);
		scroll.width = 430;
		scroll.height = 230;
		scroll.scrollMax = 550;
		// scroll.newScroller = true;
		int lastId = 39710;
		int lastImage = 4;
		for (int i = 0; i < 78; i++) {
			addHover(lastId + 1, 1, 0, lastId + 2, lastImage + 1,
					"Interfaces/Pouches/Sprite", 46, 50, "Infuse "
							+ pouchNames[i] + " pouch");
			addHovered(lastId + 2, lastImage + 2, "Interfaces/Pouches/Sprite",
					46, 50, lastId + 3);
			lastId += 3;
			lastImage += 2;
		}
		
	
	rsinterface.children = new int[7];		rsinterface.childX = new int[7];	rsinterface.childY = new int[7];
	
	rsinterface.children[0] = 39701;		rsinterface.childX[0] = 14;			rsinterface.childY[0] = 17;
	rsinterface.children[1] = 39702;		rsinterface.childX[1] = 475;		rsinterface.childY[1] = 30;        
	rsinterface.children[2] = 39703;		rsinterface.childX[2] = 475;		rsinterface.childY[2] = 30;        
	rsinterface.children[3] = 39705;		rsinterface.childX[3] = 25;			rsinterface.childY[3] = 30;       
	rsinterface.children[4] = 39706;		rsinterface.childX[4] = 128;		rsinterface.childY[4] = 30;       
	rsinterface.children[5] = 39707;		rsinterface.childX[5] = 268;		rsinterface.childY[5] = 30;       
	rsinterface.children[6] = 39710;		rsinterface.childX[6] = 35;			rsinterface.childY[6] = 65;
	
	
	scroll.children = new int[156];		scroll.childX = new int[156];			scroll.childY = new int[156];
	
	int lastNumber = -1;
	int lastId2 = 39710;
	int lastX = -52;
	int lastY = 0;
	for (int i = 0; i < 78; i++) {
		scroll.children[lastNumber+=1] = lastId2+=1;			scroll.childX[lastNumber] = lastX+=52;		scroll.childY[lastNumber] = lastY;
		scroll.children[lastNumber+=1] = lastId2+=1;			scroll.childX[lastNumber] = lastX;		scroll.childY[lastNumber] = lastY;
		lastId2 += 1;
		if (lastX == 364) {
			lastX = -52;
			lastY += 55;
		}
	}
}
	private static void addHead2(int id, int w, int h, int zoom) {//tewst
       RSInterface rsinterface = addInterface(id);
        rsinterface.type = 6;
		rsinterface.anInt233 = 2;
		rsinterface.mediaID = 4000;//
        rsinterface.modelZoom = zoom;
        rsinterface.modelRotation1 = 40;//40;//wait
        rsinterface.modelRotation2 = 1900;//1900;
        rsinterface.height = h;
        rsinterface.width = w;
    }
  
	public static void SummonTab(TextDrawingArea[] tda) {
		RSInterface tab = addTabInterface(25605);
		addSprite(25606, 4, "Interfaces/Summon/SUMMON");
		addSprite(25607, 5, "Interfaces/Summon/SUMMON");
		addSprite(25608, 9, "Interfaces/Summon/SUMMON");
		addButton(25609, 1, "Interfaces/Summon/SUMMON", "Call");
		addButton(25619, 2, "Interfaces/Summon/SUMMON", "Renew");
		addButton(25610, 3, "Interfaces/Summon/SUMMON", "Dismiss");
		//addSprite(25611, 7, "Interfaces/Summon/SUMMON");
		addText(25612, "", tda, 0, 0xc4b074, false, true); // Leveling ID
		addText(25616, "0", tda, 1, 0xff9040, false, true);
		addText(25614, "", tda, 1, 0xcc9900, true, true);
		addText(25615, "99.99", tda, 0, 0xc4b074, false, true); // Timer ID
		addButton(25613, 8, "Interfaces/Summon/SUMMON", "Special Move");
		addText(25622, "", tda, 0, 0xffffff, false, true);
		addButton(25617, 7, "Interfaces/Summon/SUMMON", "Special Move");
		addSprite(25618, 11, "Interfaces/Summon/SUMMON");
		addHead2(23027, 50, 50, 800);
		tab.totalChildren(15);
		tab.child(0, 25606, 11, 163);
		tab.child(1, 25607, 12, 141);
		tab.child(2, 25608, 9, 29);
		tab.child(3, 25609, 23, 211);
		tab.child(4, 25610, 121, 211);
		tab.child(5, 25612, 29, 194);
		tab.child(6, 25613, 9, 29);
		tab.child(7, 25614, 97, 144);
		tab.child(8, 25615, 127, 194);
		tab.child(9, 25616, 20, 63);
		tab.child(10, 25617, 31, 10);
		tab.child(11, 25618, 70, 59);
		tab.child(12, 25619, 72, 211);
		tab.child(13, 23027, 75, 55);
		tab.child(14, 25622, 42, 10);
		//tab.child(5, 25611, 20, 10);
		/*tab.child(6, 25612, 29, 194);
		tab.child(7, 25613, 9, 29);
		tab.child(8, 25614, 97, 144);
		tab.child(9, 25615, 127, 194);
		tab.child(10, 25616, 20, 63);
		tab.child(11, 25617, 37, 10);
		tab.child(12, 25618, 70, 59);
		tab.child(13, 25619, 72, 211);
		tab.child(14, 23027, 75, 55);*/
	}
	
	
	
	public static void quickPrayers(TextDrawingArea[] TDA) {
		int i = 0;
    		RSInterface localRSInterface = addTabInterface(20000);
    		addSprite(17201, 3, "/Interfaces/QuickPrayer/Sprite");
			addText(17240, "Select your quick prayers:", TDA, 0, 16750623, false, true);
    		addHerpDerpSprite(17249, 0, "/Interfaces/QuickPrayer/Sprite", 50);
    		int j = 17202;
		for (int k = 630; (j <= 17231) || (k <= 659); ++k) {
     			addConfigButton(j, 17200, 2, 1, "/Interfaces/QuickPrayer/Sprite", 14, 15, "Select", 0, 1, k);
			j++;
    		}
    		addHoverButton(17241, "/Interfaces/QuickPrayer/Sprite", 4, 190, 24, "Confirm Selection", -1, 17242, 1);
    		addHoveredButton(17242, "/Interfaces/QuickPrayer/Sprite", 5, 190, 24, 17243);
    		setChildren(58, localRSInterface);
    		setBounds(25001, 5, 28, i++, localRSInterface);
    		setBounds(25003, 44, 28, i++, localRSInterface);
    		setBounds(25005, 79, 31, i++, localRSInterface);
    		setBounds(25007, 116, 30, i++, localRSInterface);
    		setBounds(25009, 153, 29, i++, localRSInterface);
    		setBounds(25011, 5, 68, i++, localRSInterface);
    		setBounds(25013, 44, 67, i++, localRSInterface);
    		setBounds(25015, 79, 69, i++, localRSInterface);
    		setBounds(25017, 116, 70, i++, localRSInterface);
    		setBounds(25019, 154, 70, i++, localRSInterface);
    		setBounds(25021, 4, 104, i++, localRSInterface);
    		setBounds(25023, 44, 107, i++, localRSInterface);
    		setBounds(25025, 81, 105, i++, localRSInterface);
    		setBounds(25027, 117, 105, i++, localRSInterface);
    		setBounds(25029, 156, 107, i++, localRSInterface);
    		setBounds(25031, 5, 145, i++, localRSInterface);
    		setBounds(25033, 43, 144, i++, localRSInterface);
    		setBounds(25035, 83, 144, i++, localRSInterface);
    		setBounds(25037, 115, 141, i++, localRSInterface);
    		setBounds(25039, 154, 144, i++, localRSInterface);
    		setBounds(25041, 5, 180, i++, localRSInterface);
    		setBounds(25043, 41, 178, i++, localRSInterface);
    		setBounds(25045, 79, 183, i++, localRSInterface);
    		setBounds(25047, 116, 178, i++, localRSInterface);
    		setBounds(25049, 161, 180, i++, localRSInterface);
    		setBounds(25051, 5, 217, i++, localRSInterface);
    		setBounds(17249, 0, 25, i++, localRSInterface);
    		setBounds(17201, 0, 22, i++, localRSInterface);
    		setBounds(17201, 0, 237, i++, localRSInterface);
    		setBounds(17202, 2, 25, i++, localRSInterface);
    		setBounds(17203, 41, 25, i++, localRSInterface);
    		setBounds(17204, 76, 25, i++, localRSInterface);
    		setBounds(17205, 113, 25, i++, localRSInterface);
    		setBounds(17206, 150, 25, i++, localRSInterface);
    		setBounds(17207, 2, 65, i++, localRSInterface);
    		setBounds(17208, 41, 65, i++, localRSInterface);
    		setBounds(17209, 76, 65, i++, localRSInterface);
    		setBounds(17210, 113, 65, i++, localRSInterface);
    		setBounds(17211, 150, 65, i++, localRSInterface);
    		setBounds(17212, 2, 102, i++, localRSInterface);
    		setBounds(17213, 41, 102, i++, localRSInterface);
    		setBounds(17214, 76, 102, i++, localRSInterface);
    		setBounds(17215, 113, 102, i++, localRSInterface);
    		setBounds(17216, 150, 102, i++, localRSInterface);
    		setBounds(17217, 2, 141, i++, localRSInterface);
    		setBounds(17218, 41, 141, i++, localRSInterface);
    		setBounds(17219, 76, 141, i++, localRSInterface);
    		setBounds(17220, 113, 141, i++, localRSInterface);
    		setBounds(17221, 150, 141, i++, localRSInterface);
    		setBounds(17222, 2, 177, i++, localRSInterface);
    		setBounds(17223, 41, 177, i++, localRSInterface);
    		setBounds(17224, 76, 177, i++, localRSInterface);
    		setBounds(17225, 113, 177, i++, localRSInterface);
    		setBounds(17226, 150, 177, i++, localRSInterface);
    		setBounds(17227, 1, 211, i++, localRSInterface);
    		setBounds(17240, 5, 5, i++, localRSInterface);
			setBounds(17241, 0, 237, i++, localRSInterface);
    		setBounds(17242, 0, 237, i++, localRSInterface);
    }

	public static void quickCurses(TextDrawingArea[] TDA) {
		int frame = 0;
		RSInterface tab = addTabInterface(22000);
		addSprite(17201, 3, "/Interfaces/QuickPrayer/Sprite");
		addText(17235, "Select your quick curses:", TDA, 0, 16750623, false, true);
		addHerpDerpSprite(17249, 0, "/Interfaces/QuickPrayer/Sprite", 50);
		int j = 17202;
		for (int k = 630; (j <= 17222) || (k <= 656); k++) {
			addConfigButton(j, 17200, 2, 1, "/Interfaces/QuickPrayer/Sprite", 14, 15, "Select", 0, 1, k);
			j++;
		}
		setChildren(46, tab);
		setBounds(22504, 5, 8+17, frame++, tab);
		setBounds(22506, 44, 8+20, frame++, tab);
		setBounds(22508, 79, 11+19, frame++, tab);
		setBounds(22510, 116, 10+18, frame++, tab);
		setBounds(22512, 153, 9+20, frame++, tab);
		setBounds(22514, 5, 48+18, frame++, tab);
		setBounds(22516, 44, 47+21, frame++, tab);
		setBounds(22518, 79, 49+20, frame++, tab);	
		setBounds(22520, 116, 50+19, frame++, tab);
		setBounds(22522, 154, 50+20, frame++, tab);		
		setBounds(22524, 4, 84+21, frame++, tab);		
		setBounds(22526, 44, 87+19, frame++, tab);		
		setBounds(22528, 81, 85+20, frame++, tab);	
		setBounds(22530, 117, 85+20, frame++, tab);					
		setBounds(22532, 156, 87+18, frame++, tab);			
		setBounds(22534, 5, 125+19, frame++, tab);			
		setBounds(22536, 43, 124+19, frame++, tab);		
		setBounds(22538, 83, 124+20, frame++, tab);										
		setBounds(22540, 115, 125+21, frame++, tab);
		setBounds(22542, 154, 126+22, frame++, tab);
    	setBounds(17249, 0, 25, frame++, tab);
		setBounds(17201, 0, 22, frame++, tab);
    	setBounds(17201, 0, 237, frame++, tab);
    	setBounds(17202, 2, 25, frame++, tab);
    	setBounds(17203, 41, 25, frame++, tab);
    	setBounds(17204, 76, 25, frame++, tab);
    	setBounds(17205, 113, 25, frame++, tab);
    	setBounds(17206, 150, 25, frame++, tab);
    	setBounds(17207, 2, 65, frame++, tab);
    	setBounds(17208, 41, 65, frame++, tab);
    	setBounds(17209, 76, 65, frame++, tab);
    	setBounds(17210, 113, 65, frame++, tab);
    	setBounds(17211, 150, 65, frame++, tab);
    	setBounds(17212, 2, 102, frame++, tab);
    	setBounds(17213, 41, 102, frame++, tab);
    	setBounds(17214, 76, 102, frame++, tab);
    	setBounds(17215, 113, 102, frame++, tab);
    	setBounds(17216, 150, 102, frame++, tab);
    	setBounds(17217, 2, 141, frame++, tab);
    	setBounds(17218, 41, 141, frame++, tab);
    	setBounds(17219, 76, 141, frame++, tab);
    	setBounds(17220, 113, 141, frame++, tab);
    	setBounds(17221, 150, 141, frame++, tab);
    	setBounds(17235, 5, 5, frame++, tab);
    	setBounds(17241, 0, 237, frame++, tab);
    	setBounds(17242, 0, 237, frame++, tab);
	}
	
	public boolean interfaceShown;
	/*private static String[] pouchNames = {"Spirit Wolf", "Dreadfowl", "Spirit Spider", "Thorny Snail", "Granite Crab", "Spirit Mosquito", "Desert Wyrm", "Spirit Scorpian", "Spirit Tz-Kih", "Albino rat", "Spirit Kalphite", "Compost mound", "Giant Chinchompa", "Vampire Bat", "Honey badger", "Beaver", "Void Ravager", "Void Spinner", "Void Torcher", "Void Shifter", "Bronze minotaur", "Bull ant", "Macaw", "Evil Turnip", "Spirit Cockatrice", "Spirit Guthatrice", "Spirit Saratrice", "Spirit Zamatrice", "Spirit Pengatrice", "Spirit Coraxatrice", "Spirit Vulatrice", "Iron minotaur", "Pyrelord", "Magpie", "Bloated Leech", "Spirit terrorbird", "Abyssal Parasite", "Spirit Jelly", "Steel Minotaur", "Ibis","Spirit kyatt", "Spirit laurpia", "Spirit graahk", "Karamthulhu overlord", "Smoke Devil", "Abyssal Lurker", "Spirit cobra", "Stranger Plant", "Mithril minotaur", "Barker Toad", "War tortoise","Bunyip", "Fruit bat", "Ravenous Locust", "Artic Bear", "Pheonix", "Obsidian Golem", "Granite Lobster", "Praying mantis", "Forge regent", "Adamant minotaur", "Talon Beast", "Giant ent","Fire titan","Moss titan","Ice titan","Hydra","Spirit daggannoth","Lava titan","Swamp titan","Rune minotaur", "Unicorn Stallion", "Geyser titan", "Wolpertinger", "Abyssal Titan", "Iron titan","Pack Yack","Steel titan"};
	public static void pouches(TextDrawingArea[] TDA) {
		RSInterface rsinterface = addInterface(39700);
		addSprite(39701, 0, "Interfaces/Pouches/SPRITE");
		addHover(39702, 3, 0, 39703, 1, "Interfaces/Pouches/SPRITE", 17, 17, "Close Window");
		addHovered(39703, 2, "Interfaces/Pouches/SPRITE", 17, 17, 39704);
		addButton(39705, 3, "Interfaces/Pouches/SPRITE", "Pouches", 27640, 1, 116, 20);
		addButton(39706, 4, "Interfaces/Pouches/SPRITE", "Scrolls", 27640, 1, 116, 20);
		addText(39707, "Summoning Pouch Creation", TDA, 2, 0xff981f, false, true);
			
		RSInterface scroll = addTabInterface(39710);
		scroll.width = 430; scroll.height = 230; scroll.scrollMax = 550;
		//scroll.newScroller = true;
		int lastId = 39710;
		int lastImage = 4;
		for (int i = 0; i < 78; i++) {
			addHover(lastId+1, 1, 0, lastId+2, lastImage+1, "Interfaces/Pouches/Sprite", 46, 50, "Infuse " + pouchNames[i] + " pouch");
			addHovered(lastId+2, lastImage+2, "Interfaces/Pouches/Sprite", 46, 50, lastId+3);
			lastId += 3;
			lastImage += 2;
		}
}*/
public static void addButton(int id, int sid, String spriteName, String tooltip, int mOver, int atAction, int width, int height) {
		RSInterface tab = interfaceCache[id] = new RSInterface();
		tab.id = id;
		tab.parentID = id;
		tab.type = 5;
		tab.atActionType = atAction;
		tab.contentType = 0;
		tab.aByte254 = 0;
		tab.mOverInterToTrigger = mOver;
		tab.sprite1 = imageLoader(sid, spriteName);
		tab.sprite2 = imageLoader(sid, spriteName);
		tab.width = width;
		tab.height = height;
		tab.tooltip = tooltip;
		tab.inventoryhover = true;
	}
	public boolean inventoryhover;
	public static void FightCave(TextDrawingArea[] tda) {
		RSInterface RSinterface = addInterface(21400);
		addSprite(21445, 0, "Interfaces/Other/JAD");
		addText(21450, "", tda, 1, 0xff9040, true, true);
		int last = 2;
		RSinterface.children = new int[last];
		RSinterface.childX = new int[last];
		RSinterface.childY = new int[last];
		setBounds(21445, 440, 5, 0,RSinterface);
		setBounds(21450, 470, 9, 1,RSinterface);
	}
	
	public static void Crafting(TextDrawingArea[] tda) {
	RSInterface tab = addScreenInterface(21500);
	addSprite(21501, 0, "Interfaces/Crafting/BACKGROUND");
	/*First Row*/
	addButton(21502, 1, "Interfaces/Crafting/SOFTL", 93, 83, "Tan Soft Leather", 1);
	addButton(21503, 2, "Interfaces/Crafting/HARDL", 86, 77, "Tan Hard Leather", 1);
	addButton(21504, 3, "Interfaces/Crafting/SNAKESKIN", 98, 85, "Tan Snakeskin", 1);
	addButton(21505, 4, "Interfaces/Crafting/SNAKESK", 97, 83, "Tan Snakeskin", 1);
	/*Second Row*/
	addButton(21506, 1, "Interfaces/Crafting/GREEN", 81, 76, "Tan Green d'hide", 1);
	addButton(21507, 2, "Interfaces/Crafting/BLUE", 81, 76, "Tan Blue d'hide", 1);
	addButton(21508, 3, "Interfaces/Crafting/RED", 83, 78, "Tan Red d'hide", 1);
	addButton(21509, 4, "Interfaces/Crafting/BLACK", 89, 80, "Tan Black d'hide", 1);
	tab.totalChildren(9);
	/*First Row*/
	tab.child(0, 21501, 10, 15);
	tab.child(1, 21502, 46, 56);
	tab.child(2, 21503, 150, 56);
	tab.child(3, 21504, 256, 56);
	tab.child(4, 21505, 362, 56);
	/*Second row*/
	tab.child(5, 21506, 50, 190);
	tab.child(6, 21507, 155, 190);
	tab.child(7, 21508, 264, 190);
	tab.child(8, 21509, 370, 190);
	}
	public static void StarterInfomation(TextDrawingArea[] tda) {
	  RSInterface tab = addScreenInterface(16100);
	addSprite(16101, 1, "Interfaces/StarterI/Background");
	addHoverButton(16102, "Interfaces/StarterI/Melee", 1, 110, 43, "Melee Infomation", -1, 16103, 5);
	addHoveredButton(16103, "Interfaces/StarterI/Melee", 2, 306, 218, 16104);
	addHoverButton(16105, "Interfaces/StarterI/Mage", 1, 110, 43, "Magic Infomation", -1, 16106, 5);
	addHoveredButton(16106, "Interfaces/StarterI/Mage", 2, 306, 218, 16107);
	addHoverButton(16108, "Interfaces/StarterI/Range", 1, 110, 43, "Range Infomation", -1, 16109, 5);
	addHoveredButton(16109, "Interfaces/StarterI/Range", 2, 306, 218, 16110);
	addHoverButton(16111, "Interfaces/StarterI/Back", 1, 16, 16, "Back to starters", -1, 16112, 5);
	addHoveredButton(16112, "Interfaces/StarterI/Back", 2, 16, 16, 16113);
	addText(15002, "Starter Infomation", tda, 1, 0xFF981F, true, true);
	addText(15003, "Hover over the buttons below so see what each starter contains", tda, 1, 0xFF981F, true, true);
	tab.totalChildren(11);
	tab.child(0, 16101, 30, 10);
	tab.child(1, 16102, 45, 92);
	tab.child(2, 16103, 45, 92);
	tab.child(3, 16105, 199, 92);
	tab.child(4, 16106, 157, 91);
	tab.child(5, 16108, 350, 92);
	tab.child(6, 16109, 154, 92);
	tab.child(7, 16111, 38, 17);
	tab.child(8, 16112, 38, 17);
	tab.child(9, 15003, 255, 55);
	tab.child(10, 15002, 260, 17);
	}
	
	 public static void bossinterface(TextDrawingArea[] tda)
    {
        RSInterface rsinterface = addTabInterface(45100);
        addSprite(45101, 1, "Interfaces/Boss/BACKGROUND");
        addHoverButton(45102, "Interfaces/Boss/EXIT", 1, 17, 17, "Close Window", -1, 45103, 1);
        addHoveredButton(45103, "Interfaces/Boss/EXIT", 2, 17, 17, 45104);
        addHoverButton(45105, "Interfaces/Boss/BUTTON", 1, 129, 21, "Nex", -1, 45106, 1);
        addHoveredButton(45106, "Interfaces/Boss/BUTTON", 2, 129, 21, 45107);
        addText(45108, "Nex", tda, 0, 0xff9b00, true, true);
        addHoverButton(45109, "Interfaces/Boss/BUTTON", 1, 129, 21, "Nomad", -1, 45110, 1);
        addHoveredButton(45110, "Interfaces/Boss/BUTTON", 2, 129, 21, 45111);
        addText(45112, "Nomad", tda, 0, 0xff9b00, true, true);
        addHoverButton(45113, "Interfaces/Boss/BUTTON", 1, 129, 21, "Ice Queen", -1, 45114, 1);
        addHoveredButton(45114, "Interfaces/Boss/BUTTON", 2, 129, 21, 45115);
        addText(45116, "Ice Queen", tda, 0, 0xff9b00, true, true);
        addHoverButton(45117, "Interfaces/Boss/BUTTON", 1, 129, 21, "Sea Troll Queen", -1, 45118, 1);
        addHoveredButton(45118, "Interfaces/Boss/BUTTON", 2, 129, 21, 45119);
        addText(45120, "Sea Troll Queen", tda, 0, 0xff9b00, true, true);
        addHoverButton(45121, "Interfaces/Boss/BUTTON", 1, 129, 21, "Bork", -1, 45122, 1);
        addHoveredButton(45122, "Interfaces/Boss/BUTTON", 2, 129, 21, 45123);
        addText(45124, "Bork", tda, 0, 0xff9b00, true, true);
        addHoverButton(45125, "Interfaces/Boss/BUTTON", 1, 129, 21, "Avatar of Destruction", -1, 45126, 1);
        addHoveredButton(45126, "Interfaces/Boss/BUTTON", 2, 129, 21, 45127);
        addText(45128, "Avatar of Destruction", tda, 0, 0xff9b00, true, true);
		
        addHoverButton(45129, "Interfaces/Boss/Back", 0, 129, 21, "More", -1, 45130, 1);
        addHoveredButton(45130, "Interfaces/Boss/Back", 1, 129, 21, 45131);
		
        rsinterface.totalChildren(23);
        rsinterface.child(0, 45101, 152, 10);
        rsinterface.child(1, 45102, 334, 22);
        rsinterface.child(2, 45103, 334, 22);
        rsinterface.child(3, 45105, 191, 68);
        rsinterface.child(4, 45106, 191, 68);
        rsinterface.child(5, 45108, 257, 75);
        rsinterface.child(6, 45109, 191, 108);
        rsinterface.child(7, 45110, 191, 108);
        rsinterface.child(8, 45112, 257, 115);
        rsinterface.child(9, 45113, 191, 148);
        rsinterface.child(10, 45114, 191, 148);
        rsinterface.child(11, 45116, 257, 155);
        rsinterface.child(12, 45117, 191, 188);
        rsinterface.child(13, 45118, 191, 188);
        rsinterface.child(14, 45120, 257, 195);
        rsinterface.child(15, 45121, 191, 226);
        rsinterface.child(16, 45122, 191, 226);
        rsinterface.child(17, 45124, 257, 230);
        rsinterface.child(18, 45125, 191, 266);
        rsinterface.child(19, 45126, 191, 266);
        rsinterface.child(20, 45128, 257, 270);
        rsinterface.child(21, 45129, 314, 47);
        rsinterface.child(22, 45130, 314, 47);
    }
	public static void addBobStorage(int index) {
    		 RSInterface rsi = interfaceCache[index] = new RSInterface();
   		 rsi.actions = new String[10];
    		 rsi.spritesX = new int[20];
    		 rsi.invStackSizes = new int[30];
    		 rsi.inv = new int[30];
    		 rsi.spritesY = new int[20];

   		 rsi.children = new int[0];
   	 	 rsi.childX = new int[0];
    		 rsi.childY = new int[0];

    		 rsi.actions[0] = "Take 1";
   		 rsi.actions[1] = "Take 5";
   		 rsi.actions[2] = "Take 10";
   		 rsi.actions[3] = "Take All";
		 rsi.actions[4] = "Take X";
    		 rsi.centerText = true;
    		 rsi.aBoolean227 = false;
    		 rsi.aBoolean235 = false;
   		 rsi.usableItemInterface = false;
    		 rsi.isInventoryInterface = false;
   		 rsi.aBoolean259 = true;
   		 rsi.textShadow = false;
   		 rsi.invSpritePadX = 22;
   		 rsi.invSpritePadY = 21; 
		 rsi.height = 5;
		 rsi.width = 6;
		 rsi.parentID = 2702;
   		 rsi.id = 2700;
    		 rsi.type = 2;
  }
  
	public static void bobInterface(TextDrawingArea[] tda) {
		RSInterface rsi = addTabInterface(2700);
		addSprite(2701, 0, "Custom/BoB/SPRITE");
		addBobStorage(2702);
		addHoverButton(2703, "Interfaces/Equipment/SPRITE", 1, 21, 21, "Close", 250, 2704, 3);
		addHoveredButton(2704, "Interfaces/Equipment/SPRITE", 3, 21, 21, 2705);
		rsi.totalChildren(4);
		rsi.child(0, 2701, 90, 14);
		rsi.child(1, 2702, 100, 56);
		rsi.child(2, 2703, 431, 23);
		rsi.child(3, 2704, 431, 23);
  }
	public static void godWars(TextDrawingArea[] tda) {
	RSInterface rsinterface = addTabInterface(16210);
	addText(16211, "NPC killcount", tda, 0, 0xff9040, true, true);
	addText(16212, "Armadyl kills", tda, 0, 0xff9040, true, true);
	addText(16213, "Bandos kills", tda, 0, 0xff9040, true, true);
	addText(16214, "Saradomin kills", tda, 0, 0xff9040, true, true);
	addText(16215, "Zamorak kills", tda, 0, 0xff9040, true, true);
	addText(16216, "0", tda, 0, 0x66FFFF, true, true);//armadyl
	addText(16217, "0", tda, 0, 0x66FFFF, true, true);//bandos
	addText(16218, "0", tda, 0, 0x66FFFF, true, true);//saradomin
	addText(16219, "0", tda, 0, 0x66FFFF, true, true);//zamorak
	rsinterface.scrollMax = 0;
	rsinterface.children = new int[9];
	rsinterface.childX = new int[9];
	rsinterface.childY = new int[9];
	rsinterface.children[0] = 16211;
		rsinterface.childX[0] = -52+375+30;		rsinterface.childY[0] = 7;
	rsinterface.children[1] = 16212;
		rsinterface.childX[1] = -52+375+30;		rsinterface.childY[1] = 30;
	rsinterface.children[2] = 16213;
		rsinterface.childX[2] = -52+375+30;		rsinterface.childY[2] = 44;
	rsinterface.children[3] = 16214;
		rsinterface.childX[3] = -52+375+30;		rsinterface.childY[3] = 58;
	rsinterface.children[4] = 16215;
		rsinterface.childX[4] = -52+375+30;		rsinterface.childY[4] = 73;
	rsinterface.children[5] = 16216;
		rsinterface.childX[5] = -52+460+60;		rsinterface.childY[5] = 31;
	rsinterface.children[6] = 16217;
		rsinterface.childX[6] = -52+460+60;		rsinterface.childY[6] = 45;
	rsinterface.children[7] = 16218;
		rsinterface.childX[7] = -52+460+60;		rsinterface.childY[7] = 59;
	rsinterface.children[8] = 16219;
		rsinterface.childX[8] = -52+460+60;		rsinterface.childY[8] = 74;
}
	public static void buyRXPShop1(TextDrawingArea[] tda) {
	RSInterface tab = addTabInterface(21850);
        RSInterface scroll = addTabInterface(21849);
	addSprite(21851, 45, "Interfaces/rxShop/SPRITE");
	addSprite(21852, 11, "Interfaces/rxShop/SPRITE");
	addSprite(21853, 12, "Interfaces/rxShop/SPRITE");
        addHoverButton1(21854, "Interfaces/rxShop/SPRITE", 9, 102, 19, "Home", 21855, 1);
        addHoveredButton(21855, "Interfaces/rxShop/SPRITE", 10, 102, 19, 21856);
        addHoverButton1(21859, "Interfaces/rxShop/SPRITE", 32, 218, 61, "Auras", 21860, 1);
        addHoveredButton(21860, "Interfaces/rxShop/SPRITE", 33, 218, 61, 21861);
        addHoverButton1(21865, "Interfaces/rxShop/SPRITE", 34, 218, 61, "Title", 21866, 1);
        addHoveredButton(21866, "Interfaces/rxShop/SPRITE", 35, 218, 61, 21867);
        addHoverButton1(21868, "Interfaces/rxShop/SPRITE", 36, 218, 61, "Re-Colour", 21869, 1);
        addHoveredButton(21869, "Interfaces/rxShop/SPRITE", 37, 218, 61, 21870);
	addSprite(21857, 14, "Interfaces/rxShop/SPRITE");
	addSprite(21858, 1, "Interfaces/rxShop/SPRITE");
//buttons
        addHoverButton1(21862, "Interfaces/rxShop/SPRITE", 21, 120, 28, "Auras", 21863, 1);
        addHoveredButton(21863, "Interfaces/rxShop/SPRITE", 22, 120, 28, 21864);
        addHoverButton1(21871, "Interfaces/rxShop/SPRITE", 23, 120, 28, "Pets", 21872, 1);
        addHoveredButton(21872, "Interfaces/rxShop/SPRITE", 24, 120, 28, 21873);
        addHoverButton1(21874, "Interfaces/rxShop/SPRITE", 51, 120, 28, "Items", 21875, 1);
        addHoveredButton(21875, "Interfaces/rxShop/SPRITE", 52, 120, 28, 21876);
        addHoverButton1(21877, "Interfaces/rxShop/SPRITE", 25, 120, 28, "Titles", 21878, 1);
        addHoveredButton(21878, "Interfaces/rxShop/SPRITE", 26, 120, 28, 21880);
        addHoverButton1(21881, "Interfaces/rxShop/SPRITE", 49, 120, 28, "Features", 21882, 1);
        addHoveredButton(21882, "Interfaces/rxShop/SPRITE", 50, 120, 28, 21883);
        addHoverButton1(21897, "Interfaces/rxShop/SPRITE", 38, 120, 28, "Special Offers", 21898, 1);
        addHoveredButton(21898, "Interfaces/rxShop/SPRITE", 39, 120, 28, 21899);
        addHoverButton1(21907, "Interfaces/rxShop/SPRITE", 40, 78, 16, "Favourites", 21908, 1);
        addHoveredButton(21908, "Interfaces/rxShop/SPRITE", 41, 78, 16, 21909);
        addHoverButton1(21911, "Interfaces/rxShop/SPRITE", 42, 15, 13, "Close", 21912, 1);
        addHoveredButton(21912, "Interfaces/rxShop/SPRITE", 43, 15, 13, 21913);
//text
	addText(21884, "RXP Shop", tda, 0, 0xe6be78, false, true);
	addText(21885, "My RXP:", tda, 0, 0xe6be78, false, true);
	addText(21886, "1,000", tda, 0, 0xe6be78, false, true);
	addText(21887, "Now Viewing: Titles", tda, 0, 0xe6be78, false, true);
	addText(21888, "Auras", tda, 0, 0xe6be78, false, true);
	addText(21889, "Pets", tda, 0, 0xe6be78, false, true);
	addText(21890, "Items", tda, 0, 0xe6be78, false, true);
	addText(21891, "Titles", tda, 0, 0xe6be78, false, true);
	addText(21892, "Features", tda, 0, 0xe6be78, false, true);
	addText(21893, "", tda, 0, 0xe6be78, false, true);
	addText(21894, "Vampyrism", tda, 0, 0xe6be78, false, true);
	addText(21895, "23,000 Points", tda, 0, 0xe6be78, false, true);
	addText(21896, "See More", tda, 0, 0xe6be78, false, true);
	addText(21900, "Special Offers", tda, 0, 0xe6be78, false, true);
	addText(21901, "Disappear", tda, 0, 0xe6be78, false, true);
	addText(21902, "12,000 Points", tda, 0, 0xe6be78, false, true);
	addText(21903, "See More", tda, 0, 0xe6be78, false, true);
	addText(21904, "King", tda, 0, 0xe6be78, false, true);
	addText(21905, "25,000 Points", tda, 0, 0xe6be78, false, true);
	addText(21906, "See More", tda, 0, 0xe6be78, false, true);
	addText(21910, "Edit Titles", tda, 0, 0xe6be78, false, true);
//
//title buttons & backs
	addSprite(21914, 44, "Interfaces/rxShop/SPRITE");
	addText(21915, "Sir", tda, 0, 0xe6be78, false, true);
	addText(21916, "1,000 Points", tda, 0, 0xdb9000, false, true);
	addText(21917, "Buy", tda, 0, 0xe6be78, false, true);
        addHoverButton1(21923, "Interfaces/rxShop/SPRITE", 8, 56, 11, "Buy", 21924, 1);
        addHoveredButton(21924, "Interfaces/rxShop/SPRITE", 7, 56, 11, 21925);
	addSprite(21922, 44, "Interfaces/rxShop/SPRITE");
        addHoverButton1(21926, "Interfaces/rxShop/SPRITE", 8, 56, 11, "Buy", 21927, 1);
        addHoveredButton(21927, "Interfaces/rxShop/SPRITE", 7, 56, 11, 21928);
        addHoverButton1(21929, "Interfaces/rxShop/SPRITE", 8, 56, 11, "Buy", 21930, 1);
        addHoveredButton(21930, "Interfaces/rxShop/SPRITE", 7, 56, 11, 21931);
        addHoverButton1(21932, "Interfaces/rxShop/SPRITE", 8, 56, 11, "Buy", 21933, 1);
        addHoveredButton(21933, "Interfaces/rxShop/SPRITE", 7, 56, 11, 21934);
        addHoverButton1(21935, "Interfaces/rxShop/SPRITE", 8, 56, 11, "Buy", 21936, 1);
        addHoveredButton(21936, "Interfaces/rxShop/SPRITE", 7, 56, 11, 21937);
        addHoverButton1(21938, "Interfaces/rxShop/SPRITE", 8, 56, 11, "Buy", 21939, 1);
        addHoveredButton(21939, "Interfaces/rxShop/SPRITE", 7, 56, 11, 21940);
	//3rd row below
        addHoverButton1(21941, "Interfaces/rxShop/SPRITE", 8, 56, 11, "Buy", 21942, 1);
        addHoveredButton(21942, "Interfaces/rxShop/SPRITE", 7, 56, 11, 21943);
        addHoverButton1(21944, "Interfaces/rxShop/SPRITE", 8, 56, 11, "Buy", 21945, 1);
        addHoveredButton(21945, "Interfaces/rxShop/SPRITE", 7, 56, 11, 21946);
        addHoverButton1(21947, "Interfaces/rxShop/SPRITE", 8, 56, 11, "Buy", 21948, 1);
        addHoveredButton(21948, "Interfaces/rxShop/SPRITE", 7, 56, 11, 21949);
	//4th row
        addHoverButton1(21950, "Interfaces/rxShop/SPRITE", 8, 56, 11, "Buy", 21951, 1);
        addHoveredButton(21951, "Interfaces/rxShop/SPRITE", 7, 56, 11, 21952);
        addHoverButton1(21953, "Interfaces/rxShop/SPRITE", 8, 56, 11, "Buy", 21954, 1);
        addHoveredButton(21954, "Interfaces/rxShop/SPRITE", 7, 56, 11, 21955);
        addHoverButton1(21956, "Interfaces/rxShop/SPRITE", 8, 56, 11, "Buy", 21957, 1);
        addHoveredButton(21957, "Interfaces/rxShop/SPRITE", 7, 56, 11, 21958);
	//5th
        addHoverButton1(21959, "Interfaces/rxShop/SPRITE", 8, 56, 11, "Buy", 21960, 1);
        addHoveredButton(21960, "Interfaces/rxShop/SPRITE", 7, 56, 11, 21961);
        addHoverButton1(21962, "Interfaces/rxShop/SPRITE", 8, 56, 11, "Buy", 21963, 1);
        addHoveredButton(21963, "Interfaces/rxShop/SPRITE", 7, 56, 11, 21964);
        addHoverButton1(21965, "Interfaces/rxShop/SPRITE", 8, 56, 11, "Buy", 21966, 1);
        addHoveredButton(21966, "Interfaces/rxShop/SPRITE", 7, 56, 11, 21967);
	//6th
        addHoverButton1(21968, "Interfaces/rxShop/SPRITE", 8, 56, 11, "Buy", 21969, 1);
        addHoveredButton(21969, "Interfaces/rxShop/SPRITE", 7, 56, 11, 21970);
        addHoverButton1(21971, "Interfaces/rxShop/SPRITE", 8, 56, 11, "Buy", 21972, 1);
        addHoveredButton(21972, "Interfaces/rxShop/SPRITE", 7, 56, 11, 21973);
	//start titles & price
	addText(21919, "Lord", tda, 0, 0xe6be78, false, true);
	addText(21920, "1,000 Points", tda, 0, 0xdb9000, false, true);
	addText(21921, "Buy", tda, 0, 0xe6be78, false, true);
	addText(21974, "Duderino", tda, 0, 0xe6be78, false, true);
	addText(21975, "4,000 Points", tda, 0, 0xdb9000, false, true);
	addText(21976, "Lionheart", tda, 0, 0xe6be78, false, true);
	addText(21977, "4,000 Points", tda, 0, 0xdb9000, false, true);
	addText(21978, "Crusader", tda, 0, 0xe6be78, false, true);
	addText(21979, "8,000 Points", tda, 0, 0xdb9000, false, true);
	addText(21980, "HellRaiser", tda, 0, 0xe6be78, false, true);
	addText(21981, "8,000 Points", tda, 0, 0xdb9000, false, true);
	addText(21982, "Desperado", tda, 0, 0xe6be78, false, true);
	addText(21843, "10,000 Points", tda, 0, 0xdb9000, false, true);
	addText(21842, "Baron", tda, 0, 0xe6be78, false, true);
	addText(21985, "10,000 Points", tda, 0, 0xdb9000, false, true);
	addText(21986, "Count", tda, 0, 0xe6be78, false, true);
	addText(21987, "15,000 Points", tda, 0, 0xdb9000, false, true);
	addText(21988, "Overlord", tda, 0, 0xe6be78, false, true);
	addText(21989, "15,000 Points", tda, 0, 0xdb9000, false, true);
	addText(21990, "Bandito", tda, 0, 0xe6be78, false, true);
	addText(21991, "20,000 Points", tda, 0, 0xdb9000, false, true);
	addText(21992, "Duke", tda, 0, 0xe6be78, false, true);
	addText(21993, "20,000 Points", tda, 0, 0xdb9000, false, true);
	addText(21994, "Big Cheese", tda, 0, 0xe6be78, false, true);
	addText(21995, "25,000 Points", tda, 0, 0xdb9000, false, true);
	addText(21996, "Bigwig", tda, 0, 0xe6be78, false, true);
	addText(21848, "25,000 Points", tda, 0, 0xdb9000, false, true);
	addText(21847, "King", tda, 0, 0xe6be78, false, true);
	addText(21846, "25,000 Points", tda, 0, 0xdb9000, false, true);
	addText(21845, "Wunderkin", tda, 0, 0xe6be78, false, true);
	addText(21844, "50,000 Points", tda, 0, 0xdb9000, false, true);
	addText(21841, "Bigwig", tda, 0, 0xe6be78, false, true);
	addText(21840, "25,000 Points", tda, 0, 0xdb9000, false, true);
	addText(21839, "Master", tda, 0, 0xe6be78, false, true);
	addText(21838, "75,000 Points", tda, 0, 0xdb9000, false, true);
	//
//
	tab.totalChildren(27);
	tab.child(0, 21851, 1, 1);
	//tab.child(1, 21854, 15, 50);
	//tab.child(2, 21855, 15, 50);
	/*tab.child(3, 21859, 360, 90);
	tab.child(4, 21860, 360, 90);
	tab.child(5, 21865, 360, 155);
	tab.child(6, 21866, 360, 155);
	tab.child(7, 21868, 360, 220);
	tab.child(8, 21869, 360, 220);*/
	tab.child(1, 21862, 9, 96);
	tab.child(2, 21863, 9, 96);
	tab.child(3, 21871, 9, 126);
	tab.child(4, 21872, 9, 126);
	tab.child(5, 21874, 9, 156);
	tab.child(6, 21875, 9, 156);
	tab.child(7, 21877, 9, 186);
	tab.child(8, 21878, 9, 186);
	tab.child(9, 21881, 9, 216);
	tab.child(10, 21882, 9, 216);
	tab.child(11, 21884, 45, 55);
	tab.child(12, 21885, 16, 79);
	tab.child(13, 21886, 75, 79);
	tab.child(14, 21887, 132, 53);
	tab.child(15, 21888, 84, 104);
	tab.child(16, 21889, 80, 134);
	tab.child(17, 21890, 90, 164);
	tab.child(18, 21891, 87, 194);
	tab.child(19, 21892, 68, 224);
	tab.child(20, 21893, 145, 104);
	tab.child(21, 21911, 491, 6);
	tab.child(22, 21912, 491, 6);
	tab.child(23, 21897, 9, 246);
	tab.child(24, 21898, 9, 246);
	tab.child(25, 21900, 39, 254);
	tab.child(26, 21849, 130, 70);

	scroll.totalChildren(102);
	scroll.child(0, 21914, 5, 5);
	scroll.child(1, 21915, 65, 13);
	scroll.child(2, 21916, 45, 24);
	scroll.child(3, 21923, 54, 38);
	scroll.child(4, 21924, 54, 38);
	scroll.child(5, 21922, 122, 5);	
	scroll.child(6, 21917, 72, 38);
	scroll.child(7, 21922, 240, 5);
	scroll.child(8, 21922, 5, 55);
	scroll.child(9, 21922, 122, 55);
	scroll.child(10, 21922, 240, 55);
	scroll.child(11, 21922, 5, 105);
	scroll.child(12, 21922, 122, 105);
	scroll.child(13, 21922, 240, 105);
	scroll.child(14, 21922, 5, 155);
	scroll.child(15, 21922, 122, 155);
	scroll.child(16, 21922, 240, 155);
	scroll.child(17, 21922, 5, 205);
	scroll.child(18, 21922, 122, 205);
	scroll.child(19, 21922, 240, 205);
	scroll.child(20, 21922, 5, 255);
	scroll.child(21, 21922, 122, 255);
	scroll.child(22, 21926, 172, 38);
	scroll.child(23, 21927, 172, 38);
	scroll.child(24, 21929, 290, 38);
	scroll.child(25, 21930, 290, 38);
	//2ndrow
	scroll.child(26, 21932, 54, 88);
	scroll.child(27, 21933, 54, 88);
	scroll.child(28, 21935, 172, 88);
	scroll.child(29, 21936, 172, 88);
	scroll.child(30, 21938, 290, 88);
	scroll.child(31, 21939, 290, 88);
	//3rdrow
	scroll.child(32, 21941, 54, 138);
	scroll.child(33, 21942, 54, 138);
	scroll.child(34, 21944, 172, 138);
	scroll.child(35, 21945, 172, 138);
	scroll.child(36, 21947, 290, 138);
	scroll.child(37, 21948, 290, 138);
	//4throw
	scroll.child(38, 21950, 54, 188);
	scroll.child(39, 21951, 54, 188);
	scroll.child(40, 21953, 172, 188);
	scroll.child(41, 21954, 172, 188);
	scroll.child(42, 21956, 290, 188);
	scroll.child(43, 21957, 290, 188);
	//5th
	scroll.child(44, 21959, 54, 238);
	scroll.child(45, 21960, 54, 238);
	scroll.child(46, 21962, 172, 238);
	scroll.child(47, 21963, 172, 238);
	scroll.child(48, 21965, 290, 238);
	scroll.child(49, 21966, 290, 238);
	//6th
	scroll.child(50, 21968, 54, 288);
	scroll.child(51, 21969, 54, 288);
	scroll.child(52, 21971, 172, 288);
	scroll.child(53, 21972, 172, 288);
//buy 1st column
	scroll.child(54, 21917, 72, 88);
	scroll.child(55, 21917, 72, 138);
	scroll.child(56, 21917, 72, 188);
	scroll.child(57, 21917, 72, 238);
	scroll.child(58, 21917, 72, 288);
//2nd column
	scroll.child(59, 21917, 190, 38);
	scroll.child(60, 21917, 190, 88);
	scroll.child(61, 21917, 190, 138);
	scroll.child(62, 21917, 190, 188);
	scroll.child(63, 21917, 190, 238);
	scroll.child(64, 21917, 190, 288);
//3rd column buy
	scroll.child(65, 21917, 308, 38);
	scroll.child(66, 21917, 308, 88);
	scroll.child(67, 21917, 308, 138);
	scroll.child(68, 21917, 308, 188);
	scroll.child(69, 21917, 308, 238);
	//start titles
		//1st row
	scroll.child(70, 21919, 183, 13);
	scroll.child(71, 21920, 163, 24);
	scroll.child(72, 21974, 293, 13);
	scroll.child(73, 21975, 281, 24);
		//2nd row
	scroll.child(74, 21976, 57, 63);
	scroll.child(75, 21977, 45, 74);
	scroll.child(76, 21978, 174, 63);
	scroll.child(77, 21979, 163, 74);
	scroll.child(78, 21980, 289, 63);
	scroll.child(79, 21981, 281, 74);
		//3rd row
	scroll.child(80, 21982, 52, 113);
	scroll.child(81, 21843, 43, 124);
	scroll.child(82, 21842, 183, 113);
	scroll.child(83, 21985, 161, 124);
	scroll.child(84, 21986, 301, 113);
	scroll.child(85, 21987, 279, 124);
		//4th row
	scroll.child(86, 21988, 57, 163);
	scroll.child(87, 21989, 45, 174);
	scroll.child(88, 21990, 178, 163);
	scroll.child(89, 21991, 160, 174);
	scroll.child(90, 21992, 301, 163);
	scroll.child(91, 21993, 278, 174);
		//5th row
	scroll.child(92, 21994, 52, 213);
	scroll.child(93, 21848, 42, 224);
	scroll.child(94, 21841, 179, 213);
	scroll.child(95, 21840, 160, 224);
	scroll.child(96, 21847, 301, 213);
	scroll.child(97, 21846, 278, 224);
		//6th row
	scroll.child(98, 21845, 52, 263);
	scroll.child(99, 21844, 42, 274);
	scroll.child(100, 21839, 180, 263);
	scroll.child(101, 21838, 160, 274);
	scroll.width = 357;
	scroll.height = 238;
	scroll.scrollMax = 302;
//UPDATE TOTAL CHILDS!
	}
	public static void addHoverButton1(int i, String imageName, int j, int width, int height, String text, int hoverOver, int aT) {
		RSInterface tab = addTabInterface(i);
		tab.id = i;
		tab.parentID = i;
		tab.interfaceType = 5;
		tab.atActionType = aT;
		tab.opacity = 0;
		tab.hoverType = hoverOver;
		tab.disabledSprite = imageLoader(j, imageName);
		tab.enabledSprite = imageLoader(j, imageName);
		tab.width = width;
		tab.height = height;
		tab.tooltip = text;
	}
	public static void addHoverButton3(int i, String imageName, int j, int width, int height, int hoverOver) {
RSInterface tab = addTabInterface(i);
tab.id = i;
tab.parentID = i;
tab.interfaceType = 5;
tab.opacity = 0;
tab.hoverType = hoverOver;
tab.disabledSprite = imageLoader(j, imageName);
tab.enabledSprite = imageLoader(j, imageName);
tab.width = width;
tab.height = height;
} 
	public static void createClan(TextDrawingArea[] tda) {
        RSInterface tab = addTabInterface(10409);
        RSInterface scroll = addTabInterface(10309);
        addText(10558, "Create your clan!", tda, 0, 0xff9933, false, true);
//textstart
	addText(10017, "Create A Clan", 0xffffcc, false, true, -1, tda, 3);
        addText(10012, "Clan Name:", tda, 0, 0xffffcc, false, true);
        addText(10013, "Clan Leader:", tda, 0, 0xffffcc, false, true);
        addText(10014, "Clan Username:", tda, 0, 0xffffcc, false, true);
        addText(10016, "Clan Password:", tda, 0, 0xffffcc, false, true);
        addText(10018, "Information", tda, 0, 0xffffcc, false, true);
        addText(10019, "You may create a clan", tda, 0, 0xffffcc, false, true);
        addText(10020, "of 15 to fight other", tda, 0, 0xffffcc, false, true);
        addText(10021, "clans anywhere in RX", tda, 0, 0xffffcc, false, true);
        addText(10022, "and gain points for", tda, 0, 0xffffcc, false, true);
        addText(10023, "killing other clan", tda, 0, 0xffffcc, false, true);
        addText(10024, "members, the more kills", tda, 0, 0xffffcc, false, true);
        addText(10025, "points you have", tda, 0, 0xffffcc, false, true);
        addText(10026, "the more members", tda, 0, 0xffffcc, false, true);
        addText(10027, "you can have on your", tda, 0, 0xffffcc, false, true);
        addText(10028, "clan. Your clan will be", tda, 0, 0xffffcc, false, true);
        addText(10029, "on highscores once", tda, 0, 0xffffcc, false, true);
        addText(10030, "they get 40 kills total", tda, 0, 0xffffcc, false, true);
        addText(10031, "between all members.", tda, 0, 0xffffcc, false, true);
        addText(10015, "Logo: ", tda, 0, 0xffffcc, false, true);
        addText(10032, "Clan Description: ", tda, 0, 0xffffcc, false, true);
        addText(10033, "Create Clan", tda, 0, 0xffffcc, false, true);
        addText(10034, "Reset Info", tda, 0, 0xffffcc, false, true);
        addText(10035, "View Clans", tda, 0, 0xffffcc, false, true);
        addText(10036, "None", tda, 0, 0xffffcc, false, true);
        addText(10037, "None", tda, 0, 0xffffcc, false, true);
        addText(10038, "None", tda, 0, 0xffffcc, false, true);
        addText(10039, "None", tda, 0, 0xffffcc, false, true);
        addText(10040, "None", tda, 0, 0xffffcc, false, true);
        addText(10041, "None", tda, 0, 0xffffcc, false, true);
//
        /*addSprite(19514, 21, "Interfaces/clanCreation/SPRITE");
        addSprite(19511, 16, "Interfaces/clanCreation/SPRITE");
        addSprite(19512, 24, "Interfaces/clanCreation/SPRITE");
        addSprite(19513, 27, "Interfaces/clanCreation/SPRITE");*/
        addSprite(10569, 46, "Interfaces/clanCreation/SPRITE");
        addSprite(10510, 53, "Interfaces/clanCreation/SPRITE");
        addSprite(10410, 52, "Interfaces/clanCreation/SPRITE");
        addSprite(19234, 9, "Interfaces/clanCreation/SPRITE");
        addSprite(19237, 9, "Interfaces/clanCreation/SPRITE");
        addSprite(19240, 9, "Interfaces/clanCreation/SPRITE");
        addSprite(19243, 9, "Interfaces/clanCreation/SPRITE");
        addSprite(10411, 48, "Interfaces/clanCreation/SPRITE");
        addSprite(10412, 65, "Interfaces/clanCreation/SPRITE");
        addHoverButton1(10528, "Interfaces/clanCreation/SPRITE", 2, 200, 27, "Clan Name", 10529, 1);
        addHoveredButton(10529, "Interfaces/clanCreation/SPRITE", 3, 200, 27, 10530);
//AddInterfaceButton(int i, int j, int hoverId, String name, int W, int H, String S, int AT)
	AddInterfaceButton(10591, 32, 10592, "Interfaces/clanCreation/SPRITE", 15, 13, "Close", 1);
        //addHoverButton1(10591, "Interfaces/clanCreation/SPRITE", 32, 15, 13, "Close", 10592, 1);
        addHoveredButton(10592, "Interfaces/clanCreation/SPRITE", 33, 15, 13, 10593);
        //addHoverButton1(10594, "Interfaces/clanCreation/SPRITE", 2, 200, 27, "Leader", 10595, 1);
        //addHoveredButton(10595, "Interfaces/clanCreation/SPRITE", 3, 200, 27, 10596);
        addHoverButton1(19228, "Interfaces/clanCreation/SPRITE", 2, 200, 27, "Clan Leader", 19229, 1);
        addHoveredButton(19229, "Interfaces/clanCreation/SPRITE", 3, 200, 27, 19230);
        addHoverButton1(19526, "Interfaces/clanCreation/SPRITE", 2, 200, 27, "Clan Password", 19527, 1);
        addHoveredButton(19527, "Interfaces/clanCreation/SPRITE", 3, 200, 27, 19528);
        addHoverButton1(19231, "Interfaces/clanCreation/SPRITE", 2, 200, 27, "Clan Username", 19232, 1);
        addHoveredButton(19232, "Interfaces/clanCreation/SPRITE", 3, 200, 27, 19233);
        //addHoverButton1(19234, "Interfaces/clanCreation/SPRITE", 9, 60, 60, "", 19235, 1);
        //addHoveredButton(19235, "Interfaces/clanCreation/SPRITE", 8, 60, 60, 19236);
        //addHoverButton1(19237, "Interfaces/clanCreation/SPRITE", 9, 60, 60, "", 19238, 1);
        //addHoveredButton(19238, "Interfaces/clanCreation/SPRITE", 8, 60, 60, 19239);
        //addHoverButton1(19240, "Interfaces/clanCreation/SPRITE", 9, 60, 60, "", 19241, 1);
        //addHoveredButton(19241, "Interfaces/clanCreation/SPRITE", 8, 60, 60, 19242);
        //addHoverButton1(19243, "Interfaces/clanCreation/SPRITE", 9, 60, 60, "", 19244, 1);
        //addHoveredButton(19244, "Interfaces/clanCreation/SPRITE", 8, 60, 60, 19245);
        addHoverButton1(19246, "Interfaces/clanCreation/SPRITE", 30, 22, 20, "Previous", 19247, 1);
        addHoveredButton(19247, "Interfaces/clanCreation/SPRITE", 31, 22, 20, 19248);
        addHoverButton1(19249, "Interfaces/clanCreation/SPRITE", 28, 22, 20, "Next", 19250, 1);
        addHoveredButton(19250, "Interfaces/clanCreation/SPRITE", 29, 22, 20, 19251);
//
        addHoverButton1(19515, "Interfaces/clanCreation/SPRITE", 16, 50, 50, "Choose Logo", 19511, 1);
        addHoveredButton(19511, "Interfaces/clanCreation/SPRITE", 39, 50, 50, 19516);

        addHoverButton1(19512, "Interfaces/clanCreation/SPRITE", 24, 50, 50, "Choose Logo", 19517, 1);
        addHoveredButton(19517, "Interfaces/clanCreation/SPRITE", 38, 50, 50, 19518);

        addHoverButton1(19513, "Interfaces/clanCreation/SPRITE", 27, 50, 50, "Choose Logo", 19519, 1);
        addHoveredButton(19519, "Interfaces/clanCreation/SPRITE", 37, 50, 50, 19520);

        addHoverButton1(19514, "Interfaces/clanCreation/SPRITE", 21, 50, 50, "Choose Logo", 19521, 1);
        addHoveredButton(19521, "Interfaces/clanCreation/SPRITE", 36, 50, 50, 19522);

//new
        addHoverButton1(19543, "Interfaces/clanCreation/SPRITE", 12, 50, 50, "Choose Logo", 19544, 1);
        addHoveredButton(19544, "Interfaces/clanCreation/SPRITE", 34, 50, 50, 19545);

        addHoverButton1(19540, "Interfaces/clanCreation/SPRITE", 13, 50, 50, "Choose Logo", 19541, 1);
        addHoveredButton(19541, "Interfaces/clanCreation/SPRITE", 35, 50, 50, 19542);
//new2
        addHoverButton1(19553, "Interfaces/clanCreation/SPRITE", 14, 50, 50, "Choose Logo", 19554, 1);
        addHoveredButton(19554, "Interfaces/clanCreation/SPRITE", 58, 50, 50, 19555);

        addHoverButton1(19550, "Interfaces/clanCreation/SPRITE", 18, 50, 50, "Choose Logo", 19551, 1);
        addHoveredButton(19551, "Interfaces/clanCreation/SPRITE", 59, 50, 50, 19552);
//new3
        addHoverButton1(19556, "Interfaces/clanCreation/SPRITE", 22, 50, 50, "Choose Logo", 19568, 1);
        addHoveredButton(19568, "Interfaces/clanCreation/SPRITE", 60, 50, 50, 19569);

        addHoverButton1(19559, "Interfaces/clanCreation/SPRITE", 23, 50, 50, "Choose Logo", 19560, 1);
        addHoveredButton(19560, "Interfaces/clanCreation/SPRITE", 61, 50, 50, 19561);
//new4
        addHoverButton1(19562, "Interfaces/clanCreation/SPRITE", 62, 50, 50, "Choose Logo", 19563, 1);
        addHoveredButton(19563, "Interfaces/clanCreation/SPRITE", 64, 50, 50, 19564);

        addHoverButton1(19565, "Interfaces/clanCreation/SPRITE", 19, 50, 50, "Choose Logo", 19566, 1);
        addHoveredButton(19566, "Interfaces/clanCreation/SPRITE", 63, 50, 50, 19567);
//

        addHoverButton1(19114, "Interfaces/clanCreation/SPRITE", 54, 307, 48, "Clan Description", 19121, 1);
        addHoveredButton(19121, "Interfaces/clanCreation/SPRITE", 55, 307, 48, 19122);

        addHoverButton1(19814, "Interfaces/clanCreation/SPRITE", 5, 129, 61, "Create Clan", 19821, 1);
        addHoveredButton(19821, "Interfaces/clanCreation/SPRITE", 6, 129, 61, 19822);

        addHoverButton1(19023, "Interfaces/clanCreation/SPRITE", 5, 129, 61, "Reset Info", 19024, 1);
        addHoveredButton(19024, "Interfaces/clanCreation/SPRITE", 6, 129, 61, 19025);

        addHoverButton1(19026, "Interfaces/clanCreation/SPRITE", 5, 129, 61, "View another Clan", 19027, 1);
        addHoveredButton(19027, "Interfaces/clanCreation/SPRITE", 6, 129, 61, 19028);

        addHoverButton3(19523, "Interfaces/clanCreation/SPRITE", 44, 122, 143, 19524);
        addHoveredButton(19524, "Interfaces/clanCreation/SPRITE", 45, 122, 143, 19525);

        tab.totalChildren(55);
	tab.child(0, 10569, 3, 10);//background
	tab.child(1, 10410, 12, 50);//background2
	tab.child(2, 10412, 55, 264);//background3bottom
	tab.child(3, 10510, 353, 50);//selectionbackground
	tab.child(4, 10411, 10, 45);//selectionbackground
        tab.child(5, 10528, 143, 54);//clannameback
        tab.child(6, 10529, 143, 54);//clannamehover
        tab.child(7, 10012, 158, 61);//nametext
        tab.child(8, 10591, 485, 15);//close
        tab.child(9, 10592, 485, 15);//close
        tab.child(10, 19228, 143, 89);//leaderback
        tab.child(11, 19229, 143, 89);//leaderbackhover
        tab.child(12, 19231, 143, 124);//leaderback
        tab.child(13, 19232, 143, 124);//leaderbackhover
	//tab.child(15, 19234, 333, 173);//chooselogo1
        tab.child(14, 10013, 154, 96);//leadertext
        tab.child(15, 10014, 147, 132);//nametext
        tab.child(16, 10015, 373, 56);//logotext
        tab.child(17, 19523, 16, 54);//block
        tab.child(18, 19524, 16, 54);//block
        tab.child(19, 19526, 143, 159);//password
        tab.child(20, 19527, 143, 159);//password
        tab.child(21, 10016, 150, 168);//passtext
	tab.child(22, 10309, 353, 72);
	tab.child(23, 10017, 205, 15);
	tab.child(24, 10018, 50, 58);
	tab.child(25, 10019, 22, 72);
	tab.child(26, 10020, 22, 82);
	tab.child(27, 10021, 22, 92);
	tab.child(28, 10022, 22, 102);
	tab.child(29, 10023, 22, 112);
	tab.child(30, 10024, 22, 122);
	tab.child(31, 10026, 22, 132);
	tab.child(32, 10027, 22, 142);
	tab.child(33, 10028, 22, 152);
	tab.child(34, 10029, 22, 162);
	tab.child(35, 10030, 22, 172);
	tab.child(36, 10031, 22, 182);
	tab.child(37, 19114, 28, 207);
	tab.child(38, 19121, 28, 207);
	tab.child(39, 10032, 44, 216);//desctext
	tab.child(40, 19814, 61, 275);//button3bottom1
	tab.child(41, 19821, 61, 275);//button3bottom1
	tab.child(42, 19023, 189, 275);//button3bottom2
	tab.child(43, 19024, 189, 275);//button3bottom2
	tab.child(44, 19026, 318, 275);//button3bottom3
	tab.child(45, 19027, 318, 275);//button3bottom3
	tab.child(46, 10033, 96, 280);
	tab.child(47, 10034, 222, 280);
	tab.child(48, 10035, 354, 280);
	tab.child(49, 10040, 165, 216);//desctextinput
        tab.child(50, 10036, 240, 61);//nametextinput
        tab.child(51, 10039, 240, 168);//passtextinput
        tab.child(52, 10037, 240, 96);//leadertext
        tab.child(53, 10038, 240, 132);//nametext
        tab.child(54, 10041, 388, 56);//logotext
//
	scroll.totalChildren(36);
	scroll.child(0, 19234, 5, 0);//chooselogo1
	scroll.child(1, 19237, 67, 0);//chooselogo2
	scroll.child(2, 19240, 67, 64);//chooselogo3
	scroll.child(3, 19243, 5, 64);//chooselogo4
	scroll.child(4, 19243, 5, 128);//chooselogo5
	scroll.child(5, 19243, 67, 128);//chooselogo6
	scroll.child(6, 19234, 5, 192);//chooselogo7
	scroll.child(7, 19237, 67, 192);//chooselogo8
	scroll.child(8, 19240, 5, 256);//chooselogo9
	scroll.child(9, 19243, 67, 256);//chooselogo10
	scroll.child(10, 19243, 5, 320);//chooselogo11
	scroll.child(11, 19243, 67, 320);//chooselogo12
	scroll.child(12, 19515, 10, 5);//logo1
	scroll.child(13, 19511, 10, 5);//logo1
	scroll.child(14, 19512, 73, 5);//logo2
	scroll.child(15, 19517, 73, 5);//logo2
	scroll.child(16, 19513, 73, 68);//logo4
	scroll.child(17, 19519, 73, 68);//logo4
	scroll.child(18, 19514, 10, 68);//logo3
	scroll.child(19, 19521, 10, 68);//logo3
	scroll.child(20, 19543, 73, 132);//logo5
	scroll.child(21, 19544, 73, 132);//logo5
	scroll.child(22, 19540, 10, 132);//logo6
	scroll.child(23, 19541, 10, 132);//logo6
	scroll.child(24, 19550, 73, 196);//logo7
	scroll.child(25, 19551, 73, 196);//logo7
	scroll.child(26, 19553, 10, 196);//logo8
	scroll.child(27, 19554, 10, 196);//logo8
	scroll.child(28, 19556, 73, 260);//logo9
	scroll.child(29, 19568, 73, 260);//logo9
	scroll.child(30, 19559, 10, 260);//logo10
	scroll.child(31, 19560, 10, 260);//logo10
	scroll.child(32, 19562, 73, 324);//logo11
	scroll.child(33, 19563, 73, 324);//logo11
	scroll.child(34, 19565, 10, 324);//logo12
	scroll.child(35, 19566, 10, 324);//logo12
	scroll.width = 129;
	scroll.height = 188;
	scroll.scrollMax = 400;
        //end clancreate;
    }

public static void Bank()
    {
        RSInterface rsinterface = addTabInterface(5292);
        setChildren(21, rsinterface);
        addSprite(5293, 0, "Interfaces/Bank/BANK");
        setBounds(5293, 13, 13, 0, rsinterface);
        addHover(5384, 3, 0, 5380, 1, "Interfaces/Bank/BANK", 17, 17, "Close Window");
        addHovered(5380, 2, "Interfaces/Bank/BANK", 17, 17, 5379);
        addHoverButton(22020, "Interfaces/Equipment/BANK", 0, 72, 32, "Equipment Stats", -1, 22021, 5);
        addHoveredButton(22021, "Interfaces/Equipment/BANK", 1, 72, 32, 22033);
        setBounds(5384, 476, 16, 3, rsinterface);
        setBounds(5380, 476, 16, 4, rsinterface);
        setBounds(22020, 457, 35, 19, rsinterface);
        setBounds(22021, 457, 35, 20, rsinterface);
        addHover(5294, 4, 0, 5295, 3, "Interfaces/Bank/BANK", 114, 25, "Set A Bank PIN");
        addHovered(5295, 4, "Interfaces/Bank/BANK", 114, 25, 5296);
        setBounds(5294, 110, 285, 5, rsinterface);
        setBounds(5295, 110, 285, 6, rsinterface);
        addHoverButton(21000, "Interfaces/Bank/BANK", 7, 35, 25, "Swap Withdraw Mode", -1, 21001, 5);
        addHoveredButton(21001, "Interfaces/Bank/BANK", 8, 35, 25, 22030);
        setBounds(21000, 25, 285, 7, rsinterface);
        setBounds(21001, 25, 285, 8, rsinterface);
        addHoverButton(21004, "Interfaces/Bank/BANK", 14, 35, 25, "Search Bank", -1, 21005, 5);
        addHoveredButton(21005, "Interfaces/Bank/BANK", 15, 35, 25, 22029);
        setBounds(21004, 65, 285, 9, rsinterface);
        setBounds(21005, 65, 285, 10, rsinterface);
		addHoverButton(21008, "Interfaces/Bank/BANK", 9, 35, 25, "Swap Items Noted", -1, 21009, 5);
        addHoveredButton(21009, "Interfaces/Bank/BANK", 11, 35, 25, 22031);
        setBounds(21008, 240, 285, 11, rsinterface);
        setBounds(21009, 240, 285, 12, rsinterface);
        addHoverButton(21012, "Interfaces/Bank/BANK", 17, 35, 25, "Deposit Carried Items", -1, 21013, 5);
        addHoveredButton(21013, "Interfaces/Bank/BANK", 18, 35, 25, 22032);
        setBounds(21012, 375, 285, 13, rsinterface);
        setBounds(21013, 375, 285, 14, rsinterface);
        addHoverButton(21016, "Interfaces/Bank/BANK", 19, 35, 25, "Deposit Worn Items", -1, 21017, 5);
        addHoveredButton(21017, "Interfaces/Bank/BANK", 20, 35, 25, 22023);
        setBounds(21016, 415, 285, 15, rsinterface);
        setBounds(21017, 415, 285, 16, rsinterface);
        addHoverButton(21020, "Interfaces/Bank/BANK", 21, 35, 25, "Deposit Beast of Burden", -1, 21021, 5);
        addHoveredButton(21021, "Bank/BANK", 22, 35, 25, 22022);
        setBounds(21020, 455, 285, 17, rsinterface);
        setBounds(21021, 455, 285, 18, rsinterface);
        setBounds(5383, 170, 15, 1, rsinterface);
        setBounds(5385, -4, 74, 2, rsinterface);
        rsinterface = interfaceCache[5385];
        rsinterface.height = 206;
        rsinterface.width = 480;
        rsinterface = interfaceCache[5382];
        rsinterface.width = 10;
        rsinterface.invSpritePadX = 12;
        rsinterface.height = 35;
    } 
public static void addHover(int i, int j, int k, int l, int i1, String s, int j1, int k1, 
            String s1)
    {
        RSInterface rsinterface = addTabInterface(i);
        rsinterface.id = i;
        rsinterface.parentID = i;
        rsinterface.type = 5;
        rsinterface.atActionType = j;
        rsinterface.contentType = k;
        rsinterface.mOverInterToTrigger = l;
        rsinterface.sprite1 = imageLoader(i1, s);
        rsinterface.sprite2 = imageLoader(i1, s);
        rsinterface.width = j1;
        rsinterface.height = k1;
        rsinterface.tooltip = s1;
    }
public static void addHovered(int i, int j, String s, int k, int l, int i1)
    {
        RSInterface rsinterface = addTabInterface(i);
        rsinterface.parentID = i;
        rsinterface.id = i;
        rsinterface.type = 0;
        rsinterface.atActionType = 0;
        rsinterface.width = k;
        rsinterface.height = l;
        rsinterface.isMouseoverTriggered = true;
        rsinterface.mOverInterToTrigger = -1;
        addSprite(i1, j, s);
        setChildren(1, rsinterface);
        setBounds(i1, 0, 0, 0, rsinterface);
    }

public static void SettingsTab(TextDrawingArea[] tda) {
		RSInterface tab = addTabInterface(6299);
        	addSprite(37001, 1, "Interfaces/Switcher/Background");
		addButton(37046, 1, "Switcher/Top", 63, 24, "Settings Menu", 1);
		addText(37050, "Gameframe", tda, 0, 0xFFFFFF, false, true);
		addText(37051, "x10 Hitting", tda, 0, 0xFFFFFF, false, true);
		addText(37052, "New Hitpoints Bar", tda, 0, 0xFFFFFF, false, true);
		addText(37053, "New Tooltips", tda, 0, 0xFFFFFF, false, true);
		addText(37054, "New Context Menu", tda, 0, 0xFFFFFF, false, true);
		addText(37055, "Names Above Heads", tda, 0, 0xFFFFFF, false, true);
		addText(37056, "HP Above Heads", tda, 0, 0xFFFFFF, false, true);
		addText(37057, "Fog", tda, 0, 0xFFFFFF, false, true);
		addText(37058, "", tda, 0, 0xFFFFFF, false, true);
		addText(37059, "", tda, 0, 0x67E300, false, true);
		addText(37060, "", tda, 0, 0x67E300, false, true);
		addText(37061, "", tda, 0, 0xF30021, false, true);
		addText(37062, "", tda, 0, 0x67E300, false, true);
		addText(37063, "", tda, 0, 0xF30021, false, true);
		addText(37064, "", tda, 0, 0xF30021, false, true);
		addText(37065, "", tda, 0, 0x67E300, false, true);
		addButton(37002, 1, "Interfaces/Switcher/SmallerTextBox", 63, 24, "Change gameframe", 1);
		addButton(37003, 1, "Interfaces/Switcher/TextBox", 63, 24, "Toggle On/Off", 1);
		addButton(37004, 1, "Interfaces/Switcher/TextBox", 63, 24, "Toggle On/Off", 1);
		addButton(37005, 1, "Interfaces/Switcher/TextBox", 63, 24, "Toggle On/Off", 1);
		addButton(37006, 1, "Interfaces/Switcher/TextBox", 63, 24, "Toggle On/Off", 1);
		addButton(37007, 1, "Interfaces/Switcher/TextBox", 63, 24, "Toggle On/Off", 1);
		addButton(37008, 1, "Interfaces/Switcher/TextBox", 63, 24, "Toggle On/Off", 1);
		addButton(37009, 1, "Interfaces/Switcher/TextBox", 63, 24, "Toggle On/Off", 1);
		addHoverButton(37020, "Interfaces/Switcher/SmallButton", 0, 200, 30, "Change Gameframe", -1, 37021, 1);
		addHoveredButton(37021, "Interfaces/Switcher/SmallButton", 1, 200, 30, 37022);
		addHoverButton(37023, "Interfaces/Switcher/SmallButton", 0, 200, 30, "Toggle On/Off", -1, 37024, 1);
		addHoveredButton(37024, "Interfaces/Switcher/SmallButton", 1, 200, 30, 37025);
		addHoverButton(37026, "Interfaces/Switcher/SmallButton", 0, 200, 30, "Toggle On/Off", -1, 37027, 1);
		addHoveredButton(37027, "Interfaces/Switcher/SmallButton", 1, 200, 30, 37028);
		addHoverButton(37029, "Interfaces/Switcher/SmallButton", 0, 200, 30, "Toggle On/Off", -1, 37030, 1);
		addHoveredButton(37030, "Interfaces/Switcher/SmallButton", 1, 200, 30, 37031);
		addHoverButton(37033, "Interfaces/Switcher/SmallButton", 0, 200, 30, "Toggle On/Off", -1, 37034, 1);
		addHoveredButton(37034, "Interfaces/Switcher/SmallButton", 1, 200, 30, 37035);
		addHoverButton(37036, "Interfaces/Switcher/SmallButton", 0, 200, 30, "Toggle On/Off", -1, 37037, 1);
		addHoveredButton(37037, "Interfaces/Switcher/SmallButton", 1, 200, 30, 37038);
		addHoverButton(37039, "Interfaces/Switcher/SmallButton", 0, 200, 30, "Toggle On/Off", -1, 37040, 1);
		addHoveredButton(37040, "Interfaces/Switcher/SmallButton", 1, 200, 30, 37042);
		addHoverButton(37043, "Interfaces/Switcher/SmallButton", 0, 200, 30, "Toggle On/Off", -1, 37044, 1);
		addHoveredButton(37044, "Interfaces/Switcher/SmallButton", 1, 200, 30, 37045);
       	        addHoverButton(37047, "Interfaces/Minigame/Back", 0, 16, 16, "Back", -1, 37048, 1);
       	        addHoveredButton(37048, "Interfaces/Minigame/Back", 1, 16, 16, 37049);
		tab.totalChildren(44);
		/*BUTTONS*/
       		tab.child(0, 37001, 0, 0);
       		tab.child(1, 37002, 5, 20);
       		tab.child(2, 37003, 5, 50);
       		tab.child(3, 37004, 5, 80);
       		tab.child(4, 37005, 5, 110);
       		tab.child(5, 37006, 5, 140);
       		tab.child(6, 37007, 5, 170);
       		tab.child(7, 37008, 5, 200);
       		tab.child(8, 37009, 5, 230);
		/*HOVER/HOVERED BUTTONS*/
		tab.child(9, 37020, 140, 20);
		tab.child(10, 37021, 140, 20);
		tab.child(11, 37023, 150, 50);
		tab.child(12, 37024, 150, 50);
		tab.child(13, 37026, 150, 80);
		tab.child(14, 37027, 150, 80);
		tab.child(15, 37029, 150, 110);
		tab.child(16, 37030, 150, 110);
		 tab.child(17, 37033, 150, 140);
		tab.child(18, 37034, 150, 140);
		tab.child(19, 37036, 150, 170);
		tab.child(20, 37037, 150, 170);
		tab.child(21, 37039, 150, 200);
		tab.child(22, 37040, 150, 200);
		tab.child(23, 37043, 150, 230);
		tab.child(24, 37044, 150, 230);
		/*TOP SPRITE*/
		tab.child(25, 37046, 35, 0);
		tab.child(26, 37047, 0, 0);
		tab.child(27, 37048, 0, 0);
		/*TEXT*/
		tab.child(43, 37050, 8, 23);
		tab.child(28, 37051, 8, 53);
		tab.child(29, 37052, 8, 83);
		tab.child(30, 37053, 8, 113);
		tab.child(31, 37054, 8, 143);
		tab.child(32, 37055, 8, 173);
		tab.child(33, 37056, 8, 203);
		tab.child(34, 37057, 8, 233);
		//dddd
		tab.child(35, 37058, 146, 23);
		tab.child(36, 37059, 159, 53);
		tab.child(37, 37060, 159, 83);
		tab.child(38, 37061, 159, 113);
		tab.child(39, 37062, 159, 143);
		tab.child(40, 37063, 159, 173);
		tab.child(41, 37064, 159, 203);
		tab.child(42, 37065, 159, 233);


	}
	
 /* Skill Interface Start*/
	public static void addButton(int i, int j, int hoverId, String name, int W, int H, String S, int AT) {
		RSInterface RSInterface = addInterface(i);
		RSInterface.id = i;
		RSInterface.parentID = i;
		RSInterface.type = 5;
		RSInterface.atActionType = AT;
		RSInterface.aByte254 = 0;
		RSInterface.mOverInterToTrigger = hoverId;
		RSInterface.sprite1 = imageLoader(j,name);
		RSInterface.sprite2 = imageLoader(j,name);
		RSInterface.width = W;
		RSInterface.height = H;
		RSInterface.tooltip = S;
	}
	
	public int hoverType;	
	public int interfaceType;
	public byte opacity; 

	public static int boxIds[] = {
		4041, 4077, 4113, 4047, 4083, 4119, 4053, 4089, 4125, 4059, 4095,
		4131, 4065, 4101, 4137, 4071, 4107, 4143, 4154, 12168, 13918
	};

	public static void skillInterface(int i, int j){
		RSInterface rsinterface = interfaceCache[i] = new RSInterface();
		rsinterface.id = i;
		rsinterface.parentID = i;
		rsinterface.type = 5;
		rsinterface.atActionType = 0;
		rsinterface.contentType = 0;
		rsinterface.width = 26;
		rsinterface.height = 34;
		rsinterface.aByte254 = 0;
		rsinterface.mOverInterToTrigger = 0;
		rsinterface.sprite1 = imageLoader(j, "Interfaces/Skill/Skill");
		rsinterface.sprite2 = imageLoader(j, "Interfaces/Skill/Skill");
	}
public static void addText(int id, String text, TextDrawingArea wid[], int idx, int color) {
		RSInterface rsinterface = addTab(id);
		rsinterface.id = id;
		rsinterface.parentID = id;
		rsinterface.type = 4;
		rsinterface.atActionType = 0;
		rsinterface.width = 174;
		rsinterface.height = 11;
		rsinterface.contentType = 0;
		rsinterface.aByte254 = 0;
		rsinterface.mOverInterToTrigger = -1;
		rsinterface.centerText = false;
		rsinterface.textShadow = true;
		rsinterface.textDrawingAreas = wid[idx];
		rsinterface.message = text;
		rsinterface.aString228 = "";
		rsinterface.textColor = color;
		rsinterface.anInt219 = 0;
		rsinterface.anInt216 = 0;
		rsinterface.anInt239 = 0;	
	}

public static void skillInterface(TextDrawingArea[] wid) {
	RSInterface Interface = addTab(3917);
	int index = 0;
		skillInterface(19746, 255);
		skillInterface(19749, 52);
		addText(29801, "", wid, 0, 0xFFEE33); //Summoning
		addText(29800, "", wid, 0, 0xFFEE33); //Hunter
		
		addButton(19747, 51, 27700, "Interfaces/Skill/Skill", 62, 32, "View @lre@Hunter @whi@Guide", 1);
		addButton(19748, 50, 27701, "Interfaces/Skill/Skill", 62, 32, "View @lre@Summoning @whi@Guide", 1);
		
		addText(13984, "Total", wid, 0, 0xFFEE33);
		addText(3985, "", wid, 0, 0xFFEE33);
		addText(13983, "", wid, 0, 0xFFEE33, true, true);
		for(int k = 0; k < boxIds.length; k++) {
			skillInterface(boxIds[k], 256);
		}
		RSInterface rsinterface = addTab(3917);
		rsinterface.children = new int[63];		rsinterface.childX = new int[63];	rsinterface.childY = new int[63];
		rsinterface.children[0] = 3918;			rsinterface.childX[0] = 0;			rsinterface.childY[0] = 0;
		rsinterface.children[1] = 3925;			rsinterface.childX[1] = 0;			rsinterface.childY[1] = 31;
		rsinterface.children[2] = 3932;			rsinterface.childX[2] = 0;			rsinterface.childY[2] = 62;
		rsinterface.children[3] = 3939;			rsinterface.childX[3] = 0;			rsinterface.childY[3] = 93;
		rsinterface.children[4] = 3946;			rsinterface.childX[4] = 0;			rsinterface.childY[4] = 124;
		rsinterface.children[5] = 3953;			rsinterface.childX[5] = 0;			rsinterface.childY[5] = 155;
		rsinterface.children[6] = 4148;			rsinterface.childX[6] = 0;			rsinterface.childY[6] = 186;
		rsinterface.children[7] = 19746;		rsinterface.childX[7] = 70;			rsinterface.childY[7] = 69;
		rsinterface.children[8] = 19748;		rsinterface.childX[8] = 1;			rsinterface.childY[8] = 219;
		rsinterface.children[9] = 19747;		rsinterface.childX[9] = 64;			rsinterface.childY[9] = 219;
		rsinterface.children[10] = 14000;		rsinterface.childX[10] = 10;		rsinterface.childY[10] = 219;
		rsinterface.children[11] = 19749;		rsinterface.childX[11] = 128;		rsinterface.childY[11] = 220;
		rsinterface.children[12] = 13983; 		rsinterface.childX[12] = 158;  		rsinterface.childY[12] = 238;
		rsinterface.children[13] = 3984;		rsinterface.childX[13] = 300;		rsinterface.childY[13] = 225;
		rsinterface.children[14] = 3985;		rsinterface.childX[14] = 130;		rsinterface.childY[14] = 238;
		rsinterface.children[15] = 29800;		rsinterface.childX[15] = 98;  		rsinterface.childY[15] = 220;
		rsinterface.children[16] = 29800;		rsinterface.childX[16] = 107;  		rsinterface.childY[16] = 235;
		rsinterface.children[17] = 29801;		rsinterface.childX[17] = 36;		rsinterface.childY[17] = 220;
		rsinterface.children[18] = 29801;		rsinterface.childX[18] = 45;		rsinterface.childY[18] = 235;
		rsinterface.children[19] = 4040;		rsinterface.childX[19] = 5;			rsinterface.childY[19] = 20;
		rsinterface.children[20] = 8654;		rsinterface.childX[20] = 0;			rsinterface.childY[20] = 2;
		rsinterface.children[21] = 8655;		rsinterface.childX[21] = 64;		rsinterface.childY[21] = 2;
		rsinterface.children[22] = 4076;		rsinterface.childX[22] = 20;		rsinterface.childY[22] = 20;
		rsinterface.children[23] = 8656;		rsinterface.childX[23] = 128;		rsinterface.childY[23] = 2;
		rsinterface.children[24] = 4112;		rsinterface.childX[24] = 20;		rsinterface.childY[24] = 20;
		rsinterface.children[25] = 8657;		rsinterface.childX[25] = 0;			rsinterface.childY[25] = 33;
		rsinterface.children[26] = 4046;		rsinterface.childX[26] = 20;		rsinterface.childY[26] = 50;
		rsinterface.children[27] = 8658;		rsinterface.childX[27] = 64;		rsinterface.childY[27] = 33;
		rsinterface.children[28] = 4082;		rsinterface.childX[28] = 20;		rsinterface.childY[28] = 50;
		rsinterface.children[29] = 8659;		rsinterface.childX[29] = 128;		rsinterface.childY[29] = 33;
		rsinterface.children[30] = 4118;		rsinterface.childX[30] = 20;		rsinterface.childY[30] = 50;
		rsinterface.children[31] = 8660;		rsinterface.childX[31] = 0;			rsinterface.childY[31] = 60+10;
		rsinterface.children[32] = 4052;		rsinterface.childX[32] = 20;		rsinterface.childY[32] = 83;
		rsinterface.children[33] = 8661;		rsinterface.childX[33] = 65;		rsinterface.childY[33] = 60+10;
		rsinterface.children[34] = 4088;		rsinterface.childX[34] = 20;		rsinterface.childY[34] = 83;
		rsinterface.children[35] = 8662;		rsinterface.childX[35] = 130;		rsinterface.childY[35] = 60+10;
		rsinterface.children[36] = 4124;		rsinterface.childX[36] = 20;		rsinterface.childY[36] = 83;
		rsinterface.children[37] = 8663;		rsinterface.childX[37] = 0;			rsinterface.childY[37] = 90+10;
		rsinterface.children[38] = 4058;		rsinterface.childX[38] = 20;		rsinterface.childY[38] = 120;
		rsinterface.children[39] = 8664;		rsinterface.childX[39] = 65;		rsinterface.childY[39] = 90+10;
		rsinterface.children[40] = 4094;		rsinterface.childX[40] = 20;		rsinterface.childY[40] = 120;
		rsinterface.children[41] = 8665;		rsinterface.childX[41] = 130;		rsinterface.childY[41] = 90+10;
		rsinterface.children[42] = 4130;		rsinterface.childX[42] = 20;		rsinterface.childY[42] = 120;
		rsinterface.children[43] = 8666;		rsinterface.childX[43] = 0;			rsinterface.childY[43] = 130;
		rsinterface.children[44] = 4064;		rsinterface.childX[44] = 20;		rsinterface.childY[44] = 150;
		rsinterface.children[45] = 8667;		rsinterface.childX[45] = 65;		rsinterface.childY[45] = 130;
		rsinterface.children[46] = 4100;		rsinterface.childX[46] = 20;		rsinterface.childY[46] = 150;
		rsinterface.children[47] = 8668;		rsinterface.childX[47] = 130;		rsinterface.childY[47] = 130;
		rsinterface.children[48] = 4136;		rsinterface.childX[48] = 20;		rsinterface.childY[48] = 150;
		rsinterface.children[49] = 8669;		rsinterface.childX[49] = 0;			rsinterface.childY[49] = 160;
		rsinterface.children[50] = 4070;		rsinterface.childX[50] = 20;		rsinterface.childY[50] = 180;
		rsinterface.children[51] = 8670;		rsinterface.childX[51] = 65;		rsinterface.childY[51] = 160;
		rsinterface.children[52] = 4106;		rsinterface.childX[52] = 20;		rsinterface.childY[52] = 180;
		rsinterface.children[53] = 8671;		rsinterface.childX[53] = 130;		rsinterface.childY[53] = 160;
		rsinterface.children[54] = 4142;		rsinterface.childX[54] = 20;		rsinterface.childY[54] = 180;
		rsinterface.children[55] = 8672;		rsinterface.childX[55] = 0;			rsinterface.childY[55] = 190;
		rsinterface.children[56] = 4160;		rsinterface.childX[56] = 20;		rsinterface.childY[56] = 150;
		rsinterface.children[57] = 4160;		rsinterface.childX[57] = 20;		rsinterface.childY[57] = 150;
		rsinterface.children[58] = 12162;		rsinterface.childX[58] = 65;		rsinterface.childY[58] = 190;
		rsinterface.children[59] = 2832;		rsinterface.childX[59] = 20;		rsinterface.childY[59] = 150;
		rsinterface.children[60] = 13928;		rsinterface.childX[60] = 130;		rsinterface.childY[60] = 190;
		rsinterface.children[61] = 13917;		rsinterface.childX[61] = 20;		rsinterface.childY[61] = 150;
		rsinterface.children[62] = 13984;		rsinterface.childX[62] = 145;		rsinterface.childY[62] = 225;
	}
	
	public static RSInterface addTab(int i) {
		RSInterface rsinterface = interfaceCache[i] = new RSInterface();
		rsinterface.id = i;
		rsinterface.parentID = i;
		rsinterface.interfaceType = 0;
		rsinterface.atActionType = 0;
		rsinterface.contentType = 0;
		rsinterface.width = 512;
		rsinterface.height = 334;
		rsinterface.opacity = 0;
		rsinterface.hoverType = 0;
		return rsinterface;
	}

	/*Skill interface end*/
	public static void notesTab(TextDrawingArea[] tda) {
		RSInterface tab = addTabInterface(17350);
		addSprite(17351, 0, "Interfaces/Notes/NOTE");
		addHoverButton(17352, "Interfaces/Notes/NOTE", 1, 17, 17, "Add note", -1, 17353, 1);
		addHoveredButton(17353, "Interfaces/Notes/NOTE", 2, 17, 17, 17354);
		addHoverButton(17355, "Interfaces/Notes/NOTE", 3, 17, 17, "Delete all", -1, 17356, 1);
		addHoveredButton(17356, "Interfaces/Notes/NOTE", 4, 17, 17, 17357);
     	addText(13800, "No notes", tda, 0, 0xffffff, false, true);
     	addText(17820, "Notes", tda, 1, 0xFF981F, true, false);
		addText(17822, "Delete", tda, 1, 0xFF981F, true, false);
		addText(17823, "", tda, 0, 0xff981f, false, true);
		addText(17824, "", tda, 0, 0xff981f, false, true);
		tab.totalChildren(8);
		tab.child(0, 17351, 0, 27);
		tab.child(1, 17352, 8, 6);
		tab.child(2, 17353, 8, 6);
		tab.child(3, 17355, 165, 231);
		tab.child(4, 17356, 165, 231);
		tab.child(5, 13800, 68, 78);
		tab.child(6, 17820, 92, 8);
		tab.child(7, 17822, 94, 231);
		tab = addTabInterface(14000);
		tab.width = 474;
		tab.height = 213;
		tab.scrollMax = 305;
		for(int i = 14001; i <= 14030; i++){
		addText(i, "", tda, 1, 0xffffff, false, true);
		}
		tab.totalChildren(30);
		int Child = 0;
		int Y = 5;
		for(int i = 14001; i <= 14030; i++){
		tab.child(Child, i, 248, Y);
		Child++;
		Y += 13;
		}
	}

	public static void questTab(TextDrawingArea[] tda)
  {
    RSInterface localRSInterface = addInterface(638);
    setChildren(4, localRSInterface);
    addText(29155, "", 16750623, false, true, 52, tda, 2);
    addButton(29156, 2, "QuestTab/QUEST", 18, 18, "Swap to Information", 1);
    addSprite(29157, 0, "Interfaces/QuestTab/QUEST");
    setBounds(29155, 10, 5, 0, localRSInterface);
    setBounds(29156, 165, 5, 1, localRSInterface);
    setBounds(29157, 3, 24, 2, localRSInterface);
    setBounds(29160, 5, 29, 3, localRSInterface);
    localRSInterface = addInterface(29160);
    localRSInterface.height = 214;
    localRSInterface.width = 165;
    localRSInterface.scrollMax = 1700;
    setChildren(105, localRSInterface);
    addText(29161, "", 16750623, false, true, 52, tda, 2);
    addHoverText(29162, "", "", tda, 0, 16711680, false, true, 150);
    addHoverText(29163, "", "", tda, 0, 16711680, false, true, 150);
    addHoverText(29164, "", "", tda, 0, 16711680, false, true, 150);
    addText(29165, "", 16750623, false, true, 52, tda, 2);
    addHoverText(29166, "", "Register", tda, 0, 16711680, false, true, 150);
    setBounds(29161, 4, 4, 0, localRSInterface);
    setBounds(29162, 8, 22, 1, localRSInterface);
    setBounds(29163, 4, 35, 2, localRSInterface);
    setBounds(29164, 8, 53, 3, localRSInterface);
    setBounds(663, 4, 67, 4, localRSInterface);
    int i = 83;
    int j = 5;
    for (int k = 29165; k <= 29264; k++) {
      addHoverText(k, "", "Buy", tda, 0, 16711680, false, true, 150);
      setBounds(k, 8, i, j, localRSInterface);
      j++;
      i += 15;
      i++;
    }
    localRSInterface = addInterface(29265);
    try {
      setChildren(4, localRSInterface);
      addText(29266, "Coming soon!", 16750623, false, true, -1, tda, 2);
      addButton(29267, 1, "Interfaces/QuestTab/QUEST", 18, 18, "Swap to Player Info", 1);
      addSprite(29269, 0, "Interfaces/QuestTab/QUEST");
      setBounds(29266, 10, 5, 0, localRSInterface);
      setBounds(29267, 165, 5, 1, localRSInterface);
      setBounds(29269, 3, 24, 2, localRSInterface);
      setBounds(29268, 5, 29, 3, localRSInterface);
      localRSInterface = addInterface(29268);
      localRSInterface.height = 214;
      localRSInterface.width = 165;
      localRSInterface.scrollMax = 1700;
      setChildren(20, localRSInterface);
      setBounds(29295, 8, 4, 0, localRSInterface);
      setBounds(29296, 8, 16, 1, localRSInterface);
      setBounds(29297, 8, 29, 2, localRSInterface);
      setBounds(29298, 8, 42, 3, localRSInterface);
      setBounds(29299, 8, 54, 4, localRSInterface);
      setBounds(29300, 8, 66, 5, localRSInterface);
      setBounds(29301, 8, 78, 6, localRSInterface);
      setBounds(29302, 8, 90, 7, localRSInterface);
      setBounds(29303, 8, 102, 8, localRSInterface);
      setBounds(29304, 8, 114, 9, localRSInterface);
      setBounds(29305, 8, 126, 10, localRSInterface);
      setBounds(29306, 8, 138, 11, localRSInterface);
      setBounds(29307, 8, 150, 12, localRSInterface);
      setBounds(29308, 8, 162, 13, localRSInterface);
      setBounds(29309, 8, 174, 14, localRSInterface);
      setBounds(29310, 8, 186, 15, localRSInterface);
      setBounds(29311, 8, 198, 16, localRSInterface);
      setBounds(29312, 8, 210, 17, localRSInterface);
      setBounds(29313, 8, 222, 18, localRSInterface);
      setBounds(29314, 8, 234, 19, localRSInterface);
      addHoverText(29295, "Please register at", "Please Register", tda, 1, 16750623, false, true, 150);
      addHoverText(29296, "TavloniaRSPS.com", "", tda, 0, 16711680, false, true, 150);
      addHoverText(29297, "And advertise/vote daily!", "", tda, 0, 16711680, false, true, 150);
      addHoverText(29298, "::vote for more players!", "", tda, 0, 16711680, false, true, 150);
      addHoverText(29299, "More players=More updates!", "", tda, 0, 16711680, false, true, 150);
      addHoverText(29300, "", "", tda, 0, 16711680, false, true, 150);
      addHoverText(29301, "", "", tda, 1, 16750623, false, true, 150);
      addHoverText(29302, "", "", tda, 0, 16711680, false, true, 150);
      addHoverText(29303, "", "", tda, 0, 16711680, false, true, 150);
      addHoverText(29304, "", "", tda, 0, 16711680, false, true, 150);
      addHoverText(29305, "", "", tda, 0, 16711680, false, true, 150);
      addHoverText(29306, "", "", tda, 1, 16750623, false, true, 150);
      addHoverText(29307, "", "", tda, 0, 16711680, false, true, 150);
      addHoverText(29308, "", "", tda, 0, 16711680, false, true, 150);
      addHoverText(29309, "", "", tda, 0, 16711680, false, true, 150);
      addHoverText(29310, "", "", tda, 0, 16711680, false, true, 150);
      addHoverText(29311, "", "", tda, 0, 16711680, false, true, 150);
      addHoverText(29312, "", "", tda, 0, 16711680, false, true, 150);
      addHoverText(29313, "", "", tda, 0, 16711680, false, true, 150);
      addHoverText(29314, "", "", tda, 0, 16711680, false, true, 150);
    } catch (Exception localException) {
      localException.printStackTrace();
    }
  }

public static void achievement(TextDrawingArea[] tda)
  {
    RSInterface localRSInterface1 = addTabInterface(17000);
    RSInterface localRSInterface2 = addTabInterface(17001);
    addText(17002, "Achievements", tda, 2, 16750848, false, true);
    addSprite(17003, 0, "Interfaces/Achieve/ACH");
    addSprite(17004, 3, "Interfaces/Achieve/ACH");
    addSprite(17005, 0, "Interfaces/Achieve/ACH");
    addButton(17090, 1, "QuestTab/QUEST", 18, 18, "Swap To Quest Tab", 1);
    localRSInterface1.totalChildren(6);
    localRSInterface1.child(0, 17002, 5, 5);
    localRSInterface1.child(1, 17003, 0, 25);
    localRSInterface1.child(2, 17004, 0, 28);
    localRSInterface1.child(3, 17005, 0, 249);
    localRSInterface1.child(4, 17001, 0, 25);
    localRSInterface1.child(5, 17090, 165, 3);
    localRSInterface2.width = 174; localRSInterface2.height = 224; localRSInterface2.scrollMax = 1250;

    addHoverText(17049, "Information", "Read", tda, 1, 16750848, false, true, 150);
    addText(17006, "Quests", tda, 2, 16750848, false, true);
    addHoverText(17007, "Cook's Assistant", "View", tda, 0, 16711680, false, true, 150);
    addHoverText(17008, "Save The Desert!", "View", tda, 0, 16711680, false, true, 150);
    addHoverText(17009, "", "View", tda, 0, 16711680, false, true, 150);
    addHoverText(17010, "", "View", tda, 0, 16711680, false, true, 150);
    addHoverText(17011, "", "View", tda, 0, 16711680, false, true, 150);
    addHoverText(17012, "", "View", tda, 0, 16711680, false, true, 150);
    addHoverText(17013, "", "View", tda, 0, 16711680, false, true, 150);
    addHoverText(17014, "", "View", tda, 0, 16711680, false, true, 150);
    addHoverText(17015, "", "View", tda, 0, 16711680, false, true, 150);
    addText(17016, "Player Killing", tda, 2, 16750848, false, true);
    addHoverText(17017, "Kill 10 Players", "View", tda, 0, 16711680, false, true, 150);
    addHoverText(17018, "Kill 25 Players", "View", tda, 0, 16711680, false, true, 150);
    addHoverText(17019, "Kill 50 Players", "View", tda, 0, 16711680, false, true, 150);
    addHoverText(17020, "Kill 100 Players", "View", tda, 0, 16711680, false, true, 150);
    addHoverText(17021, "Kill 200 Players", "View", tda, 0, 16711680, false, true, 150);
    addHoverText(17022, "Kill 350 Players", "View", tda, 0, 16711680, false, true, 150);
    addHoverText(17023, "Kill 500 Players", "View", tda, 0, 16711680, false, true, 150);
    addHoverText(17024, "Kill 750 Players", "View", tda, 0, 16711680, false, true, 150);
    addHoverText(17025, "Kill 1000 Players", "View", tda, 0, 16711680, false, true, 150);

    addText(17026, "NPC's", tda, 2, 16750848, false, true);
    addHoverText(17027, "Defeat the King Black Dragon", "View", tda, 0, 16711680, false, true, 150);
    addHoverText(17028, "Defeat the Chaos Elemental", "View", tda, 0, 16711680, false, true, 150);
    addHoverText(17029, "Defeat the Corporal Beast", "View", tda, 0, 16711680, false, true, 150);
    addHoverText(17030, "Defeat a Tormented Demon", "View", tda, 0, 16711680, false, true, 150);
    addHoverText(17031, "Complete 5 Slayer tasks", "View", tda, 0, 16711680, false, true, 150);
    addHoverText(17032, "Complete 10 Slayer tasks", "View", tda, 0, 16711680, false, true, 150);
    addHoverText(17033, "Complete 25 Slayer tasks", "View", tda, 0, 16711680, false, true, 150);
    addHoverText(17034, "Complete 50 Slayer tasks", "View", tda, 0, 16711680, false, true, 150);
    addText(17035, "Minigames", tda, 2, 16750848, false, true);
    addHoverText(17036, "Win one game of Pest Control", "View", tda, 0, 16711680, false, true, 150);
    addHoverText(17037, "Win five games of Pest Control", "View", tda, 0, 16711680, false, true, 150);
    addHoverText(17038, "Complete Barrow once", "View", tda, 0, 16711680, false, true, 150);
    addHoverText(17039, "Complete Barrows five times", "View", tda, 0, 16711680, false, true, 150);
    addHoverText(17040, "", "View", tda, 0, 16711680, false, true, 150);
    addHoverText(17041, "", "View", tda, 0, 16711680, false, true, 150);
    addHoverText(17042, "", "View", tda, 0, 16711680, false, true, 150);
    addHoverText(17043, "", "View", tda, 0, 16711680, false, true, 150);
    addHoverText(17044, "", "View", tda, 0, 16711680, false, true, 150);
    addHoverText(17045, "", "View", tda, 0, 16711680, false, true, 150);
    addHoverText(17046, "", "View", tda, 0, 16711680, false, true, 150);
    addHoverText(17047, "", "View", tda, 0, 16711680, false, true, 150);
    addHoverText(17048, "", "View", tda, 0, 16711680, false, true, 150);
    localRSInterface2.totalChildren(44);
    localRSInterface2.child(0, 17007, 5, 47);
    localRSInterface2.child(1, 17008, 5, 61);
    localRSInterface2.child(2, 17009, 5, 75);
    localRSInterface2.child(3, 17010, 5, 89);
    localRSInterface2.child(4, 17011, 5, 103);
    localRSInterface2.child(5, 17006, 5, 20);
    localRSInterface2.child(6, 17012, 5, 117);
    localRSInterface2.child(7, 17013, 5, 131);
    localRSInterface2.child(8, 17014, 5, 145);
    localRSInterface2.child(9, 17015, 5, 159);
    localRSInterface2.child(10, 17016, 5, 187);
    localRSInterface2.child(11, 17017, 5, 215);
    localRSInterface2.child(12, 17018, 5, 229);
    localRSInterface2.child(13, 17019, 5, 243);
    localRSInterface2.child(14, 17020, 5, 257);
    localRSInterface2.child(15, 17021, 5, 271);
    localRSInterface2.child(16, 17022, 5, 285);
    localRSInterface2.child(17, 17023, 5, 299);
    localRSInterface2.child(18, 17024, 5, 313);
    localRSInterface2.child(19, 17025, 5, 327);
    localRSInterface2.child(20, 17026, 5, 350);
    localRSInterface2.child(21, 17027, 5, 380);
    localRSInterface2.child(22, 17028, 5, 393);
    localRSInterface2.child(23, 17029, 5, 411);
    localRSInterface2.child(24, 17030, 5, 424);
    localRSInterface2.child(25, 17031, 5, 439);
    localRSInterface2.child(26, 17032, 5, 453);
    localRSInterface2.child(27, 17033, 5, 467);
    localRSInterface2.child(28, 17034, 5, 481);
    localRSInterface2.child(29, 17035, 5, 509);
    localRSInterface2.child(30, 17036, 5, 537);
    localRSInterface2.child(31, 17037, 5, 551);
    localRSInterface2.child(32, 17038, 5, 565);
    localRSInterface2.child(33, 17039, 5, 579);
    localRSInterface2.child(34, 17040, 5, 593);
    localRSInterface2.child(35, 17041, 5, 607);
    localRSInterface2.child(36, 17042, 5, 621);
    localRSInterface2.child(37, 17043, 5, 635);
    localRSInterface2.child(38, 17044, 5, 649);
    localRSInterface2.child(39, 17045, 5, 663);
    localRSInterface2.child(40, 17046, 5, 677);
    localRSInterface2.child(41, 17047, 5, 691);
    localRSInterface2.child(42, 17048, 5, 705);
    localRSInterface2.child(43, 17049, 5, 6);
  }
	
	public static void addHoverText(int paramInt1, String paramString1, String paramString2, TextDrawingArea[] paramArrayOfTextDrawingArea, int paramInt2, int paramInt3, boolean paramBoolean1, boolean paramBoolean2, int paramInt4)
  {
    RSInterface localRSInterface = addInterface(paramInt1);
    localRSInterface.id = paramInt1;
    localRSInterface.parentID = paramInt1;
    localRSInterface.type = 4;
    localRSInterface.atActionType = 1;
    localRSInterface.width = paramInt4;
    localRSInterface.height = 11;
    localRSInterface.contentType = 0;
    localRSInterface.aByte254 = 0;
    localRSInterface.mOverInterToTrigger = -1;
    localRSInterface.centerText = paramBoolean1;
    localRSInterface.textShadow = paramBoolean2;
    localRSInterface.textDrawingAreas = paramArrayOfTextDrawingArea[paramInt2];
    localRSInterface.message = paramString1;
    localRSInterface.aString228 = "";
    localRSInterface.textColor = paramInt3;
    localRSInterface.anInt219 = 0;
    localRSInterface.anInt216 = 16777215;
    localRSInterface.anInt239 = 0;
    localRSInterface.tooltip = paramString2;
  }
	public static void magicTab(TextDrawingArea[] tda) {
		RSInterface tab = addTabInterface(1151);
		RSInterface homeHover = addTabInterface(1196);
		RSInterface spellButtons = interfaceCache[12424];
		spellButtons.scrollMax = 0; spellButtons.height = 260; spellButtons.width = 190;
		int[] spellButton = {
			1196, 1199, 1206, 1215, 1224, 1231, 1240, 1249, 1258, 1267, 1274, 1283, 1573,
			1290, 1299, 1308, 1315, 1324, 1333, 1340, 1349, 1358, 1367, 1374, 1381, 1388,
			1397, 1404, 1583, 12038, 1414, 1421, 1430, 1437, 1446, 1453, 1460, 1469, 15878,
			1602, 1613, 1624, 7456, 1478, 1485, 1494, 1503, 1512, 1521, 1530, 1544, 1553,
			1563, 1593, 1635, 12426, 12436, 12446, 12456, 6004, 18471
		};
		tab.totalChildren(63);
		tab.child(0, 12424, 13, 24);
		for(int i1 = 0; i1 < spellButton.length; i1++) {
			int yPos = i1 > 34 ? 8 : 183;
			tab.child(1, 1195, 13, 24);
			tab.child(i1 + 2, spellButton[i1], 5, yPos);
			addButton(1195, 1, "Custom/Magic/Home", "Cast @gre@Home Teleport");
			RSInterface homeButton = interfaceCache[1195];
			homeButton.mOverInterToTrigger = 1196;
		}
		for(int i2 = 0; i2 < spellButton.length; i2++) {
			if(i2 < 60)
				spellButtons.childX[i2] = spellButtons.childX[i2] + 24;
			if(i2 == 6 || i2 == 12 || i2 == 19 || i2 == 35 || i2 == 41 || i2 == 44 || i2 == 49 || i2 == 51)
				spellButtons.childX[i2] = 0;
			spellButtons.childY[6] = 24; spellButtons.childY[12] = 48;
			spellButtons.childY[19] = 72; spellButtons.childY[49] = 96;
			spellButtons.childY[44] = 120; spellButtons.childY[51] = 144;
			spellButtons.childY[35] = 170; spellButtons.childY[41] = 192;
		}
		homeHover.isMouseoverTriggered = true;
		addText(1197, "Level 0: Home Teleport", tda, 1, 0xFE981F, true, true);
		RSInterface homeLevel = interfaceCache[1197]; homeLevel.width = 174; homeLevel.height = 68;
		addText(1198, "A teleport which requires no", tda, 0, 0xAF6A1A, true, true);
		addText(18998, "runes and no required level that", tda, 0, 0xAF6A1A, true, true);
		addText(18999, "teleports you to the main land.", tda, 0, 0xAF6A1A, true, true);
		homeHover.totalChildren(4);
		homeHover.child(0, 1197, 3, 4);
		homeHover.child(1, 1198, 91, 23);
		homeHover.child(2, 18998, 91, 34);
		homeHover.child(3, 18999, 91, 45);
	}
	
	public static void ancientMagicTab(TextDrawingArea[] tda) {
		RSInterface tab = addInterface(12855);
		addButton(12856, 1, "Custom/Magic/Home", "Cast @gre@Home Teleport");
		RSInterface homeButton = interfaceCache[12856]; 
		homeButton.mOverInterToTrigger = 1196;
		int[] itfChildren = {
			12856, 12939, 12987, 13035, 12901, 12861, 13045, 12963, 13011, 13053, 12919, 12881, 13061,
			12951, 12999, 13069, 12911, 12871, 13079, 12975, 13023, 13087, 12929, 12891, 13095, 1196,
			12940, 12988, 13036, 12902, 12862, 13046, 12964, 13012, 13054, 12920, 12882, 13062, 12952,
			13000, 13070, 12912, 12872, 13080, 12976, 13024, 13088, 12930, 12892, 13096
		};
		tab.totalChildren(itfChildren.length);
		for(int i1 = 0, xPos = 18, yPos = 8; i1 < itfChildren.length; i1++, xPos += 45) {
			if(xPos > 175) {
				xPos = 18; yPos += 28;
			}
			if(i1 < 25)
				tab.child(i1, itfChildren[i1], xPos, yPos);
			if(i1 > 24) {
				yPos = i1 < 41 ? 181 : 1;
				tab.child(i1, itfChildren[i1], 4, yPos);
			}
		}
	}
	
	public static void drawBlackBox(int xPos, int yPos) {
        ///Light Coloured Borders\\\
		DrawingArea.drawPixels(71, yPos - 1, xPos - 2, 0x726451, 1); // Left line
		DrawingArea.drawPixels(69, yPos, xPos + 174, 0x726451, 1); // Right line
		DrawingArea.drawPixels(1, yPos - 2, xPos - 2, 0x726451, 178); // Top Line
		DrawingArea.drawPixels(1, yPos + 68, xPos, 0x726451, 174); // Bottom Line

        ///Dark Coloured Borders\\\
		DrawingArea.drawPixels(71, yPos - 1, xPos - 1, 0x2E2B23, 1); // Left line
		DrawingArea.drawPixels(71, yPos - 1, xPos + 175, 0x2E2B23, 1); // Right line
		DrawingArea.drawPixels(1, yPos - 1, xPos, 0x2E2B23, 175); // Top line
		DrawingArea.drawPixels(1, yPos + 69, xPos, 0x2E2B23, 175); // Top line

        ///Black Box\\\
        DrawingArea.method335(0x000000, yPos, 174, 68, 220, xPos); //Yes method335 is galkons opacity method
    }
	public static void AddInterfaceButton(int i, int j, int hoverId, String name, int W, int H, String S, int AT) {
		RSInterface RSInterface = addInterface(i);
		RSInterface.id = i;
		RSInterface.parentID = i;
		RSInterface.interfaceType = 5;
		RSInterface.atActionType = AT;
		RSInterface.opacity = 0;
		RSInterface.hoverType = hoverId;
		RSInterface.disabledSprite = imageLoader(j,name);
		RSInterface.enabledSprite = imageLoader(j,name);
		RSInterface.width = W;
		RSInterface.height = H;
		RSInterface.tooltip = S;
	}
	
	private static void addTransparentSprite(int id, int spriteId, String spriteName, int transparency) {
		RSInterface tab = interfaceCache[id] = new RSInterface();
		tab.id = id;
		tab.parentID = id;
		tab.type = 5;
		tab.atActionType = 0;
		tab.contentType = 0;
		tab.transparency = (byte) transparency;
		tab.hoverType = 52;
		tab.sprite1 = imageLoader(spriteId, spriteName);
		tab.sprite2 = imageLoader(spriteId, spriteName);
		tab.width = 512;
		tab.height = 334;
		tab.drawsTransparent = true;
	}

	public int transparency;
	
	public static void addButton(int id, int sid, String spriteName, String tooltip) {
		RSInterface tab = interfaceCache[id] = new RSInterface();
		tab.id = id;
		tab.parentID = id;
		tab.type = 5;
		tab.atActionType = 1;
		tab.contentType = 0;
		tab.aByte254 = (byte)0;
		tab.mOverInterToTrigger = 52;
		tab.sprite1 = imageLoader(sid, spriteName);
		tab.sprite2 = imageLoader(sid, spriteName);
		tab.width = tab.sprite1.myWidth;
		tab.height = tab.sprite2.myHeight;
		tab.tooltip = tooltip;
	}
	
	
	public Sprite enabledSprite;
	
	public static void disabledSprite(int id, int sprite) {
		RSInterface class9 = interfaceCache[id];
        class9.disabledSprite = CustomSpriteLoader(sprite, "");
    }
	public Sprite disabledSprite;	
	

	public static void addTransparentSprite2(int id, int spriteId, String spriteName, int opacity) {
		RSInterface tab = interfaceCache[id] = new RSInterface();
		tab.id = id;
		tab.parentID = id;
		tab.type = 10;
		tab.atActionType = 0;
		tab.contentType = 0;
		tab.opacity = (byte)opacity;
		tab.hoverType = 52;
		tab.disabledSprite = imageLoader(spriteId, spriteName);
		tab.enabledSprite = imageLoader(spriteId, spriteName); 
		tab.width = 512;
		tab.height = 334;
		tab.drawsTransparent = true;
	}
		
	public static void addSpriteWithHover(int id, int spriteId, String spriteName, int hover) {
		RSInterface tab = interfaceCache[id] = new RSInterface();
		tab.id = id;
		tab.parentID = id;
		tab.type = 5;
		tab.atActionType = 0;
		tab.contentType = 0;
		tab.opacity = (byte)0;
		tab.hoverType = hover;
		tab.sprite1 = imageLoader(spriteId, spriteName);
		tab.sprite2 = imageLoader(spriteId, spriteName); 
		tab.width = 190;
		tab.height = 47;
	}
	public static void curseTab(TextDrawingArea[] TDA) {
	try {
		RSInterface Interface = addTabInterface(22500);
		int index = 0;
		addSpriteWithHover(688, 11, "Interfaces/CurseTab/SPRITE", 19021);
		addButton(689, 12, "Interfaces/CurseTab/SPRITE", "",190,17);
		addText(29765, "Hide stat adjustments.", 0xFFCC00, false, true, -1, TDA, 0, 0xFFFFFF);
		addText(690, "", 0xFF981F, false, false, -1, TDA, 0);
		addText(691, "", 0xFF981F, false, false, -1, TDA, 0);
		addText(692, "", 0xFF981F, false, false, -1, TDA, 0);
		addText(693, "", 0xFF981F, false, false, -1, TDA, 0);
		addText(694, "", 0xFF981F, false, false, -1, TDA, 0);
		addText(687, "990/990", 0xFF981F, false, false, -1, TDA, 1);
		addSprite(22502, 0, "Interfaces/CurseTab/ICON");
		addPrayer(22503, 0, 610, 49, 7, "Protect Item", 22582);//1
		addPrayer(22505, 0, 611, 49, 4, "Sap Warrior", 22544);//2
		addPrayer(22507, 0, 612, 51, 5, "Sap Ranger", 22546);//3
		addPrayer(22509, 0, 613, 53, 3, "Sap Mage", 22548);//4
		addPrayer(22511, 0, 614, 55, 2, "Sap Spirit", 22550);//5
		addPrayer(22513, 0, 615, 58, 18, "Berserker", 22552);//6
		addPrayer(22515, 0, 616, 61, 15, "Deflect Summoning", 22554);///7
		addPrayer(22517, 0, 617, 64, 17, "Deflect Magic", 22556);///8
		addPrayer(22519, 0, 618, 67, 16, "Deflect Missiles", 22558);///9
		addPrayer(22521, 0, 619, 70, 6, "Deflect Melee", 22560);///10
		addPrayer(22523, 0, 620, 73, 9, "Leech Attack", 22562);//11
		addPrayer(22525, 0, 621, 75, 10, "Leech Ranged", 22564);//12
		addPrayer(22527, 0, 622, 77, 11, "Leech Magic", 22566);//13
		addPrayer(22529, 0, 623, 79, 12, "Leech Defence", 22568);//14
		addPrayer(22531, 0, 624, 81, 13, "Leech Strength", 22570);//15
		addPrayer(22533, 0, 625, 83, 14, "Leech Energy", 22572);//16
		addPrayer(22535, 0, 626, 85, 19, "Leech Special Attack", 22574);//17
		addPrayer(22537, 0, 627, 88, 1, "Wrath", 22576);///18
		addPrayer(22539, 0, 628, 91, 8, "Soul Split", 22578);///19
		addPrayer(22541, 0, 629, 94, 20, "Turmoil", 22580);//20
		
		addTooltip(22582, "Level 50\nProtect Item\nKeep 1 extra item if you die");
		addTooltip(22544, "Level 50\nSap Warrior\nDrains 10% of enemy Attack,\nStrength and Defence,\nincreasing to 20% over time");
		addTooltip(22546, "Level 52\nSap Ranger\nDrains 10% of enemy Ranged\nand Defence, increasing to 20%\nover time");
		addTooltip(22548, "Level 54\nSap Mage\nDrains 10% of enemy Magic\nand Defence, increasing to 20%\nover time");
		addTooltip(22550, "Level 56\nSap Spirit\nDrains enenmy special attack\nenergy");
		addTooltip(22552, "Level 59\nBerserker\nBoosted stats last 15% longer");
		addTooltip(22554, "Level 62\nDeflect Summoning\nReduces damage dealt from\nSummoning scrolls, prevents the\nuse of a familiar's special\nattack, and can deflect some of\ndamage back to the attacker");
		addTooltip(22556, "Level 65\nDeflect Magic\nProtects against magical attacks\nand can deflect some of the\ndamage back to the attacker");
		addTooltip(22558, "Level 68\nDeflect Missiles\nProtects against ranged attacks\nand can deflect some of the\ndamage back to the attacker");
		addTooltip(22560, "Level 71\nDeflect Melee\nProtects against melee attacks\nand can deflect some of the\ndamage back to the attacker");
		addTooltip(22562, "Level 74\nLeech Attack\nBoosts Attack by 5%, increasing\nto 10% over time, while draining\nenemy Attack by 10%, increasing\nto 25% over time");
		addTooltip(22564, "Level 76\nLeech Ranged\nBoosts Ranged by 5%, increasing\nto 10% over time, while draining\nenemy Ranged by 10%,\nincreasing to 25% over\ntime");
		addTooltip(22566, "Level 78\nLeech Magic\nBoosts Magic by 5%, increasing\nto 10% over time, while draining\nenemy Magic by 10%, increasing\nto 25% over time");
		addTooltip(22568, "Level 80\nLeech Defence\nBoosts Defence by 5%, increasing\nto 10% over time, while draining\n enemy Defence by10%,\nincreasing to 25% over\ntime");
		addTooltip(22570, "Level 82\nLeech Strength\nBoosts Strength by 5%, increasing\nto 10% over time, while draining\nenemy Strength by 10%, increasing\n to 25% over time");
		addTooltip(22572, "Level 84\nLeech Energy\nDrains enemy run energy, while\nincreasing your own");
		addTooltip(22574, "Level 86\nLeech Special Attack\nDrains enemy special attack\nenergy, while increasing your\nown");
		addTooltip(22576, "Level 89\nWrath\nInflicts damage to nearby\ntargets if you die");
		addTooltip(22578, "Level 92\nSoul Split\n1/4 of damage dealt is also removed\nfrom opponent's Prayer and\nadded to your Hitpoints");
		addTooltip(22580, "Level 95\nTurmoil\nIncreases Attack and Defence\nby 15%, plus 15% of enemy's\nlevel, and Strength by 23% plus\n10% of enemy's level");
		setChildren(62, Interface);

		setBounds(687, 85, 241, index, Interface);index++;
		setBounds(22502, 65, 241, index, Interface);index++;
		setBounds(22503, 2, 5, index, Interface);index++;
		setBounds(22504, 8, 8, index, Interface);index++;
		setBounds(22505, 40, 5, index, Interface);index++;
		setBounds(22506, 47, 12, index, Interface);index++;
		setBounds(22507, 76, 5, index, Interface);index++;
		setBounds(22508, 82, 11, index, Interface);index++;
		setBounds(22509, 113, 5, index, Interface);index++;
		setBounds(22510, 116, 8, index, Interface);index++;
		setBounds(22511, 150, 5, index, Interface);index++;
		setBounds(22512, 155, 10, index, Interface);index++;
		setBounds(22513, 2, 45, index, Interface);index++;
		setBounds(22514, 9, 48, index, Interface);index++;
		setBounds(22515, 39, 45, index, Interface);index++;
		setBounds(22516, 42, 47, index, Interface);index++;
		setBounds(22517, 76, 45, index, Interface);index++;
		setBounds(22518, 79, 48, index, Interface);index++;
		setBounds(22519, 113, 45, index, Interface);index++;
		setBounds(22520, 116, 48, index, Interface);index++;
		setBounds(22521, 151, 45, index, Interface);index++;
		setBounds(22522, 154, 48, index, Interface);index++;
		setBounds(22523, 2, 82, index, Interface);index++;
		setBounds(22524, 6, 86, index, Interface);index++;
		setBounds(22525, 40, 82, index, Interface);index++;
		setBounds(22526, 42, 86, index, Interface);index++;
		setBounds(22527, 77, 82, index, Interface);index++;
		setBounds(22528, 79, 86, index, Interface);index++;
		setBounds(22529, 114, 83, index, Interface);index++;
		setBounds(22530, 119, 87, index, Interface);index++;
		setBounds(22531, 153, 83, index, Interface);index++;
		setBounds(22532, 156, 86, index, Interface);index++;
		setBounds(22533, 2, 120, index, Interface);index++;
		setBounds(22534, 7, 125, index, Interface);index++;
		setBounds(22535, 40, 120, index, Interface);index++;
		setBounds(22536, 45, 124, index, Interface);index++;
		setBounds(22537, 78, 120, index, Interface);index++;
		setBounds(22538, 86, 124, index, Interface);index++;
		setBounds(22539, 114, 120, index, Interface);index++;
		setBounds(22540, 120, 125, index, Interface);index++;
		setBounds(22541, 151, 120, index, Interface);index++;
		setBounds(22542, 153, 127, index, Interface);index++;
		setBounds(22582, 10, 40, index, Interface);index++;
		setBounds(22544, 20, 40, index, Interface);index++;
		setBounds(22546, 20, 40, index, Interface);index++;
		setBounds(22548, 20, 40, index, Interface);index++;
		setBounds(22550, 20, 40, index, Interface);index++;
		setBounds(22552, 10, 80, index, Interface);index++;
		setBounds(22554, 10, 80, index, Interface);index++;
		setBounds(22556, 10, 80, index, Interface);index++;
		setBounds(22558, 10, 80, index, Interface);index++;
		setBounds(22560, 10, 80, index, Interface);index++;
		setBounds(22562, 10, 120, index, Interface);index++;
		setBounds(22564, 10, 120, index, Interface);index++;
		setBounds(22566, 10, 120, index, Interface);index++;
		setBounds(22568, 5, 120, index, Interface);index++;
		setBounds(22570, 5, 120, index, Interface);index++;
		setBounds(22572, 10, 160, index, Interface);index++;
		setBounds(22574, 10, 160, index, Interface);index++;
		setBounds(22576, 10, 160, index, Interface);index++;
		setBounds(22578, 10, 160, index, Interface);index++;
		setBounds(22580, 10, 160, index, Interface);index++;
		setBounds(689, 0, 217, index, Interface);index++;
		setBounds(688, 0, 170, index, Interface);index++;
		setBounds(690, 89, 200, index, Interface);index++;
		setBounds(691, 10, 200, index, Interface);index++;
		setBounds(692, 50, 200, index, Interface);index++;
		setBounds(693, 130, 200, index, Interface);index++;
		setBounds(694, 170, 200, index, Interface);index++;
		}
		catch (Exception e) {
		}
	}
	public static void drawTooltip(int id, String text) {
		RSInterface rsinterface = addTabInterface(id);
		rsinterface.parentID = id;
		rsinterface.type = 0;
		rsinterface.interfaceShown = true;
		rsinterface.hoverType = -1;
		addTooltipBox(id + 1, text);
		rsinterface.totalChildren(1);
		rsinterface.child(0, id + 1, 0, 0);
	}
	

	
	/*public static void Curses(TextDrawingArea atextdrawingarea[])
    {
        RSInterface rsinterface = addTabInterface(22500);
        int i = 0;
        setChildren(70, rsinterface);
		addSpriteWithHover(688, 11, "Interfaces/CurseTab/SPRITE", 19021);
		addButton(689, 12, "Interfaces/CurseTab/SPRITE", "",190,17);
		addText(29765, "Hide stat adjustments.", 0xFFCC00, false, true, -1, atextdrawingarea, 0, 0xFFFFFF);
		addText(690, "", 0xFF981F, false, false, -1, atextdrawingarea, 0);
		addText(691, "", 0xFF981F, false, false, -1, atextdrawingarea, 0);
		addText(692, "", 0xFF981F, false, false, -1, atextdrawingarea, 0);
		addText(693, "", 0xFF981F, false, false, -1, atextdrawingarea, 0);
		addText(694, "", 0xFF981F, false, false, -1, atextdrawingarea, 0);
        addText(687, "99/99", 0xFF981F, false, false, -1, atextdrawingarea, 1);

        addPrayer(22503, 0, 83, 49, 7, "Protect Item", 22543);
        setBounds(22503, 2, 5, i, rsinterface); i++;
        setBounds(22504, 8, 8, i, rsinterface); i++;

        addPrayer(22505, 0, 84, 49, 4, "Sap Warrior", 22544);
        setBounds(22505, 40, 5, i, rsinterface);  i++;
        setBounds(22506, 47, 12, i, rsinterface); i++;

        addPrayer(22507, 0, 85, 51, 5, "Sap Ranger", 22546);
        setBounds(22507, 76, 5, i, rsinterface);  i++;
        setBounds(22508, 82, 11, i, rsinterface); i++;

        addPrayer(22509, 0, 101, 53, 3, "Sap Mage", 22548);
        setBounds(22509, 113, 5, i, rsinterface); i++;
        setBounds(22510, 116, 8, i, rsinterface); i++;

        addPrayer(22511, 0, 102, 55, 2, "Sap Spirit", 22550);
        setBounds(22511, 150, 5, i, rsinterface);  i++;
        setBounds(22512, 155, 10, i, rsinterface); i++;

        addPrayer(22513, 0, 86, 58, 18, "Berserker", 22552);
        setBounds(22513, 2, 45, i, rsinterface); i++;
        setBounds(22514, 9, 48, i, rsinterface); i++;

        addPrayer(22515, 0, 87, 61, 15, "Deflect Summoning", 22554);
        setBounds(22515, 39, 45, i, rsinterface); i++;
        setBounds(22516, 42, 47, i, rsinterface); i++;

        addPrayer(22517, 0, 88, 64, 17, "Deflect Magic", 22556);
        setBounds(22517, 76, 45, i, rsinterface); i++;
        setBounds(22518, 79, 48, i, rsinterface); i++;

        addPrayer(22519, 0, 89, 67, 16, "Deflect Missiles", 22558);
        setBounds(22519, 113, 45, i, rsinterface); i++;
        setBounds(22520, 116, 48, i, rsinterface); i++;

        addPrayer(22521, 0, 90, 70, 6, "Deflect Melee", 22560);
        setBounds(22521, 151, 45, i, rsinterface); i++;
        setBounds(22522, 154, 48, i, rsinterface); i++;

        addPrayer(22523, 0, 91, 73, 9, "Leech Attack", 22562);
        setBounds(22523, 2, 82, i, rsinterface); i++;
        setBounds(22524, 6, 86, i, rsinterface); i++;

        addPrayer(22525, 0, 103, 75, 10, "Leech Ranged", 22564);
        setBounds(22525, 40, 82, i, rsinterface); i++;
        setBounds(22526, 42, 86, i, rsinterface); i++;

        addPrayer(22527, 0, 104, 77, 11, "Leech Magic", 22566);
        setBounds(22527, 77, 82, i, rsinterface); i++;
        setBounds(22528, 79, 86, i, rsinterface); i++;

        addPrayer(22529, 0, 92, 79, 12, "Leech Defence", 22568);
        setBounds(22529, 114, 83, i, rsinterface); i++;
        setBounds(22530, 119, 87, i, rsinterface); i++;

        addPrayer(22531, 0, 93, 81, 13, "Leech Strength", 22570);
        setBounds(22531, 153, 83, i, rsinterface); i++;
        setBounds(22532, 156, 86, i, rsinterface); i++;

        addPrayer(22533, 0, 94, 83, 14, "Leech Energy", 22572);
        setBounds(22533, 2, 120, i, rsinterface); i++;
        setBounds(22534, 7, 125, i, rsinterface); i++;

        addPrayer(22535, 0, 95, 85, 19, "Leech Special Attack", 22574);
        setBounds(22535, 40, 120, i, rsinterface); i++;
        setBounds(22536, 45, 124, i, rsinterface); i++;

        addPrayer(22537, 0, 96, 88, 1, "Wrath", 22576);
        setBounds(22537, 78, 120, i, rsinterface); i++;
        setBounds(22538, 86, 124, i, rsinterface); i++;

        addPrayer(22539, 0, 97, 91, 8, "Soul Split", 22578);
        setBounds(22539, 114, 120, i, rsinterface); i++;
        setBounds(22540, 120, 125, i, rsinterface); i++;

        addPrayer(22541, 0, 105, 94, 0, "Turmoil", 22580);
        setBounds(22541, 151, 120, i, rsinterface); i++;
        setBounds(22542, 153, 127, i, rsinterface); i++;

        addSprite(22502, 0, "Interfaces/Curses/ICON");
        setBounds(687, 85, 241, i, rsinterface); i++;
        setBounds(22502, 65, 241, i, rsinterface); i++;

        addTooltip(22543, "Level 50\nProtect Item\nKeep 1 extra item if you die", 100, 150);
        addTooltip(22544, "Level 50\nSap Warrior\nDrains 10% of enemy Attack,\nStrength and Defence,\nincre" +
"asing to 20% over time"
, 100, 150);
        addTooltip(22546, "Level 52\nSap Ranger\nDrains 10% of enemy Ranged\nand Defence, increasing to 20%" +
"\nover time"
, 100, 175);
        addTooltip(22548, "Level 54\nSap Mage\nDrains 10% of enemy Magic\nand Defence, increasing to 20%\no" +
"ver time"
, 100, 175);
        addTooltip(22550, "Level 56\nSap Spirit\nDrains enenmy special attack\nenergy", 100, 175);
        addTooltip(22552, "Level 59\nBerserker\nBoosted stats last 15% longer", 100, 175);
        addTooltip(22554, "Level 62\nDeflect Summoning\nReduces damage dealt from\nSummoning scrolls, preve" +
"nts the\nuse of a familiar's special\nattack, and can deflect some of\ndamage ba" +
"ck to the attacker"
, 125, 175);
        addTooltip(22556, "Level 65\nDeflect Magic\nProtects against magical attacks\nand can deflect some " +
"of the\ndamage back to the attacker"
, 100, 175);
        addTooltip(22558, "Level 68\nDeflect Missiles\nProtects against ranged attacks\nand can deflect som" +
"e of the\ndamage back to the attacker"
, 100, 175);
        addTooltip(22560, "Level 71\nDeflect Melee\nProtects against melee attacks\nand can deflect some of" +
" the\ndamage back to the attacker"
, 100, 175);
        addTooltip(22562, "Level 74\nLeech Attack\nBoosts Attack by 5%, increasing\nto 10% over time, while" +
" draining\nenemy Attack by 10%,\nincreasing to 25% over time"
, 100, 175);
        addTooltip(22564, "Level 76\nLeech Ranged\nBoosts Ranged by 5%, increasing\nto 10% over time,\nwhil" +
"e draining enemy Ranged by\n10%, increasing to 25% over\ntime"
, 113, 175);
        addTooltip(22566, "Level 78\nLeech Magic\nBoosts Magic by 5%, increasing\nto 10% over time, while d" +
"raining\nenemy Magic by 10%, increasing\nto 25% over time"
, 100, 175);
        addTooltip(22568, "Level 80\nLeech Defence\nBoosts Defence by 5%, increasing\nto 10% over time,\nwh" +
"ile draining enemy Defence by\n10%, increasing to 25% over\ntime"
, 113, 180);
        addTooltip(22570, "Level 82\nLeech Strength\nBoosts Strength by 5%, increasing\nto 10% over time,\n" +
"while draining enemy Strength by\n10%, increasing to 25% over\ntime"
, 113, 180);
        addTooltip(22572, "Level 84\nLeech Energy\nDrains enemy run energy, while\nincreasing your own", 113, 180);
        addTooltip(22574, "Level 86\nLeech Special Attack\nDrains enemy special attack\nenergy, while incre" +
"asing your\nown"
, 113, 180);
        addTooltip(22576, "Level 89\nWrath\nInflicts damage to nearby\ntargets if you die", 113, 180);
        addTooltip(22578, "Level 92\nSoul Split\n1/4 of damage dealt is\nalso removed from\nopponent's Pray" +
"er and added to\nyour Hitpoints"
, 113, 180);
        addTooltip(22580, "Level 95\nTurmoil\nIncreases Attack and Defence\nby 15%, plus 15% of enemy's\nle" +
"vel, and Strength by 23% plus\n10% of enemy's level"
, 113, 180);
        setBounds(22543, 10, 40, i, rsinterface);
        i++;
        setBounds(22544, 20, 40, i, rsinterface);
        i++;
        setBounds(22546, 20, 40, i, rsinterface);
        i++;
        setBounds(22548, 20, 40, i, rsinterface);
        i++;
        setBounds(22550, 20, 40, i, rsinterface);
        i++;
        setBounds(22552, 10, 80, i, rsinterface);
        i++;
        setBounds(22554, 10, 80, i, rsinterface);
        i++;
        setBounds(22556, 10, 80, i, rsinterface);
        i++;
        setBounds(22558, 10, 80, i, rsinterface);
        i++;
        setBounds(22560, 10, 80, i, rsinterface);
        i++;
        setBounds(22562, 10, 120, i, rsinterface);
        i++;
        setBounds(22564, 10, 120, i, rsinterface);
        i++;
        setBounds(22566, 10, 120, i, rsinterface);
        i++;
        setBounds(22568, 5, 120, i, rsinterface);
        i++;
        setBounds(22570, 5, 120, i, rsinterface);
        i++;
        setBounds(22572, 10, 160, i, rsinterface);
        i++;
        setBounds(22574, 10, 160, i, rsinterface);
        i++;
        setBounds(22576, 10, 160, i, rsinterface);
        i++;
        setBounds(22578, 10, 160, i, rsinterface);
        i++;
        setBounds(22580, 10, 160, i, rsinterface);
        i++;
		setBounds(689, 0, 217, i, rsinterface);i++;
		//setBounds(701, 0, 217, i, rsnterface);i++;
		//setBounds(29766, 85, 241, i, rsinterface);i++;
		setBounds(688, 0, 170, i, rsinterface);i++;
		setBounds(690, 89, 200, i, rsinterface);i++;
		setBounds(691, 10, 200, i, rsinterface);i++;
		setBounds(692, 50, 200, i, rsinterface);i++;
		setBounds(693, 130, 200, i, rsinterface);i++;
		setBounds(694, 170, 200, i, rsinterface);i++;
		
		setBounds(29765, 47, 217, i, rsinterface);i++;
    }*/
	public static void addConfigButton2(int i, int j, int k, int l, String s, int i1, int j1, String s1, 
            int k1, int l1, int i2)
    {
        RSInterface rsinterface = addTabInterface(i);
        rsinterface.parentID = j;
        rsinterface.id = i;
        rsinterface.type = 5;
        rsinterface.atActionType = l1;
        rsinterface.contentType = 0;
        rsinterface.width = i1;
        rsinterface.height = j1;
        rsinterface.aByte254 = 0;
        rsinterface.mOverInterToTrigger = -1;
        rsinterface.anIntArray245 = new int[1];
        rsinterface.anIntArray212 = new int[1];
        rsinterface.anIntArray245[0] = 1;
        rsinterface.anIntArray212[0] = k1;
        rsinterface.valueIndexArray = new int[1][3];
        rsinterface.valueIndexArray[0][0] = 5;
        rsinterface.valueIndexArray[0][1] = i2;
        rsinterface.valueIndexArray[0][2] = 0;
        rsinterface.sprite1 = imageLoader(k, s);
        rsinterface.sprite2 = imageLoader(l, s);
        rsinterface.tooltip = s1;
    }
	public static void prayerTab(TextDrawingArea[] tda) {
        RSInterface rsinterface = addInterface(5608);
        int i = 0;
        int j = 0;
        byte byte0 = 10;
        byte byte1 = 50;
        byte byte2 = 10;
        byte byte3 = 86;
        byte byte4 = 10;
        byte byte5 = 122;
        byte byte6 = 10;
        char c = '\237';
        byte byte7 = 10;
        byte byte8 = 86;
        int k = 1;
        byte byte9 = 52;
        addText(687, "", 0xff981f, false, true, -1, tda, 1);
        addSprite(25105, 0, "Interfaces/PrayerTab/PRAYERICON");
        addPrayerWithTooltip(25000, 0, 83, 0, j, 25052, "Activate @lre@Thick Skin");
        j++;
        addPrayerWithTooltip(25002, 0, 84, 3, j, 25054, "Activate @lre@Burst of Strength");
        j++;
        addPrayerWithTooltip(25004, 0, 85, 6, j, 25056, "Activate @lre@Clarity of Thought");
        j++;
        addPrayerWithTooltip(25006, 0, 601, 7, j, 25058, "Activate @lre@Sharp Eye");
        j++;
        addPrayerWithTooltip(25008, 0, 602, 8, j, 25060, "Activate @lre@Mystic Will");
        j++;
        addPrayerWithTooltip(25010, 0, 86, 9, j, 25062, "Activate @lre@Rock Skin");
        j++;
        addPrayerWithTooltip(25012, 0, 87, 12, j, 25064, "Activate @lre@Superhuman Strength");
        j++;
        addPrayerWithTooltip(25014, 0, 88, 15, j, 25066, "Activate @lre@Improved Reflexes");
        j++;
        addPrayerWithTooltip(25016, 0, 89, 18, j, 25068, "Activate @lre@Rapid Restore");
        j++;
        addPrayerWithTooltip(25018, 0, 90, 21, j, 25070, "Activate @lre@Rapid Heal");
        j++;
        addPrayerWithTooltip(25020, 0, 91, 24, j, 25072, "Activate @lre@Protect Item");
        j++;
        addPrayerWithTooltip(25022, 0, 603, 25, j, 25074, "Activate @lre@Hawk Eye");
        j++;
        addPrayerWithTooltip(25024, 0, 604, 26, j, 25076, "Activate @lre@Mystic Lore");
        j++;
        addPrayerWithTooltip(25026, 0, 92, 27, j, 25078, "Activate @lre@Steel Skin");
        j++;
        addPrayerWithTooltip(25028, 0, 93, 30, j, 25080, "Activate @lre@Ultimate Strength");
        j++;
        addPrayerWithTooltip(25030, 0, 94, 33, j, 25082, "Activate @lre@Incredible Reflexes");
        j++;
        addPrayerWithTooltip(25032, 0, 95, 36, j, 25084, "Activate @lre@Protect from Magic");
        j++;
        addPrayerWithTooltip(25034, 0, 96, 39, j, 25086, "Activate @lre@Protect from Missles");
        j++;
        addPrayerWithTooltip(25036, 0, 97, 42, j, 25088, "Activate @lre@Protect from Melee");
        j++;
        addPrayerWithTooltip(25038, 0, 605, 43, j, 25090, "Activate @lre@Eagle Eye");
        j++;
        addPrayerWithTooltip(25040, 0, 606, 44, j, 25092, "Activate @lre@Mystic Might");
        j++;
        addPrayerWithTooltip(25042, 0, 98, 45, j, 25094, "Activate @lre@Retribution");
        j++;
        addPrayerWithTooltip(25044, 0, 99, 48, j, 25096, "Activate @lre@Redemption");
        j++;
        addPrayerWithTooltip(25046, 0, 100, 51, j, 25098, "Activate @lre@Smite");
        j++;
        addPrayerWithTooltip(25048, 0, 607, 59, j, 25100, "Activate @lre@Chivalry");
        j++;
        addPrayerWithTooltip(25050, 0, 608, 69, j, 25102, "Activate @lre@Piety");
        j++;
        addTooltip(25052, "Level 01\nThick Skin\nIncreases your Defence by 5%");
        addTooltip(25054, "Level 04\nBurst of Strength\nIncreases your Strength by 5%");
        addTooltip(25056, "Level 07\nClarity of Thought\nIncreases your Attack by 5%");
        addTooltip(25058, "Level 08\nSharp Eye\nIncreases your Ranged by 5%");
        addTooltip(25060, "Level 09\nMystic Will\nIncreases your Magic by 5%");
        addTooltip(25062, "Level 10\nRock Skin\nIncreases your Defence by 10%");
        addTooltip(25064, "Level 13\nSuperhuman Strength\nIncreases your Strength by 10%");
        addTooltip(25066, "Level 16\nImproved Reflexes\nIncreases your Attack by 10%");
        addTooltip(25068, "Level 19\nRapid Restore\n2x restore rate for all stats\nexcept Hitpoints, Summon" +
"ing\nand Prayer"
);
        addTooltip(25070, "Level 22\nRapid Heal\n2x restore rate for the\nHitpoints stat");
        addTooltip(25072, "Level 25\nProtect Item\nKeep 1 extra item if you die");
        addTooltip(25074, "Level 26\nHawk Eye\nIncreases your Ranged by 10%");
        addTooltip(25076, "Level 27\nMystic Lore\nIncreases your Magic by 10%");
        addTooltip(25078, "Level 28\nSteel Skin\nIncreases your Defence by 15%");
        addTooltip(25080, "Level 31\nUltimate Strength\nIncreases your Strength by 15%");
        addTooltip(25082, "Level 34\nIncredible Reflexes\nIncreases your Attack by 15%");
        addTooltip(25084, "Level 37\nProtect from Magic\nProtection from magical attacks");
        addTooltip(25086, "Level 40\nProtect from Missles\nProtection from ranged attacks");
        addTooltip(25088, "Level 43\nProtect from Melee\nProtection from melee attacks");
        addTooltip(25090, "Level 44\nEagle Eye\nIncreases your Ranged by 15%");
        addTooltip(25092, "Level 45\nMystic Might\nIncreases your Magic by 15%");
        addTooltip(25094, "Level 46\nRetribution\nInflicts damage to nearby\ntargets if you die");
        addTooltip(25096, "Level 49\nRedemption\nHeals you when damaged\nand Hitpoints falls\nbelow 10%");
        addTooltip(25098, "Level 52\nSmite\n1/4 of damage dealt is\nalso removed from\nopponent's Prayer");
        addTooltip(25100, "Level 60\nChivalry\nIncreases your Defence by 20%,\nStrength by 18%, and Attack " +
"by\n15%"
);
        addTooltip(25102, "Level 70\nPiety\nIncreases your Defence by 25%,\nStrength by 23%, and Attack by\n" +
"20%"
);
        setChildren(80, rsinterface);
        setBounds(687, 85, 241, i, rsinterface);
        i++;
        setBounds(25105, 65, 241, i, rsinterface);
        i++;
        setBounds(25000, 2, 5, i, rsinterface);
        i++;
        setBounds(25001, 5, 8, i, rsinterface);
        i++;
        setBounds(25002, 40, 5, i, rsinterface);
        i++;
        setBounds(25003, 44, 8, i, rsinterface);
        i++;
        setBounds(25004, 76, 5, i, rsinterface);
        i++;
        setBounds(25005, 79, 11, i, rsinterface);
        i++;
        setBounds(25006, 113, 5, i, rsinterface);
        i++;
        setBounds(25007, 116, 10, i, rsinterface);
        i++;
        setBounds(25008, 150, 5, i, rsinterface);
        i++;
        setBounds(25009, 153, 9, i, rsinterface);
        i++;
        setBounds(25010, 2, 45, i, rsinterface);
        i++;
        setBounds(25011, 5, 48, i, rsinterface);
        i++;
        setBounds(25012, 39, 45, i, rsinterface);
        i++;
        setBounds(25013, 44, 47, i, rsinterface);
        i++;
        setBounds(25014, 76, 45, i, rsinterface);
        i++;
        setBounds(25015, 79, 49, i, rsinterface);
        i++;
        setBounds(25016, 113, 45, i, rsinterface);
        i++;
        setBounds(25017, 116, 50, i, rsinterface);
        i++;
        setBounds(25018, 151, 45, i, rsinterface);
        i++;
        setBounds(25019, 154, 50, i, rsinterface);
        i++;
        setBounds(25020, 2, 82, i, rsinterface);
        i++;
        setBounds(25021, 4, 84, i, rsinterface);
        i++;
        setBounds(25022, 40, 82, i, rsinterface);
        i++;
        setBounds(25023, 44, 87, i, rsinterface);
        i++;
        setBounds(25024, 77, 82, i, rsinterface);
        i++;
        setBounds(25025, 81, 85, i, rsinterface);
        i++;
        setBounds(25026, 114, 83, i, rsinterface);
        i++;
        setBounds(25027, 117, 85, i, rsinterface);
        i++;
        setBounds(25028, 153, 83, i, rsinterface);
        i++;
        setBounds(25029, 156, 87, i, rsinterface);
        i++;
        setBounds(25030, 2, 120, i, rsinterface);
        i++;
        setBounds(25031, 5, 125, i, rsinterface);
        i++;
        setBounds(25032, 40, 120, i, rsinterface);
        i++;
        setBounds(25033, 43, 124, i, rsinterface);
        i++;
        setBounds(25034, 78, 120, i, rsinterface);
        i++;
        setBounds(25035, 83, 124, i, rsinterface);
        i++;
        setBounds(25036, 114, 120, i, rsinterface);
        i++;
        setBounds(25037, 115, 121, i, rsinterface);
        i++;
        setBounds(25038, 151, 120, i, rsinterface);
        i++;
        setBounds(25039, 154, 124, i, rsinterface);
        i++;
        setBounds(25040, 2, 158, i, rsinterface);
        i++;
        setBounds(25041, 5, 160, i, rsinterface);
        i++;
        setBounds(25042, 39, 158, i, rsinterface);
        i++;
        setBounds(25043, 41, 158, i, rsinterface);
        i++;
        setBounds(25044, 76, 158, i, rsinterface);
        i++;
        setBounds(25045, 79, 163, i, rsinterface);
        i++;
        setBounds(25046, 114, 158, i, rsinterface);
        i++;
        setBounds(25047, 116, 158, i, rsinterface);
        i++;
        setBounds(25048, 153, 158, i, rsinterface);
        i++;
        setBounds(25049, 161, 160, i, rsinterface);
        i++;
        setBounds(25050, 2, 196, i, rsinterface);
        i++;
        setBounds(25051, 4, 207, i, rsinterface);
        setBoundry(++i, 25052, byte0 - 2, byte1, rsinterface);
        setBoundry(++i, 25054, byte0 - 5, byte1, rsinterface);
        setBoundry(++i, 25056, byte0, byte1, rsinterface);
        setBoundry(++i, 25058, byte0, byte1, rsinterface);
        setBoundry(++i, 25060, byte0, byte1, rsinterface);
        setBoundry(++i, 25062, byte2 - 9, byte3, rsinterface);
        setBoundry(++i, 25064, byte2 - 11, byte3, rsinterface);
        setBoundry(++i, 25066, byte2, byte3, rsinterface);
        setBoundry(++i, 25068, byte2, byte3, rsinterface);
        setBoundry(++i, 25070, byte2 + 25, byte3, rsinterface);
        setBoundry(++i, 25072, byte4, byte5, rsinterface);
        setBoundry(++i, 25074, byte4 - 2, byte5, rsinterface);
        setBoundry(++i, 25076, byte4, byte5, rsinterface);
        setBoundry(++i, 25078, byte4 - 7, byte5, rsinterface);
        setBoundry(++i, 25080, byte4 - 10, byte5, rsinterface);
        setBoundry(++i, 25082, byte6, c, rsinterface);
        setBoundry(++i, 25084, byte6 - 8, c, rsinterface);
        setBoundry(++i, 25086, byte6 - 7, c, rsinterface);
        setBoundry(++i, 25088, byte6 - 2, c, rsinterface);
        setBoundry(++i, 25090, byte6 - 2, c, rsinterface);
        setBoundry(++i, 25092, byte7, byte8, rsinterface);
        setBoundry(++i, 25094, byte7, byte8 - 20, rsinterface);
        setBoundry(++i, 25096, byte7, byte8 - 25, rsinterface);
        setBoundry(++i, 25098, byte7 + 15, byte8 - 25, rsinterface);
        setBoundry(++i, 25100, byte7 - 12, byte8 - 20, rsinterface);
        setBoundry(++i, 25102, k - 2, byte9, rsinterface);
        i++;
    }
	
	public static void addPrayerWithTooltip(int i, int configId, int configFrame, int requiredValues, int prayerSpriteID, int Hover, String tooltip) {
		RSInterface Interface = addTabInterface(i);
		Interface.id = i;
		Interface.parentID = 5608;
		Interface.type = 5;
		Interface.atActionType = 4;
		Interface.contentType = 0;
		Interface.opacity = 0;
		Interface.hoverType = Hover;
		Interface.sprite1 = imageLoader(0, "Interfaces/PrayerTab/PRAYERGLOW");
		Interface.sprite2 = imageLoader(1, "Interfaces/PrayerTab/PRAYERGLOW");
		Interface.width = 34;
		Interface.height = 34;
		Interface.anIntArray245 = new int[1];
		Interface.anIntArray212 = new int[1];
		Interface.anIntArray245[0] = 1;
		Interface.anIntArray212[0] = configId;
		Interface.valueIndexArray = new int[1][3];
		Interface.valueIndexArray[0][0] = 5;
		Interface.valueIndexArray[0][1] = configFrame;
		Interface.valueIndexArray[0][2] = 0;
		Interface.tooltip = tooltip;
		Interface = addTabInterface(i + 1);
		Interface.id = i + 1;
		Interface.parentID = 5608;
		Interface.type = 5;
		Interface.atActionType = 0;
		Interface.contentType  = 0;
		Interface.opacity = 0;
		Interface.sprite1 = imageLoader(prayerSpriteID, "Interfaces/PrayerTab/PRAYERON");
		Interface.sprite2 = imageLoader(prayerSpriteID, "Interfaces/PrayerTab/PRAYEROFF");
		Interface.width = 34;
		Interface.height = 34;
		Interface.anIntArray245 = new int[1];
		Interface.anIntArray212 = new int[1];
		Interface.anIntArray245[0] = 2;
		Interface.anIntArray212[0] = requiredValues + 1;
		Interface.valueIndexArray = new int[1][3];
		Interface.valueIndexArray[0][0] = 2;
		Interface.valueIndexArray[0][1] = 5;
		Interface.valueIndexArray[0][2] = 0;
	}
	
	public String popupString;
	public static void addTooltipBox(int id, String text) {
		RSInterface rsi = addInterface(id);
		rsi.id = id;
		rsi.parentID = id;
		rsi.type = 8;
		rsi.popupString = text;
	}
	public static void addTooltip(int id, String text) {
		RSInterface rsi = addInterface(id);
		rsi.id = id;
		rsi.type = 0;
		rsi.isMouseoverTriggered = true;
		rsi.mOverInterToTrigger = -1;
		addTooltipBox(id + 1, text);
		rsi.totalChildren(1);
		rsi.child(0, id + 1, 0, 0);
	}

	public static void addPrayer(int i, int configId, int configFrame, int anIntArray212, int spriteID, String prayerName) {
        RSInterface tab = addTabInterface(i);
        tab.id = i;
        tab.parentID = 5608;
        tab.type = 5;
        tab.atActionType = 4;
        tab.contentType = 0;
        tab.aByte254 = 0;
        tab.mOverInterToTrigger = -1;
        tab.sprite1 = imageLoader(0, "PRAYERGLOW");
        tab.sprite2 = imageLoader(1, "PRAYERGLOW");
        tab.width = 34;
        tab.height = 34;
        tab.anIntArray245 = new int[1];
        tab.anIntArray212 = new int[1];
        tab.anIntArray245[0] = 1;
        tab.anIntArray212[0] = configId;
        tab.valueIndexArray = new int[1][3];
        tab.valueIndexArray[0][0] = 5;
        tab.valueIndexArray[0][1] = configFrame;
        tab.valueIndexArray[0][2] = 0;
        tab.tooltip = "Activate@or2@ " + prayerName;
        //tab.tooltip = "Select";
        RSInterface tab2 = addTabInterface(i + 1);
        tab2.id = i + 1;
        tab2.parentID = 5608;
        tab2.type = 5;
        tab2.atActionType = 0;
        tab2.contentType  = 0;
        tab2.aByte254 = 0;
        tab2.mOverInterToTrigger = -1;
        tab2.sprite1 = imageLoader(spriteID, "/PRAYER/PRAYON");
        tab2.sprite2 = imageLoader(spriteID, "/PRAYER/PRAYOFF");
        tab2.width = 34;
        tab2.height = 34;
        tab2.anIntArray245 = new int[1];
        tab2.anIntArray212 = new int[1];
        tab2.anIntArray245[0] = 2;
        tab2.anIntArray212[0] = anIntArray212 + 1;
        tab2.valueIndexArray = new int[1][3];
        tab2.valueIndexArray[0][0] = 2;
        tab2.valueIndexArray[0][1] = 5;
        tab2.valueIndexArray[0][2] = 0;
		//RSInterface tab3 = addTabInterface(i + 50);
    }
	//
	public static void addPrayer(int i, int configId, int configFrame, int requiredValues, int prayerSpriteID, String PrayerName, int Hover) {
		RSInterface Interface = addTabInterface(i);
		Interface.id = i;
		Interface.parentID = 22500;
		Interface.type = 5;
		Interface.atActionType = 4;
		Interface.contentType = 0;
		Interface.aByte254 = 0;
		Interface.mOverInterToTrigger = Hover;
		Interface.sprite1 = imageLoader(0, "Interfaces/CurseTab/GLOW");
		Interface.sprite2 = imageLoader(1, "Interfaces/CurseTab/GLOW");
		Interface.width = 34;
		Interface.height = 34;
		Interface.anIntArray245 = new int[1];
		Interface.anIntArray212 = new int[1];
		Interface.anIntArray245[0] = 1;
		Interface.anIntArray212[0] = configId;
		Interface.valueIndexArray = new int[1][3];
		Interface.valueIndexArray[0][0] = 5;
		Interface.valueIndexArray[0][1] = configFrame;
		Interface.valueIndexArray[0][2] = 0;
		Interface.tooltip = "Activate@or1@ " + PrayerName;
		Interface = addTabInterface(i + 1);
		Interface.id = i + 1;
		Interface.parentID = 22500;
		Interface.type = 5;
		Interface.atActionType = 0;
		Interface.contentType  = 0;
		Interface.aByte254 = 0;
Interface.sprite1 = imageLoader(prayerSpriteID, "Interfaces/CurseTab/PRAYON");
		Interface.sprite2 = imageLoader(prayerSpriteID, "Interfaces/CurseTab/PRAYOFF");
		Interface.width = 34;
		Interface.height = 34;
		Interface.anIntArray245 = new int[1];
		Interface.anIntArray212 = new int[1];
		Interface.anIntArray245[0] = 2;
		Interface.anIntArray212[0] = requiredValues + 1;
		Interface.valueIndexArray = new int[1][3];
		Interface.valueIndexArray[0][0] = 2;
		Interface.valueIndexArray[0][1] = 5;
		Interface.valueIndexArray[0][2] = 0;
	}
	public static void addText(int i, String s,int k, boolean l, boolean m, int a,TextDrawingArea[] TDA, int j, int dsc) {
		RSInterface rsinterface = addTabInterface(i);
		rsinterface.parentID = i;
		rsinterface.id = i;
		rsinterface.type = 4;
		rsinterface.atActionType = 1;
		rsinterface.width = 174;
		rsinterface.height = 11;
		rsinterface.contentType = 0;
		rsinterface.aByte254 = 0;
		rsinterface.mOverInterToTrigger = a;
		rsinterface.centerText = l;
		rsinterface.textShadow = m;
		rsinterface.textDrawingAreas = TDA[j];
		rsinterface.message = s;
		rsinterface.aString228 = "";
		rsinterface.anInt219 = 0;
		rsinterface.textColor = k;
		rsinterface.anInt216 = dsc;
		rsinterface.tooltip = s;
	}
	public static void setBounds(int ID, int X, int Y, int frame, RSInterface RSinterface){
		RSinterface.children[frame] = ID;
		RSinterface.childX[frame] = X;
		RSinterface.childY[frame] = Y;
	}

	public static void setChildren(int total,RSInterface i){
		i.children = new int[total];
		i.childX = new int[total];
		i.childY = new int[total];
	}

	public static void addTooltip(int id, String text, int H, int W) {
		RSInterface rsi = addTabInterface(id);
		rsi.id = id;
		rsi.type = 0;
		rsi.isMouseoverTriggered = true;
		rsi.mOverInterToTrigger = -1;
		addTooltipBox(id + 1, text);
		rsi.totalChildren(1);
		rsi.child(0, id + 1, 0, 0);
		rsi.height = H;
		rsi.width = W;
	}
	//
	public static void friendsTab(TextDrawingArea[] tda) {
        RSInterface tab = addTabInterface(5065);
        RSInterface list = interfaceCache[5066];
        addText(5067, "Friends List", tda, 1, 0xff9933, true, true);
        addText(5070, "Add Friend", tda, 0, 0xff9933, false, true);
        addText(5071, "Delete Friend", tda, 0, 0xff9933, false, true);
        addSprite(16126, 4, "Custom/Friends/SPRITE");
        addSprite(16127, 8, "Custom/Friends/SPRITE");
        addHoverButton(5068, "Custom/Friends/SPRITE", 6, 72, 32, "Add Friend", 201, 5072, 1);
        addHoveredButton(5072, "Custom/Friends/SPRITE", 7, 72, 32, 5073);
        addHoverButton(5069, "Custom/Friends/SPRITE", 6, 72, 32, "Delete Friend", 202, 5074, 1);
        addHoveredButton(5074, "Custom/Friends/SPRITE", 7, 72, 32, 5075);
        tab.totalChildren(11);
        tab.child(0, 5067, 95, 4);
        tab.child(1, 16127, 0, 25);
        tab.child(2, 16126, 0, 221);
        tab.child(3, 5066, 0, 24);
        tab.child(4, 16126, 0, 22);
        tab.child(5, 5068, 15, 226);
        tab.child(6, 5072, 15, 226);
        tab.child(7, 5069, 103, 226);
        tab.child(8, 5074, 103, 226);
        tab.child(9, 5070, 25, 237);
        tab.child(10, 5071, 106, 237);
        list.height = 196; list.width = 174;
        for(int id = 5092, i = 0; id <= 5191 && i <= 99; id++, i++) {
            list.children[i] = id; list.childX[i] = 3; list.childY[i] = list.childY[i] - 7;
        } for(int id = 5192, i = 100; id <= 5291 && i <= 199; id++, i++) {
            list.children[i] = id; list.childX[i] = 131; list.childY[i] = list.childY[i] - 7;
        }
    }

    public static void ignoreTab(TextDrawingArea[] tda) {
        RSInterface tab = addTabInterface(5715);
        RSInterface list = interfaceCache[5716];
        addText(5717, "Ignore List", tda, 1, 0xff9933, true, true);
        addText(5720, "Add Name", tda, 0, 0xff9933, false, true);
        addText(5721, "Delete Name", tda, 0, 0xff9933, false, true);
        addHoverButton(5718, "Custom/Friends/SPRITE", 6, 72, 32, "Add Name", 501, 5722, 1);
        addHoveredButton(5722, "Custom/Friends/SPRITE", 7, 72, 32, 5723);
        addHoverButton(5719, "Custom/Friends/SPRITE", 6, 72, 32, "Delete Name", 502, 5724, 1);
        addHoveredButton(5724, "Custom/Friends/SPRITE", 7, 72, 32, 5725);
        tab.totalChildren(11);
        tab.child(0, 5717, 95, 4);
        tab.child(1, 16127, 0, 25);
        tab.child(2, 16126, 0, 221);
        tab.child(3, 5716, 0, 24);
        tab.child(4, 16126, 0, 22);
        tab.child(5, 5718, 15, 226);
        tab.child(6, 5722, 15, 226);
        tab.child(7, 5719, 103, 226);
        tab.child(8, 5724, 103, 226);
        tab.child(9, 5720, 27, 237);
        tab.child(10, 5721, 108, 237);
        list.height = 196;
        list.width = 174;
        for(int id = 5742, i = 0; id <= 5841 && i <= 99; id++, i++) {
            list.children[i] = id; list.childX[i] = 3; list.childY[i] = list.childY[i] - 7;
        }
    }

		private static Sprite CustomSpriteLoader(int id, String s)
    {
        long l = (TextClass.method585(s) << 8) + (long)id;
        Sprite sprite = (Sprite)aMRUNodes_238.insertFromCache(l);
        if(sprite != null) { return sprite; }
        try {
            sprite = new Sprite("Interfaces/Attack/"+id + s);
            aMRUNodes_238.removeFromCache(sprite, l);
        } catch(Exception exception) { 
			return null; }
        return sprite;
    }

    public static RSInterface addInterface(int id)
    {
        RSInterface rsi = interfaceCache[id] = new RSInterface();
        rsi.id = id;
        rsi.parentID = id;
        rsi.width = 512;
        rsi.height = 334;
        return rsi;
    }

    public static void addText(int id, String text, TextDrawingArea tda[], int idx, int color, boolean centered) {
        RSInterface rsi = interfaceCache[id] = new RSInterface();
        if(centered)
          rsi.centerText = true;
        rsi.textShadow = true;
        rsi.textDrawingAreas = tda[idx];
        rsi.message = text;
        rsi.textColor = color;
        rsi.id = id;
        rsi.type = 4;
    }

    public static void textColor(int id, int color)
    { RSInterface rsi = interfaceCache[id]; rsi.textColor = color; }

    public static void textSize(int id, TextDrawingArea tda[], int idx)
    { RSInterface rsi = interfaceCache[id]; rsi.textDrawingAreas = tda[idx]; }

    public static void addCacheSprite(int id, int sprite1, int sprite2, String sprites)
    {
        RSInterface rsi = interfaceCache[id] = new RSInterface();
        rsi.sprite1 = method207(sprite1, aClass44, sprites);
        rsi.sprite2 = method207(sprite2, aClass44, sprites);
        rsi.parentID = id;
        rsi.id = id;
        rsi.type = 5;
    }
    
    public static void sprite1(int id, int sprite)
    { RSInterface class9 = interfaceCache[id];
        class9.sprite1 = CustomSpriteLoader(sprite, "");
    }

    public static void addActionButton(int id, int sprite, int sprite2, int width, int height, String s)
    {
        RSInterface rsi = interfaceCache[id] = new RSInterface();
        rsi.sprite1 = CustomSpriteLoader(sprite, "");
        if (sprite2 == sprite)
          rsi.sprite2 = CustomSpriteLoader(sprite, "a");
        else
          rsi.sprite2 = CustomSpriteLoader(sprite2, "");
        rsi.tooltip = s;
        rsi.contentType = 0;
        rsi.atActionType = 1;
        rsi.width = width;
        rsi.mOverInterToTrigger = 52;
        rsi.parentID = id;
        rsi.id = id;
        rsi.type = 5;
        rsi.height = height;
    }

    public static void addToggleButton(int id, int sprite, int setconfig, int width, int height, String s)
    {
        RSInterface rsi = addInterface(id);
        rsi.sprite1 = CustomSpriteLoader(sprite, "");
        rsi.sprite2 = CustomSpriteLoader(sprite, "a");
        rsi.anIntArray212 = new int[1];
        rsi.anIntArray212[0] = 1;
        rsi.anIntArray245 = new int[1];
        rsi.anIntArray245[0] = 1;
        rsi.valueIndexArray = new int[1][3];
        rsi.valueIndexArray[0][0] = 5;
        rsi.valueIndexArray[0][1] = setconfig;
        rsi.valueIndexArray[0][2] = 0;
        rsi.atActionType = 4;
        rsi.width = width;
        rsi.mOverInterToTrigger = -1;
        rsi.parentID = id;
        rsi.id = id;
        rsi.type = 5;
        rsi.height = height;
        rsi.tooltip = s;
    }

    public void totalChildren(int id, int x, int y)
    { children = new int[id]; childX = new int[x]; childY = new int[y]; }

    public static void removeSomething(int id)
    { RSInterface rsi = interfaceCache[id] = new RSInterface(); }
	
	public void specialBar(int id) //7599
    {
        /*addActionButton(ID, SpriteOFF, SpriteON, Width, Height, "SpriteText");*/
            addActionButton(id-12, 7587, -1, 150, 26, "Use @gre@Special Attack");
        /*removeSomething(ID);*/
        for (int i = id-11; i < id; i++)
            removeSomething(i);

        RSInterface rsi = interfaceCache[id-12];
            rsi.width = 150;
            rsi.height = 26;

        rsi = interfaceCache[id];
            rsi.width = 150;
            rsi.height = 26;

            rsi.child(0, id-12, 0, 0);

            rsi.child(12, id+1, 3, 7);

            rsi.child(23, id+12, 16, 8);

        for (int i = 13; i < 23; i++) {
            rsi.childY[i] -= 1;
        }

        rsi = interfaceCache[id+1];
            rsi.type = 5;
            rsi.sprite1 = CustomSpriteLoader(7600, "");

        for (int i = id+2; i < id+12; i++) {
        rsi = interfaceCache[i];
            rsi.type = 5;
        }

        sprite1(id+2, 7601);sprite1(id+3, 7602);
        sprite1(id+4, 7603);sprite1(id+5, 7604);
        sprite1(id+6, 7605);sprite1(id+7, 7606);
        sprite1(id+8, 7607);sprite1(id+9, 7608);
        sprite1(id+10, 7609);sprite1(id+11, 7610);
    }

    public static void Sidebar0(TextDrawingArea[] tda)
    {
        /*Sidebar0a(id, id2, id3, "text1", "text2", "text3", "text4", str1x, str1y, str2x, str2y, str3x, str3y, str4x, str4y, img1x, img1y, img2x, img2y, img3x, img3y, img4x, img4y, tda);*/
            Sidebar0a(1698, 1701, 7499, "Chop", "Hack", "Smash", "Block", 42, 75, 127, 75, 39, 128, 125, 128, 122, 103, 40, 50, 122, 50, 40, 103, tda);
            Sidebar0a(2276, 2279, 7574, "Stab", "Lunge", "Slash", "Block", 43, 75, 124, 75, 41, 128, 125, 128, 122, 103, 40, 50, 122, 50, 40, 103, tda);
            Sidebar0a(2423, 2426, 7599, "Chop", "Slash", "Lunge", "Block", 42, 75, 125, 75, 40, 128, 125, 128, 122, 103, 40, 50, 122, 50, 40, 103, tda);
            Sidebar0a(3796, 3799, 7624, "Pound", "Pummel", "Spike", "Block", 39, 75, 121, 75, 41, 128, 125, 128, 122, 103, 40, 50, 122, 50, 40, 103, tda);
            Sidebar0a(4679, 4682, 7674, "Lunge", "Swipe", "Pound", "Block", 40, 75, 124, 75, 39, 128, 125, 128, 122, 103, 40, 50, 122, 50, 40, 103, tda);
            Sidebar0a(4705, 4708, 7699, "Chop", "Slash", "Smash", "Block", 42, 75, 125, 75, 39, 128, 125, 128, 122, 103, 40, 50, 122, 50, 40, 103, tda);
            Sidebar0a(5570, 5573, 7724, "Spike", "Impale", "Smash", "Block", 41, 75, 123, 75, 39, 128, 125, 128, 122, 103, 40, 50, 122, 50, 40, 103, tda);
            Sidebar0a(7762, 7765, 7800, "Chop", "Slash", "Lunge", "Block", 42, 75, 125, 75, 40, 128, 125, 128, 122, 103, 40, 50, 122, 50, 40, 103, tda);
        /*Sidebar0b(id, id2, "text1", "text2", "text3", "text4", str1x, str1y, str2x, str2y, str3x, str3y, str4x, str4y, img1x, img1y, img2x, img2y, img3x, img3y, img4x, img4y, tda);*/
            Sidebar0b(776, 779, "Reap", "Chop", "Jab", "Block", 42, 75, 126, 75, 46, 128, 125, 128, 122, 103, 122, 50, 40, 103, 40, 50, tda);
        /*Sidebar0c(id, id2, id3, "text1", "text2", "text3", str1x, str1y, str2x, str2y, str3x, str3y, img1x, img1y, img2x, img2y, img3x, img3y, tda);*/
            Sidebar0c(425, 428, 7474, "Pound", "Pummel", "Block", 39, 75, 121, 75, 42, 128, 40, 103, 40, 50, 122, 50, tda);
            Sidebar0c(1749, 1752, 7524, "Accurate", "Rapid", "Longrange", 33, 75, 125, 75, 29, 128, 40, 103, 40, 50, 122, 50, tda);
            Sidebar0c(1764, 1767, 7549, "Accurate", "Rapid", "Longrange", 33, 75, 125, 75, 29, 128, 40, 103, 40, 50, 122, 50, tda);
            Sidebar0c(4446, 4449, 7649, "Accurate", "Rapid", "Longrange", 33, 75, 125, 75, 29, 128, 40, 103, 40, 50, 122, 50, tda);
            Sidebar0c(5855, 5857, 7749, "Punch", "Kick", "Block", 40, 75, 129, 75, 42, 128, 40, 50, 122, 50, 40, 103, tda);
            Sidebar0c(6103, 6132, 6117, "Bash", "Pound", "Block", 43, 75, 124, 75, 42, 128, 40, 103, 40, 50, 122, 50, tda);
            Sidebar0c(8460, 8463, 8493, "Jab", "Swipe", "Fend", 46, 75, 124, 75, 43, 128, 40, 103, 40, 50, 122, 50, tda);
            Sidebar0c(12290, 12293, 12323, "Flick", "Lash", "Deflect", 44, 75, 127, 75, 36, 128, 40, 50, 40, 103, 122, 50, tda);
        /*Sidebar0d(id, id2, "text1", "text2", "text3", str1x, str1y, str2x, str2y, str3x, str3y, img1x, img1y, img2x, img2y, img3x, img3y, tda);*/
            Sidebar0d(328, 331, "Bash", "Pound", "Focus", 42, 66, 39, 101, 41, 136, 40, 120, 40, 50, 40, 85, tda);

        RSInterface rsi = addInterface(19300);
        /*textSize(ID, wid, Size);*/
            textSize(3983, tda, 0);
        /*addToggleButton(id, sprite, config, width, height, wid);*/
            addToggleButton(150, 150, 172, 150, 44, "Auto Retaliate");

        rsi.totalChildren(2, 2, 2);
            rsi.child(0, 3983, 52, 25); //combat level
            rsi.child(1, 150, 21, 153); //auto retaliate

        rsi = interfaceCache[3983];
            rsi.centerText = true;
            rsi.textColor = 0xff981f;
    }

    public static void Sidebar0a(int id, int id2, int id3, String text1, String text2, String text3, String text4,
                                               int str1x, int str1y, int str2x, int str2y, int str3x, int str3y, int str4x, int str4y,
                                               int img1x, int img1y, int img2x, int img2y, int img3x, int img3y, int img4x, int img4y, TextDrawingArea[] tda) //4button spec
    {
        RSInterface rsi = addInterface(id); //2423
        /*addText(ID, "Text", tda, Size, Colour, Centered);*/
            addText(id2, "-2", tda, 3, 0xff981f, true); //2426
            addText(id2+11, text1, tda, 0, 0xff981f, false);
            addText(id2+12, text2, tda, 0, 0xff981f, false);
            addText(id2+13, text3, tda, 0, 0xff981f, false);
            addText(id2+14, text4, tda, 0, 0xff981f, false);
        /*specialBar(ID);*/
            rsi.specialBar(id3); //7599

            rsi.width = 190;
            rsi.height = 261;

        int last = 15; int frame = 0;
        rsi.totalChildren(last, last, last);

            rsi.child(frame, id2+3, 21, 46); frame++; //2429
            rsi.child(frame, id2+4, 104, 99); frame++; //2430
            rsi.child(frame, id2+5, 21, 99); frame++; //2431
            rsi.child(frame, id2+6, 105, 46); frame++; //2432

            rsi.child(frame, id2+7, img1x, img1y); frame++; //bottomright 2433
            rsi.child(frame, id2+8, img2x, img2y); frame++; //topleft 2434
            rsi.child(frame, id2+9, img3x, img3y); frame++; //bottomleft 2435
            rsi.child(frame, id2+10, img4x, img4y); frame++; //topright 2436

            rsi.child(frame, id2+11, str1x, str1y); frame++; //chop 2437
            rsi.child(frame, id2+12, str2x, str2y); frame++; //slash 2438
            rsi.child(frame, id2+13, str3x, str3y); frame++; //lunge 2439
            rsi.child(frame, id2+14, str4x, str4y); frame++; //block 2440

            rsi.child(frame, 19300, 0, 0); frame++; //stuffs
            rsi.child(frame, id2, 94, 4); frame++; //weapon 2426
            rsi.child(frame, id3, 21, 205); frame++; //special attack 7599

        for (int i = id2+3; i < id2+7; i++) { //2429 - 2433
        rsi = interfaceCache[i];
            rsi.sprite1 = CustomSpriteLoader(19301, ""); rsi.sprite2 = CustomSpriteLoader(19301, "a");
            rsi.width = 68; rsi.height = 44;
        }
    }

    public static void Sidebar0b(int id, int id2, String text1, String text2, String text3, String text4,
                                               int str1x, int str1y, int str2x, int str2y, int str3x, int str3y, int str4x, int str4y,
                                               int img1x, int img1y, int img2x, int img2y, int img3x, int img3y, int img4x, int img4y, TextDrawingArea[] tda) //4button nospec
    {
        RSInterface rsi = addInterface(id); //2423
        /*addText(ID, "Text", tda, Size, Colour, Centered);*/
            addText(id2, "-2", tda, 3, 0xff981f, true); //2426
            addText(id2+11, text1, tda, 0, 0xff981f, false);
            addText(id2+12, text2, tda, 0, 0xff981f, false);
            addText(id2+13, text3, tda, 0, 0xff981f, false);
            addText(id2+14, text4, tda, 0, 0xff981f, false);

            rsi.width = 190;
            rsi.height = 261;

        int last = 14; int frame = 0;
        rsi.totalChildren(last, last, last);

            rsi.child(frame, id2+3, 21, 46); frame++; //2429
            rsi.child(frame, id2+4, 104, 99); frame++; //2430
            rsi.child(frame, id2+5, 21, 99); frame++; //2431
            rsi.child(frame, id2+6, 105, 46); frame++; //2432

            rsi.child(frame, id2+7, img1x, img1y); frame++; //bottomright 2433
            rsi.child(frame, id2+8, img2x, img2y); frame++; //topleft 2434
            rsi.child(frame, id2+9, img3x, img3y); frame++; //bottomleft 2435
            rsi.child(frame, id2+10, img4x, img4y); frame++; //topright 2436

            rsi.child(frame, id2+11, str1x, str1y); frame++; //chop 2437
            rsi.child(frame, id2+12, str2x, str2y); frame++; //slash 2438
            rsi.child(frame, id2+13, str3x, str3y); frame++; //lunge 2439
            rsi.child(frame, id2+14, str4x, str4y); frame++; //block 2440

            rsi.child(frame, 19300, 0, 0); frame++; //stuffs
            rsi.child(frame, id2, 94, 4); frame++; //weapon 2426

        for (int i = id2+3; i < id2+7; i++) { //2429 - 2433
        rsi = interfaceCache[i];
            rsi.sprite1 = CustomSpriteLoader(19301, ""); rsi.sprite2 = CustomSpriteLoader(19301, "a");
            rsi.width = 68; rsi.height = 44;
        }
    }

    public static void Sidebar0c(int id, int id2, int id3, String text1, String text2, String text3,
                                               int str1x, int str1y, int str2x, int str2y, int str3x, int str3y,
                                               int img1x, int img1y, int img2x, int img2y, int img3x, int img3y, TextDrawingArea[] tda) //3button spec
    {
        RSInterface rsi = addInterface(id); //2423
        /*addText(ID, "Text", tda, Size, Colour, Centered);*/
            addText(id2, "-2", tda, 3, 0xff981f, true); //2426
            addText(id2+9, text1, tda, 0, 0xff981f, false);
            addText(id2+10, text2, tda, 0, 0xff981f, false);
            addText(id2+11, text3, tda, 0, 0xff981f, false);
        /*specialBar(ID);*/
            rsi.specialBar(id3); //7599

            rsi.width = 190;
            rsi.height = 261;

        int last = 12; int frame = 0;
        rsi.totalChildren(last, last, last);

            rsi.child(frame, id2+3, 21, 99); frame++;
            rsi.child(frame, id2+4, 105, 46); frame++;
            rsi.child(frame, id2+5, 21, 46); frame++;

            rsi.child(frame, id2+6, img1x, img1y); frame++; //topleft
            rsi.child(frame, id2+7, img2x, img2y); frame++; //bottomleft
            rsi.child(frame, id2+8, img3x, img3y); frame++; //topright

            rsi.child(frame, id2+9, str1x, str1y); frame++; //chop
            rsi.child(frame, id2+10, str2x, str2y); frame++; //slash
            rsi.child(frame, id2+11, str3x, str3y); frame++; //lunge

            rsi.child(frame, 19300, 0, 0); frame++; //stuffs
            rsi.child(frame, id2, 94, 4); frame++; //weapon
            rsi.child(frame, id3, 21, 205); frame++; //special attack 7599

        for (int i = id2+3; i < id2+6; i++) {
        rsi = interfaceCache[i];
            rsi.sprite1 = CustomSpriteLoader(19301, ""); rsi.sprite2 = CustomSpriteLoader(19301, "a");
            rsi.width = 68; rsi.height = 44;
        }
    }

    public static void Sidebar0d(int id, int id2, String text1, String text2, String text3,
                                               int str1x, int str1y, int str2x, int str2y, int str3x, int str3y,
                                               int img1x, int img1y, int img2x, int img2y, int img3x, int img3y, TextDrawingArea[] tda) //3button nospec (magic intf)
    {
        RSInterface rsi = addInterface(id); //2423
        /*addText(ID, "Text", tda, Size, Colour, Centered);*/
            addText(id2, "-2", tda, 3, 0xff981f, true); //2426
            addText(id2+9, text1, tda, 0, 0xff981f, false);
            addText(id2+10, text2, tda, 0, 0xff981f, false);
            addText(id2+11, text3, tda, 0, 0xff981f, false);

            //addText(353, "Spell", tda, 0, 0xff981f, false);
			removeSomething(353);
            addText(354, "Spell", tda, 0, 0xff981f, false);

            addCacheSprite(337, 19, 0, "combaticons");
            addCacheSprite(338, 13, 0, "combaticons2");
            addCacheSprite(339, 14, 0, "combaticons2");

        /*addToggleButton(id, sprite, config, width, height, tooltip);*/
            //addToggleButton(349, 349, 108, 68, 44, "Select");
			removeSomething(349);
            addToggleButton(350, 350, 108, 68, 44, "Select");

            rsi.width = 190;
            rsi.height = 261;

			int last = 15; int frame = 0;
			rsi.totalChildren(last, last, last);

            rsi.child(frame, id2+3, 20, 115); frame++;
            rsi.child(frame, id2+4, 20, 80); frame++;
            rsi.child(frame, id2+5, 20, 45); frame++;

            rsi.child(frame, id2+6, img1x, img1y); frame++; //topleft
            rsi.child(frame, id2+7, img2x, img2y); frame++; //bottomleft
            rsi.child(frame, id2+8, img3x, img3y); frame++; //topright

            rsi.child(frame, id2+9, str1x, str1y); frame++; //bash
            rsi.child(frame, id2+10, str2x, str2y); frame++; //pound
            rsi.child(frame, id2+11, str3x, str3y); frame++; //focus

            rsi.child(frame, 349, 105, 46); frame++; //spell1
            rsi.child(frame, 350, 104, 106); frame++; //spell2

            rsi.child(frame, 353, 125, 74); frame++; //spell
            rsi.child(frame, 354, 125, 134); frame++; //spell

            rsi.child(frame, 19300, 0, 0); frame++; //stuffs
            rsi.child(frame, id2, 94, 4); frame++; //weapon
    }
	
	public static void emoteTab() {
        RSInterface tab = addTabInterface(147);
        RSInterface scroll = addTabInterface(148);
        tab.totalChildren(1);
        tab.child(0, 148, 0, 1);
        addButton(168, 0, "Custom/Emotes/EMOTE", "Yes",41,47);
        addButton(169, 1, "Custom/Emotes/EMOTE", "No",41,47);
        addButton(164, 2, "Custom/Emotes/EMOTE", "Bow",41,47);
        addButton(165, 3, "Custom/Emotes/EMOTE", "Angry",41,47);
        addButton(162, 4, "Custom/Emotes/EMOTE", "Think",41,47);
        addButton(163, 5, "Custom/Emotes/EMOTE", "Wave",41,47);
        addButton(13370, 6, "Custom/Emotes/EMOTE", "Shrug",41,47);
        addButton(171, 7, "Custom/Emotes/EMOTE", "Cheer",41,47);
        addButton(167, 8, "Custom/Emotes/EMOTE", "Beckon",41,47);
        addButton(170, 9, "Custom/Emotes/EMOTE", "Laugh",41,47);
        addButton(13366, 10, "Custom/Emotes/EMOTE", "Jump for Joy",41,47);
        addButton(13368, 11, "Custom/Emotes/EMOTE", "Yawn",41,47);
        addButton(166, 12, "Custom/Emotes/EMOTE", "Dance",41,47);
        addButton(13363, 13, "Custom/Emotes/EMOTE", "Jig",41,47);
        addButton(13364, 14, "Custom/Emotes/EMOTE", "Spin",41,47);
        addButton(13365, 15, "Custom/Emotes/EMOTE", "Headbang",41,47);
        addButton(161, 16, "Custom/Emotes/EMOTE", "Cry",41,47);
        addButton(11100, 17, "Custom/Emotes/EMOTE", "Blow kiss",41,47);
        addButton(13362, 18, "Custom/Emotes/EMOTE", "Panic",41,47);
        addButton(13367, 19, "Custom/Emotes/EMOTE", "Raspberry",41,47);
        addButton(172, 20, "Custom/Emotes/EMOTE", "Clap",41,47);
        addButton(13369, 21, "Custom/Emotes/EMOTE", "Salute",41,47);
        addButton(13383, 22, "Custom/Emotes/EMOTE", "Goblin Bow",41,47);
        addButton(13384, 23, "Custom/Emotes/EMOTE", "Goblin Salute",41,47);
        addButton(667, 24, "Custom/Emotes/EMOTE", "Glass Box",41,47);
        addButton(6503, 25, "Custom/Emotes/EMOTE", "Climb Rope",41,47);
        addButton(6506, 26, "Custom/Emotes/EMOTE", "Lean On Air",41,47);
        addButton(666, 27, "Custom/Emotes/EMOTE", "Glass Wall",41,47);
        addButton(18464, 28, "Custom/Emotes/EMOTE", "Zombie Walk",41,47);
        addButton(18465, 29, "Custom/Emotes/EMOTE", "Zombie Dance",41,47);
        addButton(15166, 30, "Custom/Emotes/EMOTE", "Scared",41,47);
        addButton(18686, 31, "Custom/Emotes/EMOTE", "Rabbit Hop",41,47);
        addConfigButton(154, 147, 32, 33, "/Emotes/EMOTE", 41, 47, "Skillcape Emote", 0, 1, 700);
        scroll.totalChildren(33);
        scroll.child(0, 168, 10, 7);
        scroll.child(1, 169, 54, 7);
        scroll.child(2, 164, 98, 14);
        scroll.child(3, 165, 137, 7);
        scroll.child(4, 162, 9, 56);
        scroll.child(5, 163, 48, 56);
        scroll.child(6, 13370, 95, 56);
        scroll.child(7, 171, 137, 56);
        scroll.child(8, 167, 7, 105);
        scroll.child(9, 170, 51, 105);
        scroll.child(10, 13366, 95, 104);
        scroll.child(11, 13368, 139, 105);
        scroll.child(12, 166, 6, 154);
        scroll.child(13, 13363, 50, 154);
        scroll.child(14, 13364, 90, 154);
        scroll.child(15, 13365, 135, 154);
        scroll.child(16, 161, 8, 204);
        scroll.child(17, 11100, 51, 203);
        scroll.child(18, 13362, 99, 204);
        scroll.child(19, 13367, 137, 203);
        scroll.child(20, 172, 10, 253);
        scroll.child(21, 13369, 53, 253);
        scroll.child(22, 13383, 88, 258);
        scroll.child(23, 13384, 138, 252);
        scroll.child(24, 667, 2, 303);
        scroll.child(25, 6503, 49, 302);
        scroll.child(26, 6506, 93, 302);
        scroll.child(27, 666, 137, 302);
        scroll.child(28, 18464, 9, 352);
        scroll.child(29, 18465, 50, 352);
        scroll.child(30, 15166, 94, 356);
        scroll.child(31, 18686, 141, 353);
        scroll.child(32, 154, 5, 401);
        scroll.width = 173; scroll.height = 258; scroll.scrollMax = 450;
    }
	
	public static void optionTab(TextDrawingArea[] tda) {
        RSInterface tab = addTabInterface(904);
        RSInterface energy = interfaceCache[149];
        energy.textColor = 0xff9933;
        addSprite(905, 9, "Custom/Options/SPRITE");
        addSprite(907, 18, "Custom/Options/SPRITE");
        addSprite(909, 29, "Custom/Options/SPRITE");
        addSprite(951, 32, "Custom/Options/SPRITE");
        addSprite(953, 33, "Custom/Options/SPRITE");
        addSprite(955, 34, "Custom/Options/SPRITE");
        addSprite(947, 36, "Custom/Options/SPRITE");
        addSprite(949, 35, "Custom/Options/SPRITE");
		//run button here
        addConfigButton(152, 904, 30, 31, "Custom/Options/SPRITE", 40, 40, "Toggle-run", 1, 5, 173);
        addConfigButton(906, 904, 10, 14, "Custom/Options/SPRITE", 32, 16, "Dark", 1, 5, 166);
        addConfigButton(908, 904, 11, 15, "Custom/Options/SPRITE", 32, 16, "Normal", 2, 5, 166);
        addConfigButton(910, 904, 12, 16, "Custom/Options/SPRITE", 32, 16, "Bright", 3, 5, 166);
        addConfigButton(912, 904, 13, 17, "Custom/Options/SPRITE", 32, 16, "Very Bright", 4, 5, 166);
        addConfigButton(930, 904, 19, 24, "Custom/Options/SPRITE", 26, 16, "Music Off", 4, 5, 168);
        addConfigButton(931, 904, 20, 25, "Custom/Options/SPRITE", 26, 16, "Music Level-1", 3, 5, 168);
        addConfigButton(932, 904, 21, 26, "Custom/Options/SPRITE", 26, 16, "Music Level-2", 2, 5, 168);
        addConfigButton(933, 904, 22, 27, "Custom/Options/SPRITE", 26, 16, "Music Level-3", 1, 5, 168);
        addConfigButton(934, 904, 23, 28, "Custom/Options/SPRITE", 24, 16, "Music Level-4", 0, 5, 168);
        addConfigButton(941, 904, 19, 24, "Custom/Options/SPRITE", 26, 16, "Sound Effects Off", 4, 5, 169);
        addConfigButton(942, 904, 20, 25, "Custom/Options/SPRITE", 26, 16, "Sound Effects Level-1", 3, 5, 169);
        addConfigButton(943, 904, 21, 26, "Custom/Options/SPRITE", 26, 16, "Sound Effects Level-2", 2, 5, 169);
        addConfigButton(944, 904, 22, 27, "Custom/Options/SPRITE", 26, 16, "Sound Effects Level-3", 1, 5, 169);
        addConfigButton(945, 904, 23, 28, "Custom/Options/SPRITE", 24, 16, "Sound Effects Level-4", 0, 5, 169);
        addConfigButton(913, 904, 30, 31, "Custom/Options/SPRITE", 40, 40, "Toggle-Mouse Buttons", 0, 5, 170);
        addConfigButton(915, 904, 30, 31, "Custom/Options/SPRITE", 40, 40, "Toggle-Chat Effects", 0, 5, 171);
        addConfigButton(957, 904, 30, 31, "Custom/Options/SPRITE", 40, 40, "Toggle-Split Private Chat", 1, 5, 287);
        addConfigButton(12464, 904, 30, 31, "Custom/Options/SPRITE", 40, 40, "Toggle-Accept Aid", 0, 5, 427);
        tab.totalChildren(28);
        int x = 0;
        int y = 2;
        tab.child(0, 905, 13 + x, 10 + y);
        tab.child(1, 906, 48 + x, 18 + y);
        tab.child(2, 908, 80 + x, 18 + y);
        tab.child(3, 910, 112 + x, 18 + y);
        tab.child(4, 912, 144 + x, 18 + y);
        tab.child(5, 907, 14 + x, 55 + y);
        tab.child(6, 930, 49 + x, 61 + y);
        tab.child(7, 931, 75 + x, 61 + y);
        tab.child(8, 932, 101 + x, 61 + y);
        tab.child(9, 933, 127 + x, 61 + y);
        tab.child(10, 934, 151 + x, 61 + y);
        tab.child(11, 909, 13 + x, 99 + y);
        tab.child(12, 941, 49 + x, 104 + y);
        tab.child(13, 942, 75 + x, 104 + y);
        tab.child(14, 943, 101 + x, 104 + y);
        tab.child(15, 944, 127 + x, 104 + y);
        tab.child(16, 945, 151 + x, 104 + y);
        tab.child(17, 913, 15, 153);
        tab.child(18, 955, 19, 159);
        tab.child(19, 915, 75, 153);
        tab.child(20, 953, 79, 160);
        tab.child(21, 957, 135, 153);
        tab.child(22, 951, 139, 159);
        tab.child(23, 12464, 15, 208);
        tab.child(24, 949, 20, 213);
        tab.child(25, 152, 75, 208);
        tab.child(26, 947, 87, 212);
        tab.child(27, 149, 80, 231);
    }
	
	public static void clanChatTab(TextDrawingArea[] tda) {
        RSInterface tab = addTabInterface(18128);
        addHoverButton(18129, "Custom/Clan Chat/SPRITE", 6, 72, 32, "Join Chat", 550, 18130, 1);
        addHoveredButton(18130, "Custom/Clan Chat/SPRITE", 7, 72, 32, 18131);
        addHoverButton(18132, "Custom/Clan Chat/SPRITE", 6, 72, 32, "Leave Chat", -1, 18133, 5);
        addHoveredButton(18133, "Custom/Clan Chat/SPRITE", 7, 72, 32, 18134);
		addButton(18250, 0, "Custom/Clan Chat/Lootshare", "Toggle lootshare");
        addText(18135, "Join Chat", tda, 0, 0xff9b00, true, true);
        addText(18136, "Leave Chat", tda, 0, 0xff9b00, true, true);
        addSprite(18137, 37, "Custom/Clan Chat/SPRITE");
        addText(18138, "Clan Chat", tda, 2, 0xff9b00, true, true);
        addText(18139, "Talking in: Not in chat", tda, 0, 0xff9b00, false, true);
        addText(18140, "Owner: None", tda, 0, 0xff9b00, false, true);
        tab.totalChildren(14);
        tab.child(0, 16126, 0, 221);
        tab.child(1, 16126, 0, 59);
        tab.child(2, 18137, 0, 62);
        tab.child(3, 18143, 0, 62);
        tab.child(4, 18129, 15, 226);
        tab.child(5, 18130, 15, 226);
        tab.child(6, 18132, 103, 226);
        tab.child(7, 18133, 103, 226);
        tab.child(8, 18135, 51, 237);
        tab.child(9, 18136, 139, 237);
        tab.child(10, 18138, 95, 1);
        tab.child(11, 18139, 10, 23);
        tab.child(12, 18140, 25, 38);
		tab.child(13, 18250, 145,15);
        /* Text area */
        RSInterface list = addTabInterface(18143);
        list.totalChildren(100);
        for(int i = 18144; i <= 18244; i++) {
            addText(i, "", tda, 0, 0xffffff, false, true);
        }
        for(int id = 18144, i = 0; id <= 18243 && i <= 99; id++, i++) {
            list.children[i] = id; list.childX[i] = 5;
            for(int id2 = 18144, i2 = 1; id2 <= 18243 && i2<= 99; id2++, i2++) {
                list.childY[0] = 2;
                list.childY[i2] = list.childY[i2 - 1] + 14;
            }
        }
        list.height = 158; list.width = 174;
        list.scrollMax = 1405;
    }
	
	public static void addText(int i, String s,int k, boolean l, boolean m, int a,TextDrawingArea[] TDA, int j) {
		RSInterface RSInterface = addInterface(i);
		RSInterface.parentID = i;
		RSInterface.id = i;
		RSInterface.type = 4;
		RSInterface.atActionType = 0;
		RSInterface.width = 0;
		RSInterface.height = 0;
		RSInterface.contentType = 0;
		RSInterface.aByte254 = 0;
		RSInterface.mOverInterToTrigger = a;
		RSInterface.centerText = l;
		RSInterface.textShadow = m;
		RSInterface.textDrawingAreas = TDA[j];
		RSInterface.message = s;
		RSInterface.aString228 = "";
		RSInterface.textColor = k;
	}
	
	public static void Pestpanel(TextDrawingArea[] tda) {
		RSInterface RSinterface = addInterface(21119);
		addText(21120, "What", 0x999999, false, true, 52, tda, 1);
		addText(21121, "What", 0x33cc00, false, true, 52, tda, 1);
		addText(21122, "(Need 5 to 25 players)", 0xFFcc33, false, true, 52, tda, 1);
		addText(21123, "Points", 0x33ccff, false, true, 52, tda, 1);
		int last = 4;
		RSinterface.children = new int[last];
		RSinterface.childX = new int[last];
		RSinterface.childY = new int[last];
		setBounds(21120, 15, 12, 0,RSinterface);
		setBounds(21121, 15, 30, 1,RSinterface);
		setBounds(21122, 15, 48, 2,RSinterface);
		setBounds(21123, 15, 66, 3,RSinterface);
	}
		
	public static void Pestpanel2(TextDrawingArea[] tda) {
		RSInterface RSinterface = addInterface(21100);
		addSprite(21101, 0, "Custom/Pest Control/PEST1");
		addSprite(21102, 1, "Custom/Pest Control/PEST1");
		addSprite(21103, 2, "Custom/Pest Control/PEST1");
		addSprite(21104, 3, "Custom/Pest Control/PEST1");
		addSprite(21105, 4, "Custom/Pest Control/PEST1");
		addSprite(21106, 5, "Custom/Pest Control/PEST1");
		addText(21107, "", 0xCC00CC, false, true, 52, tda, 1);
		addText(21108, "", 0x0000FF, false, true, 52, tda, 1);
		addText(21109, "", 0xFFFF44, false, true, 52, tda, 1);
		addText(21110, "", 0xCC0000, false, true, 52, tda, 1);
		addText(21111, "250", 0x99FF33, false, true, 52, tda, 1);//w purp
		addText(21112, "250", 0x99FF33, false, true, 52, tda, 1);//e blue
		addText(21113, "250", 0x99FF33, false, true, 52, tda, 1);//se yel
		addText(21114, "250", 0x99FF33, false, true, 52, tda, 1);//sw red
		addText(21115, "200", 0x99FF33, false, true, 52, tda, 1);//attacks
		addText(21116, "0", 0x99FF33, false, true, 52, tda, 1);//knights hp
		addText(21117, "Time Remaining:", 0xFFFFFF, false, true, 52, tda, 0);
		addText(21118, "", 0xFFFFFF, false, true, 52, tda, 0);
		int last = 18;
		RSinterface.children = new int[last];
		RSinterface.childX = new int[last];
		RSinterface.childY = new int[last];
		setBounds(21101, 361, 26, 0,RSinterface);
		setBounds(21102, 396, 26, 1,RSinterface);
		setBounds(21103, 436, 26, 2,RSinterface);
		setBounds(21104, 474, 26, 3,RSinterface);
		setBounds(21105, 3, 21, 4,RSinterface);
		setBounds(21106, 3, 50, 5,RSinterface);
		setBounds(21107, 371, 60, 6,RSinterface);
		setBounds(21108, 409, 60, 7,RSinterface);
		setBounds(21109, 443, 60, 8,RSinterface);
		setBounds(21110, 479, 60, 9,RSinterface);
		setBounds(21111, 362, 10, 10,RSinterface);
		setBounds(21112, 398, 10, 11,RSinterface);
		setBounds(21113, 436, 10, 12,RSinterface);
		setBounds(21114, 475, 10, 13,RSinterface);
		setBounds(21115, 32, 32, 14,RSinterface);
		setBounds(21116, 32, 62, 15,RSinterface);
		setBounds(21117, 8, 88, 16,RSinterface);
		setBounds(21118, 87, 88, 17,RSinterface);
	}
	
	public String hoverText;
	public static void addHoverBox(int id, String text) {
        RSInterface rsi = interfaceCache[id];//addTabInterface(id);
        rsi.id = id;
        rsi.parentID = id;
		rsi.isMouseoverTriggered = true;
        rsi.type = 8;
        rsi.hoverText = text;
    }
	
	public static void addText(int id, String text, TextDrawingArea tda[], int idx, int color, boolean center, boolean shadow) {
		RSInterface tab = addTabInterface(id);
		tab.parentID = id;
		tab.id = id;
		tab.type = 4;
		tab.atActionType = 0;
		tab.width = 0;
		tab.height = 11;
		tab.contentType = 0;
		tab.aByte254 = 0;
		tab.mOverInterToTrigger = -1;
		tab.centerText = center;
		tab.textShadow = shadow;
		tab.textDrawingAreas = tda[idx];
		tab.message = text;
		tab.aString228 = "";
		tab.textColor = color;
		tab.anInt219 = 0;
		tab.anInt216 = 0;
		tab.anInt239 = 0;	
	}

	public static void addButton(int id, int sid, String spriteName, String tooltip, int w, int h) {
		RSInterface tab = interfaceCache[id] = new RSInterface();
		tab.id = id;
		tab.parentID = id;
		tab.type = 5;
		tab.atActionType = 1;
		tab.contentType = 0;
		tab.aByte254 = (byte)0;
		tab.mOverInterToTrigger = 52;
		tab.sprite1 = imageLoader(sid, spriteName);
		tab.sprite2 = imageLoader(sid, spriteName);
		tab.width = w;
		tab.height = h;
		tab.tooltip = tooltip;
	}
	
	public static void addConfigButton(int ID, int pID, int bID, int bID2, String bName, int width, int height, String tT, int configID, int aT, int configFrame) {
        RSInterface Tab = addTabInterface(ID);
        Tab.parentID = pID;
        Tab.id = ID;
        Tab.type = 5;
        Tab.atActionType = aT;
        Tab.contentType = 0;
        Tab.width = width;
        Tab.height = height;
        Tab.aByte254 = 0;
        Tab.mOverInterToTrigger = -1;
        Tab.anIntArray245 = new int[1];
        Tab.anIntArray212 = new int[1];
        Tab.anIntArray245[0] = 1;
        Tab.anIntArray212[0] = configID;
        Tab.valueIndexArray = new int[1][3];
        Tab.valueIndexArray[0][0] = 5;
        Tab.valueIndexArray[0][1] = configFrame;
        Tab.valueIndexArray[0][2] = 0;
        Tab.sprite1 = imageLoader(bID, bName);
        Tab.sprite2 = imageLoader(bID2, bName);
        Tab.tooltip = tT;
    }

	public static void addSprite(int id, int spriteId, String spriteName) {
		RSInterface tab = interfaceCache[id] = new RSInterface();
		tab.id = id;
		tab.parentID = id;
		tab.type = 5;
		tab.atActionType = 0;
		tab.contentType = 0;
		tab.aByte254 = (byte)0;
		tab.mOverInterToTrigger = 52;
		tab.sprite1 = imageLoader(spriteId, spriteName);
		tab.sprite2 = imageLoader(spriteId, spriteName); 
		tab.width = 512;
		tab.height = 334;
	}
	private static void addHerpDerpSprite(int id, int spriteId, String spriteName, int trans) {
		RSInterface tab = interfaceCache[id] = new RSInterface();
		tab.id = id;
		tab.parentID = id;
		tab.type = 16;
		tab.atActionType = 0;
		tab.contentType = 0;
		tab.trans = (byte) trans;
		tab.mOverInterToTrigger = 52;
		tab.sprite1 = imageLoader(spriteId, spriteName);
		tab.sprite2 = imageLoader(spriteId, spriteName);
		tab.width = 512;
		tab.height = 334;
		tab.drawsTransparent = true;
	}

	public static void addHoverButton(int i, String imageName, int j, int width, int height, String text, int contentType, int hoverOver, int aT) {//hoverable button
		RSInterface tab = addTabInterface(i);
		tab.id = i;
		tab.parentID = i;
		tab.type = 5;
		tab.atActionType = aT;
		tab.contentType = contentType;
		tab.aByte254 = 0;
		tab.mOverInterToTrigger = hoverOver;
		tab.sprite1 = imageLoader(j, imageName);
		tab.sprite2 = imageLoader(j, imageName);
		tab.width = width;
		tab.height = height;
		tab.tooltip = text;
	}
	public int trans;
	public static void addHoveredButton(int i, String imageName, int j, int w, int h, int IMAGEID) {//hoverable button
		RSInterface tab = addTabInterface(i);
		tab.parentID = i;
		tab.id = i;
		tab.type = 0;
		tab.atActionType = 0;
		tab.width = w;
		tab.height = h;
		tab.isMouseoverTriggered = true;
		tab.aByte254 = 0;
		tab.mOverInterToTrigger = -1;
		tab.scrollMax = 0;
		addHoverImage(IMAGEID, j, j, imageName);
		tab.totalChildren(1);
		tab.child(0, IMAGEID, 0, 0);
	}
	

	public static void addHoverImage(int i, int j, int k, String name) {
		RSInterface tab = addTabInterface(i);
		tab.id = i;
		tab.parentID = i;
		tab.type = 5;
		tab.atActionType = 0;
		tab.contentType = 0;
		tab.width = 512;
		tab.height = 334;
		tab.aByte254 = 0;
		tab.mOverInterToTrigger = 52;
		tab.sprite1 = imageLoader(j, name);
		tab.sprite2 = imageLoader(k, name);
	}

	public static RSInterface addScreenInterface(int id) {
		RSInterface tab = interfaceCache[id] = new RSInterface();
		tab.id = id;
		tab.parentID = id;
		tab.type = 0;
		tab.atActionType = 0;
		tab.contentType = 0;
		tab.width = 512;
		tab.height = 334;
		tab.aByte254 = (byte)0;
		tab.mOverInterToTrigger = 0;
		return tab;
	}

	public static RSInterface addTabInterface(int id) {
		RSInterface tab = interfaceCache[id] = new RSInterface();
		tab.id = id;//250
		tab.parentID = id;//236
		tab.type = 0;//262
		tab.atActionType = 0;//217
		tab.contentType = 0;
		tab.width = 512;//220
		tab.height = 700;//267
		tab.aByte254 = (byte)0;
		tab.mOverInterToTrigger = -1;//Int 230
		return tab;
	}

	private static Sprite imageLoader(int i, String s) {
		long l = (TextClass.method585(s) << 8) + (long)i;
		Sprite sprite = (Sprite) aMRUNodes_238.insertFromCache(l);
		if(sprite != null)
			return sprite;
		try {
			sprite = new Sprite(s+" "+i);
			aMRUNodes_238.removeFromCache(sprite, l);
		} catch(Exception exception) {
			return null;
		}
		return sprite;
	}

	public void child(int id, int interID, int x, int y) {
		children[id] = interID;
		childX[id] = x;
		childY[id] = y;
	}
	public void totalChildren(int t) {
		children = new int[t];
		childX = new int[t];
		childY = new int[t];
	}
	public static void setBoundry(int frame, int ID, int X, int Y, RSInterface RSInterface) {
		RSInterface.children[frame] = ID;
		RSInterface.childX[frame] = X;
		RSInterface.childY[frame] = Y;
	}
	
	private Model method206(int i, int j)
	{
		Model model = (Model) aMRUNodes_264.insertFromCache((i << 16) + j);
		if(model != null)
			return model;
		if(i == 1)
			model = Model.method462(j);
		if(i == 2)
			model = EntityDef.forID(j).method160();
		if(i == 3)
			model = client.myPlayer.method453();
		if(i == 4)
			model = ItemDef.forID(j).method202(50);
		if(i == 5)
			model = null;
		if(model != null)
			aMRUNodes_264.removeFromCache(model, (i << 16) + j);
		return model;
	}

	private static Sprite method207(int i, StreamLoader streamLoader, String s) {
		long l = (TextClass.method585(s) << 8) + (long)i;
		Sprite sprite = (Sprite) aMRUNodes_238.insertFromCache(l);
		if(sprite != null)
			return sprite;
		try {
			sprite = new Sprite(streamLoader, s, i);
			aMRUNodes_238.removeFromCache(sprite, l);
		} catch(Exception _ex) {
			return null;
		}
		return sprite;
	}

	public static void method208(boolean flag, Model model) {
		int i = 0;//was parameter
		int j = 5;//was parameter
		if(flag)
			return;
		aMRUNodes_264.unlinkAll();
		if(model != null && j != 4)
			aMRUNodes_264.removeFromCache(model, (j << 16) + i);
	}

	public Model method209(int j, int k, boolean flag) {
		Model model;
		if(flag)
			model = method206(anInt255, anInt256);
		else
			model = method206(anInt233, mediaID);
		if(model == null)
			return null;
		if(k == -1 && j == -1 && model.anIntArray1640 == null)
			return model;
		Model model_1 = new Model(true, Class36.method532(k) & Class36.method532(j), false, model);
		if(k != -1 || j != -1)
			model_1.method469();
		if(k != -1)
			model_1.method470(k);
		if(j != -1)
			model_1.method470(j);
		model_1.method479(64, 768, -50, -10, -50, true);
			return model_1;
	}

	public RSInterface() {}

	public static StreamLoader aClass44;
	public boolean drawsTransparent;
	public Sprite sprite1;
	public int anInt208;
	public Sprite sprites[];
	public static RSInterface interfaceCache[];
	public int anIntArray212[];
	public int contentType;//anInt214
	public int spritesX[];
	public int anInt216;
	public int atActionType;
	public String disabledMessage;
	public String spellName;
	public int anInt219;
	public int width;
	public String tooltip;
	public String selectedActionName;
	public boolean centerText;
	public int scrollPosition;
	public String actions[];
	public int valueIndexArray[][];
	public boolean aBoolean227;
	public String aString228;
	public int mOverInterToTrigger;
	public int invSpritePadX;
	public int textColor;
	public int anInt233;
	public int mediaID;
	public boolean aBoolean235;
	public int parentID;
	public int spellUsableOn;
	private static MRUNodes aMRUNodes_238;
	public int anInt239;
	public int children[];
	public int childX[];
	public boolean usableItemInterface;
	public TextDrawingArea textDrawingAreas;
	public int invSpritePadY;
	public int anIntArray245[];
	public int anInt246;
	public int spritesY[];
	public String message;
	public boolean isInventoryInterface;
	public int id;
	public int invStackSizes[];
	public int inv[];
	public byte aByte254;
	private int anInt255;
	private int anInt256;
	public int anInt257;
	public int anInt258;
	public boolean aBoolean259;
	public Sprite sprite2;
	public int scrollMax;
	public int type;
	public int anInt263;
	private static final MRUNodes aMRUNodes_264 = new MRUNodes(30);
	public int anInt265;
	public boolean isMouseoverTriggered;
	public int height;
	public boolean textShadow;
	public int modelZoom;
	public int modelRotation1;
	public int modelRotation2;
	public int childY[];

	
	public static void equipmentScreen(TextDrawingArea[] wid) {
        RSInterface Interface = RSInterface.interfaceCache[1644];
		addButton(19144, 6, "Custom/Equipment/CUSTOM", 150, 40, "Show Equipment Stats", 1);
		removeSomething(19145);
		removeSomething(19146);
		removeSomething(19147);
		setBounds(19144, 21, 210, 23, Interface);
		setBounds(19145, 40, 210, 24, Interface);
		setBounds(19146, 40, 210, 25, Interface);
		setBounds(19147, 40, 210, 26, Interface);
		
		RSInterface tab = addTabInterface(15106);
		addSprite(15107, 7, "Custom/Equipment/CUSTOM");
		addHoverButton(15210, "Custom/Equipment/CUSTOM", 8, 21, 21, "Close", 250, 15211, 3);
		addHoveredButton(15211, "Custom/Equipment/CUSTOM", 9, 21, 21, 15212);
		addText(15111, "Equip Your Character...", wid, 2, 0xe4a146, false, true);
		addText(15112, "Attack bonus", wid, 2, 0xe4a146, false, true);
		addText(15113, "Defence bonus", wid, 2, 0xe4a146, false, true);
		addText(15114, "Other bonuses", wid, 2, 0xe4a146, false, true);
		for(int i = 1675; i <= 1684; i++) { textSize(i, wid, 1); }
		textSize(1686, wid, 1); textSize(1687, wid, 1);
		addChar(15125);
		tab.totalChildren(44);
		tab.child(0, 15107, 4, 20);
		tab.child(1, 15210, 476, 29);
		tab.child(2, 15211, 476, 29);
		tab.child(3, 15111, 14, 30);
		int Child = 4; int Y = 69;
		for(int i = 1675; i <= 1679; i++){
		tab.child(Child, i, 20, Y);
		Child++; Y += 14; }
		tab.child(9, 1680, 20, 161);
		tab.child(10, 1681, 20, 177);
		tab.child(11, 1682, 20, 192);
		tab.child(12, 1683, 20, 207);
		tab.child(13, 1684, 20, 221);
		tab.child(14, 1686, 20, 262);
		tab.child(15, 15125, 170, 200);
		tab.child(16, 15112, 16, 55);
		tab.child(17, 1687, 20, 276);
		tab.child(18, 15113, 16, 147);
		tab.child(19, 15114, 16, 248);
		tab.child(20, 1645, 104+295, 149-52);
		tab.child(21, 1646, 399, 163);
		tab.child(22, 1647, 399, 163);
		tab.child(23, 1648, 399, 58+146);
		tab.child(24, 1649, 26+22+297-2, 110-44+118-13+5);
		tab.child(25, 1650, 321+22, 58+154);
		tab.child(26, 1651, 321+134, 58+118);
		tab.child(27, 1652, 321+134, 58+154);
		tab.child(28, 1653, 321+48, 58+81);
		tab.child(29, 1654, 321+107, 58+81);
		tab.child(30, 1655, 321+58, 58+42);
		tab.child(31, 1656, 321+112, 58+41);
		tab.child(32, 1657, 321+78, 58+4);
		tab.child(33, 1658, 321+37, 58+43);
		tab.child(34, 1659, 321+78, 58+43);
		tab.child(35, 1660, 321+119, 58+43);
		tab.child(36, 1661, 321+22, 58+82);
		tab.child(37, 1662, 321+78, 58+82);
		tab.child(38, 1663, 321+134, 58+82);
		tab.child(39, 1664, 321+78, 58+122);
		tab.child(40, 1665, 321+78, 58+162);
		tab.child(41, 1666, 321+22, 58+162);
		tab.child(42, 1667, 321+134, 58+162);
		tab.child(43, 1688, 50+297-2, 110-13+5);
		for(int i = 1675; i <= 1684; i++){
		RSInterface rsi = interfaceCache[i];
       		rsi.textColor = 0xe4a146;
		rsi.centerText = false; }
		for(int i = 1686; i <= 1687; i++) {
		RSInterface rsi = interfaceCache[i];
		rsi.textColor = 0xe4a146;
		rsi.centerText = false; }
		}
		
		public static void addChar(int ID) { 
			RSInterface t = interfaceCache[ID] = new RSInterface(); 
			t.id = ID; 
			t.parentID = ID; 
			t.type = 6;
			t.atActionType = 0; 
			t.contentType = 328; 
			t.width = 136; 
			t.height = 168; 
			t.aByte254 = 0;
			t.mOverInterToTrigger = 0;
			t.modelZoom = 560;
			t.modelRotation1 = 150;
			t.modelRotation2 = 0; 
			t.anInt257 = -1; 
			t.anInt258 = -1; 
		}
		
		private static Sprite LoadLunarSprite(int i, String s) {
			Sprite sprite = imageLoader(i,"/Lunar/" + s);
			return sprite;
		}
		
		public static void addLunarSprite(int i, int j, String name) {
        RSInterface RSInterface = addInterface(i);
        RSInterface.id = i;
        RSInterface.parentID = i;
        RSInterface.type = 5;
        RSInterface.atActionType = 5;
        RSInterface.contentType = 0;
        RSInterface.aByte254 = 0;
        RSInterface.mOverInterToTrigger = 52;
        RSInterface.sprite1 = LoadLunarSprite(j, name);
        RSInterface.width = 500;
        RSInterface.height = 500;
        RSInterface.tooltip = "";
    }
	public static void drawRune(int i,int id, String runeName) {
        RSInterface RSInterface = addInterface(i);
        RSInterface.type = 5;
        RSInterface.atActionType = 0;
        RSInterface.contentType = 0;
        RSInterface.aByte254 = 0;
        RSInterface.mOverInterToTrigger = 52;
        RSInterface.sprite1 = LoadLunarSprite(id, "RUNE");
        RSInterface.width = 500;
        RSInterface.height = 500;
    }
	public static void addRuneText(int ID, int runeAmount, int RuneID, TextDrawingArea[] font){
		RSInterface rsInterface = addInterface(ID);
		rsInterface.id = ID;
		rsInterface.parentID = 1151;
		rsInterface.type = 4;
		rsInterface.atActionType = 0;
		rsInterface.contentType = 0;
		rsInterface.width = 0;
		rsInterface.height = 14;
		rsInterface.aByte254 = 0;
		rsInterface.mOverInterToTrigger= -1;
		rsInterface.anIntArray245 = new int[1];
		rsInterface.anIntArray212 = new int[1];
		rsInterface.anIntArray245[0] = 3;
		rsInterface.anIntArray212[0] = runeAmount;
		rsInterface.valueIndexArray = new int[1][4];
		rsInterface.valueIndexArray[0][0] = 4;
		rsInterface.valueIndexArray[0][1] = 3214;
		rsInterface.valueIndexArray[0][2] = RuneID;
		rsInterface.valueIndexArray[0][3] = 0;
		rsInterface.centerText = true;
		rsInterface.textDrawingAreas = font[0];
		//rsInterface.textShadowed = true;
		rsInterface.message = "%1/"+runeAmount+"";
		rsInterface.popupString = "";
		//rsInterface.disabledColour = 12582912;
		//rsInterface.enabledColour = 49152;	
	}
	public static void Shop(TextDrawingArea[] TDA) {
		RSInterface rsinterface = addTabInterface(3824);
		setChildren(8, rsinterface);
		addSprite(3825, 0, "Interfaces/Shop/SHOP");
		addHover(3902, 3, 0, 3826, 1, "Interfaces/Shop/CLOSE", 17, 17, "Close Window");
		addHovered(3826, 2, "Shop/CLOSE", 17, 17, 3827);
		addText(19679, "", 0xff981f, false, true, 52, TDA, 1);
		addText(19680, "", 0xbf751d, false, true, 52, TDA, 1);
		addButton(19681, 2, "Interfaces/Shop/SHOP", 0, 0, "", 1);
		addSprite(19687, 1, "Interfaces/Shop/ITEMBG");
		setBounds(3825, 6, 8, 0, rsinterface);
		setBounds(3902, 478, 10, 1, rsinterface);
		setBounds(3826, 478, 10, 2, rsinterface);
		setBounds(3900, 26, 44, 3, rsinterface);
		setBounds(3901, 240, 11, 4, rsinterface);
		setBounds(19679, 42, 54, 5, rsinterface);
		setBounds(19680, 150, 54, 6, rsinterface);
		setBounds(19681, 129, 50, 7, rsinterface);
		rsinterface = interfaceCache[3900];
		setChildren(1, rsinterface);
		setBounds(19687, 6, 15, 0, rsinterface);
		rsinterface.invSpritePadX = 15;
		rsinterface.width = 10;
		rsinterface.height = 4;
		rsinterface.invSpritePadY = 25;
		rsinterface = addTabInterface(19682);
		addSprite(19683, 1, "Interfaces/Shop/SHOP");
		addText(19684, "Main Stock", 0xbf751d, false, true, 52, TDA, 1);
		addText(19685, "Store Info", 0xff981f, false, true, 52, TDA, 1);
		addButton(19686, 2, "Interfaces/Shop/SHOP", 95, 19, "Main Stock", 1);
		setChildren(7, rsinterface);
		setBounds(19683, 12, 12, 0, rsinterface);
		setBounds(3901, 240, 21, 1, rsinterface);
		setBounds(19684, 42, 54, 2, rsinterface);
		setBounds(19685, 150, 54, 3, rsinterface);
		setBounds(19686, 23, 50, 4, rsinterface);
		setBounds(3902, 471, 22, 5, rsinterface);
		setBounds(3826, 60, 85, 6, rsinterface);
}

	public static void homeTeleport(){
		RSInterface RSInterface = addInterface(30000);
		RSInterface.tooltip = "Cast @gre@Lunar Home Teleport";
		RSInterface.id = 30000;
        RSInterface.parentID = 30000;
        RSInterface.type = 5;
        RSInterface.atActionType = 5;
        RSInterface.contentType = 0;
        RSInterface.aByte254 = 0;
        RSInterface.mOverInterToTrigger = 30001;
        RSInterface.sprite1 = LoadLunarSprite(1, "SPRITE");
        RSInterface.width = 20;
        RSInterface.height = 20;
		RSInterface Int = addInterface(30001);
		Int.isMouseoverTriggered = true;
		Int.totalChildren(1);
		addLunarSprite(30002, 0, "SPRITE");
		setBounds(30002, 0, 0,0, Int);
	}
	public static void addLunar2RunesSmallBox(int ID, int r1, int r2, int ra1, int ra2,int rune1, int lvl,String name, String descr,TextDrawingArea[] TDA,int sid,int suo,int type){
		RSInterface rsInterface = addInterface(ID);
		rsInterface.id = ID;
		rsInterface.parentID = 1151;
		rsInterface.type = 5;
		rsInterface.atActionType = type;
		rsInterface.contentType = 0;
		rsInterface.mOverInterToTrigger = ID+1;
		//rsInterface.spellUsableOn = suo;
		rsInterface.selectedActionName = "Cast On";
		rsInterface.width = 20;
		rsInterface.height = 20;
		rsInterface.tooltip = "Cast @gre@"+name;
		rsInterface.spellName = name;
		rsInterface.anIntArray245 = new int[3];
		rsInterface.anIntArray212 = new int[3];
		rsInterface.anIntArray245[0] = 3;
		rsInterface.anIntArray212[0] = ra1;
		rsInterface.anIntArray245[1] = 3;
		rsInterface.anIntArray212[1] = ra2;
		rsInterface.anIntArray245[2] = 3;
		rsInterface.anIntArray212[2] = lvl;
		rsInterface.valueIndexArray = new int[3][];
		rsInterface.valueIndexArray[0] = new int[4];
		rsInterface.valueIndexArray[0][0] = 4;
		rsInterface.valueIndexArray[0][1] = 3214;
		rsInterface.valueIndexArray[0][2] = r1;
		rsInterface.valueIndexArray[0][3] = 0;
		rsInterface.valueIndexArray[1] = new int[4];
		rsInterface.valueIndexArray[1][0] = 4;
		rsInterface.valueIndexArray[1][1] = 3214;
		rsInterface.valueIndexArray[1][2] = r2;
		rsInterface.valueIndexArray[1][3] = 0;
		rsInterface.valueIndexArray[2] = new int[3];
		rsInterface.valueIndexArray[2][0] = 1;
		rsInterface.valueIndexArray[2][1] = 6;
		rsInterface.valueIndexArray[2][2] = 0;
		rsInterface.sprite2 = LoadLunarSprite(sid, "LUNARON");
		rsInterface.sprite1 = LoadLunarSprite(sid, "LUNAROFF");
		RSInterface INT = addInterface(ID+1);
		INT.isMouseoverTriggered = true;
		INT.totalChildren(7);
		addLunarSprite(ID+2, 0, "BOX");
		setBounds(ID+2, 0, 0, 0, INT);
		addText(ID+3, "Level "+(lvl+1)+": "+name, 0xFF981F, true, true, 52, TDA, 1);
		setBounds(ID+3, 90, 4, 1, INT);
		addText(ID+4, descr, 0xAF6A1A, true, true, 52, TDA, 0);	
		setBounds(ID+4, 90, 19, 2, INT);
		setBounds(30016, 37, 35, 3, INT);//Rune
		setBounds(rune1, 112, 35, 4, INT);//Rune
		addRuneText(ID+5, ra1+1, r1, TDA);
		setBounds(ID+5, 50, 66, 5, INT);
		addRuneText(ID+6, ra2+1, r2, TDA);
		setBounds(ID+6, 123, 66, 6, INT);

	}
	public static void addLunar3RunesSmallBox(int ID, int r1, int r2, int r3, int ra1, int ra2, int ra3,int rune1, int rune2, int lvl,String name, String descr,TextDrawingArea[] TDA, int sid,int suo,int type){
		RSInterface rsInterface = addInterface(ID);
		rsInterface.id = ID;
		rsInterface.parentID = 1151;
		rsInterface.type = 5;
		rsInterface.atActionType = type;
		rsInterface.contentType = 0;
		rsInterface.mOverInterToTrigger = ID+1;
		//rsInterface.spellUsableOn = suo;
		rsInterface.selectedActionName = "Cast on";
		rsInterface.width = 20;
		rsInterface.height = 20;
		rsInterface.tooltip = "Cast @gre@"+name;
		rsInterface.spellName = name;
		rsInterface.anIntArray245 = new int[4];
		rsInterface.anIntArray212 = new int[4];
		rsInterface.anIntArray245[0] = 3;
		rsInterface.anIntArray212[0] = ra1;
		rsInterface.anIntArray245[1] = 3;
		rsInterface.anIntArray212[1] = ra2;
		rsInterface.anIntArray245[2] = 3;
		rsInterface.anIntArray212[2] = ra3;
		rsInterface.anIntArray245[3] = 3;
		rsInterface.anIntArray212[3] = lvl;
		rsInterface.valueIndexArray = new int[4][];
		rsInterface.valueIndexArray[0] = new int[4];
		rsInterface.valueIndexArray[0][0] = 4;
		rsInterface.valueIndexArray[0][1] = 3214;
		rsInterface.valueIndexArray[0][2] = r1;
		rsInterface.valueIndexArray[0][3] = 0;
		rsInterface.valueIndexArray[1] = new int[4];
		rsInterface.valueIndexArray[1][0] = 4;
		rsInterface.valueIndexArray[1][1] = 3214;
		rsInterface.valueIndexArray[1][2] = r2;
		rsInterface.valueIndexArray[1][3] = 0;
		rsInterface.valueIndexArray[2] = new int[4];
		rsInterface.valueIndexArray[2][0] = 4;
		rsInterface.valueIndexArray[2][1] = 3214;
		rsInterface.valueIndexArray[2][2] = r3;
		rsInterface.valueIndexArray[2][3] = 0;
		rsInterface.valueIndexArray[3] = new int[3];
		rsInterface.valueIndexArray[3][0] = 1;
		rsInterface.valueIndexArray[3][1] = 6;
		rsInterface.valueIndexArray[3][2] = 0;
		rsInterface.sprite2 = LoadLunarSprite(sid, "LUNARON");
		rsInterface.sprite1 = LoadLunarSprite(sid, "LUNAROFF");
		RSInterface INT = addInterface(ID+1);
		INT.isMouseoverTriggered = true;
		INT.totalChildren(9);
		addLunarSprite(ID+2, 0, "BOX");
		setBounds(ID+2, 0, 0, 0, INT);
		addText(ID+3, "Level "+(lvl+1)+": "+name, 0xFF981F, true, true, 52, TDA, 1);setBounds(ID+3, 90, 4, 1, INT);
		addText(ID+4, descr, 0xAF6A1A, true, true, 52, TDA, 0);	setBounds(ID+4, 90, 19, 2, INT);
		setBounds(30016, 14, 35, 3, INT);
		setBounds(rune1, 74, 35, 4, INT);
		setBounds(rune2, 130, 35, 5, INT);
		addRuneText(ID+5, ra1+1, r1, TDA);
		setBounds(ID+5, 26, 66, 6, INT);
		addRuneText(ID+6, ra2+1, r2, TDA);
		setBounds(ID+6, 87, 66, 7, INT);
		addRuneText(ID+7, ra3+1, r3, TDA);
		setBounds(ID+7, 142, 66, 8, INT);
	}
	public static void addLunar3RunesBigBox(int ID, int r1, int r2, int r3, int ra1, int ra2, int ra3,int rune1, int rune2, int lvl,String name, String descr,TextDrawingArea[] TDA, int sid,int suo,int type){
		RSInterface rsInterface = addInterface(ID);
		rsInterface.id = ID;
		rsInterface.parentID = 1151;
		rsInterface.type = 5;
		rsInterface.atActionType = type;
		rsInterface.contentType = 0;
		rsInterface.mOverInterToTrigger = ID+1;
		//rsInterface.spellUsableOn = suo;
		rsInterface.selectedActionName = "Cast on";
		rsInterface.width = 20;
		rsInterface.height = 20;
		rsInterface.tooltip = "Cast @gre@"+name;
		rsInterface.spellName = name;
		rsInterface.anIntArray245 = new int[4];
		rsInterface.anIntArray212 = new int[4];
		rsInterface.anIntArray245[0] = 3;
		rsInterface.anIntArray212[0] = ra1;
		rsInterface.anIntArray245[1] = 3;
		rsInterface.anIntArray212[1] = ra2;
		rsInterface.anIntArray245[2] = 3;
		rsInterface.anIntArray212[2] = ra3;
		rsInterface.anIntArray245[3] = 3;
		rsInterface.anIntArray212[3] = lvl;
		rsInterface.valueIndexArray = new int[4][];
		rsInterface.valueIndexArray[0] = new int[4];
		rsInterface.valueIndexArray[0][0] = 4;
		rsInterface.valueIndexArray[0][1] = 3214;
		rsInterface.valueIndexArray[0][2] = r1;
		rsInterface.valueIndexArray[0][3] = 0;
		rsInterface.valueIndexArray[1] = new int[4];
		rsInterface.valueIndexArray[1][0] = 4;
		rsInterface.valueIndexArray[1][1] = 3214;
		rsInterface.valueIndexArray[1][2] = r2;
		rsInterface.valueIndexArray[1][3] = 0;
		rsInterface.valueIndexArray[2] = new int[4];
		rsInterface.valueIndexArray[2][0] = 4;
		rsInterface.valueIndexArray[2][1] = 3214;
		rsInterface.valueIndexArray[2][2] = r3;
		rsInterface.valueIndexArray[2][3] = 0;
		rsInterface.valueIndexArray[3] = new int[3];
		rsInterface.valueIndexArray[3][0] = 1;
		rsInterface.valueIndexArray[3][1] = 6;
		rsInterface.valueIndexArray[3][2] = 0;
		rsInterface.sprite2 = LoadLunarSprite(sid, "LUNARON");
		rsInterface.sprite1 = LoadLunarSprite(sid, "LUNAROFF");
		RSInterface INT = addInterface(ID+1);
		INT.isMouseoverTriggered = true;
		INT.totalChildren(9);
		addLunarSprite(ID+2, 1, "BOX");
		setBounds(ID+2, 0, 0, 0, INT);
		addText(ID+3, "Level "+(lvl+1)+": "+name, 0xFF981F, true, true, 52, TDA, 1);setBounds(ID+3, 90, 4, 1, INT);
		addText(ID+4, descr, 0xAF6A1A, true, true, 52, TDA, 0);	setBounds(ID+4, 90, 21, 2, INT);
		setBounds(30016, 14, 48, 3, INT);
		setBounds(rune1, 74, 48, 4, INT);
		setBounds(rune2, 130, 48, 5, INT);
		addRuneText(ID+5, ra1+1, r1, TDA);
		setBounds(ID+5, 26, 79, 6, INT);
		addRuneText(ID+6, ra2+1, r2, TDA);
		setBounds(ID+6, 87, 79, 7, INT);
		addRuneText(ID+7, ra3+1, r3, TDA);
		setBounds(ID+7, 142, 79, 8, INT);
	}
	public static void addLunar3RunesLargeBox(int ID, int r1, int r2, int r3, int ra1, int ra2, int ra3,int rune1, int rune2, int lvl,String name, String descr,TextDrawingArea[] TDA, int sid,int suo,int type){
		RSInterface rsInterface = addInterface(ID);
		rsInterface.id = ID;
		rsInterface.parentID = 1151;
		rsInterface.type = 5;
		rsInterface.atActionType = type;
		rsInterface.contentType = 0;
		rsInterface.mOverInterToTrigger = ID+1;
		//rsInterface.spellUsableOn = suo;
		rsInterface.selectedActionName = "Cast on";
		rsInterface.width = 20;
		rsInterface.height = 20;
		rsInterface.tooltip = "Cast @gre@"+name;
		rsInterface.spellName = name;
		rsInterface.anIntArray245 = new int[4];
		rsInterface.anIntArray212 = new int[4];
		rsInterface.anIntArray245[0] = 3;
		rsInterface.anIntArray212[0] = ra1;
		rsInterface.anIntArray245[1] = 3;
		rsInterface.anIntArray212[1] = ra2;
		rsInterface.anIntArray245[2] = 3;
		rsInterface.anIntArray212[2] = ra3;
		rsInterface.anIntArray245[3] = 3;
		rsInterface.anIntArray212[3] = lvl;
		rsInterface.valueIndexArray = new int[4][];
		rsInterface.valueIndexArray[0] = new int[4];
		rsInterface.valueIndexArray[0][0] = 4;
		rsInterface.valueIndexArray[0][1] = 3214;
		rsInterface.valueIndexArray[0][2] = r1;
		rsInterface.valueIndexArray[0][3] = 0;
		rsInterface.valueIndexArray[1] = new int[4];
		rsInterface.valueIndexArray[1][0] = 4;
		rsInterface.valueIndexArray[1][1] = 3214;
		rsInterface.valueIndexArray[1][2] = r2;
		rsInterface.valueIndexArray[1][3] = 0;
		rsInterface.valueIndexArray[2] = new int[4];
		rsInterface.valueIndexArray[2][0] = 4;
		rsInterface.valueIndexArray[2][1] = 3214;
		rsInterface.valueIndexArray[2][2] = r3;
		rsInterface.valueIndexArray[2][3] = 0;
		rsInterface.valueIndexArray[3] = new int[3];
		rsInterface.valueIndexArray[3][0] = 1;
		rsInterface.valueIndexArray[3][1] = 6;
		rsInterface.valueIndexArray[3][2] = 0;
		rsInterface.sprite2 = LoadLunarSprite(sid, "LUNARON");
		rsInterface.sprite1 = LoadLunarSprite(sid, "LUNAROFF");
		RSInterface INT = addInterface(ID+1);
		INT.isMouseoverTriggered = true;
		INT.totalChildren(9);
		addLunarSprite(ID+2, 2, "BOX");
		setBounds(ID+2, 0, 0, 0, INT);
		addText(ID+3, "Level "+(lvl+1)+": "+name, 0xFF981F, true, true, 52, TDA, 1);
		setBounds(ID+3, 90, 4, 1, INT);
		addText(ID+4, descr, 0xAF6A1A, true, true, 52, TDA, 0);	
		setBounds(ID+4, 90, 34, 2, INT);
		setBounds(30016, 14, 61, 3, INT);
		setBounds(rune1, 74, 61, 4, INT);
		setBounds(rune2, 130, 61, 5, INT);
		addRuneText(ID+5, ra1+1, r1, TDA);
		setBounds(ID+5, 26, 92, 6, INT);
		addRuneText(ID+6, ra2+1, r2, TDA);
		setBounds(ID+6, 87, 92, 7, INT);
		addRuneText(ID+7, ra3+1, r3, TDA);
		setBounds(ID+7, 142, 92, 8, INT);
	}
	public static void configureLunar(TextDrawingArea[] TDA){
		homeTeleport();
		drawRune(30003,1, "Fire");
		drawRune(30004,2, "Water");
		drawRune(30005,3, "Air");
		drawRune(30006,4, "Earth");
		drawRune(30007,5, "Mind");
		drawRune(30008,6, "Body");
		drawRune(30009,7, "Death");
		drawRune(30010,8, "Nature");
		drawRune(30011,9, "Chaos");
		drawRune(30012,10, "Law");
		drawRune(30013,11, "Cosmic");
		drawRune(30014,12, "Blood");
		drawRune(30015,13, "Soul");
		drawRune(30016,14, "Astral");
		addLunar3RunesSmallBox(30017, 9075, 554, 555, 0, 4, 3, 30003, 30004, 64, "Bake Pie","Bake pies without a stove",TDA,0, 16,2);
		addLunar2RunesSmallBox(30025, 9075, 557, 0, 7, 30006, 65,"Cure Plant", "Cure disease on farming patch",TDA,1, 4,2);
		addLunar3RunesBigBox(30032, 9075, 564, 558, 0,0, 0, 30013, 30007, 65,"Monster Examine", "Detect the combat statistics of a\\nmonster",TDA, 2,2,2);
		addLunar3RunesSmallBox(30040, 9075, 564, 556, 0, 0, 1, 30013, 30005, 66, "NPC Contact","Speak with varied NPCs",TDA,3,0,2);
		addLunar3RunesSmallBox(30048, 9075, 563, 557, 0, 0, 9, 30012, 30006, 67, "Cure Other","Cure poisoned players",TDA,4,8,2);
		addLunar3RunesSmallBox(30056, 9075, 555, 554, 0, 2, 0, 30004, 30003, 67, "Humidify","fills certain vessels with water",TDA,5,0,5);
		addLunar3RunesSmallBox(30064, 9075, 563, 557, 1, 0, 1, 30012, 30006, 68, "Moonclan Teleport","Teleports you to moonclan island",TDA,6,0,5);
		addLunar3RunesBigBox(30075, 9075, 563, 557, 1, 0, 3, 30012,  30006, 69,"Tele Group Moonclan", "Teleports players to Moonclan\\nisland",TDA, 7,0,5);
		addLunar3RunesSmallBox(30083, 9075, 563, 557, 1, 0, 5, 30012, 30006, 70, "Ourania Teleport","Teleports you to ourania rune altar",TDA,8,0,5);
		addLunar3RunesSmallBox(30091, 9075, 564, 563, 1, 1, 0, 30013, 30012, 70, "Cure Me","Cures Poison",TDA,9,0,5);
		addLunar2RunesSmallBox(30099, 9075, 557, 1, 1, 30006, 70,"Hunter Kit", "Get a kit of hunting gear",TDA,10,0,5);
		addLunar3RunesSmallBox(30106, 9075, 563, 555,  1, 0,0, 30012, 30004, 71,"Waterbirth Teleport", "Teleports you to Waterbirth island",TDA,11,0,5);
		addLunar3RunesBigBox(30114, 9075, 563, 555, 1, 0, 4, 30012, 30004, 72,"Tele Group Waterbirth", "Teleports players to Waterbirth\\nisland",TDA, 12,0,5);
		addLunar3RunesSmallBox(30122, 9075, 564, 563, 1, 1, 1, 30013, 30012, 73, "Cure Group","Cures Poison on players",TDA,13,0,5);
		addLunar3RunesBigBox(30130, 9075, 564, 559, 1, 1, 4, 30013, 30008, 74,"Stat Spy", "Cast on another player to see their\\nskill levels",TDA, 14,8,2);
		addLunar3RunesBigBox(30138, 9075, 563, 554, 1, 1, 2, 30012, 30003, 74,"Barbarian Teleport", "Teleports you to the Barbarian\\noutpost",TDA, 15,0,5);
		addLunar3RunesBigBox(30146, 9075, 563, 554, 1, 1, 5, 30012, 30003, 75,"Tele Group Barbarian", "Teleports players to the Barbarian\\noutpost",TDA, 16,0,5);
		addLunar3RunesSmallBox(30154, 9075, 554, 556, 1, 5, 9, 30003, 30005, 76, "Superglass Make","Make glass without a furnace",TDA,17, 16,2);
		addLunar3RunesSmallBox(30162, 9075, 563, 555, 1, 1, 3, 30012, 30004, 77, "Khazard Teleport","Teleports you to Port khazard",TDA,18,0,5);
		addLunar3RunesSmallBox(30170, 9075, 563, 555, 1, 1, 7, 30012, 30004, 78, "Tele Group Khazard","Teleports players to Port khazard",TDA,19,0,5);
		addLunar3RunesBigBox(30178, 9075, 564, 559, 1, 0, 4, 30013, 30008, 78,"Dream", "Take a rest and restore hitpoints 3\\n times faster",TDA, 20,0,5);
		addLunar3RunesSmallBox(30186, 9075, 557, 555, 1, 9, 4, 30006, 30004, 79, "String Jewellery","String amulets without wool",TDA,21,0,5);
		addLunar3RunesLargeBox(30194, 9075, 557, 555, 1, 9, 9, 30006, 30004, 80,"Stat Restore Pot\\nShare", "Share a potion with up to 4 nearby\\nplayers",TDA, 22,0,5);
		addLunar3RunesSmallBox(30202, 9075, 554, 555, 1, 6, 6, 30003, 30004, 81, "Magic Imbue","Combine runes without a talisman",TDA,23,0,5);
		addLunar3RunesBigBox(30210, 9075, 561, 557, 2, 1, 14, 30010, 30006, 82,"Fertile Soil", "Fertilise a farming patch with super\\ncompost",TDA, 24, 4,2);
		addLunar3RunesBigBox(30218, 9075, 557, 555, 2, 11, 9, 30006, 30004, 83,"Boost Potion Share", "Shares a potion with up to 4 nearby\\nplayers",TDA, 25, 0,5);
		addLunar3RunesSmallBox(30226, 9075, 563, 555, 2, 2, 9, 30012, 30004, 84, "Fishing Guild Teleport","Teleports you to the fishing guild",TDA,26,0,5);
		addLunar3RunesLargeBox(30234, 9075, 563, 555, 1, 2, 13, 30012, 30004, 85, "Tele Group Fishing\\nGuild", "Teleports players to the Fishing\\nGuild",TDA, 27,0,5);
		addLunar3RunesSmallBox(30242, 9075, 557, 561, 2, 14, 0, 30006, 30010, 85, "Plank Make","Turn Logs into planks",TDA,28,16,5);
		/********Cut Off Limit**********/
		addLunar3RunesSmallBox(30250, 9075, 563, 555, 2, 2, 9, 30012, 30004, 86, "Catherby Teleport","Teleports you to Catherby",TDA,29,0,5);
		addLunar3RunesSmallBox(30258, 9075, 563, 555, 2, 2, 14, 30012, 30004, 87, "Tele Group Catherby","Teleports players to Catherby",TDA,30,0,5);
		addLunar3RunesSmallBox(30266, 9075, 563, 555, 2, 2, 7, 30012, 30004, 88, "Ice Plateau Teleport","Teleports you to Ice Plateau",TDA,31,0,5);
		addLunar3RunesBigBox(30274, 9075, 563, 555, 2, 2, 15, 30012, 30004, 89, "Tele Group Ice\\n Plateau","Teleports players to Ice Plateau",TDA,32,0,5);
		addLunar3RunesBigBox(30282, 9075, 563, 561, 2, 1, 0, 30012, 30010, 90, "Energy Transfer","Spend hitpoints and SA Energy to\\n give another player hitpoints and run energy",TDA,33,8,2);
		addLunar3RunesBigBox(30290, 9075, 563, 565, 2, 2, 0, 30012, 30014, 91, "Heal Other","Transfer up to 75% of hitpoints\\n to another player",TDA,34,8,2);
		addLunar3RunesBigBox(30298, 9075, 560, 557, 2, 1, 9, 30009, 30006, 92, "Vengeance Other","Allows another player to rebound\\ndamage to an opponent",TDA,35,8,2);
		addLunar3RunesSmallBox(30306, 9075, 560, 557, 3, 1, 9,30009, 30006, 93, "Vengeance","Rebound damage to an opponent",TDA,36,0,5);
		addLunar3RunesBigBox(30314, 9075, 565, 563, 3, 2, 5, 30014, 30012, 94, "Heal Group","Transfer up to 75% of hitpoints to a group",TDA,37,0,5);
		addLunar3RunesBigBox(30322, 9075, 564, 563, 2, 1, 0, 30013, 30012, 95, "Spellbook Swap","Change to another spellbook for 1\\nspell cast",TDA,38,0,5);
	}
	public static void constructLunar(){
		RSInterface Interface = addInterface(29999);
		Interface.totalChildren(80);
		setBounds(30000, 11, 10, 0, Interface);
		setBounds(30017, 40, 9, 1, Interface);
		setBounds(30025, 71, 12, 2, Interface);
		setBounds(30032, 103, 10, 3, Interface);
		setBounds(30040, 135, 12, 4, Interface);
		setBounds(30048, 165, 10, 5, Interface);
		setBounds(30056, 8, 38, 6, Interface);
		setBounds(30064, 39, 39, 7, Interface);
		setBounds(30075, 71, 39, 8, Interface);
		setBounds(30083, 103, 39, 9, Interface);
		setBounds(30091, 135, 39, 10, Interface);
		setBounds(30099, 165, 37, 11, Interface);
		setBounds(30106, 12, 68, 12, Interface);
		setBounds(30114, 42, 68, 13, Interface);
		setBounds(30122, 71, 68, 14, Interface);
		setBounds(30130, 103, 68, 15, Interface);
		setBounds(30138, 135, 68, 16, Interface);
		setBounds(30146, 165, 68, 17, Interface);
		setBounds(30154, 14, 97, 18, Interface);
		setBounds(30162, 42, 97, 19, Interface);
		setBounds(30170, 71, 97, 20, Interface);
		setBounds(30178, 101, 97, 21, Interface);
		setBounds(30186, 135, 98, 22, Interface);
		setBounds(30194, 168, 98, 23, Interface);
		setBounds(30202, 11, 125, 24, Interface);
		setBounds(30210, 42, 124, 25, Interface);
		setBounds(30218, 74, 125, 26, Interface);
		setBounds(30226, 103, 125, 27, Interface);
		setBounds(30234, 135, 125, 28, Interface);
		setBounds(30242, 164, 126, 29, Interface);
		setBounds(30250, 10, 155, 30, Interface);	
		setBounds(30258, 42, 155, 31, Interface);	
		setBounds(30266, 71, 155, 32, Interface);	
		setBounds(30274, 103, 155, 33, Interface);
		setBounds(30282, 136, 155, 34, Interface);	
		setBounds(30290, 165, 155, 35, Interface);	
		setBounds(30298, 13, 185, 36, Interface);	
		setBounds(30306, 42, 185, 37, Interface);	
		setBounds(30314, 71, 184, 38, Interface);
		setBounds(30322, 104, 184, 39, Interface);	
		setBounds(30001, 6, 184, 40, Interface);//hover
		setBounds(30018, 5, 176, 41, Interface);//hover
		setBounds(30026, 5, 176, 42, Interface);//hover
		setBounds(30033, 5, 163, 43, Interface);//hover
		setBounds(30041, 5, 176, 44, Interface);//hover
		setBounds(30049, 5, 176, 45, Interface);//hover
		setBounds(30057, 5, 176, 46, Interface);//hover
		setBounds(30065, 5, 176, 47, Interface);//hover
		setBounds(30076, 5, 163, 48, Interface);//hover
		setBounds(30084, 5, 176, 49, Interface);//hover
		setBounds(30092, 5, 176, 50, Interface);//hover
		setBounds(30100, 5, 176, 51, Interface);//hover
		setBounds(30107, 5, 176, 52, Interface);//hover
		setBounds(30115, 5, 163, 53, Interface);//hover
		setBounds(30123, 5, 176, 54, Interface);//hover
		setBounds(30131, 5, 163, 55, Interface);//hover
		setBounds(30139, 5, 163, 56, Interface);//hover
		setBounds(30147, 5, 163, 57, Interface);//hover
		setBounds(30155, 5, 176, 58, Interface);//hover
		setBounds(30163, 5, 176, 59, Interface);//hover
		setBounds(30171, 5, 176, 60, Interface);//hover
		setBounds(30179, 5, 163, 61, Interface);//hover
		setBounds(30187, 5, 176, 62, Interface);//hover
		setBounds(30195, 5, 149, 63, Interface);//hover
		setBounds(30203, 5, 176, 64, Interface);//hover
		setBounds(30211, 5, 163, 65, Interface);//hover
		setBounds(30219, 5, 163, 66, Interface);//hover
		setBounds(30227, 5, 176, 67, Interface);//hover
		setBounds(30235, 5, 149, 68, Interface);//hover
		setBounds(30243, 5, 176, 69, Interface);//hover
		setBounds(30251, 5, 5, 70, Interface);//hover
		setBounds(30259, 5, 5, 71, Interface);//hover
		setBounds(30267, 5, 5, 72, Interface);//hover
		setBounds(30275, 5, 5, 73, Interface);//hover	
		setBounds(30283, 5, 5, 74, Interface);//hover
		setBounds(30291, 5, 5, 75, Interface);//hover
		setBounds(30299, 5, 5, 76, Interface);//hover
		setBounds(30307, 5, 5, 77, Interface);//hover
		setBounds(30323, 5, 5, 78, Interface);//hover
		setBounds(30315, 5, 5, 79, Interface);//hover
	}
		
		public static void addButton(int i, int j, String name, int W, int H, String S, int AT) {
			RSInterface RSInterface = addInterface(i);
			RSInterface.id = i;
			RSInterface.parentID = i;
			RSInterface.type = 5;
			RSInterface.atActionType = AT;
			RSInterface.contentType = 0;
			RSInterface.aByte254 = 0;
			RSInterface.mOverInterToTrigger = 52;
			RSInterface.sprite1 = imageLoader(j,name);
			RSInterface.sprite2 = imageLoader(j,name);
			RSInterface.width = W;
			RSInterface.height = H;
			RSInterface.tooltip = S;
		}


	
	
}