package server.model.players;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Future;

import org.apache.mina.common.IoSession;
import server.Config;
import server.Server;
import server.model.items.ItemAssistant;
import server.model.shops.ShopAssistant;
import server.net.HostList;
import server.net.Packet;
import server.net.StaticPacketBuilder;
import server.util.Misc;
import server.util.Stream;
import server.model.players.skills.*;
import server.event.EventManager;
import server.event.Event;
import server.model.players.Hit.CombatType;
import server.model.players.PlayerSave;
import server.model.players.PlayerHandler;
import server.event.EventContainer;
import server.model.players.combat.range.DwarfMultiCannon;
import server.model.minigames.WarriorsGuild;
import server.event.CycleEventHandler;
import server.event.CycleEvent;
import server.event.CycleEventContainer;
import server.model.minigames.PyramidePlunder;
import server.model.players.skills.*;
import server.model.players.Curses.*;
import server.model.players.*;


public class Client extends Player {

public void stackableItemSlot(int itemId) {
		for (summoningslot = 0; occupied[summoningslot] == true; summoningslot += 1) {
			if (storeditems[summoningslot] == itemId) {
				break;
			}
		}
	}
	
	
	/*
	 * Summoning
	 */
	 public boolean summon;
	 public Summoning Summoning = new Summoning(this);
	 
	 public Summoning Summoning() {
		return Summoning;
	}
	 public void ResetKeepItems() {
		WillKeepItem1 = 0;
		WillKeepItem1Slot = 0;
		WillKeepItem2 = 0;
		WillKeepItem2Slot = 0;
		WillKeepItem3 = 0;
		WillKeepItem3Slot = 0;
		WillKeepItem4 = 0;
		WillKeepItem4Slot = 0;
		WillKeepAmt1 = 0;
		WillKeepAmt2 = 0;
		WillKeepAmt3 = 0;
	}
	 public int maxstore = 0;

	public void storesummon(int npcType) {
		switch (npcType) {

		case 6807:

			if (lastsummon > 0) {
				for (int i = 0; i < Server.npcHandler.maxNPCs; i++) {
					if (Server.npcHandler.npcs[i] != null) {
						if (Server.npcHandler.npcs[i].summon == true) {
							if (Server.npcHandler.npcs[i].spawnedBy == getId()
									&& Server.npcHandler.npcs[i].npcId == npcslot) {
								sendMessage("You are now storing items inside your npc");
								Summoning().store();
							}
						}
					}
				}

			}
			break;

		}
	}

	public void firstslot() {
		for (summoningslot = 0; occupied[summoningslot] == true; summoningslot += 1) {

		}
	}

	public int attackingplayer;
	public int lastsummon;
	public int summoningslot = 0;

	public int storeditems[] = new int[29];

	public boolean picking = false;

	public int amount[] = new int[29];
	public boolean occupied[] = new boolean[29];

	public boolean storing = false;

	public byte buffer[] = null;
	public Stream inStream = null, outStream = null;
	private IoSession session;
	private WarriorsGuild warriorsGuild = new WarriorsGuild();
	private ItemAssistant itemAssistant = new ItemAssistant(this);
	public boolean isInPyramidePlunder = false;
	public PouchCreation PouchCreation = new PouchCreation(this);
	private PlayerAction playeraction = new PlayerAction(this);
	private ShopAssistant shopAssistant = new ShopAssistant(this);
	private TradeAndDuel tradeAndDuel = new TradeAndDuel(this);
	private PlayerAssistant playerAssistant = new PlayerAssistant(this);
	private CombatAssistant combatAssistant = new CombatAssistant(this);
	private FamiliarSpecials familiarSpecials = new FamiliarSpecials(this);
	private ActionHandler actionHandler = new ActionHandler(this);
	private PlayerKilling playerKilling = new PlayerKilling(this);
	private DialogueHandler dialogueHandler = new DialogueHandler(this);
	private Queue<Packet> queuedPackets = new LinkedList<Packet>();
	private Potions potions = new Potions(this);
	private PotionMixing potionMixing = new PotionMixing(this);
	private Food food = new Food(this);
	/**
	 * Skill instances
	 */
	private DwarfMultiCannon cannon = new DwarfMultiCannon(this);
	private Slayer slayer = new Slayer(this);
	private Tanning tan = new Tanning(this);
	private FlaxStringer flax = new FlaxStringer(this);
	private Magic magic = new Magic(this);
	private Fishing fish = new Fishing(this);
	private Crafting crafting = new Crafting(this);
	private Smithing smith = new Smithing(this);
	private Prayer prayer = new Prayer(this);
	private Curse curse = new Curse(this);
	private Deflects deflects = new Deflects(this);
	private CurseEffects curseeffects = new CurseEffects(this);
	private SmithingInterface smithInt = new SmithingInterface(this);
	private Farming farming = new Farming(this);
	private Firemaking firemaking = new Firemaking(this);
	private int somejunk;
	public int lowMemoryVersion = 0;
	public int timeOutCounter = 0;
	public int returnCode = 2;
	private Future<?> currentTask;

	public int clawDamage;
	public int clawIndex;
	public int clawType = 0;

	public Client(IoSession s, int _playerId) {
		super(_playerId);
		this.session = s;
		synchronized (this) {
			outStream = new Stream(new byte[Config.BUFFER_SIZE]);
			outStream.currentOffset = 0;
		}
		inStream = new Stream(new byte[Config.BUFFER_SIZE]);
		inStream.currentOffset = 0;
		buffer = new byte[Config.BUFFER_SIZE];
	}

