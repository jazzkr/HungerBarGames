package me.tomjw64.HungerBarGames.Listeners;

import me.tomjw64.HungerBarGames.Game;
import me.tomjw64.HungerBarGames.HungerBarGames;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

public abstract class GameListener implements Listener{
	private Game game;
	
	public GameListener(Game game)
	{
		this.game=game;
		game.addListener(this);
		Bukkit.getServer().getPluginManager().registerEvents(this,HungerBarGames.plugin);
	}
	
	public Game getGame()
	{
		return game;
	}
	
	public void unregister()
	{
		HandlerList.unregisterAll(this);
	}
	
}
