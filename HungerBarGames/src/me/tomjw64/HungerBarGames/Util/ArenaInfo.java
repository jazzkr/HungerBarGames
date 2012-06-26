package me.tomjw64.HungerBarGames.Util;

public class ArenaInfo {
	private String name;
	private int min;
	private int max;
	
	public ArenaInfo(String name,int min,int max)
	{
		this.name=name;
		this.min=min;
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
