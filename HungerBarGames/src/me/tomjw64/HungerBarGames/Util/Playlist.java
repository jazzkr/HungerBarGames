package me.tomjw64.HungerBarGames.Util;

import java.util.List;

import me.tomjw64.HungerBarGames.Arena;
import me.tomjw64.HungerBarGames.Game;
import me.tomjw64.HungerBarGames.Util.Enums.Status;

public class Playlist {
	private String name;
	private List<Arena> arenas;
	boolean active=false;
	private int index=-1;
	private int loops=0;
	
	public Playlist(String name)
	{
		this.name=name;
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
				active=true;
				loops=0;
				game.startLobby(this);
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
		loops=0;
		active=false;
	}
	
	public void addArena(Arena a)
	{
		arenas.add(a);
	}
	
	public boolean isActive()
	{
		return active;
	}
	
	public String getName()
	{
		return name;
	}

}
