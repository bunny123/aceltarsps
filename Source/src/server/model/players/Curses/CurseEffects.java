package server.model.players.Curses;

import server.model.players.Hit.CombatType;
import server.Config;
import server.Server;
import server.util.Misc;
import server.model.players.*;
import server.model.npcs.*;
import server.event.*;
import server.model.players.skills.*;

/**
 * Handles Curse Effects
 * 4:30 - 5:41 3/1/2012
 **/ 

public class CurseEffects {
	
	private Client c;
		public CurseEffects(Client c) {
			this.c = c;
		}
	
		public void deflectDamage(int damage) {
                int damage2 = 0;
                if (damage < 10)
                        damage2 = 0;
                else 
                        damage2 = damage/10;
                c.dealDamage(damage2);
        }
        
        
        
        public void applyLeeches(int index) {
                if (Misc.random(20) == 0) {
                        leechAttack(index);
                }
                if (Misc.random(23) == 0) {
                        leechDefence(index);
                }
                if (Misc.random(27) == 0) {
                        leechStrength(index);
                }
                if (Misc.random(29) == 0) {
                        leechSpecial(index);
                }
                if (Misc.random(30) == 0) {
                        leechRanged(index);
                }
                if (Misc.random(31) == 0) {
                        leechMagic(index);
                }
                if (Misc.random(32) == 0) {
                        leechEnergy(index);
                }
        }
		
       public void applySaps(int index) {
               if (Misc.random(1) == 0) {
                        sapWarrior(index);
                }
				if (Misc.random(1) == 0) {
                        sapRanged(index);
                }
				if (Misc.random(1) == 0) {
                        sapMage(index);
                }
        }
		
			public void npcSapWarrior(int index) {
               if (!c.curseActive[1])
                        return;
				if (c.oldNpcIndex > 0) {
				if (NPCHandler.npcs[c.oldNpcIndex] != null) {
				final int pX = c.getX();
				final int pY = c.getY();
				final int nX = NPCHandler.npcs[c.oldNpcIndex].getX();
				final int nY = NPCHandler.npcs[c.oldNpcIndex].getY();
				final int offX = (pY - nY) * -1;
				final int offY = (pX - nX) * -1;
                    c.startAnimation(12569);
                    c.getPA().createPlayersProjectile(pX, pY, offX, offY, 50, 45, 2215, 43, 31, - c.oldNpcIndex - 1, 1);
                    c.leechAttackDelay = 2;
                    EventManager.getSingleton().addEvent(new Event() {
                        @Override
					public void execute(EventContainer s) {
                        if (c.leechAttackDelay > 0) {
                            c.leechAttackDelay--;
                        }
						if (c.oldNpcIndex > 0) {
						if (NPCHandler.npcs[c.oldNpcIndex] != null) {
                        if (c.leechAttackDelay == 1) {
                                NPCHandler.npcs[c.oldNpcIndex].gfx0(2216);
						if (c.attackMultiplier < 1.10) {
								c.attackMultiplier += .10;
                        }
						if (c.defenceMultiplier < 1.10) {
                                 c.defenceMultiplier += .5;
								}
							}
						}
					}
                        if (c.leechAttackDelay == 0) {  
                                s.stop();
                                }
							}
						}, 500);
					}
				}
			}
					
					public void npcSapMagic(int index) {
						if (!c.curseActive[3])
							return;
						if (c.oldNpcIndex > 0) {
						if (NPCHandler.npcs[c.oldNpcIndex] != null) {
							final int pX = c.getX();
							final int pY = c.getY();
							final int nX = NPCHandler.npcs[c.oldNpcIndex].getX();
							final int nY = NPCHandler.npcs[c.oldNpcIndex].getY();
							final int offX = (pY - nY) * -1;
							final int offY = (pX - nX) * -1;
								c.startAnimation(12569);
									c.getPA().createPlayersProjectile(pX, pY, offX, offY, 50, 45, 2221, 43, 31, - c.oldNpcIndex - 1, 1);
									c.leechAttackDelay = 2;
								EventManager.getSingleton().addEvent(new Event() {
									@Override
								public void execute(EventContainer s) {
                                        if (c.leechAttackDelay > 0) {
                                                c.leechAttackDelay--;
                                        }
										if (c.oldNpcIndex > 0) {
										if (NPCHandler.npcs[c.oldNpcIndex] != null) {
                                        if (c.leechAttackDelay == 1) {
                                                NPCHandler.npcs[c.oldNpcIndex].gfx0(2222);
										if (c.attackMultiplier < 1.10) {
												c.attackMultiplier += .10;
										}
										if (c.defenceMultiplier < 1.10) {
                                                c.defenceMultiplier += .5;
                                        }
									}
								}
							}
                                        if (c.leechAttackDelay == 0) {  
                                                s.stop();
                                        }
									}
								}, 500);
							}
						}
					}
					
