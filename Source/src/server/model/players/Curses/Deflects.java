package server.model.players.Curses;

import server.model.players.Hit.CombatType;
import server.model.players.*;
import server.Config;
import server.Server;
import server.util.Misc;

	/**
	 * Handles Deflects.
	 * This version (For now).
	 **/

public class Deflects {
	
	private Client client;
		public Deflects(Client client) {
			this.client = client;
		}

				/*
				* Handles Curse Magic Protection Prayers Deflecting
				*/
			
				public static void handleMageDeflects(Client c, int i, int damage) {
				Client o = (Client) Server.playerHandler.players[i];
					if(o.curseActive[7]) { // protect mage
						if (damage > 0 && Server.playerHandler.players[i].curseActive[7]) {
							Client c2 = (Client)Server.playerHandler.players[i];
								int recDamage = damage/40 + 1;
					if (!c.getHitUpdateRequired()) {
								c.hitDiff = new Hit(recDamage, CombatType.DEFLECT);
								c2.gfx0(2227);
								c2.startAnimation(12573);
								c.setHitUpdateRequired(true);				
					} else if (!c.getHitUpdateRequired2()) {
								c.hitDiff2 = new Hit(recDamage, CombatType.DEFLECT);
								c.setHitUpdateRequired2(true);
							}
							c.dealDamage(recDamage);
							c.updateRequired = true;
							}	
							return;
						}
					}
				
				/*
				 * Handles Curse Ranged Protection Prayers Deflecting
				 */
				
				public static void handleRangeDeflects(Client c, int i, int damage) {
				Client o = (Client) Server.playerHandler.players[i];
					if(o.curseActive[8]) { // protect range
						if (damage > 0 && Server.playerHandler.players[i].curseActive[8]) {
							Client c2 = (Client)Server.playerHandler.players[i];
								int recDamage = damage/40 + 1;
					if (!c.getHitUpdateRequired()) {
								c.hitDiff = new Hit(recDamage, CombatType.DEFLECT);
								c2.gfx0(2229);
								c2.startAnimation(12573);
								c.setHitUpdateRequired(true);				
					} else if (!c.getHitUpdateRequired2()) {
								c.hitDiff2 = new Hit(recDamage, CombatType.DEFLECT);
								c.setHitUpdateRequired2(true);
							}
								c.dealDamage(recDamage);
								c.updateRequired = true;
							}	
							return;
						}
					}
					
					/*
					 * Handles Curse Melee Protection Prayers Deflecting
					 */
						
				public static void handleMeleeDeflects(Client c, int i, int damage) {		
				Client o = (Client) Server.playerHandler.players[i];
					if(o.curseActive[9]) {
						if (damage > 0 && Server.playerHandler.players[i].curseActive[9]) {
							Client c2 = (Client)Server.playerHandler.players[i];
								int recDamage = damage/40 + 1;
					if (!c.getHitUpdateRequired()) {
								c.hitDiff = new Hit(recDamage, CombatType.DEFLECT);
								c2.gfx0(2230);
								c2.startAnimation(12573);
								c.setHitUpdateRequired(true);				
					} else if (!c.getHitUpdateRequired2()) {
								c.hitDiff2 = new Hit(recDamage, CombatType.DEFLECT);
								c.setHitUpdateRequired2(true);
							}
								c.dealDamage(recDamage);
								c.updateRequired = true;
							}	
							return;
						}
					}
				}