package server.world;

import java.util.ArrayList;

import server.model.objects.Object;
import server.util.Misc;
import server.model.players.Client;
import server.Server;

/**
 * @author Sanity
 */

public class ObjectManager {

	public ArrayList<Object> objects = new ArrayList<Object>();
	private ArrayList<Object> toRemove = new ArrayList<Object>();
	public void process() {
		for (Object o : objects) {
			if (o.tick > 0)
				o.tick--;
			else {
				updateObject(o);
				toRemove.add(o);
			}		
		}
		for (Object o : toRemove) {
			if (isObelisk(o.newId)) {
				int index = getObeliskIndex(o.newId);
				if (activated[index]) {
					activated[index] = false;
					teleportObelisk(index);
				}
			}
			objects.remove(o);	
		}
		toRemove.clear();
	}
	
	public void removeObject(int x, int y) {
		for (int j = 0; j < Server.playerHandler.players.length; j++) {
			if (Server.playerHandler.players[j] != null) {
				Client c = (Client)Server.playerHandler.players[j];
				c.getPA().object(-1, x, y, 0, 10);			
			}	
		}	
	}
	
	public void updateObject(Object o) {
		for (int j = 0; j < Server.playerHandler.players.length; j++) {
			if (Server.playerHandler.players[j] != null) {
				Client c = (Client)Server.playerHandler.players[j];
				c.getPA().object(o.newId, o.objectX, o.objectY, o.face, o.type);			
			}	
		}	
	}
	
	public void placeObject(Object o) {
		for (int j = 0; j < Server.playerHandler.players.length; j++) {
			if (Server.playerHandler.players[j] != null) {
				Client c = (Client)Server.playerHandler.players[j];
				if (c.distanceToPoint(o.objectX, o.objectY) <= 60)
					c.getPA().object(o.objectId, o.objectX, o.objectY, o.face, o.type);
			}	
		}
	}
	
	public Object getObject(int x, int y, int height) {
		for (Object o : objects) {
			if (o.objectX == x && o.objectY == y && o.height == height)
				return o;
		}	
		return null;
	}
	
	public void loadObjects(Client c) {
		if (c == null)
			return;
		for (Object o : objects) {
			if (loadForPlayer(o,c))
				c.getPA().object(o.objectId, o.objectX, o.objectY, o.face, o.type);
		}
		loadCustomSpawns(c);
		if (c.distanceToPoint(2813, 3463) <= 60) {
			c.getFarming().updateHerbPatch();
		}
	}
	
