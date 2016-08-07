package server.model.players;

import java.util.HashMap;
import java.util.Map;

public class Tanning {

	private Client c;

	public Tanning(Client c) {
		this.c = c;
	}
	private enum Tan {
		SOFTLEATHER(1739, 1741, 1),	
		HARDLEATHER(1739, 1743, 3),	
		GREENDHIDE(1753, 1745, 20),
		BLUEDHIDE(1751, 2505, 20),	
		REDDHIDE(1749, 2507, 20),	
		BLACKDHIDE(6289, 2509, 20), 
		SNAKESKIN1(6287, 6289, 15), 
		SNAKESKIN2(7801, 6289, 20);

		private int hide, tannedHide, cost;

		private Tan(int hide, int tannedHide, int cost) {
			this.hide = hide;
			this.tannedHide = tannedHide;
			this.cost = cost;
		}

		public int getHide() {
			return hide;
		}

		public int getTannedHide() {
			return tannedHide;
		}

		public int getCost() {
			return cost;
		}

		public static final Map<Integer, Tan> tan = new HashMap<Integer, Tan>();

		public static Tan forId(int id) {
			return tan.get(id);
		}
		static {
			for (Tan t : Tan.values()) {
				tan.put(t.getTannedHide(), t);
			}
		}
	}
	
	public void tanHide(Tan hide) {
		Tan t = Tan.forId(hide.getTannedHide());
		if (t == null) {
			return;
		}
		int amtOfHides, cost; 
		if (c.getItems().getItemCount(t.getHide()) > 0) {
			amtOfHides = c.getItems().getItemCount(t.getHide());
			cost = amtOfHides * t.getCost();
			if (c.getItems().playerHasItem(995, cost)) {
				amtOfHides = c.getItems().getItemCount(t.getHide());
			} else {
				c.sendMessage("You do not have enough money with you to tan all the hides at once.");
				return;
			}
			c.getItems().deleteItem(t.getHide(), amtOfHides);
			c.getItems().deleteItem(995, c.getItems().getItemSlot(995), cost);
			c.getItems().addItem(t.getTannedHide(), amtOfHides);
			c.sendMessage("You tan "+amtOfHides+(amtOfHides > 1 ? " hides" : " hide")+" for "+cost+" coins.");
		} else {
			c.sendMessage("You don't have any hides you can tan.");
		}
		resetCrafting();
	}

	private void resetCrafting() {
		c.tanning = false;
	}

	public void handleActionButton(int abutton) {
		switch(abutton) {
		case 83254: //Soft leather 
			tanHide(Tan.SOFTLEATHER);
			break;
		case 83255: //Hard leather
			tanHide(Tan.HARDLEATHER);
			break;
		case 84002: //Green d-hide
			tanHide(Tan.GREENDHIDE);
			break;
		case 84003: //Blue d-hide
			tanHide(Tan.BLUEDHIDE);
			break;
		case 84004: //Red d-hide
			tanHide(Tan.REDDHIDE);
			break;
		case 84005://Black d-hide
			tanHide(Tan.BLACKDHIDE);
			break;
		case 84001: //Unused
			tanHide(Tan.SNAKESKIN1);
			break;
		case 84000: //Unused
			tanHide(Tan.SNAKESKIN2);
			break;
		}
	}

	public void setupInterface() {
		c.getPA().showInterface(21500);
		c.tanning = true;
		}
	}