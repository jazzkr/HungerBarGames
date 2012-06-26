package me.tomjw64.HungerBarGames;

import java.util.Map;

import org.bukkit.Location;
import org.bukkit.World;

import me.tomjw64.HungerBarGames.Util.ArenaInfo;
import me.tomjw64.HungerBarGames.Util.ArenaWarps;
import me.tomjw64.HungerBarGames.Util.Boundary;
import me.tomjw64.HungerBarGames.Util.ChestInfo;

public class Arena {
	private ArenaInfo info;
	private Boundary boundary;
	private ArenaWarps warps;
	private ChestInfo chests;
	private Game game;
	
	public Arena(ArenaInfo info,Boundary boundary,ArenaWarps warps,ChestInfo chests)
	{
		this.info=info;
		this.boundary=boundary;
		this.warps=warps;
		this.chests=chests;
	}
	
	public void fillChests()
	{
		//TODO: Fill assigned chests
	}
	
	public ArenaWarps getWarps()
	{
		return warps;
	}
	
	public String getName()
	{
		return info.getName();
	}
	
	public int getMin()
	{
		return 0;
	}
	
	public int getMax()
	{
		return 0;
	}
	
	public Boundary getBoundary()
	{
		return boundary;
	}
	
	public World getWorld()
	{
		return boundary.getWorld();
	}
	
	public Game getGame()
	{
		return game;
	}
	
}
