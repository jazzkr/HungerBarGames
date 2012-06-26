package me.tomjw64.HungerBarGames;

import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.tomjw64.HungerBarGames.Threads.Lobby;
import me.tomjw64.HungerBarGames.Threads.NightCheck;
import me.tomjw64.HungerBarGames.Util.ChatVariableHolder;
import me.tomjw64.HungerBarGames.Util.PlayerHandler;
import me.tomjw64.HungerBarGames.Util.Status;

public class Game extends ChatVariableHolder{
	private Arena arena;
	private Lobby lobby;
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
		nightCheck=new NightCheck(this);
	}
	
	public void endGame(boolean forced)
	{
		setStatus(Status.IDLE);
		players.removeAll();
		Bukkit.getServer().broadcastMessage(prefix+YELLOW+"The game in arena "+BLUE+arena.getName()+YELLOW+" has been cancelled!");
		//TODO: End game
	}
	
	public void setStatus(Status status)
	{
		this.status=status;
		updateListeners();
	}
	
	public void updateListeners()
	{
		//TODO: Update listeners
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