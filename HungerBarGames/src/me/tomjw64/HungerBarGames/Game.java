package me.tomjw64.HungerBarGames;

import java.util.Set;

import org.bukkit.entity.Player;

import me.tomjw64.HungerBarGames.Threads.Countdown;
import me.tomjw64.HungerBarGames.Threads.Lobby;
import me.tomjw64.HungerBarGames.Threads.NightCheck;
import me.tomjw64.HungerBarGames.Util.Status;

public class Game {
	private Arena arena;
	private Lobby lobby;
	private Countdown countdown;
	private NightCheck nightCheck;
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
	
	public void stopGame()
	{
		setStatus(Status.IDLE);
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
		//TODO: Return tributes
		return null;
	}
	
	public int getPop()
	{
		//TODO: Return tributes.size
		return 0;
	}
	
	public Arena getArena()
	{
		return arena;
	}
	
}