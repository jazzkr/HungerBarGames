package me.tomjw64.HungerBarGames.Listeners.Countdown;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import me.tomjw64.HungerBarGames.Game;
import me.tomjw64.HungerBarGames.Listeners.GameListener;
import me.tomjw64.HungerBarGames.Managers.ConfigManager;

public class CountdownCommandListener extends GameListener{

	public CountdownCommandListener(Game gm) {
		super(gm);
	}
	
	@EventHandler(priority=EventPriority.NORMAL, ignoreCancelled=true)
	public void command(PlayerCommandPreprocessEvent command)
	{
		if(getGame().isTribute(command.getPlayer()))
		{
			String[] cmd=command.getMessage().replace("/", "").split(" ");
			if(!ConfigManager.getAllowedCommands().contains(cmd[0]))
			{
				command.setCancelled(true);
			}
		}
	}

}