					public void npcSapRange(int index) {
						if (!c.curseActive[2])
							return;
						if (c.oldNpcIndex > 0) {
						if (NPCHandler.npcs[c.oldNpcIndex] != null) {
						final int pX = c.getX();
						final int pY = c.getY();
						final int nX = NPCHandler.npcs[c.oldNpcIndex].getX();
						final int nY = NPCHandler.npcs[c.oldNpcIndex].getY();
						final int offX = (pY - nY) * -1;
						final int offY = (pX - nX) * -1;
							c.startAnimation(12569);
							c.getPA().createPlayersProjectile(pX, pY, offX, offY, 50, 45, 2218, 43, 31, - c.oldNpcIndex - 1, 1);
							c.leechAttackDelay = 2;
                        EventManager.getSingleton().addEvent(new Event() {
							@Override
						public void execute(EventContainer s) {
                            if (c.leechAttackDelay > 0) {
									c.leechAttackDelay--;
							}
							if (c.oldNpcIndex > 0) {
							if (NPCHandler.npcs[c.oldNpcIndex] != null) {
                            if (c.leechAttackDelay == 1) {
                                    NPCHandler.npcs[c.oldNpcIndex].gfx0(2219);
							if (c.attackMultiplier < 1.10) {
									c.attackMultiplier += .10;
                            }
							if (c.defenceMultiplier < 1.10) {
                                    c.defenceMultiplier += .5;
									}
								}
							}
						}
                            if (c.leechAttackDelay == 0) {  
                                    s.stop();
                                    }
								}
							}, 500);
						}
					}
				}
		
		public void sapWarrior(int index) {
                if (!c.curseActive[1])
                        return;
                if (Server.playerHandler.players[index] != null) { 
                        final Client c2 = (Client)Server.playerHandler.players[index];
                        final int pX = c.getX();
                        final int pY = c.getY();
                        final int oX = c2.getX();
                        final int oY = c2.getY();
                        int offX = (pY - oY)* -1;
                        int offY = (pX - oX)* -1;
                        c.startAnimation(12569);
                        c.getPA().createPlayersProjectile(pX, pY, offX, offY, 50, 45, 2215, 43, 31, - c.oldPlayerIndex - 1, 1);
                        c.sapWDelay = 2;
                        EventManager.getSingleton().addEvent(new Event() {
                                @Override
								public void execute(EventContainer s) {
                                        if (c.sapWDelay > 0) {
                                                c.sapWDelay--;
                                        }
                                        if (c.sapWDelay == 1) {
                                                c2.gfx0(2216);
                                                if (c.attackMultiplier < 1.10) {
                                                        c.attackMultiplier += .10;
                                                }
                                                if (c2.attackMultiplier > 0.80) {
                                                        c2.attackMultiplier -= .10;
                                                }
												if (c.defenceMultiplier < 1.10) {
                                                        c.defenceMultiplier += .10;
                                                }
                                                if (c2.defenceMultiplier > 0.80) {
                                                        c2.defenceMultiplier -= .10;
                                                }
												if (c.strengthMultiplier < 1.10) {
                                                        c.strengthMultiplier += .10;
                                                }
                                                if (c2.strengthMultiplier > 0.80) {
                                                        c2.strengthMultiplier -= .10;
                                                }
                                        }
                                        if (c.sapWDelay == 0) {  
                                                s.stop();
                                        }
									}
								}, 500);
							}
						}
		