	private int[][] customObjects = {{}};
	public void loadCustomSpawns(Client c) {
		c.getPA().checkObjectSpawn(2213, 2337, 3689, -1, 10);
		c.getPA().checkObjectSpawn(1, 3088, 3933, 0, 10);
		c.getPA().checkObjectSpawn(1, 3088, 3934, 0, 10);
		c.getPA().checkObjectSpawn(1, 3240, 3617, 0, 10);
		c.getPA().checkObjectSpawn(1, 3239, 3617, 0, 10);
		c.getPA().checkObjectSpawn(2213, 2337, 3689, -1, 10);
		c.getPA().checkObjectSpawn(411, 3093, 3506, 4, 10);
		c.getPA().checkObjectSpawn(410, 3104, 3508, 0, 10); 
		//c.getPA().checkObjectSpawn(411, 3098, 3506, 0, 10);
		c.getPA().checkObjectSpawn(4874, 2322, 3679, 1, 10);
		c.getPA().checkObjectSpawn(4875, 2322, 3681, 1, 10);
		c.getPA().checkObjectSpawn(4876, 2322, 3683, 0, 10);
		c.getPA().checkObjectSpawn(4877, 2322, 3685, 0, 10);
		c.getPA().checkObjectSpawn(4878, 2322, 3687, 0, 10);
		c.getPA().checkObjectSpawn(2728, 2816, 3438, 0, 0);
		c.getPA().checkObjectSpawn(2728, 2816, 3438, 0, 0);
		c.getPA().checkObjectSpawn(2728, 3109, 3167, 0, 0);
		c.getPA().checkObjectSpawn(-1, 3109, 3167, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2207, 5345, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2208, 5344, -1, 10);
		c.getPA().checkObjectSpawn(2728, 3111, 3162, 0, 0);
		c.getPA().checkObjectSpawn(-1, 3111, 3162, -1, 10);
		c.getPA().checkObjectSpawn(1596, 3008, 3850, 1, 0);
		c.getPA().checkObjectSpawn(1596, 3008, 3849, -1, 0);
		c.getPA().checkObjectSpawn(1596, 3040, 10307, -1, 0);
		c.getPA().checkObjectSpawn(1596, 3040, 10308, 1, 0);
		c.getPA().checkObjectSpawn(1596, 3022, 10311, -1, 0);
		c.getPA().checkObjectSpawn(1596, 3022, 10312, 1, 0);
		c.getPA().checkObjectSpawn(1596, 3044, 10341, -1, 0);
		c.getPA().checkObjectSpawn(1596, 3044, 10342, 1, 0);
		c.getPA().checkObjectSpawn(6552, 3095, 3506, 4, 10);
		c.getPA().checkObjectSpawn(409, 3091, 3506, 4, 10);
		//c.getPA().checkObjectSpawn(12356, 3089, 3496, 4, 10);
		//c.getPA().checkObjectSpawn(2403, 3089, 3495, 3, 10);
		c.getPA().checkObjectSpawn(2090, 2312, 3679, 1, 10);
		c.getPA().checkObjectSpawn(28719, 3243, 9783, 1, 10);
		//c.getPA().checkObjectSpawn(29954, 3241, 9784, 0, 10);
		c.getPA().checkObjectSpawn(2094, 2312, 3680, 1, 10);
		c.getPA().checkObjectSpawn(2092, 2312, 3681, 1, 10);
		c.getPA().checkObjectSpawn(2097, 2312, 3682, 1, 10);
		c.getPA().checkObjectSpawn(2103, 2312, 3683, 1, 10);
		c.getPA().checkObjectSpawn(2105, 2312, 3684, 1, 10);
		c.getPA().checkObjectSpawn(1276, 2324, 3673, 1, 10);
		c.getPA().checkObjectSpawn(3044, 2312, 3691, 0, 10);
		c.getPA().checkObjectSpawn(2783, 2317, 3690, 0, 10);
		c.getPA().checkObjectSpawn(1307, 2330, 3673, 1, 10);
		c.getPA().checkObjectSpawn(1306, 2335, 3673, 1, 10);
		c.getPA().checkObjectSpawn(1309, 2332, 3673, 1, 10);
		c.getPA().checkObjectSpawn(1308, 2328, 3673, 1, 10);
		c.getPA().checkObjectSpawn(1281, 2325, 3673, 1, 10);
		//Start Of Corp Waiting Room
		c.getPA().checkObjectSpawn(2213, 2465, 4781, 3, 10);
		c.getPA().checkObjectSpawn(2213, 2465, 4782, 3, 10);
		//End Of Corp Waiting Room
		//c.getPA().checkObjectSpawn(12356, 3018, 3358, 1, 10);
		c.getPA().checkObjectSpawn(11758, 3013, 3353, 1, 10);
		c.getPA().checkObjectSpawn(1596, 3044, 10342, 1, 0);
		c.getPA().checkObjectSpawn(1, 3102, 9506, 0, 10);
		c.getPA().checkObjectSpawn(2728, 3215, 3211, 0, 0);
		c.getPA().checkObjectSpawn(2728, 3208, 3211, 0, 0);

		c.getPA().checkObjectSpawn(2213, 2818, 3373, 1, 10);
		c.getPA().checkObjectSpawn(2213, 2818, 3374, 1, 10);

		//Skilling
		c.getPA().checkObjectSpawn(14859, 2839, 3439, 0, 10);//runite ore skilling.
		c.getPA().checkObjectSpawn(2213, 2855, 3439, -1, 10);
		c.getPA().checkObjectSpawn(2090, 2839, 3440, -1, 10);
		c.getPA().checkObjectSpawn(2094, 2839, 3441, -1, 10);
		c.getPA().checkObjectSpawn(2092, 2839, 3442, -1, 10);
		c.getPA().checkObjectSpawn(2096, 2839, 3443, -1, 10);
		c.getPA().checkObjectSpawn(2102, 2839, 3444, -1, 10);
		c.getPA().checkObjectSpawn(2105, 2839, 3445, 0, 10);
		c.getPA().checkObjectSpawn(1276, 2843, 3442, 0, 10);
		c.getPA().checkObjectSpawn(1281, 2844, 3499, 0, 10);
		c.getPA().checkObjectSpawn(4156, 3083, 3440, 0, 10);
		c.getPA().checkObjectSpawn(1308, 2846, 3436, 0, 10);
		c.getPA().checkObjectSpawn(1309, 2846, 3439, -1, 10);
		c.getPA().checkObjectSpawn(1306, 2850, 3439, -1, 10);
		c.getPA().checkObjectSpawn(2783, 2841, 3436, 0, 10);
		c.getPA().checkObjectSpawn(2728, 2861, 3429, 0, 10);
		c.getPA().checkObjectSpawn(3044, 2857, 3427, -1, 10);
		c.getPA().checkObjectSpawn(320, 3048, 10342, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2844, 3440, -1, 10);
		c.getPA().checkObjectSpawn(-1, 3244, 9783, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2846, 3437, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2840, 3439, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2841, 3443, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2851, 3438, -1, 10);
		//c.getPA().checkObjectSpawn(4874, 3084, 3496, 0, 10); //Level 1 Stall
		//c.getPA().checkObjectSpawn(4875, 3084, 3494, 0, 10); //Level 25 Stall
		//c.getPA().checkObjectSpawn(4876, 3084, 3492, 0, 10); //Level 50 Stall
		//c.getPA().checkObjectSpawn(4877, 3084, 3490, 0, 10); //Level 75 Stall
		//c.getPA().checkObjectSpawn(4878, 3084, 3488, 0, 10); //Level 90 Stall
		//Skilling End

c.getPA().checkObjectSpawn(1, 3103, 9505, 0, 10);
c.getPA().checkObjectSpawn(1, 3104, 9505, 0, 10);
c.getPA().checkObjectSpawn(1, 3110, 9515, 0, 10);
c.getPA().checkObjectSpawn(1, 3111, 9515, 0, 10);
c.getPA().checkObjectSpawn(1, 3112, 9515, 0, 10);
c.getPA().checkObjectSpawn(1, 3106, 9510, 0, 10);
c.getPA().checkObjectSpawn(1, 3106, 9509, 0, 10);
c.getPA().checkObjectSpawn(1, 3106, 9508, 0, 10);
c.getPA().checkObjectSpawn(1, 3103, 9506, 0, 10);
c.getPA().checkObjectSpawn(1, 3104, 9506, 0, 10);
c.getPA().checkObjectSpawn(1, 3105, 9506, 0, 10);
c.getPA().checkObjectSpawn(1, 3105, 9505, 0, 10);
c.getPA().checkObjectSpawn(1, 3102, 9505, 0, 10);
c.getPA().checkObjectSpawn(1, 3104, 9504, 0, 10);
c.getPA().checkObjectSpawn(1, 3095, 9502, 0, 10);
c.getPA().checkObjectSpawn(1, 3095, 9503, 0, 10);


		c.getPA().checkObjectSpawn(2213, 3080, 9502, 1, 10);
		c.getPA().checkObjectSpawn(2213, 2315, 9798, 2, 10);
		c.getPA().checkObjectSpawn(2213, 2314, 9798, 2, 10);
		c.getPA().checkObjectSpawn(409, 2310, 9810, 1, 10);
		c.getPA().checkObjectSpawn(2213, 2313, 9798, 2, 10);
		c.getPA().checkObjectSpawn(2213, 2312, 9798, 2, 10);
		c.getPA().checkObjectSpawn(2213, 2311, 9798, 2, 10);
		c.getPA().checkObjectSpawn(1530, 3093, 3487, 1, 10);
		//c.getPA().checkObjectSpawn(3192, 3081, 3484, 4, 10);
		c.getPA().checkObjectSpawn(-1, 3215, 3211, 0, 10);
		c.getPA().checkObjectSpawn(-1, 3302, 3116, 0, 10);
		c.getPA().checkObjectSpawn(-1, 3208, 3211, 0, 10);
		c.getPA().checkObjectSpawn(-1, 3080, 3510, 1, 10);
		c.getPA().checkObjectSpawn(-1, 2816, 3438, 0, 10);
		c.getPA().checkObjectSpawn(-1, 3242, 3617, 1, 10);
		c.getPA().checkObjectSpawn(-1, 3079, 3510, 1, 10);
		c.getPA().checkObjectSpawn(-1, 3081, 3510, 1, 10);
		c.getPA().checkObjectSpawn(-1, 3078, 3510, 1, 10);
		c.getPA().checkObjectSpawn(-1, 3098, 3496, 1, 10);
		c.getPA().checkObjectSpawn(-1, 3017, 3358, 1, 10);
		c.getPA().checkObjectSpawn(-1, 3018, 3353, 1, 10);
		c.getPA().checkObjectSpawn(-1, 3020, 3355, 1, 10);
		c.getPA().checkObjectSpawn(-1, 3017, 3353, 1, 10);
		c.getPA().checkObjectSpawn(-1, 3018, 3356, 1, 10);
		c.getPA().checkObjectSpawn(-1, 3015, 3354, 1, 10);
		c.getPA().checkObjectSpawn(-1, 3014, 3354, 1, 10);


		if (c.heightLevel == 0)
			c.getPA().checkObjectSpawn(2492, 2911, 3614, 1, 10);
		else
			c.getPA().checkObjectSpawn(-1, 2911, 3614, 1, 10);
	}
	
