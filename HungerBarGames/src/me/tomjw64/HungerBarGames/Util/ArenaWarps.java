package me.tomjw64.HungerBarGames.Util;

import java.util.Map;

import org.bukkit.Location;

public class ArenaWarps {
	private Map<Integer,Location> spawns;
	private Location lobby;
	private Location spec;
	
	public Map<Integer,Location> getSpawns()
	{
		return spawns;
	}
	
	public int getNumSpawns()
	{
		return spawns.size();
	}
	
	public Location getLobby()
	{
		return lobby;
	}
	
	public Location getSpec()
	{
		return spec;
	}
	
}
