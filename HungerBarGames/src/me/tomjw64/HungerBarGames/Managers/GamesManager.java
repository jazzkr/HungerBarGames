package me.tomjw64.HungerBarGames.Managers;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.entity.Player;

import me.tomjw64.HungerBarGames.Arena;
import me.tomjw64.HungerBarGames.Game;
import me.tomjw64.HungerBarGames.HungerBarGames;
import me.tomjw64.HungerBarGames.Util.Enums.Status;

public class GamesManager {
	private static Set<Arena> arenas=new HashSet<Arena>();
	
	public static void addArena(Arena ar)
	{
		if(getArena(ar.getName())==null)
		{
			arenas.add(ar);
		}
		else
		{
			HungerBarGames.logger.warning("Did not load arena "+ar.getName()+"! There is already an arena with that name!");
		}
	}
	
	public static Set<Arena> getArenas()
	{
		return arenas;
	}
	
	public static Arena getArena(String name)
	{
		for(Arena a:arenas)
		{
			if(a.getName().equalsIgnoreCase(name))
			{
				return a;
			}
		}
		return null;
	}
	
	public static void delArena(Arena a)
	{
		DataManager.removeArena(a.getName());
		arenas.remove(a);
	}
	
	public static Game getGame(Player p,boolean tribute)
	{
		if(tribute)
		{
			for(Arena a:arenas)
			{
				if(a.getGame().getStatus()!=Status.IDLE)
				{
					if(a.getGame().isTribute(p))
					{
						return a.getGame();
					}
				}
			}
		}
		else
		{
			for(Arena a: arenas)
			{
				if(a.getGame().getStatus()!=Status.IDLE)
				{
					if(a.getGame().isSpectator(p))
					{
						return a.getGame();
					}
				}
			}
		}
		return null;
	}
	
	public static void endAll()
	{
		for(Arena a:arenas)
		{
			if(a.getGame()!=null)
			{
				a.getGame().stopGame(true);
			}
		}
	}
	
}