	public final int IN_USE_ID = 14825;
	public boolean isObelisk(int id) {
		for (int j = 0; j < obeliskIds.length; j++) {
			if (obeliskIds[j] == id)
				return true;
		}
		return false;
	}
	public int[] obeliskIds = {14829,14830,14827,14828,14826,14831};
	public int[][] obeliskCoords = {{3154,3618},{3225,3665},{3033,3730},{3104,3792},{2978,3864},{3305,3914}};
	public boolean[] activated = {false,false,false,false,false,false};
	
	public void startObelisk(int obeliskId) {
		int index = getObeliskIndex(obeliskId);
		if (index >= 0) {
			if (!activated[index]) {
				activated[index] = true;
				addObject(new Object(14825, obeliskCoords[index][0], obeliskCoords[index][1], 0, -1, 10, obeliskId,16));
				addObject(new Object(14825, obeliskCoords[index][0] + 4, obeliskCoords[index][1], 0, -1, 10, obeliskId,16));
				addObject(new Object(14825, obeliskCoords[index][0], obeliskCoords[index][1] + 4, 0, -1, 10, obeliskId,16));
				addObject(new Object(14825, obeliskCoords[index][0] + 4, obeliskCoords[index][1] + 4, 0, -1, 10, obeliskId,16));
			}
		}	
	}
	
