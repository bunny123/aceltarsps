package server.model.players.packets;

import server.model.players.Client;
import server.model.players.PacketType;
import server.model.players.combat.range.DwarfMultiCannon;
import server.model.npcs.*;
import server.model.minigames.PyramidePlunder;
import server.model.players.skills.Herblore;
import server.event.*;
import server.model.players.*;


/**
 * Clicking an item, bury bone, eat food etc
 **/
public class ClickItem implements PacketType {

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		int junk = c.getInStream().readSignedWordBigEndianA();
		int itemSlot = c.getInStream().readUnsignedWordA();
		int itemId = c.getInStream().readUnsignedWordBigEndian();
		if (itemId != c.playerItems[itemSlot] - 1) {
		
			return;	
		}
		switch (itemId) {
		case 6:
				c.getCannon().setUpCannon();
			break;
			}
			/*if(itemId >= 15052 && itemId <= 15053) {
			if(c.inDuelArena()) {
				c.sendMessage("Pouches are disabled in duels!");
				return;
		}
		if(itemId >= 15015 && itemId <= 15016) {
			c.getItems().deleteItem(15015,999999999);
			c.getItems().deleteItem(15016,999999999);
		}
		if(itemId >= 15052 && itemId <= 15053) {
		    if(c.playerLevel[8] >= 99 && c.playerLevel[11] >= 99 && c.playerLevel[10] >= 99 && c.playerLevel[7] >= 99
        	&& c.playerLevel[12] >= 99 && c.playerLevel[21] >= 99 && c.playerLevel[21] >= 99 && c.playerLevel[18] >= 99 && c.playerLevel[17] >= 99 && c.playerLevel[16] >= 99 && c.playerLevel[15] >= 99 && c.playerLevel[9] >= 99) {
			if (!c.hasNpc) {
				c.gfx0(1738);
				c.hasNpc = true;
				Server.npcHandler.spawnNpc3(c, Server.npcHandler.summonItemId(itemId), c.absX, c.absY-1, c.heightLevel, 0, 120, 25, 200, 200, true, false, true);
				c.getItems().deleteItem(itemId, itemSlot, c.playerItemsN[itemSlot]);
		} else {
				c.sendMessage("You alredy summoned an NPC");
					}
				}
			}
		}*/
		
		
		if (itemId >= 5509 && itemId <= 5514) {
			int pouch = -1;
			int a = itemId;
			if (a == 5509)
				pouch = 0;
			if (a == 5510)
				pouch = 1;
			if (a == 5512)
				pouch = 2;
			if (a == 5514)
				pouch = 3;
			c.getPA().fillPouch(pouch);
			return;
		}
		
		if (itemId > 15085 && itemId < 15102){
			c.useDice(itemId, false);
		}
		if (itemId == 15084)
		{//dice bag
			c.diceID = itemId;
			c.getDH().sendDialogues(106, 0);
		}
		
		/*if (c.getFood().isFood(itemId))
			c.getFood().eat(itemId,itemSlot);
		} else if(Herblore.isHerb(c, itemId)) {
			Herblore.cleanTheHerb(c, itemId);
		
		//ScriptManager.callFunc("itemClick_"+itemId, c, itemId, itemSlot);
		if (c.getPotions().isPotion(itemId))
			c.getPotions().handlePotion(itemId,itemSlot);*/
			if (c.getFood().isFood(itemId)) {
			c.getFood().eat(itemId, itemSlot);
		} else if (c.getPotions().isPotion(itemId)) {
			c.getPotions().handlePotion(itemId, itemSlot);
		} else if(Herblore.isHerb(c, itemId)) {
			Herblore.cleanTheHerb(c, itemId);
		}
		if (c.getPrayer().readBone(itemId))
			c.getPrayer().boneOnGround(itemId);
		if (itemId == 952) {
			if(c.inArea(3553, 3301, 3561, 3294)) {
				c.teleTimer = 3;
				c.newLocation = 1;
			} else if(c.inArea(3550, 3287, 3557, 3278)) {
				c.teleTimer = 3;
				c.newLocation = 2;
			} else if(c.inArea(3561, 3292, 3568, 3285)) {
				c.teleTimer = 3;
				c.newLocation = 3;
			} else if(c.inArea(3570, 3302, 3579, 3293)) {
				c.teleTimer = 3;
				c.newLocation = 4;
			} else if(c.inArea(3571, 3285, 3582, 3278)) {
				c.teleTimer = 3;
				c.newLocation = 5;
			} else if(c.inArea(3562, 3279, 3569, 3273)) {
				c.teleTimer = 3;
				c.newLocation = 6;
			}
		}
	}

}
