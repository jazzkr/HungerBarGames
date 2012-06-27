package me.tomjw64.HungerBarGames.Util.Arenas;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;

public class ArenaWarps {
	private Map<Integer,Location> spawns;
	private Location lobby;
	private Location spec;
	
	public ArenaWarps()
	{
		this(new HashMap<Integer,Location>(),null,null);
	}
	
	public ArenaWarps(Map<Integer,Location> spawns,Location lobby,Location spec)
	{
		this.spawns=spawns;
		this.lobby=lobby;
		this.spec=spec;
	}
	
	public void addSpawn(Integer pos,Location spawn)
	{
		spawns.put(pos,spawn);
	}
	
	public void setLobby(Location loc)
	{
		lobby=loc;
	}
	
	public void setSpec(Location loc)
	{
		spec=loc;
	}
	
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