		public void sapMage(int index) {
                if (!c.curseActive[3])
                        return;
                if (Server.playerHandler.players[index] != null) { 
                        final Client c2 = (Client)Server.playerHandler.players[index];
                        final int pX = c.getX();
                        final int pY = c.getY();
                        final int oX = c2.getX();
                        final int oY = c2.getY();
                        int offX = (pY - oY)* -1;
                        int offY = (pX - oX)* -1;
                        c.startAnimation(12569);
                        c.getPA().createPlayersProjectile(pX, pY, offX, offY, 50, 45, 2221, 43, 31, - c.oldPlayerIndex - 1, 1);
                        c.sapMDelay = 2;
                        EventManager.getSingleton().addEvent(new Event() {
                                @Override
								public void execute(EventContainer s) {
                                        if (c.sapMDelay > 0) {
                                                c.sapMDelay--;
                                        }
                                        if (c.sapMDelay == 1) {
                                                c2.gfx0(2222);
                                                if (c.attackMultiplier < 1.10) {
                                                        c.attackMultiplier += .10;
                                                }
                                                if (c2.attackMultiplier > 0.80) {
                                                        c2.attackMultiplier -= .10;
                                                }
												if (c.defenceMultiplier < 1.10) {
                                                        c.defenceMultiplier += .10;
                                                }
                                                if (c2.defenceMultiplier > 0.80) {
                                                        c2.defenceMultiplier -= .10;
                                                }
                                        }
                                        if (c.sapMDelay == 0) {  
                                                s.stop();
                                        }
                                }
                        }, 500);
                }
        }
		
		public void sapRanged(int index) {
                if (!c.curseActive[2])
                        return;
                if (Server.playerHandler.players[index] != null) { 
                        final Client c2 = (Client)Server.playerHandler.players[index];
                        final int pX = c.getX();
                        final int pY = c.getY();
                        final int oX = c2.getX();
                        final int oY = c2.getY();
                        int offX = (pY - oY)* -1;
                        int offY = (pX - oX)* -1;
                        c.startAnimation(12569);
                        c.getPA().createPlayersProjectile(pX, pY, offX, offY, 50, 45, 2218, 43, 31, - c.oldPlayerIndex - 1, 1);
                        c.sapRDelay = 2;
                        EventManager.getSingleton().addEvent(new Event() {
                                @Override
								public void execute(EventContainer s) {
                                        if (c.sapRDelay > 0) {
                                                c.sapRDelay--;
                                        }
                                        if (c.sapRDelay == 1) {
                                                c2.gfx0(2219);
                                                if (c.attackMultiplier < 1.10) {
                                                        c.attackMultiplier += .10;
                                                }
                                                if (c2.attackMultiplier > 0.80) {
                                                        c2.attackMultiplier -= .10;
                                                }
												if (c.defenceMultiplier < 1.10) {
                                                        c.defenceMultiplier += .10;
                                                }
                                                if (c2.defenceMultiplier > 0.80) {
                                                        c2.defenceMultiplier -= .10;
                                                }
                                        }
                                        if (c.sapRDelay == 0) {  
                                                s.stop();
                                        }
                                }
                        }, 500);
                }
        }
		
        public void leechAttack(int index) {
                if (!c.curseActive[10])
                        return;
                if (Server.playerHandler.players[index] != null) { 
                        final Client c2 = (Client)Server.playerHandler.players[index];
                        final int pX = c.getX();
                        final int pY = c.getY();
                        final int oX = c2.getX();
                        final int oY = c2.getY();
                        int offX = (pY - oY)* -1;
                        int offY = (pX - oX)* -1;
                        c.sendMessage("You leech your opponent's attack.");
                        c.startAnimation(12575);
						c.gfx0(2234);
                        c.getPA().createPlayersProjectile(pX, pY, offX, offY, 50, 45, 2231, 43, 31, - c.oldPlayerIndex - 1, 1);
                        c.leechAttackDelay = 2;
                        EventManager.getSingleton().addEvent(new Event() {
                                @Override
								public void execute(EventContainer s) {
                                        if (c.leechAttackDelay > 0) {
                                                c.leechAttackDelay--;
                                        }
                                        if (c.leechAttackDelay == 1) {
                                                c2.gfx0(2232);
                                                if (c.attackMultiplier < 1.10) {
                                                        c.attackMultiplier += 0.01;
                                                }
                                                if (c2.attackMultiplier > 0.80) {
                                                        c2.attackMultiplier -= 0.01;
                                                }
                                        }
                                        if (c.leechAttackDelay == 0) {  
                                                s.stop();
                                        }
                                }
                        }, 500);
                }
        }
        
