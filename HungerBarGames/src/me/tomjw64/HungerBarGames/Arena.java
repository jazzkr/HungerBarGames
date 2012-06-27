package me.tomjw64.HungerBarGames;

import java.util.Map;
import java.util.Set;

import org.bukkit.World;
import org.bukkit.block.Chest;

import me.tomjw64.HungerBarGames.Util.Arenas.ArenaInfo;
import me.tomjw64.HungerBarGames.Util.Arenas.ArenaWarps;
import me.tomjw64.HungerBarGames.Util.Arenas.Boundary;
import me.tomjw64.HungerBarGames.Util.Arenas.ChestInfo;
import me.tomjw64.HungerBarGames.Util.Chests.ChestClass;

public class Arena {
	private ArenaInfo info;
	private Boundary boundary;
	private ArenaWarps warps;
	private ChestInfo chests;
	private Game game=new Game(this);
	
	public Arena(String name)
	{
		this(new ArenaInfo(name),null,new ArenaWarps(),new ChestInfo());
	}
	
	public Arena(ArenaInfo info,Boundary boundary,ArenaWarps warps,ChestInfo chests)
	{
		this.info=info;
		this.boundary=boundary;
		this.warps=warps;
		this.chests=chests;
	}
	
	public void fillChests()
	{
		for(Map.Entry<ChestClass,Set<Chest>> entry:chests.getChests().entrySet())
		{
			ChestClass cc=entry.getKey();
			for(Chest c:entry.getValue())
			{
				cc.fillChest(c);
				game.setFilled(c);
			}
		}
	}
	
	public void setBoundary(Boundary bound)
	{
		boundary=bound;
	}
	
	public boolean isBounded()
	{
		return boundary!=null;
	}
	
	public boolean isSetup()
	{
		return warps.getLobby()!=null&&warps.getSpec()!=null;
	}
	
	public ArenaWarps getWarps()
	{
		return warps;
	}
	
	public String getName()
	{
		return info.getName();
	}
	
	public ArenaInfo getInfo()
	{
		return info;
	}
	
	public ChestInfo getChestInfo()
	{
		return chests;
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
