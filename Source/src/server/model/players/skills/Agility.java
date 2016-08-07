package server.model.players.skills;

import server.event.CycleEvent;
import server.event.CycleEventContainer;
import server.event.CycleEventHandler;
import server.model.players.Client;
 
public class Agility extends SkillHandler {

	private static void resetAnimation(Client c, int i) {
		c.isRunning2 = false;
		c.getPA().sendFrame36(173,0);
		c.playerWalkIndex = i;
		c.getPA().requestUpdates();
	}

	private static void setAnimationBack(Client c) {
		c.isRunning2 = true;
		c.getPA().sendFrame36(173,1);
		c.playerWalkIndex = 0x333;
		c.getPA().requestUpdates();
	}

	public static void walkAcross(final Client c, int anim, int length, int length1, int objectX, int objectY, int i) {
		if(c.stopPlayerPacket) {
			return;
		}
		if(System.currentTimeMillis() - c.agilityDelay > 2000) {
			c.getPA().walkTo(length,length1);
			resetAnimation(c, anim);
			c.agilityDelay = System.currentTimeMillis();
			c.getPA().addSkillXP(1000 * AGILITY_XP, c.playerAgility);
			c.stopPlayerPacket = true;
			CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
				@Override
				public void execute(CycleEventContainer container) {
					c.stopPlayerPacket = false;
					container.stop();
				}

				@Override
				public void stop() {
					setAnimationBack(c);
				}
			}, 7);
		}
	}

	public static void moveTele(final Client c, final int x, final int y, final int h, int anim, int i) {
		if(System.currentTimeMillis() - c.agilityDelay > 3000) {
			if(c.stopPlayerPacket) {
				return;
			}
			c.startAnimation(anim);
			c.stopPlayerPacket = true;
			CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
				@Override
				public void execute(CycleEventContainer container) {
					c.teleportToX = x;
					c.teleportToY = y;
					c.heightLevel = h;
					c.getPA().addSkillXP(900 * AGILITY_XP, c.playerAgility);
					c.stopPlayerPacket = false;
					container.stop();
				}

				@Override
				public void stop() {

				}
			}, 1);
			c.agilityDelay = System.currentTimeMillis();
		}
	}

	public static void completePipe(final Client c, int i) {
		if(c.stopPlayerPacket) {
			return;
		}
		if(c.absY == 3430) {
			if(System.currentTimeMillis() - c.agilityDelay > 4000) {
				c.getPA().walkTo(0,2);
				c.startAnimation(749);
				c.stopPlayerPacket = true;
				CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
					@Override
					public void execute(CycleEventContainer container) {
						c.teleportToX = c.absX;
						c.teleportToY = 3433;
						container.stop();
					}

					@Override
					public void stop() {

					}
				}, 2);
				CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
					@Override
					public void execute(CycleEventContainer container) {
						c.teleportToX = c.absX;
						c.teleportToY = 3437;
						c.startAnimation(748);
						c.stopPlayerPacket = false;
						container.stop();
					}

					@Override
					public void stop() {

					}
				}, 5);
				c.agilityDelay = System.currentTimeMillis();
				c.getPA().addSkillXP(1400 * AGILITY_XP, c.playerAgility);
			}
		}
	}

	public static void completeWildyPipe(final Client c) {
		if(c.stopPlayerPacket) {
			return;
		}
		final int[] coords = new int[2];

		if(c.absY <= 3945) {
			coords[0] = 10;
		} else {
			coords[0] = -10;
		}
		if(c.absY != 3937) {
			c.getPA().walkTo(c.absX - 3937, 0);
		}
		c.getPA().walkTo(0,coords[0]);
		c.isRunning2 = false;
		c.getPA().sendFrame36(173,0);
		c.startAnimation(749);
		c.stopPlayerPacket = true;
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				c.getPA().walkTo(0,coords[1]);
				container.stop();
			}
			@Override
			public void stop() {

			}
		}, 3);
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				c.teleportToX = c.absX;
				c.teleportToY = 3950;
				c.startAnimation(748);
				c.isRunning2 = true;
				c.getPA().sendFrame36(173,1);
				c.stopPlayerPacket = false;
				container.stop();
			}
			@Override
			public void stop() {
				c.getPA().addSkillXP(2000 * AGILITY_XP, c.playerAgility);
			}
		}, 10);
	}

	public static void swing(final Client c) {
		if(c.stopPlayerPacket) {
			return;
		}
		c.stopPlayerPacket = true;

		if(c.absY != 3953) {
			c.getPA().walkTo(c.absX - 3953, 0);
		}

		c.turnPlayerTo(3005, 3958);
		c.startAnimation(751);
		c.getPA().objectAnim(3005, 3952, 497, 10, 2);

		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				c.getPA().walkTo(0,1);
				container.stop();
			}
			@Override
			public void stop() {
				c.getPA().addSkillXP(2100 * AGILITY_XP, c.playerAgility);
			}
		}, 2);
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				c.teleportToX = 3005;//end
				c.teleportToY = 3958;
				container.stop();
			}
			@Override
			public void stop() {
				setAnimationBack(c);
				c.stopPlayerPacket = false;
			}
		}, 3);
	}

	public static void skippingStone(final Client c) {
		if(c.stopPlayerPacket) {
			return;
		}
		c.stopPlayerPacket = true;
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				c.startAnimation(769);
				if(c.absX <= 2997) {
					container.stop();
				}
			}
			@Override
			public void stop() {
				c.getPA().addSkillXP(2000 * AGILITY_XP, c.playerAgility);
			}
		}, 1);
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				c.teleportToX = c.absX-1;
				c.teleportToY = c.absY;
				if(c.absX <= 2997) {
					container.stop();
				}
			}
			@Override
			public void stop() {
				c.getPA().addSkillXP(1300 * AGILITY_XP, c.playerAgility);
				setAnimationBack(c);
				c.stopPlayerPacket = false;
			}
		}, 3);
	}
	public static void crumbleWallBarb(final Client c) {
		if(c.stopPlayerPacket) {
			return;
		}
		c.stopPlayerPacket = true;
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				c.startAnimation(741);
				if(c.absX <= 2536) {
					container.stop();
				}
				if(c.absX <= 2539) {
					container.stop();
				}
				if(c.absX <= 2542) {
					container.stop();
				}
			}
			@Override
			public void stop() {
			}
		}, 1);
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				c.teleportToX = c.absX+2;
				c.teleportToY = c.absY;
				c.agilityDelay = System.currentTimeMillis();
				c.getPA().addSkillXP(100 * AGILITY_XP, c.playerAgility);
				c.stopPlayerPacket = true;
				if(c.absX <= 2536) {
					container.stop();
				}
				if(c.absX <= 2539) {
					container.stop();
				}
				if(c.absX <= 2542) {
					container.stop();
				}
			}
			@Override
			public void stop() {
				setAnimationBack(c);
				c.stopPlayerPacket = false;
			}
		}, 3);
	}
	public static void ropeSwingBarb(final Client c) {
		if(c.stopPlayerPacket) {
			return;
		}
		c.stopPlayerPacket = true;

		if(c.absY != 3549) {
			c.getPA().walkTo(c.absX - 3549, 0);
		}

		c.turnPlayerTo(2551, 3541);
		c.startAnimation(751);
		c.getPA().objectAnim(2551, 3350, 497, 10, 2);

		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				c.getPA().walkTo(0,1);
				container.stop();
			}
			@Override
			public void stop() {
				c.getPA().addSkillXP(2100 * AGILITY_XP, c.playerAgility);
			}
		}, 2);
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				c.teleportToX = 2551;//end
				c.teleportToY = 3549;
				container.stop();
			}
			@Override
			public void stop() {
				setAnimationBack(c);
				c.stopPlayerPacket = false;
			}
		}, 3);
	}
	public static void obstacleNetBarb(final Client c, int anim, int objectX, int objectY, int i) {
		if(c.stopPlayerPacket) {
			return;
		}
		c.stopPlayerPacket = true;
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				c.startAnimation(828);
				if(c.absX <= 2539) {
					container.stop();
				}
			}
			@Override
			public void stop() {
				c.getPA().addSkillXP(AGILITY_XP, c.playerAgility);
			}
		}, 1);
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				if(System.currentTimeMillis() - c.agilityDelay > 500) {
				c.teleportToX = c.absX-1;
				c.teleportToY = c.absY;
				c.heightLevel = 1;
				c.agilityDelay = System.currentTimeMillis();
				c.getPA().addSkillXP(135 * AGILITY_XP, c.playerAgility);
				c.stopPlayerPacket = true;
				if(c.heightLevel <= 1) {
					container.stop();
				}
				}
			}
			@Override
			public void stop() {
				setAnimationBack(c);
				c.stopPlayerPacket = false;
			}
		}, 3);
	}
	public static void walkAcrossBarb(final Client c, int anim, int length, int length1, int objectX, int objectY, int i) {
		if(c.stopPlayerPacket) {
			return;
		}
		if(System.currentTimeMillis() - c.agilityDelay > 2000) {
			c.getPA().walkTo(length,length1);
			resetAnimation(c, anim);
			c.agilityDelay = System.currentTimeMillis();
			c.getPA().addSkillXP(150 * AGILITY_XP, c.playerAgility);
			c.stopPlayerPacket = true;
			CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
				@Override
				public void execute(CycleEventContainer container) {
					c.stopPlayerPacket = false;
					container.stop();
				}

				@Override
				public void stop() {
					setAnimationBack(c);
				}
			}, 10);
		}
	}
	public static void brimStone(final Client c) {
		if(c.stopPlayerPacket) {
			return;
		}
		c.stopPlayerPacket = true;
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				c.startAnimation(769);
				if(c.absY <= 9558) {
					container.stop();
				}
			}
			@Override
			public void stop() {
			}
		}, 1);
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				c.teleportToX = c.absX;
				c.teleportToY = c.absY-1;
				if(c.absY <= 9560) {
					c.teleportToX = c.absX-1;
					c.teleportToY = c.absY;	
				}
				if(c.absX <= 2647) {
					c.teleportToX = c.absX;
					c.teleportToY = c.absY-1;	
				}
				if(c.absY <= 9558) {
					container.stop();
				}
			}
			@Override
			public void stop() {
				c.getPA().addSkillXP(100 * AGILITY_XP, c.playerAgility);
				setAnimationBack(c);
				c.stopPlayerPacket = false;
			}
		}, 3);
	}
}