        public void leechRanged(int index) {
                if (!c.curseActive[11])
                        return;
                if (Server.playerHandler.players[index] != null) { 
                        final Client c2 = (Client)Server.playerHandler.players[index];
                        final int pX = c.getX();
                        final int pY = c.getY();
                        final int oX = c2.getX();
                        final int oY = c2.getY();
                        int offX = (pY - oY)* -1;
                        int offY = (pX - oX)* -1;
                        c.sendMessage("You leech your opponent's range.");
                        c.startAnimation(12575);
                        c.getPA().createPlayersProjectile(pX, pY, offX, offY, 50, 45, 2236, 43, 31, - c.oldPlayerIndex - 1, 0);
                        c.leechRangedDelay = 2;
                        EventManager.getSingleton().addEvent(new Event() {
                                @Override
								public void execute(EventContainer s) {
                                        if (c.leechRangedDelay > 0) {
                                                c.leechRangedDelay--;
                                        }
                                        if (c.leechRangedDelay == 1) {
                                                c2.gfx0(2238);
                                                if (c.rangedMultiplier < 1.10) {
                                                        c.rangedMultiplier += 0.01;
                                                }
                                                if (c2.rangedMultiplier > 0.80) {
                                                        c2.rangedMultiplier -= 0.01;
                                                }
                                        }
                                        if (c.leechRangedDelay == 0) {  
                                                s.stop();
                                        }
                                }
                        }, 500);
                }
        }
        
        public void leechMagic(int index) {
                if (!c.curseActive[12])
                        return;
                if (Server.playerHandler.players[index] != null) { 
                        final Client c2 = (Client)Server.playerHandler.players[index];
                        final int pX = c.getX();
                        final int pY = c.getY();
                        final int oX = c2.getX();
                        final int oY = c2.getY();
                        int offX = (pY - oY)* -1;
                        int offY = (pX - oX)* -1;
                        c.sendMessage("You leech your opponent's magic.");
                        c.startAnimation(12575);
                        c.getPA().createPlayersProjectile(pX, pY, offX, offY, 50, 45, 2240, 43, 31, - c.oldPlayerIndex - 1, 2);
                        c.leechMagicDelay = 2;
                        EventManager.getSingleton().addEvent(new Event() {
                                @Override
								public void execute(EventContainer s) {
                                        if (c.leechMagicDelay > 0) {
                                                c.leechMagicDelay--;
                                        }
                                        if (c.leechMagicDelay == 1) {
                                                c2.gfx0(2242);
                                                if (c.magicMultiplier < 1.10) {
                                                        c.magicMultiplier += 0.01;
                                                }
                                                if (c2.magicMultiplier > 0.80) {
                                                        c2.magicMultiplier -= 0.01;
                                                }
                                        }
                                        if (c.leechMagicDelay == 0) {   
                                                s.stop();
                                        }
                                }
                        }, 500);
                }
        }
        
