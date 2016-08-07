package server.model.players.packets;
import server.model.players.skills.Firemaking;

/**
 * @author Ryan / Lmctruck30
 */

import server.model.items.UseItem;
import server.model.players.Client;
import server.model.players.PacketType;
import server.model.players.skills.Fletching;
import server.model.players.skills.Herblore;

public class ItemOnItem implements PacketType {

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		int usedWithSlot = c.getInStream().readUnsignedWord();
		int itemUsedSlot = c.getInStream().readUnsignedWordA();
		int useWith = c.playerItems[usedWithSlot] - 1;
		int itemUsed = c.playerItems[itemUsedSlot] - 1;
		UseItem.ItemonItem(c, itemUsed, useWith);
		//int useWith = c.playerItems[usedWithSlot] - 1;
		//int itemUsed = c.playerItems[itemUsedSlot] - 1;

		if (Firemaking.playerLogs(c, itemUsed, useWith)) {
			Firemaking.grabData(c,itemUsed, useWith);
		} else if((useWith == 1511 || itemUsed == 1511) && (useWith == 946 || itemUsed == 946)) {
			Fletching.normal(c, itemUsed, useWith);
		} else if(useWith == 946 || itemUsed == 946) {
			Fletching.others(c, itemUsed, useWith);
		} else if(Herblore.mixPotion(c, itemUsed, useWith)) {
			Herblore.setUpUnfinished(c, itemUsed, useWith);
		} else if(Herblore.mixPotionNew(c, itemUsed, useWith)) {
			Herblore.setUpPotion(c, itemUsed, useWith);
		} else if(Fletching.arrows(c, itemUsed, useWith)) {
			Fletching.makeArrows(c, itemUsed, useWith);
		}
	}
}
