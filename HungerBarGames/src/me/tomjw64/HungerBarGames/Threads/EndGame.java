package me.tomjw64.HungerBarGames.Threads;

import me.tomjw64.HungerBarGames.Game;
import me.tomjw64.HungerBarGames.Util.Enums.Status;

public class EndGame implements Runnable{
	private Game game;
	
	public EndGame(Game game)
	{
		this.game=game;
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
		game.setStatus(Status.IDLE);
		if(game.getPlaylist()==null)
		{
			game.getPlayerHandler().removeAll();
		}
		else
		{
			game.rollback();
			game.getPlayerHandler().removeAll();
			game.getPlaylist().playNext();
		}
	}

}
