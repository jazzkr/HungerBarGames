package me.tomjw64.HungerBarGames.Listeners;

import me.tomjw64.HungerBarGames.HungerBarGames;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockEditListener extends GameListener{
	/*
	 * BlockEditListener will listen for block breaking
	 * and block placing. It will perform necessary actions
	 * and then proceed to log the change.
	 */
	private Game game;
	
	public BlockEditListener(Game gm)
	{
		game=gm;
	}
	//On a block break
	@EventHandler(priority=EventPriority.NORMAL)
	public void blockBreak(BlockBreakEvent broken)
	{
		if(game.isTribute(broken.getPlayer())
			&&ConfigManager.restrictEditing())
		{
			broken.setCancelled(true);
		}
	}
	//On a block place
	@EventHandler(priority=EventPriority.NORMAL)
	public void blockPlace(BlockBreakEvent placed)
	{
		if(game.isTribute(placed.getPlayer())
			&&ConfigManager.restrictEditing())
		{
			placed.setCancelled(true);
		}
	}
}