	public boolean kamfreenaDone;
	public boolean inCyclops;
	public boolean spawned;

	public void HighAndLow() {

		if (combatLevel < 15) {
			int Low = 3;
			int High = combatLevel + 12;
			getPA().sendFrame126("@gre@" + Low + "@yel@ - @dre@" + High + "",
					199);
		}
		if (combatLevel > 15 && combatLevel < 114) {
			int Low = combatLevel - 12;
			int High = combatLevel + 12;
			getPA().sendFrame126("@gre@" + Low + "@yel@ - @dre@" + High + "",
					199);
		}
		if (combatLevel > 114) {
			int Low = combatLevel - 12;
			int High = 126;
			getPA().sendFrame126("@gre@" + Low + "@yel@ - @dre@" + High + "",
					199);
		}

	}
	
		public void handCannonDestory() {
		cannonTimer = 0;
			int chance = playerLevel[playerFiremaking] * 5 + 25;
		if(specGfx)
			chance/=2;
		if(Misc.random(chance) == 1)
			EventManager.getSingleton().addEvent(new Event() {
		public void execute(EventContainer c) {
			if(cannonTimer <= 0) {
				gfx0(2140);
    			playerEquipment[playerWeapon] = -1;
    			sendMessage("Your hand cannon explodes!");
    			int damage = Misc.random(15) + 1;
				hitDiff = new Hit(damage, CombatType.MELEE);
				setHitUpdateRequired(true);
    			dealDamage(Misc.random(15) + 1);
    			updateRequired = true;
				getItems().sendWeapon(playerEquipment[playerWeapon], getItems().getItemName(playerEquipment[playerWeapon]));
    			getCombat().getPlayerAnimIndex(getItems().getItemName(playerEquipment[playerWeapon]).toLowerCase());
    			getItems().resetBonus();
				getItems().getBonus();
				getItems().writeBonus();
				getPA().requestUpdates();getOutStream().createFrame(34);
				getOutStream().writeWord(6);
				getOutStream().writeWord(1688);
				getOutStream().writeByte(playerWeapon);
				getOutStream().writeWord(0);
				getOutStream().writeByte(0);
				updateRequired = true; 
				setAppearanceUpdateRequired(true);
				c.stop();
				} else {
					cannonTimer--;
				}
			}
		}, 500);
	}
		public boolean specGfx = false;
		public int cannonTimer = 0;
		public void handCannonSpec() {
				cannonTimer = 0;
					EventManager.getSingleton().addEvent(new Event() {
				public void execute(EventContainer c) {
					cannonTimer--;
						if(cannonTimer == 0) {
						gfx0(2141);
						specGfx = true;
					}
						if(cannonTimer == 1) {
						if (playerIndex > 0)
							getCombat().fireProjectilePlayer();
						else if (npcIndex > 0)
							getCombat().fireProjectileNpc();	
					c.stop();
				}
			}
		}, 25);
	}

	public void flushOutStream() {
		if (disconnected || outStream.currentOffset == 0)
			return;
		synchronized (this) {
			StaticPacketBuilder out = new StaticPacketBuilder().setBare(true);
			byte[] temp = new byte[outStream.currentOffset];
			System.arraycopy(outStream.buffer, 0, temp, 0, temp.length);
			out.addBytes(temp);
			session.write(out.toPacket());
			outStream.currentOffset = 0;
		}
	}

	public void sendClan(String name, String message, String clan, int rights) {
		outStream.createFrameVarSizeWord(217);
		outStream.writeString(name);
		outStream.writeString(message);
		outStream.writeString(clan);
		outStream.writeWord(rights);
		outStream.endFrameVarSize();
	}

	public static final int PACKET_SIZES[] = { 0, 0, 0, 1, -1, 0, 0, 0, 0, 0, // 0
			0, 0, 0, 0, 8, 0, 6, 2, 2, 0, // 10
			0, 2, 0, 6, 0, 12, 0, 0, 0, 0, // 20
			0, 0, 0, 0, 0, 8, 4, 0, 0, 2, // 30
			2, 6, 0, 6, 0, -1, 0, 0, 0, 0, // 40
			0, 0, 0, 12, 0, 0, 0, 8, 8, 12, // 50
			8, 8, 0, 0, 0, 0, 0, 0, 0, 0, // 60
			6, 0, 2, 2, 8, 6, 0, -1, 0, 6, // 70
			0, 0, 0, 0, 0, 1, 4, 6, 0, 0, // 80
			0, 0, 0, 0, 0, 3, 0, 0, -1, 0, // 90
			0, 13, 0, -1, 0, 0, 0, 0, 0, 0,// 100
			0, 0, 0, 0, 0, 0, 0, 6, 0, 0, // 110
			1, 0, 6, 0, 0, 0, -1, 0, 2, 6, // 120
			0, 4, 6, 8, 0, 6, 0, 0, 0, 2, // 130
			0, 0, 0, 0, 0, 6, 0, 0, 0, 0, // 140
			0, 0, 1, 2, 0, 2, 6, 0, 0, 0, // 150
			0, 0, 0, 0, -1, -1, 0, 0, 0, 0,// 160
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 170
			0, 8, 0, 3, 0, 2, 0, 0, 8, 1, // 180
			0, 0, 12, 0, 0, 0, 0, 0, 0, 0, // 190
			2, 0, 0, 0, 0, 0, 0, 0, 4, 0, // 200
			4, 0, 0, 0, 7, 8, 0, 0, 10, 0, // 210
			0, 0, 0, 0, 0, 0, -1, 0, 6, 0, // 220
			1, 0, 0, 0, 6, 0, 6, 8, 1, 0, // 230
			0, 4, 0, 0, 0, 0, -1, 0, -1, 4,// 240
			0, 0, 6, 6, 0, 0, 0 // 250
	};

