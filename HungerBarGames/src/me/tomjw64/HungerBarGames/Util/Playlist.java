package me.tomjw64.HungerBarGames.Util;

import java.util.List;

import me.tomjw64.HungerBarGames.Arena;
import me.tomjw64.HungerBarGames.Game;
import me.tomjw64.HungerBarGames.Util.Enums.Status;

public class Playlist {
	private List<Arena> arenas;
	private int index=-1;
	private int loops=0;
	
	public Playlist(List<Arena> arenas)
	{
		this.arenas=arenas;
	}
	
	public void playNext()
	{
		if(loops>arenas.size())
		{
			quit();
			return;
		}
		index++;
		if(arenas.size()>index)
		{
			Game game=arenas.get(index).getGame();
			if(game.getStatus()==Status.IDLE&&game.getArena().isBounded()&&game.getArena().isSetup())
			{
				game.startLobby(this);
				loops=0;
			}
			else
			{
				loops++;
				playNext();
			}
		}
		else
		{
			loops++;
			index=-1;
			playNext();
		}
	}
	
	public void quit()
	{
		index=1;
	}

}
