package me.tomjw64.HungerBarGames.Threads;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Boat;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Item;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Slime;

import me.tomjw64.HungerBarGames.Game;
import me.tomjw64.HungerBarGames.Managers.ConfigManager;
import me.tomjw64.HungerBarGames.Util.ChatVariableHolder;
import me.tomjw64.HungerBarGames.Util.Players;
import me.tomjw64.HungerBarGames.Util.Enums.Status;

public class Countdown extends ChatVariableHolder implements Runnable{
	private Game game;
	private int time=ConfigManager.getCountdown();
	
	public Countdown(Game game)
	{
		this.game=game;
		game.setStatus(Status.COUNTDOWN);
		prepareWorld();
		prepareTributes();
		new Thread(this).start();
	}
	
	@Override
	public void run()
	{
		final Set<Player> tributes=game.getTributes();
		while(time>0)
		{
			if(time<11||time%10==0)
			{
				for(Player p:tributes)
				{
					p.sendMessage(prefix+GREEN+"The game begins in "+time+" seconds!");
				}
			}
			try {
				Thread.sleep(1000);
			} catch (Exception wtf) {
				wtf.printStackTrace();
			}
			time--;
		}
		Bukkit.getServer().broadcastMessage(prefix+YELLOW+"A game has begun in arena "+BLUE+game.getArena().getName()+"!");
		for(Player p:tributes)
		{
			p.sendMessage(prefix+GREEN+"May the odds be ever in your favor!");
		}
		game.startGame();
	}
	
	public void prepareTributes()
	{
		Collection<Location> spawns=game.getArena().getWarps().getSpawns().values();
		Set<Player> tributes=game.getTributes();
		Iterator<Location> i=spawns.iterator();
		String list=prefix+GREEN+"Tributes: ";
		for(Player p:tributes)
		{
			list+=RED+p.getName()+WHITE+", ";
			p.teleport(i.next());
			Players.clearInv(p);
			Players.heal(p);
		}
		list=list.substring(0,list.length()-2);
		for(Player p:tributes)
		{
			p.sendMessage(prefix+GREEN+"The countdown has begun!");
			p.sendMessage(list);
		}
	}
	
	public void prepareWorld()
	{
		game.getArena().getWorld().setTime(0);
		game.getArena().fillChests();
		for(Entity e:game.getArena().getWorld().getEntities())
		{
			if(e instanceof Monster||
					e instanceof Slime||
					e instanceof Item||
					e instanceof Projectile||
					e instanceof Boat||
					e instanceof Minecart||
					e instanceof ExperienceOrb)
			{
				if(game.getArena().getBoundary().isIn(e))
				{
					e.remove();
				}
			}
		}
	}
	
}