	public void destruct() {
		synchronized (this) {
			if (session == null)
				return;
				
			PlayerSave.saveGame(this);// dat is voor normale logout ja,maar voor
										// unexpected logout meotn we bij
										// destruct zijn denk ik
			getPA().removeFromCW();
			if (inPits)
				Server.fightPits.removePlayerFromPits(playerId);
			if (clanId >= 0)
				Server.clanChat.leaveClan(playerId, clanId);
				if (isInPyramidePlunder) {
			PyramidePlunder.endPyramidePlunder(this);
		}
		if(duelStatus == 6) {
				getTradeAndDuel().claimStakedItems();
			}
		if (isInPyramidePlunder) {
			PyramidePlunder.endPyramidePlunder(this);
		}
			Misc.println("[DEREGISTERED]: " + playerName + "");
			HostList.getHostList().remove(session);
			disconnected = true;
			session.close();
			session = null;
			inStream = null;
			outStream = null;
			isActive = false;
			buffer = null;
			super.destruct();
		}
	}

	public void sendMessage(String s) {
		synchronized (this) {
			if (getOutStream() != null) {
				outStream.createFrameVarSize(253);
				outStream.writeString(s);
				outStream.endFrameVarSize();
			}
		}
	}

	public void setSidebarInterface(int menuId, int form) {
		synchronized (this) {
			if (getOutStream() != null) {
				outStream.createFrame(71);
				outStream.writeWord(form);
				outStream.writeByteA(menuId);
			}
		}
	}

	public void initialize() {
		synchronized (this) {
			outStream.createFrame(249);
			outStream.writeByteA(1); // 1 for members, zero for free
			outStream.writeWordBigEndianA(playerId);
			for (int j = 0; j < Server.playerHandler.players.length; j++) {
				if (j == playerId)
					continue;
				if (Server.playerHandler.players[j] != null) {
					if (Server.playerHandler.players[j].playerName
							.equalsIgnoreCase(playerName))
						disconnected = true;
				}
			}
			
			for (int i = 0; i < 25; i++) {
				getPA().setSkillLevel(i, playerLevel[i], playerXP[i]);
				getPA().refreshSkill(i);
			}
			for (int p = 0; p < PRAYER.length; p++) { // reset prayer glows
				prayerActive[p] = false;
				getPA().sendFrame36(PRAYER_GLOW[p], 0);
			}

			if (MoneyCash > 99999 && MoneyCash <= 999999) {
				getPA().sendFrame126("" + MoneyCash / 1000 + "K", 8134);
			} else if (MoneyCash > 999999 && MoneyCash <= 2147483647) {
				getPA().sendFrame126("" + MoneyCash / 1000000 + "M", 8134);
			} else {
				getPA().sendFrame126("" + MoneyCash + "", 8134);
			}
			getPA().sendFrame126("" + MoneyCash + "", 8135);

			for (int p = 0; p < CURSE.length; p++) { // reset prayer glows
				curseActive[p] = false;
				getPA().sendFrame36(CURSE_GLOW[p], 0);
			}if (hasNpc == true) {
			if (summonId > 0) {
				Server.npcHandler.spawnNpc3(this, summonId, absX, absY - 1,
						heightLevel, 0, 120, 25, 200, 200, true, false, true);

			}
		}

			if (displayName.equalsIgnoreCase("notset")) {
				displayName = playerName;
			}
			getPA().handleWeaponStyle();
			getPA().handleLoginText();
			accountFlagged = getPA().checkForFlags();
			// getPA().sendFrame36(43, fightMode-1);
			getPA().sendFrame36(108, 0);// resets autocast button
			getPA().sendFrame36(172, 1);
			getPA().sendFrame107(); // reset screen
			getPA().setChatOptions(0, 0, 0); // reset private messaging options
			setSidebarInterface(1, 3917);
			setSidebarInterface(2, 638);
			setSidebarInterface(3, 3213);
			setSidebarInterface(4, 1644);
			//setSidebarInterface(5, 5608);
			if (playerMagicBook == 0) {
				setSidebarInterface(6, 1151); // modern
			} else {
				if (playerMagicBook == 2) {
					setSidebarInterface(6, 29999); // lunar
				} else {
					setSidebarInterface(6, 12855); // ancient
				}
			}

			if (altarPrayed == 0) {
				setSidebarInterface(5, 5608); // Normal Prayers
			} else {
				setSidebarInterface(5, 22500); // Curses prayer
			}
			correctCoordinates();
			setSidebarInterface(7, 18128);
			setSidebarInterface(8, 5065);
			setSidebarInterface(9, 5715);
			setSidebarInterface(10, 2449);
			// setSidebarInterface(11, 4445); // wrench tab
			setSidebarInterface(11, 904); // wrench tab
			setSidebarInterface(12, 147); // run tab
			setSidebarInterface(13, 6299);
			setSidebarInterface(0, 2423);
			setSidebarInterface(14, 17000);
			setSidebarInterface(15, 17350);
			setSidebarInterface(16, 18017);
			
			sendMessage("Welcome to Acelta");
			if (Config.doubleEXPWeekend == true) {
				sendMessage("Enjoy Double EXP Weekend!");
			}
			if (lastsummon > 0) {
			Summoning().SummonNewNPC(lastsummon);
		}
			getPA().loadAnnouncements();
			getPA().showOption(4, 0, "Trade With", 3);
			getPA().showOption(5, 0, "Follow", 4);
			getItems().resetItems(3214);
			getItems().sendWeapon(playerEquipment[playerWeapon],
					getItems().getItemName(playerEquipment[playerWeapon]));
			getItems().resetBonus();
			getItems().getBonus();
			getItems().writeBonus();
			getItems().setEquipment(playerEquipment[playerHat], 1, playerHat);
			getItems().setEquipment(playerEquipment[playerCape], 1, playerCape);
			getItems().setEquipment(playerEquipment[playerAmulet], 1,
					playerAmulet);
			getItems().setEquipment(playerEquipment[playerArrows],
					playerEquipmentN[playerArrows], playerArrows);
			getItems().setEquipment(playerEquipment[playerChest], 1,
					playerChest);
			getItems().setEquipment(playerEquipment[playerShield], 1,
					playerShield);
			getItems().setEquipment(playerEquipment[playerLegs], 1, playerLegs);
			getItems().setEquipment(playerEquipment[playerHands], 1,
					playerHands);
			getItems().setEquipment(playerEquipment[playerFeet], 1, playerFeet);
			getItems().setEquipment(playerEquipment[playerRing], 1, playerRing);
			getItems().setEquipment(playerEquipment[playerWeapon],
					playerEquipmentN[playerWeapon], playerWeapon);
			getCombat().getPlayerAnimIndex(
					getItems().getItemName(playerEquipment[playerWeapon])
							.toLowerCase());
			getPA().logIntoPM();
			getItems().addSpecialBar(playerEquipment[playerWeapon]);
			saveTimer = Config.SAVE_TIMER;
			saveCharacter = true;
			Misc.println("[REGISTERED]: " + playerName + "");
			int size = playerListSize;

			handler.updatePlayer(this, outStream);
			handler.updateNPC(this, outStream);
			flushOutStream();
			getPA().clearClanChat();
			getPA().resetFollow();
			if (addStarter)
				getPA().addStarter();
			if (autoRet == 1)
				getPA().sendFrame36(172, 1);
			else
				getPA().sendFrame36(172, 0);
		}
	}

