package me.tomjw64.HungerBarGames;

import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.tomjw64.HungerBarGames.Threads.Countdown;
import me.tomjw64.HungerBarGames.Threads.Lobby;
import me.tomjw64.HungerBarGames.Threads.NightCheck;
import me.tomjw64.HungerBarGames.Util.ChatVariableHolder;
import me.tomjw64.HungerBarGames.Util.PlayerHandler;
import me.tomjw64.HungerBarGames.Util.Status;

public class Game extends ChatVariableHolder{
	private Arena arena;
	private Lobby lobby;
	private Countdown countdown;
	private NightCheck nightCheck;
	private PlayerHandler players=new PlayerHandler(this);
	private Status status=Status.IDLE;
	
	public Game(Arena arena)
	{
		this.arena=arena;
	}
	
	public void startLobby()
	{
		lobby=new Lobby(this);
	}
	
	public void startCountdown()
	{
		if(status!=Status.IDLE)
		{
			countdown=new Countdown(this);
		}
	}
	
	public void startGame()
	{
		if(status!=Status.IDLE)
		{
			setStatus(Status.IN_GAME);
			arena.getWorld().setTime(0);
			arena.fillChests();
			nightCheck=new NightCheck(this);
		}
	}
	
	public void stopGame(boolean forced)
	{
		setStatus(Status.IDLE);
		players.removeAll();
		if(forced)
		{
			Bukkit.getServer().broadcastMessage(prefix+YELLOW+"The game in arena "+BLUE+arena.getName()+YELLOW+" has been cancelled!");
			//TODO: Stop playlist
		}
		else
		{
			players.declareWinner();
			//TODO: Continue playlist
		}
	}
	
	public void addTibute(Player p)
	{
		players.addTribute(p);
	}
	
	public void updateListeners()
	{
		//TODO: Update listeners
	}
	
	public void setStatus(Status status)
	{
		this.status=status;
		updateListeners();
	}
	
	public boolean isTribute(Player p)
	{
		return players.isTribute(p);
	}
	
	public boolean isWaiting()
	{
		return lobby.isWaiting();
	}
	
	public Status getStatus()
	{
		return status;
	}
	
	public Set<Player> getTributes()
	{
		return players.getTributes();
	}
	
	public int getPop()
	{
		return players.getPop();
	}
	
	public Arena getArena()
	{
		return arena;
	}
	
}
