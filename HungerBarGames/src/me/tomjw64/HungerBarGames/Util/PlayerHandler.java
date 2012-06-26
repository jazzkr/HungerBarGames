package me.tomjw64.HungerBarGames.Util;

import java.util.HashSet;
import java.util.Set;

import me.tomjw64.HungerBarGames.Game;
import me.tomjw64.HungerBarGames.Managers.ConfigManager;
import me.tomjw64.HungerBarGames.Managers.GamesManager;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class PlayerHandler extends ChatVariableHolder{
	private Game game;
	private Set<Player> tributes=new HashSet<Player>();
	private Set<Player> spectators=new HashSet<Player>();
	
	public PlayerHandler(Game game)
	{
		this.game=game;
	}
	
	public void addTribute(Player p)
	{
		if(game.getStatus()==Status.LOBBY)
		{
			if(game.getPop()<game.getArena().getMax())
			{
				if(game.getPop()<game.getArena().getWarps().getNumSpawns())
				{
					p.sendMessage(prefix+YELLOW+"You have joined the game in arena "+BLUE+game.getArena().getName()+"!");
					p.sendMessage(prefix+YELLOW+"This game has "+BLUE+game.getPop()+"/"+game.getArena().getMax()+YELLOW+" players!");
					tributes.add(p);
					GamesManager.setInGame(p,true);
					Players.clearInv(p);
					Players.heal(p);
					p.teleport(game.getArena().getWarps().getLobby());
					if(game.isWaiting()&&game.getPop()>=game.getArena().getMin())
					{
						game.startCountdown();
					}
				}
				else
				{
					p.sendMessage(prefix+RED+"There are not enough spawn points!");
				}
			}
			else
			{
				p.sendMessage(prefix+RED+"There is not enough room in the game!");
			}
		}
		else
		{
			p.sendMessage(prefix+RED+"The game has already been started!");
		}
	}
	
	public void eliminate(Player p)
	{
		
	}
	
	public void removeTribute(Player p)
	{
		
	}
	
	public void addSpectator(Player p)
	{
		
	}
	
	public void removeSpectator(Player p)
	{
		
	}
	
	public void declareWinner()
	{
		Player p=(Player)tributes.toArray()[0];
		//TODO: Remove winner
		Bukkit.getServer().broadcastMessage(prefix+YELLOW+"Player "+BLUE+p.getName()+YELLOW+" has won the game in arena "+BLUE+game.getArena().getName()+"!");
		Players.heal(p);
		Players.clearInv(p);
		for(String cmd:ConfigManager.getWinCommands())
		{
			cmd=cmd.replace("<player>", p.getName());
			Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),cmd);
		}
		game.stopGame(false);
	}
	
	public void removeAll()
	{
		final Set<Player> specs=spectators;
		for(Player p:specs)
		{
			//TODO: Remove spectator
		}
		final Set<Player> tribs=tributes;
		for(Player p:tribs)
		{
			//TODO: Remove tributes
		}
	}
	
	public boolean isTribute(Player p)
	{
		return tributes.contains(p);
	}
	
	public int getPop()
	{
		return tributes.size();
	}
	
	public Set<Player> getTributes()
	{
		return tributes;
	}
	
	public Set<Player> getSpectators()
	{
		return spectators;
	}

}
