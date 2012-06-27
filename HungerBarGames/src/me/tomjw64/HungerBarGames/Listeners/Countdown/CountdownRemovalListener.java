package me.tomjw64.HungerBarGames.Listeners.Countdown;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.tomjw64.HungerBarGames.Game;
import me.tomjw64.HungerBarGames.Listeners.GameListener;

public class CountdownRemovalListener extends GameListener{

	public CountdownRemovalListener(Game gm) {
		super(gm);
	}
	
	@EventHandler(priority=EventPriority.NORMAL)
	public void leave(PlayerQuitEvent quit)
	{
		Player quitter=quit.getPlayer();
		if(getGame().isTribute(quitter))
		{
			quit.setQuitMessage(null);
			getGame().getPlayerHandler().eliminate(quitter);
		}
	}
	
	@EventHandler(priority=EventPriority.NORMAL)
	public void death(EntityDamageEvent death)
	{
		if(death.getEntity() instanceof Player)
		{
			Player dead=(Player)death.getEntity();
			if(dead.getHealth()>=0&&getGame().isTribute(dead))
			{
				getGame().getPlayerHandler().eliminate(dead);
			}
		}
	}

}
