package me.tomjw64.HungerBarGames.Threads;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.tomjw64.HungerBarGames.Game;
import me.tomjw64.HungerBarGames.Managers.ConfigManager;
import me.tomjw64.HungerBarGames.Util.ChatVariableHolder;
import me.tomjw64.HungerBarGames.Util.Status;

public class Lobby extends ChatVariableHolder implements Runnable{
	private Game game;
	private int time=ConfigManager.getDelay();
	private boolean waiting=false;
	
	public Lobby(Game game)
	{
		this.game=game;
		game.setStatus(Status.LOBBY);
		Bukkit.getServer().broadcastMessage(prefix+YELLOW+"A lobby has been started for arena "+BLUE+game.getArena().getName()+"!");
		Bukkit.getServer().broadcastMessage(prefix+YELLOW+"Type "+BLUE+"/hbg join "+game.getArena().getName()+YELLOW+" to join the game");
		new Thread(this).start();
	}
	
	@Override
	public void run()
	{
		while(time>0)
		{
			if(time<6||time%30==0)
			{
				for(Player p:game.getTributes())
				{
					p.sendMessage(prefix+GREEN+"The countdown begins in "+time+" seconds!");
				}
			}
			try {
				Thread.sleep(1000);
			} catch (Exception wtf) {
				wtf.printStackTrace();
			}
			time--;
		}
		if(game.getPop()>=game.getArena().getMin())
		{
			startCountdown();
		}
		else
		{
			for(Player p:game.getTributes())
			{
				p.sendMessage(prefix+RED+"There are not enough players in the game!");
				p.sendMessage(prefix+RED+"Have "+game.getPop()+"/"+game.getArena().getMin()+" players needed to start!");
				p.sendMessage(prefix+RED+"The game will start when enough players have joined!");
			}
			waiting=true;
		}
	}
	
	public boolean isWaiting()
	{
		return waiting;
	}
	
	public void startCountdown()
	{
		new Countdown(game);
	}
	
}