        public void leechDefence(int index) {
                if (!c.curseActive[13])
                        return;
                if (Server.playerHandler.players[index] != null) { 
                        final Client c2 = (Client)Server.playerHandler.players[index];
                        final int pX = c.getX();
                        final int pY = c.getY();
                        final int oX = c2.getX();
                        final int oY = c2.getY();
                        int offX = (pY - oY)* -1;
                        int offY = (pX - oX)* -1;
                        c.sendMessage("You leech your opponent's defence.");
                        c.startAnimation(12575);
                        c.getPA().createPlayersProjectile(pX, pY, offX, offY, 50, 45, 2244, 43, 31, - c.oldPlayerIndex - 1, 3);
                        c.leechDefenceDelay = 2;
                        EventManager.getSingleton().addEvent(new Event() {
                                @Override
								public void execute(EventContainer s) {
                                        if (c.leechDefenceDelay > 0) {
                                                c.leechDefenceDelay--;
                                        }
                                        if (c.leechDefenceDelay == 1) {
                                                c2.gfx0(2246);
                                                if (c.defenceMultiplier < 1.10) {
                                                        c.defenceMultiplier += 0.01;
                                                }
                                                if (c2.defenceMultiplier > 0.80) {
                                                        c2.defenceMultiplier -= 0.01;
                                                }
                                        }
                                        if (c.leechDefenceDelay == 0) { 
                                                s.stop();
                                        }
                                }
                        }, 500);
                }
        }
        
        public void leechStrength(int index) {
                if (!c.curseActive[14])
                        return;
                if (Server.playerHandler.players[index] != null) { 
                        final Client c2 = (Client)Server.playerHandler.players[index];
                        final int pX = c.getX();
                        final int pY = c.getY();
                        final int oX = c2.getX();
                        final int oY = c2.getY();
                        int offX = (pY - oY)* -1;
                        int offY = (pX - oX)* -1;
                        c.sendMessage("You leech your opponent's strength.");
                        c.startAnimation(12575);
                        c.getPA().createPlayersProjectile(pX, pY, offX, offY, 50, 45, 2248, 43, 31, - c.oldPlayerIndex - 1, 4);
                        c.leechStrengthDelay = 2;
                        EventManager.getSingleton().addEvent(new Event() {
                                @Override
								public void execute(EventContainer s) {
                                        if (c.leechStrengthDelay > 0) {
                                                c.leechStrengthDelay--;
                                        }
                                        if (c.leechStrengthDelay == 1) {
                                                c2.gfx0(2250);
                                                if (c.strengthMultiplier < 1.10) {
                                                        c.strengthMultiplier += 0.01;
                                                }
                                                if (c2.strengthMultiplier > 0.80) {
                                                        c2.strengthMultiplier -= 0.01;
                                                }
                                        }
                                        if (c.leechStrengthDelay == 0) {        
                                                s.stop();
                                        }
                                }
                        }, 500);
                }
        }
        
        public void leechEnergy(int index) {
                if (!c.curseActive[15])
                        return;
                if (Server.playerHandler.players[index] != null) { 
                        final Client c2 = (Client)Server.playerHandler.players[index];
                        final int pX = c.getX();
                        final int pY = c.getY();
                        final int oX = c2.getX();
                        final int oY = c2.getY();
                        int offX = (pY - oY)* -1;
                        int offY = (pX - oX)* -1;
                        c.sendMessage("You leech your opponent's run energy.");
                        c.startAnimation(12575);
                        c.getPA().createPlayersProjectile(pX, pY, offX, offY, 50, 45, 2252, 43, 31, - c.oldPlayerIndex - 1, 5);
                        c.leechEnergyDelay = 2;
                        EventManager.getSingleton().addEvent(new Event() {
                                @Override
								public void execute(EventContainer s) {
                                        if (c.leechEnergyDelay > 0) {
                                                c.leechEnergyDelay--;
                                        }
                                        if (c.leechEnergyDelay == 1) {
                                                c2.gfx0(2254);
                                        }
                                        if (c.leechEnergyDelay == 0) {  
                                                s.stop();
                                        }
                                }
                        }, 500);
                }
        }
        