	public void update() {
		synchronized (this) {
			handler.updatePlayer(this, outStream);
			handler.updateNPC(this, outStream);
			flushOutStream();
		}
	}

	public void logout() {
		synchronized (this) {
			if (System.currentTimeMillis() - logoutDelay > 10000) {
				CycleEventHandler.getSingleton().stopEvents(this);
				outStream.createFrame(109);
				properLogout = true;
				PlayerSave.saveGame(this);
				if (lastsummon > 0) {
				for (int i = 0; i < Server.npcHandler.maxNPCs; i++) {
					if (Server.npcHandler.npcs[i] != null) {
						if (Server.npcHandler.npcs[i].summon == true) {
							if (Server.npcHandler.npcs[i].spawnedBy == getId()) {
								Server.npcHandler.npcs[i].isDead = true;
								Server.npcHandler.npcs[i].applyDead = true;
								Server.npcHandler.npcs[i].summon = false;
							}
						}
					}
				}
			}
			} else {
				sendMessage("You must wait a few seconds from being out of combat to logout.");
			}
		}
	}

	public void SaveGame() {
		synchronized (this) {
			PlayerSave.saveGame(this);
		}
	}

	public int packetSize = 0, packetType = -1;

	public void FetchDice() {
		int rnd;
		String Message = "";
		if (cDice == 0 || (System.currentTimeMillis() - diceDelay <= 1000)) {
			return;
		}
		switch (cDice) {
		// Dice
		case 15096:
			rnd = Misc.random(19) + 1;
			Message = ("rolled <col=16711680>" + rnd + "</col> on a twenty-sided die.");
			break;
		case 15094:
			rnd = Misc.random(11) + 1;
			Message = ("rolled <col=16711680>" + rnd + "</col> on a twelve-sided die.");
			break;
		case 15092:
			rnd = Misc.random(9) + 1;
			Message = ("rolled <col=16711680>" + rnd + "</col> on a ten-sided die.");
			break;
		case 15090:
			rnd = Misc.random(7) + 1;
			Message = ("rolled <col=16711680>" + rnd + "</col> on an eight-sided die.");
			break;
		case 15100:
			rnd = Misc.random(3) + 1;
			Message = ("rolled <col=16711680>" + rnd + "</col> on a four-sided die.");
			break;
		case 15086:
			rnd = Misc.random(5) + 1;
			Message = ("rolled <col=16711680>" + rnd + "</col> on a six-sided die.");
			break;
		case 15088:
			rnd = Misc.random(11) + 1;
			Message = ("rolled <col=16711680>" + rnd + "</col> on two six-sided dice.");
			break;
		case 15098:
			rnd = Misc.random(99) + 1;
			Message = ("rolled <col=16711680>" + rnd + "</col> on the percentile dice.");
			break;
		}
		sendMessage("You " + Message);
		if (clanDice) {
			if (clanId >= 0) {
				Server.clanChat.messageToClan(
						"Clan Chat channel-mate <col=16711680>" + playerName
								+ "</col> " + Message, clanId);
			}
		}
		cDice = 0;
	}

