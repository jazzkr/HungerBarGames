package me.tomjw64.HungerBarGames.Listeners.Countdown;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.PlayerDeathEvent;
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
			getGame().eliminateTribute(quitter);
		}
	}
	
	@EventHandler(priority=EventPriority.NORMAL)
	public void death(PlayerDeathEvent death)
	{
		Player dead=death.getEntity();
		if(getGame().isTribute(dead))
		{
			death.setDeathMessage(null);
			getGame().eliminateTribute(dead);
		}
	}

}