        public void leechSpecial(int index) {
                if (!c.curseActive[16])
                        return;
                if (Server.playerHandler.players[index] != null) { 
                        final Client c2 = (Client)Server.playerHandler.players[index];
                        final int pX = c.getX();
                        final int pY = c.getY();
                        final int oX = c2.getX();
                        final int oY = c2.getY();
                        int offX = (pY - oY)* -1;
                        int offY = (pX - oX)* -1;
                        c.sendMessage("You leech your opponent's special attack.");
                        c.startAnimation(12575);
                        c.getPA().createPlayersProjectile(pX, pY, offX, offY, 50, 45, 2256, 43, 31, - c.oldPlayerIndex - 1, 6);
                        c.leechSpecialDelay = 2;
                        EventManager.getSingleton().addEvent(new Event() {
                                @Override
								public void execute(EventContainer s) {
                                        if (c.leechSpecialDelay > 0) {
                                                c.leechSpecialDelay--;
                                        }
                                        if (c.leechSpecialDelay == 1) {
                                                c2.gfx0(2258);
                                                if (c.specAmount >= 10)
                                                        return;
                                                if (c2.specAmount <= 0)
                                                        return;
                                                c.specAmount += 1;
                                                c2.specAmount -= 1;
                                                c2.sendMessage("Your special attack has been drained.");
                                        }
                                        if (c.leechSpecialDelay == 0) { 
                                                s.stop();
                                        }
                                }
                        }, 500);
                }
        }

        public void npcleechAttack(int index) {
                if (!c.curseActive[10])
                        return;
			if (c.oldNpcIndex > 0) {
			if (NPCHandler.npcs[c.oldNpcIndex] != null) {
			final int pX = c.getX();
			final int pY = c.getY();
			final int nX = NPCHandler.npcs[c.oldNpcIndex].getX();
			final int nY = NPCHandler.npcs[c.oldNpcIndex].getY();
			final int offX = (pY - nY) * -1;
			final int offY = (pX - nX) * -1;
                        c.sendMessage("You leech your opponent's attack.");
                        c.startAnimation(12575);
						c.gfx0(2234);
                        c.getPA().createPlayersProjectile(pX, pY, offX, offY, 50, 45, 2231, 43, 31, - c.oldNpcIndex - 1, 1);
                        c.leechAttackDelay = 2;
                        EventManager.getSingleton().addEvent(new Event() {
                                @Override
								public void execute(EventContainer s) {
                                        if (c.leechAttackDelay > 0) {
                                                c.leechAttackDelay--;
                                        }
					if (c.oldNpcIndex > 0) {
						if (NPCHandler.npcs[c.oldNpcIndex] != null) {
                                        if (c.leechAttackDelay == 1) {
                                                NPCHandler.npcs[c.oldNpcIndex].gfx0(2232);
                                                if (c.attackMultiplier < 1.10) {
                                                        c.attackMultiplier += 0.01;
                                                }
                                        }
				}
			}
                                        if (c.leechAttackDelay == 0) {  
                                                s.stop();
                                        }
                                }
                        }, 500);
                }
        }
	}
        
        public void npcleechRanged(int index) {
                if (!c.curseActive[11])
                        return;
			if (c.oldNpcIndex > 0) {
			if (NPCHandler.npcs[c.oldNpcIndex] != null) {
			final int pX = c.getX();
			final int pY = c.getY();
			final int nX = NPCHandler.npcs[c.oldNpcIndex].getX();
			final int nY = NPCHandler.npcs[c.oldNpcIndex].getY();
			final int offX = (pY - nY) * -1;
			final int offY = (pX - nX) * -1;
                        c.sendMessage("You leech your opponent's range.");
                        c.startAnimation(12575);
                        c.getPA().createPlayersProjectile(pX, pY, offX, offY, 50, 45, 2236, 43, 31, - c.oldNpcIndex - 1, 0);
                        c.leechRangedDelay = 2;
                        EventManager.getSingleton().addEvent(new Event() {
                                @Override
								public void execute(EventContainer s) {
                                        if (c.leechRangedDelay > 0) {
                                                c.leechRangedDelay--;
                                        }
					if (c.oldNpcIndex > 0) {
						if (NPCHandler.npcs[c.oldNpcIndex] != null) {
                                        if (c.leechRangedDelay == 1) {
                                                NPCHandler.npcs[c.oldNpcIndex].gfx0(2238);
                                                if (c.rangedMultiplier < 1.10) {
                                                        c.rangedMultiplier += 0.01;
                                                }
                                        }
				}
			}
                                        if (c.leechRangedDelay == 0) {  
                                                s.stop();
                                        }
                                }
                        }, 500);
                }
        }
	}
        