	public void useDice(int itemId, boolean clan) {
		if (System.currentTimeMillis() - diceDelay >= 3000) {
			sendMessage("Rolling...");
			startAnimation(11900);
			diceDelay = System.currentTimeMillis();
			cDice = itemId;
			clanDice = clan;
			switch (itemId) {
			// Gfx's
			case 15086:
				gfx0(2072);
				break;
			case 15088:
				gfx0(2074);
				break;
			case 15090:
				gfx0(2071);
				break;
			case 15092:
				gfx0(2070);
				break;
			case 15094:
				gfx0(2073);
				break;
			case 15096:
				gfx0(2068);
				break;
			case 15098:
				gfx0(2075);
				break;
			case 15100:
				gfx0(2069);
				break;
			}
		}

	}

	public void process() {
		if(System.currentTimeMillis() - specDelay > Config.INCREASE_SSPEC_AMOUNT) {
			specDelay = System.currentTimeMillis();
			if(summAmount < 60) {
				summAmount += 10;
                        }
			if (summAmount > 60) {
				summAmount = 60;
			}
		}

		if (specTimer > 0) {
			specTimer--;
		}
	
		if (smeltTimer > 0 && smeltType > 0) {
			smeltTimer--;
		} else if (smeltTimer == 0 && smeltType > 0) {
			getSmithing().smelt(smeltType);
		}

		if (System.currentTimeMillis() - lastPoison > 20000 && poisonDamage > 0) {
			int damage = poisonDamage / 2;
			if (damage > 0) {
				sendMessage("The poison damages you");
				if (!getHitUpdateRequired()) {
					setHitUpdateRequired(true);
					hitDiff = new Hit(damage, CombatType.NONE);
					updateRequired = true;
					poisonMask = 1;
				} else if (!getHitUpdateRequired2()) {
					setHitUpdateRequired2(true);
					hitDiff2 = new Hit(damage, CombatType.NONE);
					updateRequired = true;
					poisonMask = 2;
				}
				lastPoison = System.currentTimeMillis();
				poisonDamage--;
				dealDamage(damage);
			} else {
				poisonDamage = -1;
				sendMessage("You are no longer poisoned.");
			}
		}

		if (System.currentTimeMillis() - duelDelay > 800 && duelCount > 0) {
			if (duelCount != 1) {
				forcedChat("" + (--duelCount));
				duelDelay = System.currentTimeMillis();
			} else {
				damageTaken = new int[Config.MAX_PLAYERS];
				forcedChat("FIGHT!");
				duelCount = 0;
			}
		}

		if (System.currentTimeMillis() - specDelay > Config.INCREASE_SPECIAL_AMOUNT) {
			specDelay = System.currentTimeMillis();
			if (specAmount < 10) {
				specAmount += .5;
				if (specAmount > 10)
					specAmount = 10;
				getItems().addSpecialBar(playerEquipment[playerWeapon]);
			}
		}
		if (System.currentTimeMillis() - specDelayy > Config.INCREASE_SSPEC_AMOUNT) {
			specDelayy = System.currentTimeMillis();
			if (summAmount < 60) {
				summAmount += 5;
				if (summAmount > 60)
					summAmount = 60;
				//getItems().addSpecialBar(playerEquipment[playerWeapon]);
			}
		}

		if (clickObjectType > 0
				&& goodDistance(objectX + objectXOffset, objectY
						+ objectYOffset, getX(), getY(), objectDistance)) {
			if (clickObjectType == 1) {
				getActions().firstClickObject(objectId, objectX, objectY);
			}
			if (clickObjectType == 2) {
				getActions().secondClickObject(objectId, objectX, objectY);
			}
			if (clickObjectType == 3) {
				getActions().thirdClickObject(objectId, objectX, objectY);
			}
		}

		if ((clickNpcType > 0) && Server.npcHandler.npcs[npcClickIndex] != null) {
			if (goodDistance(getX(), getY(),
					Server.npcHandler.npcs[npcClickIndex].getX(),
					Server.npcHandler.npcs[npcClickIndex].getY(), 1)) {
				if (clickNpcType == 1) {
					turnPlayerTo(Server.npcHandler.npcs[npcClickIndex].getX(),
							Server.npcHandler.npcs[npcClickIndex].getY());
					Server.npcHandler.npcs[npcClickIndex].facePlayer(playerId);
					getActions().firstClickNpc(npcType);
				}
				if (clickNpcType == 2) {
					turnPlayerTo(Server.npcHandler.npcs[npcClickIndex].getX(),
							Server.npcHandler.npcs[npcClickIndex].getY());
					Server.npcHandler.npcs[npcClickIndex].facePlayer(playerId);
					getActions().secondClickNpc(npcType);
				}
				if (clickNpcType == 3) {
					turnPlayerTo(Server.npcHandler.npcs[npcClickIndex].getX(),
							Server.npcHandler.npcs[npcClickIndex].getY());
					Server.npcHandler.npcs[npcClickIndex].facePlayer(playerId);
					getActions().thirdClickNpc(npcType);
				}
			}
		}
		getPA().sendFrame126(
				"Players Online: " + PlayerHandler.getPlayerCount() + " ",
				29161); // quest title
		if (clawDelay > 0) {
			clawDelay--;
		}
		if (clawDelay == 1) {
			delayedDamage = clawDamage / 4;
			delayedDamage2 = (clawDamage / 4) + 1;
			if (clawType == 2) {
				getCombat().applyNpcMeleeDamage(clawIndex, 1, clawDamage / 4);
			}
			if (clawType == 1) {
				getCombat()
						.applyPlayerMeleeDamage(clawIndex, 1, clawDamage / 4);
			}
			if (clawType == 2) {
				getCombat().applyNpcMeleeDamage(clawIndex, 2,
						(clawDamage / 4) + 1);
			}
			if (clawType == 1) {
				getCombat().applyPlayerMeleeDamage(clawIndex, 2,
						(clawDamage / 4) + 1);
			}
			clawDelay = 0;
			specEffect = 0;
			previousDamage = 0;
			usingClaws = false;
			clawType = 0;
		}
				getPA().sendFrame126("Special Amount "+summAmount+"%", 25622);
				FetchDice();
	if (needsToSpawn && !cannotSummon()) {
			Summoning.SummonNewNPC(lastsummon);
			needsToSpawn = false;
		}
				getPA().sendFrame126("Level: " + getPA().getTotalLevel() + "", 13983);

		getPA().sendFrame126("" + getPA().getLevelForXP(playerXP[21]) + "",
				29800);
		getPA().sendFrame126("" + getPA().getLevelForXP(playerXP[24]) + "",
				29801);
				
				getPA().sendFrame126("" + getPA().getLevelForXP(playerXP[24]) + "/" + getPA().getLevelForXP(playerXP[24]) + "",
				25612);
		if (walkingToItem) {
			if (getX() == pItemX && getY() == pItemY
					|| goodDistance(getX(), getY(), pItemX, pItemY, 1)) {
				walkingToItem = false;
				Server.itemHandler.removeGroundItem(this, pItemId, pItemX,
						pItemY, true);
			}
		}

		if (followId > 0) {
			getPA().followPlayer();
		} else if (followId2 > 0) {
			getPA().followNpc();
		}

		getFishing().FishingProcess();
		getCombat().handlePrayerDrain();

		if (System.currentTimeMillis() - singleCombatDelay > 3300) {
			underAttackBy = 0;
		}
		if (System.currentTimeMillis() - singleCombatDelay2 > 3300) {
			underAttackBy2 = 0;
		}

		if (System.currentTimeMillis() - restoreStatsDelay >  60000) {
			restoreStatsDelay = System.currentTimeMillis();
			if (playerLevel[3] < 1)
				return;
			for (int level = 0; level < playerLevel.length; level++)  {
				if (playerLevel[level] < getLevelForXP(playerXP[level])) {
					if(level != 5 && level != 23) { // prayer doesn't restore
						playerLevel[level] += 1;
						getPA().setSkillLevel(level, playerLevel[level], playerXP[level]);
						getPA().refreshSkill(level);
					}
				} else if (playerLevel[level] > getLevelForXP(playerXP[level])) {
					if (level == 0 || level == 1 || level == 2 || level == 4 || level == 6) {
						if (hasOverloadBoost)
							continue;
					}
					playerLevel[level] -= 1;
					getPA().setSkillLevel(level, playerLevel[level], playerXP[level]);
					getPA().refreshSkill(level);
				}
			}
		}

		if (System.currentTimeMillis() - teleGrabDelay > 1550 && usingMagic) {
			usingMagic = false;
			if (Server.itemHandler.itemExists(teleGrabItem, teleGrabX,
					teleGrabY)) {
				Server.itemHandler.removeGroundItem(this, teleGrabItem,
						teleGrabX, teleGrabY, true);
			}
		}
		if (inWild() && !isInArd() && !isInFala()) {
			int modY = absY > 6400 ? absY - 6400 : absY;
			wildLevel = (((modY - 3520) / 8) + 1);
			getPA().walkableInterface(197);
			if (Config.SINGLE_AND_MULTI_ZONES) {
				if (inMulti()) {
					getPA().sendFrame126("@yel@Level: " + wildLevel, 199);
				} else {
					getPA().sendFrame126("@yel@Level: " + wildLevel, 199);
				}
			} else {
				getPA().multiWay(-1);
				getPA().sendFrame126("@yel@Level: " + wildLevel, 199);
			}
			getPA().showOption(3, 0, "Attack", 1);
			} else if(!inWild() && !inPcBoat() && !inPcGame()){
                
		} else if (inDuelArena()) {
			getPA().walkableInterface(201);
			if (duelStatus == 5) {
				getPA().showOption(3, 0, "Attack", 1);
			} else {
				getPA().showOption(3, 0, "Challenge", 1);
			}
		} else if (inBarrows()) {
			getPA().sendFrame99(2);
			getPA().sendFrame126("Kill Count: " + barrowsKillCount, 4536);
			getPA().walkableInterface(4535);
			/*} else if (isInPyramidePlunder()) {
				getPA().sendFrame99(0);
				getPA().walkableInterface(-1);
				getPA().showOption(3, 0, "Null", 1);*/
			
		} else if (inFightCaves()) {
			getPA().walkableInterface(21400);
			getPA().sendFrame126("Wave: " + waveId + "", 21450);

		} else if (safeZone()) {
			getPA().walkableInterface(197);
			getPA().showOption(3, 0, "Attack", 1);
			if (Config.SINGLE_AND_MULTI_ZONES) {
				if (inMulti()) {
					getPA().sendFrame126("@gre@Safe Zone", 199);
				} else {
					getPA().sendFrame126("@gre@Safe Zone", 199);
				}
			} else {
				getPA().multiWay(-1);
				getPA().sendFrame126("@gre@Safe Zone", 199);
			}

		} else if (isInFala()) {
			int modY = absY > 6400 ? absY - 6400 : absY;
			wildLevel = 12;
			headIconPk = 1;
			getPA().walkableInterface(197);
			getPA().showOption(3, 0, "Attack", 1);
			if (Config.SINGLE_AND_MULTI_ZONES) {
				if (inMulti()) {
					HighAndLow();
				} else {
					HighAndLow();
				}
			}
		} else if (isInArd()) {
			int modY = absY > 6400 ? absY - 6400 : absY;
			wildLevel = 12;
			headIconPk = 0;
			getPA().walkableInterface(197);
			getPA().showOption(3, 0, "Attack", 1);
			if (Config.SINGLE_AND_MULTI_ZONES) {
				if (inMulti()) {
					HighAndLow();
				} else {
					HighAndLow();
				}
			} else {
				getPA().multiWay(-1);
				HighAndLow();
			}
			getPA().showOption(3, 0, "Attack", 1);
		} else if (inCwGame || inPits) {
			getPA().showOption(3, 0, "Attack", 1);
		} else if (getPA().inPitsWait()) {
			getPA().showOption(3, 0, "Null", 1);
		} else if (!inFightCaves()) {
			getPA().sendFrame99(0);
			getPA().walkableInterface(-1);
			getPA().showOption(3, 0, "Null", 1);
		} else if (!inCwWait) {
			getPA().sendFrame99(0);
			getPA().walkableInterface(-1);
			getPA().showOption(3, 0, "Null", 1);
		}

		if (!hasMultiSign && inMulti()) {
			hasMultiSign = true;
			getPA().multiWay(1);
		}

		if (hasMultiSign && !inMulti()) {
			hasMultiSign = false;
			getPA().multiWay(-1);
		}

		if (skullTimer > 0) {
			skullTimer--;
			if (skullTimer == 1) {
				isSkulled = false;
				attackedPlayers.clear();
				headIconPk = -1;
				skullTimer = -1;
				getPA().requestUpdates();
			}
		}

		if (isDead && respawnTimer == -6) {
			getPA().applyDead();
		}

		if (respawnTimer == 7) {
			respawnTimer = -6;
			getPA().giveLife();
		} else if (respawnTimer == 12) {
			respawnTimer--;
			startAnimation(0x900);
			poisonDamage = -1;
		}

		if (respawnTimer > -6) {
			respawnTimer--;
		}
		if (freezeTimer > -6) {
			freezeTimer--;
			if (frozenBy > 0) {
				if (Server.playerHandler.players[frozenBy] == null) {
					freezeTimer = -1;
					frozenBy = -1;
				} else if (!goodDistance(absX, absY,
						Server.playerHandler.players[frozenBy].absX,
						Server.playerHandler.players[frozenBy].absY, 20)) {
					freezeTimer = -1;
					frozenBy = -1;
				}
			}
		}

		if (hitDelay > 0) {
			hitDelay--;
		}

		if (teleTimer > 0) {
			teleTimer--;
			if (!isDead) {
				if (teleTimer == 1 && newLocation > 0) {
					teleTimer = 0;
					getPA().changeLocation();
				}
				if (teleTimer == 5) {
					teleTimer--;
					getPA().processTeleport();
				}
				if (teleTimer == 9 && teleGfx > 0) {
					teleTimer--;
					gfx100(teleGfx);
				}
			} else {
				teleTimer = 0;
			}
		}

		if (hitDelay == 1) {
			if (oldNpcIndex > 0) {
				getCombat().delayedHit(oldNpcIndex);
			}
			if (oldPlayerIndex > 0) {
				getCombat().playerDelayedHit(oldPlayerIndex);
			}
		}

		if (attackTimer > 0) {
			attackTimer--;
		}

		if (attackTimer == 1) {
			if (npcIndex > 0 && clickNpcType == 0) {
				getCombat().attackNpc(npcIndex);
			}
			if (playerIndex > 0) {
				getCombat().attackPlayer(playerIndex);
			}
		} else if (attackTimer <= 0 && (npcIndex > 0 || playerIndex > 0)) {
			if (npcIndex > 0) {
				attackTimer = 0;
				getCombat().attackNpc(npcIndex);
			} else if (playerIndex > 0) {
				attackTimer = 0;
				getCombat().attackPlayer(playerIndex);
			}
		}

		if (timeOutCounter > Config.TIMEOUT) {
			disconnected = true;
		}

		timeOutCounter++;

		if (inTrade && tradeResetNeeded) {
			Client o = (Client) Server.playerHandler.players[tradeWith];
			if (o != null) {
				if (o.tradeResetNeeded) {
					getTradeAndDuel().resetTrade();
					o.getTradeAndDuel().resetTrade();
				}
			}
		}
	}

