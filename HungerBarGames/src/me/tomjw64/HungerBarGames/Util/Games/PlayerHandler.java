package me.tomjw64.HungerBarGames.Util.Games;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import me.tomjw64.HungerBarGames.Game;
import me.tomjw64.HungerBarGames.Util.ChatVariableHolder;
import me.tomjw64.HungerBarGames.Util.Players;
import me.tomjw64.HungerBarGames.Util.Enums.Status;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PlayerHandler extends ChatVariableHolder{
	private Game game;
	private Map<Player,PlayerInfo> tributes=new HashMap<Player,PlayerInfo>();
	private Map<Player,PlayerInfo> spectators=new HashMap<Player,PlayerInfo>();
	private Set<String> deaths=new HashSet<String>();
	
	public PlayerHandler(Game game)
	{
		this.game=game;
	}
	
	public void addTribute(Player p)
	{
		addTribute(p,null);
	}
	
	public void addTribute(Player p,PlayerInfo info)
	{
		if(game.getStatus()==Status.LOBBY)
		{
			if(game.getPop()<game.getArena().getInfo().getMax())
			{
				if(game.getPop()<game.getArena().getWarps().getNumSpawns())
				{
					if(info==null)
					{
						info=new PlayerInfo(p);
					}
					tributes.put(p,info);
					p.sendMessage(prefix+YELLOW+"You have joined the game in arena "+BLUE+game.getArena().getName()+"!");
					p.sendMessage(prefix+YELLOW+"This game has "+BLUE+game.getPop()+"/"+game.getArena().getInfo().getMax()+YELLOW+" players!");
					Players.clearInv(p);
					Players.heal(p);
					p.teleport(game.getArena().getWarps().getLobby());
					if(game.isWaiting()&&game.getPop()>=game.getArena().getInfo().getMin())
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
		eliminate(p,false);
	}
	
	public void eliminate(Player p,boolean restore)
	{	
		Location deathLoc=p.getLocation();
		p.getWorld().strikeLightning(deathLoc.add(0, 100, 0));
		for(ItemStack i:p.getInventory().getContents())
		{
			deathLoc.getWorld().dropItemNaturally(deathLoc,i);
		}
		for(ItemStack i:p.getInventory().getArmorContents())
		{
			deathLoc.getWorld().dropItemNaturally(deathLoc,i);
		}
		deaths.add(p.getName());
		removeTribute(p,restore);
		if(getPop()==1)
		{
			declareWinner();
		}
	}
	
	public void declareWinner()
	{
		Player p=(Player)tributes.keySet().toArray()[0];
		Bukkit.getServer().broadcastMessage(prefix+YELLOW+"Player "+BLUE+p.getName()+YELLOW+" has won the game in arena "+BLUE+game.getArena().getName()+"!");
		game.endGame(p);
	}
	
	public void removeTribute(Player p)
	{
		removeTribute(p,true);
	}
	
	public void removeTribute(Player p,boolean restore)
	{
		if(restore)
		{
			tributes.get(p).restore();
		}
		else
		{
			addSpectator(p,tributes.get(p));
		}
		tributes.remove(p);
	}
	
	public void addSpectator(Player p)
	{
		addSpectator(p,null);
	}
	
	public void addSpectator(Player p,PlayerInfo info)
	{
		if(game.getStatus()==Status.IN_GAME)
		{
			p.sendMessage(prefix+YELLOW+"You are now spectating arena "+BLUE+game.getArena().getName()+"!");
			if(info==null)
			{
				info=new PlayerInfo(p);
			}
			spectators.put(p,info);
			Players.clearInv(p);
			Players.heal(p);
			p.setAllowFlight(true);
			for(Player other:Bukkit.getServer().getOnlinePlayers())
			{
				other.hidePlayer(p);
			}
			p.teleport(game.getArena().getWarps().getSpec());
		}
		else
		{
			p.sendMessage(prefix+RED+"There is no game in session!");
		}
	}
	
	public void removeSpectator(Player p)
	{
		spectators.get(p).restore();
		spectators.remove(p);
		p.setAllowFlight(false);
		for(Player other:Bukkit.getServer().getOnlinePlayers())
		{
			other.showPlayer(p);
		}
		p.sendMessage(prefix+YELLOW+"You have stopped spectating arena "+BLUE+game.getArena().getName()+"!");
	}
	
	public void removeAll()
	{
		final Object[] specs=spectators.keySet().toArray();
		for(Object p:specs)
		{
			removeSpectator((Player)p);
		}
		final Object[] tribs=tributes.keySet().toArray();
		for(Object p:tribs)
		{
			removeTribute((Player)p,true);
		}
	}
	
	public boolean isTribute(Player p)
	{
		return tributes.keySet().contains(p);
	}
	
	public boolean isSpectator(Player p)
	{
		return spectators.keySet().contains(p);
	}
	
	public Set<String> getDeaths()
	{
		return deaths;
	}
	
	public Set<Player> getTributes()
	{
		return tributes.keySet();
	}
	
	public Set<Player> getSpectators()
	{
		return spectators.keySet();
	}
	
	public PlayerInfo getTributeInfo(Player p)
	{
		return tributes.get(p);
	}
	
	public PlayerInfo getSpectatorInfo(Player p)
	{
		return spectators.get(p);
	}
	
	public int getPop()
	{
		return tributes.size();
	}

}