        public void npcleechMagic(int index) {
                if (!c.curseActive[12])
                        return;
			if (c.oldNpcIndex > 0) {
			if (NPCHandler.npcs[c.oldNpcIndex] != null) {
			final int pX = c.getX();
			final int pY = c.getY();
			final int nX = NPCHandler.npcs[c.oldNpcIndex].getX();
			final int nY = NPCHandler.npcs[c.oldNpcIndex].getY();
			final int offX = (pY - nY) * -1;
			final int offY = (pX - nX) * -1;
                        c.sendMessage("You leech your opponent's magic.");
                        c.startAnimation(12575);
                        c.getPA().createPlayersProjectile(pX, pY, offX, offY, 50, 45, 2240, 43, 31, - c.oldNpcIndex - 1, 2);
                        c.leechMagicDelay = 2;
                        EventManager.getSingleton().addEvent(new Event() {
                                @Override
								public void execute(EventContainer s) {
                                        if (c.leechMagicDelay > 0) {
                                                c.leechMagicDelay--;
                                        }
					if (c.oldNpcIndex > 0) {
						if (NPCHandler.npcs[c.oldNpcIndex] != null) {
                                        if (c.leechMagicDelay == 1) {
                                                NPCHandler.npcs[c.oldNpcIndex].gfx0(2242);
                                                if (c.magicMultiplier < 1.10) {
                                                        c.magicMultiplier += 0.01;
                                                }
                                        }
				}
			}
                                        if (c.leechMagicDelay == 0) {   
                                                s.stop();
                                        }
                                }
                        }, 500);
                }
        }
	}
        
        public void npcleechDefence(int index) {
                if (!c.curseActive[13])
                        return;
			if (c.oldNpcIndex > 0) {
			if (NPCHandler.npcs[c.oldNpcIndex] != null) {
			final int pX = c.getX();
			final int pY = c.getY();
			final int nX = NPCHandler.npcs[c.oldNpcIndex].getX();
			final int nY = NPCHandler.npcs[c.oldNpcIndex].getY();
			final int offX = (pY - nY) * -1;
			final int offY = (pX - nX) * -1;
                        c.sendMessage("You leech your opponent's defence.");
                        c.startAnimation(12575);
                        c.getPA().createPlayersProjectile(pX, pY, offX, offY, 50, 45, 2244, 43, 31, - c.oldNpcIndex - 1, 3);
                        c.leechDefenceDelay = 2;
                        EventManager.getSingleton().addEvent(new Event() {
                                @Override
								public void execute(EventContainer s) {
                                        if (c.leechDefenceDelay > 0) {
                                                c.leechDefenceDelay--;
                                        }
					if (c.oldNpcIndex > 0) {
						if (NPCHandler.npcs[c.oldNpcIndex] != null) {
                                        if (c.leechDefenceDelay == 1) {
                                                NPCHandler.npcs[c.oldNpcIndex].gfx0(2246);
                                                if (c.defenceMultiplier < 1.10) {
                                                        c.defenceMultiplier += 0.01;
                                                }
                                        }
				}
			}
                                        if (c.leechDefenceDelay == 0) { 
                                                s.stop();
                                        }
                                }
                        }, 500);
                }
        }
	}
        