	public void setCurrentTask(Future<?> task) {
		currentTask = task;
	}

	public Future<?> getCurrentTask() {
		return currentTask;
	}
	
	@SuppressWarnings("null")
	public void WalkTo(int x, int y) {
		newWalkCmdSteps = (Math.abs((x+y)));
		if (newWalkCmdSteps % 1 != 0) newWalkCmdSteps /= 1;
		if (++newWalkCmdSteps > walkingQueueSize) {
			println("Warning: WalkTo command contains too many steps (" + newWalkCmdSteps + ").");
			newWalkCmdSteps = 0;
		}
		int firstStepX = absX;
		firstStepX -= mapRegionX*8;

		for (int i = 1; i < newWalkCmdSteps; i++) {
			newWalkCmdX[i] = x;
			newWalkCmdY[i] = y;
		}
		newWalkCmdX[0] = newWalkCmdY[0];
		int firstStepY = absY;
		firstStepY -= mapRegionY*8;
		newWalkCmdIsRunning = ((inStream.readSignedByteC() == 1));
		for (int q = 0; q < newWalkCmdSteps; q++) {
			newWalkCmdX[q] += firstStepX;
			newWalkCmdY[q] += firstStepY;
		}
	}
public void fmwalkto(int i, int j)
    {
        newWalkCmdSteps = 0;
        if(++newWalkCmdSteps > 50)
            newWalkCmdSteps = 0;
        int k = absX + i;
        k -= mapRegionX * 8;
        newWalkCmdX[0] = newWalkCmdY[0] = tmpNWCX[0] = tmpNWCY[0] = 0;
        int l = absY + j;
        l -= mapRegionY * 8;
	isRunning2 = false;
	isRunning = false;
            newWalkCmdX[0] += k;
            newWalkCmdY[0] += l;
        poimiY = l;
        poimiX = k;
    }
	public int tmpNWCY[] = new int[walkingQueueSize];
	public int tmpNWCX[] = new int[walkingQueueSize];

