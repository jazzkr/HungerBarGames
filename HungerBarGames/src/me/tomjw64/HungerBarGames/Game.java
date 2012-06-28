package me.tomjw64.HungerBarGames;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;

import me.tomjw64.HungerBarGames.Listeners.GameListener;
import me.tomjw64.HungerBarGames.Listeners.Countdown.*;
import me.tomjw64.HungerBarGames.Listeners.Game.*;
import me.tomjw64.HungerBarGames.Listeners.Lobby.*;
import me.tomjw64.HungerBarGames.Threads.Countdown;
import me.tomjw64.HungerBarGames.Threads.EndGame;
import me.tomjw64.HungerBarGames.Threads.Lobby;
import me.tomjw64.HungerBarGames.Threads.NightCheck;
import me.tomjw64.HungerBarGames.Util.ChatVariableHolder;
import me.tomjw64.HungerBarGames.Util.Playlist;
import me.tomjw64.HungerBarGames.Util.RollbackInfo;
import me.tomjw64.HungerBarGames.Util.Enums.Status;
import me.tomjw64.HungerBarGames.Util.Games.PlayerHandler;

public class Game extends ChatVariableHolder{
	private Arena arena;
	private Lobby lobby;
	private PlayerHandler playerHandler=new PlayerHandler(this);
	private Set<GameListener> listeners=new HashSet<GameListener>();
	private Set<Chest> filledChests=new HashSet<Chest>();
	private Map<Block,RollbackInfo> rollbacks=new HashMap<Block,RollbackInfo>();
	private Status status=Status.IDLE;
	private Playlist playlist;
	
	public Game(Arena arena)
	{
		this.arena=arena;
	}
	
	public void startLobby(Playlist playlist)
	{
		this.playlist=playlist;
		lobby=new Lobby(this);
	}
	
	public void startCountdown()
	{
		if(status!=Status.IDLE)
		{
			new Countdown(this);
		}
	}
	
	public void startGame()
	{
		if(status!=Status.IDLE)
		{
			setStatus(Status.IN_GAME);
			new NightCheck(this);
		}
	}
	
	public void forceStop()
	{
		if(status!=Status.IDLE)
		{
			Bukkit.getServer().broadcastMessage(prefix+YELLOW+"The game in arena "+BLUE+arena.getName()+YELLOW+" has been cancelled!");
			reset();
			if(playlist!=null)
			{
				playlist.quit();
			}
		}
	}
	
	public void endGame(Player winner)
	{
		new EndGame(this,winner);
	}
	
	public void reset()
	{
		setStatus(Status.IDLE);
		rollback();
		playerHandler.removeAll();
	}
	
	public void rollback()
	{
		for(Map.Entry<Block,RollbackInfo> entry:rollbacks.entrySet())
		{
			Block b=entry.getKey();
			b.setTypeId(entry.getValue().getID());
			b.setData(entry.getValue().getData());
		}
		filledChests.clear();
		HungerBarGames.logger.info("Arena "+arena.getName()+" rolled back!");
	}
	
	public void updateListeners()
	{
		unregisterListeners();
		switch(status)
		{
		case LOBBY:
			new LobbyLevelListener(this);
			new LobbyBlockListener(this);
			new LobbyRemovalListener(this);
			break;
		case COUNTDOWN:
			new CountdownMotionListener(this);
			new CountdownInteractListener(this);
			new CountdownRemovalListener(this);
			new CountdownDamageListener(this);
			new CountdownArenaProtect(this);
			new CountdownCommandListener(this);
			break;
		case IN_GAME:
			new EliminationListener(this);
			new GamePvPListener(this);
			new GameBlockListener(this);
			new BlockLogger(this);
			new GameChestListener(this);
			new GameCommandListener(this);
			new GameChatListener(this);
			new GameTeleportListner(this);
			new GameMotionListener(this);
			new SpectatorRestrictionListener(this);
			break;
		}
	}
	
	public void unregisterListeners()
	{
		Object[] gls=listeners.toArray();
		for(int x=0;x<gls.length;x++)
		{
			GameListener gl=(GameListener)gls[x];
			listeners.remove(gl);
			gl.unregister();
			gl=null;
		}
	}
	
	public void addListener(GameListener gl)
	{
		listeners.add(gl);
	}
	
	public void addRollback(Block b,RollbackInfo r)
	{
		if(!rollbacks.containsKey(b))
		{
			rollbacks.put(b,r);
		}
	}
	
	public void setStatus(Status status)
	{
		this.status=status;
		updateListeners();
	}
	
	public void setFilled(Chest c)
	{
		filledChests.add(c);
	}
	
	public boolean isTribute(Player p)
	{
		return playerHandler.isTribute(p);
	}
	
	public boolean isSpectator(Player p)
	{
		return playerHandler.isSpectator(p);
	}
	
	public boolean isWaiting()
	{
		return lobby.isWaiting();
	}
	
	public boolean beenFilled(Chest c)
	{
		return filledChests.contains(c);
	}
	
	public Set<Player> getTributes()
	{
		return playerHandler.getTributes();
	}
	
	public Set<Player> getSpectators()
	{
		return playerHandler.getSpectators();
	}
	
	public PlayerHandler getPlayerHandler()
	{
		return playerHandler;
	}
	
	public Playlist getPlaylist()
	{
		return playlist;
	}
	
	public Status getStatus()
	{
		return status;
	}
	
	public int getPop()
	{
		return playerHandler.getPop();
	}
	
	public Arena getArena()
	{
		return arena;
	}
	
}
