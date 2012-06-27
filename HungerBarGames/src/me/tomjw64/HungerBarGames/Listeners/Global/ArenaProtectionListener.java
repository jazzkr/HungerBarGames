package me.tomjw64.HungerBarGames.Listeners.Global;

import me.tomjw64.HungerBarGames.Arena;
import me.tomjw64.HungerBarGames.HungerBarGames;
import me.tomjw64.HungerBarGames.Managers.GamesManager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class ArenaProtectionListener implements Listener{
	
	public ArenaProtectionListener()
	{
		Bukkit.getServer().getPluginManager().registerEvents(this,HungerBarGames.plugin);
	}
	
	@EventHandler(priority=EventPriority.NORMAL, ignoreCancelled=true)
	public void blockBreak(BlockBreakEvent broken)
	{
		Player breaker=broken.getPlayer();
		if(GamesManager.getGame(breaker,true)==null&&!breaker.isOp()&&!breaker.hasPermission("HBG.mod.edit"))
		{
			for(Arena a:GamesManager.getArenas())
			{
				if(a.isBounded()&&a.getBoundary().isIn(broken.getBlock()))
				{
					broken.setCancelled(true);
					return;
				}
			}
		}
	}
	
	@EventHandler(priority=EventPriority.NORMAL, ignoreCancelled=true)
	public void blockPlace(BlockPlaceEvent placed)
	{
		Player placer=placed.getPlayer();
		if(GamesManager.getGame(placer,true)==null&&!placer.isOp()&&!placer.hasPermission("HBG.mod.edit"))
		{
			for(Arena a:GamesManager.getArenas())
			{
				if(a.isBounded()&&a.getBoundary().isIn(placed.getBlock()))
				{
					placed.setCancelled(true);
					return;
				}
			}
		}
	}

}
