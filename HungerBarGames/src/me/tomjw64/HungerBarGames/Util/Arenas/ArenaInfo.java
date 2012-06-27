package me.tomjw64.HungerBarGames.Util.Arenas;

import me.tomjw64.HungerBarGames.Managers.ConfigManager;

public class ArenaInfo {
	private String name;
	private int min;
	private int max;
	
	public ArenaInfo(String name)
	{
		this(name,ConfigManager.getMinPlayers(),ConfigManager.getMaxPlayers());
	}
	
	public ArenaInfo(String name,int min,int max)
	{
		this.name=name;
		this.min=min;
		this.max=max;
	}
	
	public void setMin(int min)
	{
		this.min=min;
	}
	
	public void setMax(int max)
	{
		this.max=max;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getMin()
	{
		return min;
	}
	
	public int getMax()
	{
		return max;
	}

}
