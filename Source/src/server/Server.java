package server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.text.DecimalFormat;

import org.apache.mina.common.IoAcceptor;
import org.apache.mina.transport.socket.nio.SocketAcceptor;
import org.apache.mina.transport.socket.nio.SocketAcceptorConfig;

import server.event.Task;
import server.event.TaskScheduler;
import server.clip.region.ObjectDef;
import server.clip.region.Region;
import server.event.EventManager;
import server.model.npcs.NPCHandler;
import server.model.npcs.NPCDrops;
import server.model.players.PlayerHandler;
import server.model.players.Player;
import server.model.players.Client;
import server.model.players.PlayerSave;
import server.model.minigames.*;
import server.net.ConnectionHandler;
import server.net.ConnectionThrottleFilter;
import server.util.ShutDownHook;
import server.util.SimpleTimer;
import server.util.log.Logger;
import server.world.ItemHandler;
import server.world.ObjectHandler;
import server.world.ObjectManager;
import server.world.ShopHandler;
import server.world.map.VirtualWorld;
import server.world.ClanChatHandler;
import server.world.WorldMap;
import server.event.CycleEventHandler;
import server.world.WalkingCheck;

/**
 * Server.java
 *
 * @author Sanity
 * @author Graham
 * @author Blake
 * @author Ryan Lmctruck30
 *
 */

public class Server {

/**
	 * Gets the task scheduler.
	 * 
	 * @return The task scheduler.
	 */
	public static TaskScheduler getTaskScheduler() {
		return scheduler;
	}
	/**
	 * The task scheduler.
	 */
	private static final TaskScheduler scheduler = new TaskScheduler();
	
	
	public static boolean sleeping;
	public static int cycleRate;
	public static boolean UpdateServer = false;
	public static long lastMassSave = System.currentTimeMillis();
	private static IoAcceptor acceptor;
	private static ConnectionHandler connectionHandler;
	private static ConnectionThrottleFilter throttleFilter;
	private static SimpleTimer engineTimer, debugTimer;
	private static long cycleTime, cycles, totalCycleTime, sleepTime;
	private static DecimalFormat debugPercentFormat;
	public static boolean shutdownServer = false;		
	public static boolean shutdownClientHandler;			
	public static int serverlistenerPort; 
	public static ItemHandler itemHandler = new ItemHandler();
	public static PlayerHandler playerHandler = new PlayerHandler();
    public static NPCHandler npcHandler = new NPCHandler();
	public static ShopHandler shopHandler = new ShopHandler();
	public static ObjectHandler objectHandler = new ObjectHandler();
	public static ObjectManager objectManager = new ObjectManager();
	public static CastleWars castleWars = new CastleWars();
	private static WarriorsGuild warriorsGuild = new WarriorsGuild();
	public static WarriorsGuild getWarriorsGuild() {
		return warriorsGuild;
	}
	public static FightPits fightPits = new FightPits();
	public static PestControl pestControl = new PestControl();
	public static NPCDrops npcDrops = new NPCDrops();
	public static ClanChatHandler clanChat = new ClanChatHandler();
	public static FightCaves fightCaves = new FightCaves();
	//public static WorldMap worldMap = new WorldMap();
	//private static final WorkerThread engine = new WorkerThread();
	
	static {
		if(!Config.SERVER_DEBUG) {
			serverlistenerPort =43594;
		} else {
			serverlistenerPort = 43594;
		}
		cycleRate = 600;
		shutdownServer = false;
		engineTimer = new SimpleTimer();
		debugTimer = new SimpleTimer();
		sleepTime = 0;
		debugPercentFormat = new DecimalFormat("0.0#%");
	}
	//height,absX,absY,toAbsX,toAbsY,type
    /*public static final boolean checkPos(int height,int absX,int absY,int toAbsX,int toAbsY,int type)
    {
        return I.I(height,absX,absY,toAbsX,toAbsY,type);
    }*/
	public static void main(java.lang.String args[]) throws NullPointerException, IOException {
		/**
		 * Starting Up Server
		 */
		
		System.setOut(new Logger(System.out));
		System.setErr(new Logger(System.err));
		System.out.println("");
		System.out.println("");
		ObjectDef.loadConfig();
        Region.load();
		
		/**
		 * World Map Loader
		 */
		/*if(!Config.SERVER_DEBUG)
			VirtualWorld.init();
		WorldMap.loadWorldMap();*/	

		/**
		 * Script Loader
		 */
		//ScriptManager.loadScripts();
		
		/**
		 * Accepting Connections
		 */
		acceptor = new SocketAcceptor();
		connectionHandler = new ConnectionHandler();
		
		SocketAcceptorConfig sac = new SocketAcceptorConfig();
		sac.getSessionConfig().setTcpNoDelay(false);
		sac.setReuseAddress(true);
		sac.setBacklog(100);
		
		throttleFilter = new ConnectionThrottleFilter(Config.CONNECTION_DELAY);
		sac.getFilterChain().addFirst("throttleFilter", throttleFilter);
		acceptor.bind(new InetSocketAddress(serverlistenerPort), connectionHandler, sac);

		/**
		 * Initialise Handlers
		 */
		EventManager.initialize();
		Connection.initialize();
		//PlayerSaving.initialize();
		//MysqlManager.createConnection();
		
		/**
		 * Server Successfully Loaded 
		 */
		System.out.println("Server listening on port acelta.ddns.net:" + serverlistenerPort);
		/**
		 * Main Server Tick
		 */
		try {
			while (!Server.shutdownServer) {
				if (sleepTime >= 0)
					Thread.sleep(sleepTime);
				else
					Thread.sleep(600);
				engineTimer.reset();
				CycleEventHandler.getSingleton().process();
				itemHandler.process();
				playerHandler.process();	
	            npcHandler.process();
				shopHandler.process();
				objectManager.process();
				fightPits.process();
				Server.pestControl.gameProcess();
				cycleTime = engineTimer.elapsed();
				sleepTime = cycleRate - cycleTime;
				totalCycleTime += cycleTime;
				cycles++;
				debug();

			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("A fatal exception has been thrown!");
			for(Player p : PlayerHandler.players) {
				if(p == null)
					continue;
				if(p.duelStatus == 6) {
					((Client)p).getTradeAndDuel().claimStakedItems();
				}					
				PlayerSave.saveGame((Client)p);
				System.out.println("Saved game for " + p.playerName + "."); 
			}
		}
		Runtime.getRuntime().addShutdownHook(new Thread());
		acceptor = null;
		connectionHandler = null;
		sac = null;
		System.exit(0);
	}
	
	
	
	public static void processAllPackets() {
		for (int j = 0; j < playerHandler.players.length; j++) {
			if (playerHandler.players[j] != null) {
				while(playerHandler.players[j].processQueuedPackets());			
			}	
		}
	}
	
	public static boolean playerExecuted = false;
	private static void debug() {
		if (debugTimer.elapsed() > 360*1000 || playerExecuted) {
			long averageCycleTime = totalCycleTime / cycles;
			System.out.println("Average Cycle Time: " + averageCycleTime + "ms");
			double engineLoad = ((double) averageCycleTime / (double) cycleRate);
			System.out.println("Players online: " + PlayerHandler.playerCount+ ", engine load: "+ debugPercentFormat.format(engineLoad));
			totalCycleTime = 0;
			cycles = 0;
			System.gc();
			System.runFinalization();
			debugTimer.reset();
			playerExecuted = false;
		}
	}
	
	public static long getSleepTimer() {
		return sleepTime;
	}
	
}