        public void npcleechStrength(int index) {
                if (!c.curseActive[14])
                        return; 
			if (c.oldNpcIndex > 0) {
			if (NPCHandler.npcs[c.oldNpcIndex] != null) {
			final int pX = c.getX();
			final int pY = c.getY();
			final int nX = NPCHandler.npcs[c.oldNpcIndex].getX();
			final int nY = NPCHandler.npcs[c.oldNpcIndex].getY();
			final int offX = (pY - nY) * -1;
			final int offY = (pX - nX) * -1;
                        c.sendMessage("You leech your opponent's strength.");
                        c.startAnimation(12575);
                        c.getPA().createPlayersProjectile(pX, pY, offX, offY, 50, 45, 2248, 43, 31, - c.oldNpcIndex - 1, 4);
                        c.leechStrengthDelay = 2;
                        EventManager.getSingleton().addEvent(new Event() {
                                @Override
								public void execute(EventContainer s) {
                                        if (c.leechStrengthDelay > 0) {
                                                c.leechStrengthDelay--;
                                        }
					if (c.oldNpcIndex > 0) {
						if (NPCHandler.npcs[c.oldNpcIndex] != null) {
                                        if (c.leechStrengthDelay == 1) {
                                                NPCHandler.npcs[c.oldNpcIndex].gfx0(2250);
                                                if (c.strengthMultiplier < 1.10) {
                                                        c.strengthMultiplier += 0.01;
                                                }
                                        }
				}
			}
                                        if (c.leechStrengthDelay == 0) {        
                                                s.stop();
                                        }
                                }
                        }, 500);
                }
        }
	}
        
        public void npcleechEnergy(int index) {
                if (!c.curseActive[15])
                        return;
			if (c.oldNpcIndex > 0) {
			if (NPCHandler.npcs[c.oldNpcIndex] != null) {
			final int pX = c.getX();
			final int pY = c.getY();
			final int nX = NPCHandler.npcs[c.oldNpcIndex].getX();
			final int nY = NPCHandler.npcs[c.oldNpcIndex].getY();
			final int offX = (pY - nY) * -1;
			final int offY = (pX - nX) * -1;
                        c.sendMessage("You leech your opponent's run energy.");
                        c.startAnimation(12575);
                        c.getPA().createPlayersProjectile(pX, pY, offX, offY, 50, 45, 2252, 43, 31, - c.oldNpcIndex - 1, 5);
                        c.leechEnergyDelay = 2;
                        EventManager.getSingleton().addEvent(new Event() {
                                @Override
								public void execute(EventContainer s) {
                                        if (c.leechEnergyDelay > 0) {
                                                c.leechEnergyDelay--;
                                        }
					if (c.oldNpcIndex > 0) {
						if (NPCHandler.npcs[c.oldNpcIndex] != null) {
                                        if (c.leechEnergyDelay == 1) {
                                                NPCHandler.npcs[c.oldNpcIndex].gfx0(2254);
                                        }
				}
			}
                                        if (c.leechEnergyDelay == 0) {  
                                                s.stop();
                                        }
                                }
                        }, 500);
                }
        }
	}
        
        public void npcleechSpecial(int index) {
                if (!c.curseActive[16])
                        return;
			if (c.oldNpcIndex > 0) {
			if (NPCHandler.npcs[c.oldNpcIndex] != null) {
			final int pX = c.getX();
			final int pY = c.getY();
			final int nX = NPCHandler.npcs[c.oldNpcIndex].getX();
			final int nY = NPCHandler.npcs[c.oldNpcIndex].getY();
			final int offX = (pY - nY) * -1;
			final int offY = (pX - nX) * -1;
                        c.sendMessage("You leech your opponent's special attack.");
                        c.startAnimation(12575);
                        c.getPA().createPlayersProjectile(pX, pY, offX, offY, 50, 45, 2256, 43, 31, - c.oldNpcIndex - 1, 6);
                        c.leechSpecialDelay = 2;
                        EventManager.getSingleton().addEvent(new Event() {
                                @Override
								public void execute(EventContainer s) {
                                        if (c.leechSpecialDelay > 0) {
                                                c.leechSpecialDelay--;
                                        }
					if (c.oldNpcIndex > 0) {
						if (NPCHandler.npcs[c.oldNpcIndex] != null) {
                                        if (c.leechSpecialDelay == 1) {
                                                NPCHandler.npcs[c.oldNpcIndex].gfx0(2258);
                                                if (c.specAmount >= 10)
                                                        return;
                                                c.specAmount += 1;
                                        }
				}
			}
                                        if (c.leechSpecialDelay == 0) { 
                                                s.stop();
                                        }
                                }
                        }, 500);
                }
        }
	}
}