	public synchronized Stream getInStream() {
		return inStream;
	}

	public synchronized int getPacketType() {
		return packetType;
	}

	public synchronized int getPacketSize() {
		return packetSize;
	}

	public synchronized Stream getOutStream() {
		return outStream;
	}

	public ItemAssistant getItems() {
		return itemAssistant;
	}

	public PlayerAssistant getPA() {
		return playerAssistant;
	}

	public DialogueHandler getDH() {
		return dialogueHandler;
	}

	public WarriorsGuild getWarriorsGuild() {
		return warriorsGuild;
	}

	public ShopAssistant getShops() {
		return shopAssistant;
	}

	public TradeAndDuel getTradeAndDuel() {
		return tradeAndDuel;
	}

	public CombatAssistant getCombat() {
		return combatAssistant;
	}

	public FamiliarSpecials getSpecials() {
		return familiarSpecials;
	}
	
	public ActionHandler getActions() {
		return actionHandler;
	}

	public PlayerKilling getKill() {
		return playerKilling;
	}

	public IoSession getSession() {
		return session;
	}

	public Potions getPotions() {
		return potions;
	}

	public PotionMixing getPotMixing() {
		return potionMixing;
	}

	public Food getFood() {
		return food;
	}

	/**
	 * Skill Constructors
	 */
	public Slayer getSlayer() {
		return slayer;
	}
		
