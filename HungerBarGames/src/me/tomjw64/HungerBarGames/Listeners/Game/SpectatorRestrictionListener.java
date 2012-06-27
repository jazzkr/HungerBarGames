package me.tomjw64.HungerBarGames.Listeners.Game;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

import me.tomjw64.HungerBarGames.Game;
import me.tomjw64.HungerBarGames.Listeners.GameListener;
import me.tomjw64.HungerBarGames.Managers.ConfigManager;

public class SpectatorRestrictionListener extends GameListener{

	public SpectatorRestrictionListener(Game gm) {
		super(gm);
	}
	
	@EventHandler(priority=EventPriority.NORMAL)
	public void death(PlayerDeathEvent death)
	{
		if(getGame().isSpectator(death.getEntity()))
		{
			getGame().getPlayerHandler().removeSpectator(death.getEntity());
			death.setDeathMessage(null);
			death.getDrops().clear();
		}
	}

	@EventHandler(priority=EventPriority.NORMAL, ignoreCancelled=true)
	public void drop(PlayerDropItemEvent drop)
	{
		if(getGame().isSpectator(drop.getPlayer()))
		{
			drop.setCancelled(true);
		}
	}
	
	@EventHandler(priority=EventPriority.NORMAL, ignoreCancelled=true)
	public void interact(PlayerInteractEvent interact)
	{
		if(getGame().isSpectator(interact.getPlayer()))
		{
			Player toSpec=(Player)getGame().getTributes().toArray()[(int)(Math.random()*getGame().getPop())];
			interact.getPlayer().teleport(toSpec);
			interact.getPlayer().sendMessage(ConfigManager.getPrefix()+ChatColor.YELLOW+"You are now spectating player "+ChatColor.BLUE+toSpec.getName()+"!");
			interact.setCancelled(true);
		}
	}
	
	@EventHandler(priority=EventPriority.NORMAL, ignoreCancelled=true)
	public void foodChange(FoodLevelChangeEvent fc)
	{
		if(fc.getEntity() instanceof Player)
		{
			if(getGame().isSpectator((Player)fc.getEntity()))
			{
				fc.setCancelled(true);
			}
		}
	}
	
	@EventHandler(priority=EventPriority.NORMAL, ignoreCancelled=true)
	public void damage(EntityDamageEvent damage)
	{
		if(damage.getEntity() instanceof Player&&getGame().isSpectator((Player)damage.getEntity()))
		{
			damage.setCancelled(true);
		}
		else if(damage instanceof EntityDamageByEntityEvent)
		{
			Entity damager=((EntityDamageByEntityEvent)damage).getDamager();
			if(damager instanceof Player&&getGame().isSpectator((Player)damager))
			{
				damage.setCancelled(true);
			}
		}
	}
	
	@EventHandler(priority=EventPriority.NORMAL, ignoreCancelled=true)
	public void target(EntityTargetEvent target)
	{
		if(target.getTarget() instanceof Player&&getGame().isSpectator((Player)target.getTarget()))
		{
			target.setCancelled(true);
		}
	}
	
	@EventHandler(priority=EventPriority.NORMAL, ignoreCancelled=true)
	public void pickup(PlayerPickupItemEvent pickup)
	{
		if(getGame().isSpectator(pickup.getPlayer()))
		{
			pickup.setCancelled(true);
		}
	}
	
}
