package server.model.players;

import server.model.players.*;
import server.Config;
import server.Server;
import server.util.Misc;
import server.model.players.Client;
import server.model.players.Hit.CombatType;
import server.model.players.PlayerSave;
import server.event.EventManager;
import server.model.npcs.NPC;
import server.model.npcs.NPCHandler;
import server.event.EventContainer;
import server.event.Event;
import server.model.players.CombatAssistant;

/**
 * Handles Summoning Familiar Specials
 * 6:30 - 7:05: 2/18/2012.
 **/
 

public class FamiliarSpecials {

private Client c;

	public FamiliarSpecials(Client Client) {
		this.c = Client;
	}
	
	public void handleNPCSumSpec() {
	switch(c.lastsummon) {
		case 6830:
		if(c.summAmount >= 5){
				if(c.specTimer <= 0){
				if (c.npcIndex > 0 && Server.npcHandler.npcs[c.npcIndex] != null) {
				int damage = Misc.random(15) + 5;
				c.startAnimation(7660);
				c.gfx0(1313);
				c.getPA().addSkillXP(1500, 24);
				Server.npcHandler.npcs[c.npcIndex].HP -= damage;
				Server.npcHandler.npcs[c.npcIndex].hitDiff = new Hit(damage, CombatType.MELEE);
				Server.npcHandler.npcs[c.npcIndex].hitUpdateRequired = true;
				Server.npcHandler.npcs[c.npcIndex].updateRequired = true;	
				c.specTimer += 60;
				c.summAmount -= 5;	
				} else {
					c.sendMessage("You were unable to use the special because you do not have any scrolls.");
				}

				} else {
					c.sendMessage("Your familiar has just used it's special you must wait "+c.specTimer+" seconds.");
				}
	
				} else { 
					c.sendMessage("You don't have enough special to use this.");
				}
			}
		}
				public void handlePlayerSumSpec() {
					switch(c.lastsummon) {
						case 6830:
							if(c.summAmount >= 5){
							if(c.specTimer <= 0){
							if(c.oldPlayerIndex > 0) {
							if(Server.playerHandler.players[c.oldPlayerIndex] != null) {
							if (c.playerIndex > 0 && Server.playerHandler.players[c.playerIndex] != null) {
							final int damage = Misc.random(15) + 5;
								c.startAnimation(7660);
								c.gfx0(1313);
								c.getPA().addSkillXP(1500, 24);
								Server.playerHandler.players[c.oldPlayerIndex].playerLevel[3] -= damage;
								Server.playerHandler.players[c.oldPlayerIndex].hitDiff2 = new Hit(damage, CombatType.MELEE);
								Server.playerHandler.players[c.oldPlayerIndex].hitUpdateRequired2 = true;
								Server.playerHandler.players[c.oldPlayerIndex].updateRequired = true;
								c.specTimer += 60;
								c.summAmount -= 5;						
							} else {
								c.sendMessage("You were unable to use the special because you do not have any scrolls.");
							}

							} else {
								c.sendMessage("Your familiar has just used it's special you must wait "+c.specTimer+" seconds.");
							}
	
							} else { 
								c.sendMessage("You don't have enough special to use this.");
							}
						}
					}
				}
			}
					public void handleFruitBat() {
					final int[] coords = new int[2];
					coords[0] = c.absX+1;
					coords[1] = c.absY;
					/*coordss[0] = c.absX-1;
					coordss[1] = c.absY;*/
						switch(c.lastsummon) {
							case 6817:
								if(c.summAmount >= 5){
								if(c.specTimer <= 0){
								c.startAnimation(7660);
								c.gfx50(1316);	
								Server.itemHandler.createGroundItem(c, 2114, coords[0], coords[1], 1, c.getId());
								//Server.itemHandler.createGroundItem(c, 5972, coordss[0], coordss[1], 1, c.getId());
								c.specTimer += 20;
								c.summAmount -= 5;
							} else {
								c.sendMessage("Your familiar has just used it's special you must wait "+c.specTimer+" seconds.");
							}
	
							} else {
								c.sendMessage("You don't have enough special to use this.");
							}
						}
					}
				public void handleSteelTitan() {
					switch(c.lastsummon) {
					case 7344:
					final int damage = Misc.random(30) + 10;
					int damage2 = damage *10;
					if(c.npcIndex > 0) {
					if(c.summAmount >= 5){
					if(c.specTimer <= 0){
							Server.npcHandler.npcs[c.npcIndex].hitUpdateRequired2 = true;
							Server.npcHandler.npcs[c.npcIndex].updateRequired = true;
							Server.npcHandler.npcs[c.npcIndex].hitDiff2 = new Hit(damage, CombatType.MELEE);
							Server.npcHandler.npcs[c.npcIndex].HP -= damage;
							c.sendMessage("Your <shad=15007744>Steel Titan</shad> Damages your opponent for <shad=15007744>"+ damage2 +"</shad> health.");
							c.startAnimation(1914);
							NPC opp = Server.npcHandler.npcs[c.npcIndex];//handles the titans gfx on the thing it is attacking
					 		if(opp != null) 
								opp.gfx0(1449);
							c.specTimer += 60;
							c.summAmount -= 5;
							} else {
								c.sendMessage("Your familiar has just used it's special you must wait "+c.specTimer+" seconds.");
							}
	
							} else {
								c.sendMessage("You don't have enough special to use this.");
							}
							
					if(c.summAmount >= 5){
					if(c.specTimer <= 0){
					} else if(c.oldPlayerIndex > 0 || c.playerIndex > 0) {
							Server.playerHandler.players[c.playerIndex].playerLevel[3] -= damage;
							Server.playerHandler.players[c.playerIndex].hitDiff2 = new Hit(damage, CombatType.MELEE);
							Server.playerHandler.players[c.playerIndex].hitUpdateRequired2 = true;
							Server.playerHandler.players[c.playerIndex].updateRequired = true;
							c.sendMessage("Your <shad=15007744>Steel Titan</shad> Damages your opponent for <shad=15007744>"+ damage2 +"</shad> health.");
							c.startAnimation(1914);
							Client opp = (Client) Server.playerHandler.players[c.playerIndex];//handles titans gfx on players
 								if(opp != null)
					      				opp.gfx0(1449);
							c.specTimer += 60;
							c.summAmount -= 5;
							} else {
								c.sendMessage("Your familiar has just used it's special you must wait "+c.specTimer+" seconds.");
							}
	
							} else {
								c.sendMessage("You don't have enough special to use this.");
							}
					}
				}
			}
		}
			