	public PouchCreation PouchCreation() {
		return PouchCreation;
	}
	
	public PlayerAction getPlayerAction() { 
		return playeraction;
	}

	public DwarfMultiCannon getCannon() {
		return cannon;
	}

	public Tanning getTanning() {
		return tan;
	}

	public Magic getMagic() {
		return magic;
	}

	public FlaxStringer getFlaxStringer() {
		return flax;
	}

	public Fishing getFishing() {
		return fish;
	}

	public Crafting getCrafting() {
		return crafting;
	}

	public Smithing getSmithing() {
		return smith;
	}

	public Farming getFarming() {
		return farming;
	}

	public Firemaking getFiremaking() {
		return firemaking;
	}

	public SmithingInterface getSmithingInt() {
		return smithInt;
	}

	public Prayer getPrayer() {
		return prayer;
	}

	public Curse getCurse() {
		return curse;
	}
	
	public Deflects getDeflects() {
		return deflects;
	}
	
	public CurseEffects getEffects() {
		return curseeffects;
	}

	/*
	 * public Fletching getFletching() { return fletching; }
	 */

	/**
	 * End of Skill Constructors
	 */

	public void queueMessage(Packet arg1) {
		synchronized (queuedPackets) {
			// if (arg1.getId() != 41)
			queuedPackets.add(arg1);
			// else
			// processPacket(arg1);
		}
	}

	public synchronized boolean processQueuedPackets() {
		Packet p = null;
		synchronized (queuedPackets) {
			p = queuedPackets.poll();
		}
		if (p == null) {
			return false;
		}
		inStream.currentOffset = 0;
		packetType = p.getId();
		packetSize = p.getLength();
		inStream.buffer = p.getData();
		if (packetType > 0) {
			// sendMessage("PacketType: " + packetType);
			PacketHandler.processPacket(this, packetType, packetSize);
		}
		timeOutCounter = 0;
		return true;
	}

	public synchronized boolean processPacket(Packet p) {
		synchronized (this) {
			if (p == null) {
				return false;
			}
			inStream.currentOffset = 0;
			packetType = p.getId();
			packetSize = p.getLength();
			inStream.buffer = p.getData();
			if (packetType > 0) {
				// sendMessage("PacketType: " + packetType);
				PacketHandler.processPacket(this, packetType, packetSize);
			}
			timeOutCounter = 0;
			return true;
		}
	}

	public void correctCoordinates() {
		if (inPcGame()) {
			getPA().movePlayer(2657, 2639, 0);
		}
		if (inFightCaves()) {
			getPA().movePlayer(absX, absY, playerId * 4);
			sendMessage("Your wave will start in 10 seconds.");
			EventManager.getSingleton().addEvent(new Event() {
				public void execute(EventContainer c) {
					Server.fightCaves.spawnNextWave((Client) Server.playerHandler.players[playerId]);
					c.stop();
				}
			}, 10000);

		}

	}

}

