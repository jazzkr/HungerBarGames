package me.tomjw64.HungerBarGames.Util;

import java.util.HashSet;
import java.util.Set;

import me.tomjw64.HungerBarGames.Game;

import org.bukkit.entity.Player;

public class PlayerHandler {
	private Game game;
	private Set<Player> tributes=new HashSet<Player>();
	private Set<Player> spectators=new HashSet<Player>();
	
	public PlayerHandler(Game game)
	{
		this.game=game;
	}
	
	public void removeAll()
	{
		final Set<Player> specs=spectators;
		for(Player p:specs)
		{
			//TODO: Remove spectator
		}
		final Set<Player> tribs=tributes;
		for(Player p:tribs)
		{
			//TODO: Remove tributes
		}
	}
	
	public int getPop()
	{
		return tributes.size();
	}
	
	public Set<Player> getTributes()
	{
		return tributes;
	}
	
	public Set<Player> getSpectators()
	{
		return spectators;
	}

}