	public int getObeliskIndex(int id) {
		for (int j = 0; j < obeliskIds.length; j++) {
			if (obeliskIds[j] == id)
				return j;
		}
		return -1;
	}
	
	public void teleportObelisk(int port) {
		int random = Misc.random(5);
		while (random == port) {
			random = Misc.random(5);
		}
		for (int j = 0; j < Server.playerHandler.players.length; j++) {
			if (Server.playerHandler.players[j] != null) {
				Client c = (Client)Server.playerHandler.players[j];
				int xOffset = c.absX - obeliskCoords[port][0];
				int yOffset = c.absY - obeliskCoords[port][1];
				if (c.goodDistance(c.getX(), c.getY(), obeliskCoords[port][0] + 2, obeliskCoords[port][1] + 2, 1)) {
					c.getPA().startTeleport2(obeliskCoords[random][0] + xOffset, obeliskCoords[random][1] + yOffset, 0);
				}
			}		
		}
	}
	
	public boolean loadForPlayer(Object o, Client c) {
		if (o == null || c == null)
			return false;
		return c.distanceToPoint(o.objectX, o.objectY) <= 60 && c.heightLevel == o.height;
	}
	
	public void addObject(Object o) {
		if (getObject(o.objectX, o.objectY, o.height) == null) {
			objects.add(o);
			placeObject(o);
		}	
	}




}
