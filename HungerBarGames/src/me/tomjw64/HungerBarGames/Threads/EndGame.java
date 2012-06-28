package me.tomjw64.HungerBarGames.Threads;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.tomjw64.HungerBarGames.Game;
import me.tomjw64.HungerBarGames.Managers.ConfigManager;

public class EndGame implements Runnable{
	private Game game;
	private Player winner;
	
	public EndGame(Game game, Player winner)
	{
		this.game=game;
		this.winner=winner;
		new Thread(this).start();
	}

	@Override
	public void run()
	{
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		game.reset();
		for(String cmd:ConfigManager.getWinCommands())
		{
			cmd=cmd.replace("<player>", winner.getName());
			Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),cmd);
		}
		if(game.getPlaylist()!=null)
		{
			game.getPlaylist().playNext();
		}
	}

}
