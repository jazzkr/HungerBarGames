package me.tomjw64.HungerBarGames.Threads;

import java.util.Set;

import org.bukkit.World;
import org.bukkit.entity.Player;

import me.tomjw64.HungerBarGames.Game;
import me.tomjw64.HungerBarGames.Util.ChatVariableHolder;
import me.tomjw64.HungerBarGames.Util.Enums.Status;

public class NightCheck extends ChatVariableHolder implements Runnable{
	private Game game;
	private long lastDisplay;
	
	public NightCheck(Game gm)
	{
		game=gm;
		new Thread(this).start();
	}
	
	@Override
	public void run()
	{
		final World world=game.getArena().getWorld();
		while(game.getStatus()==Status.IN_GAME)
		{
			if(world.getTime()>13000&&System.currentTimeMillis()>lastDisplay+600000)
			{
				Set<String> deaths=game.getPlayerHandler().getDeaths();
				for(Player p:game.getTributes())
				{
					p.sendMessage(prefix+YELLOW+"Tributes killed today:");
					if(deaths.size()>0)
					{
						for(String x:deaths)
						{
							p.sendMessage(GREEN+x);
						}
					}
					else
					{
						p.sendMessage(GREEN+"None");
					}
				}
				lastDisplay=System.currentTimeMillis();
				deaths.clear();
			}
			try {
				Thread.sleep(120000);
			} catch (Exception wtf) {
				wtf.printStackTrace();
			}
		}
	}

}
