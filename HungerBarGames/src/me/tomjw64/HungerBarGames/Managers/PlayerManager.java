package me.tomjw64.HungerBarGames.Managers;

import java.util.HashMap;
import java.util.Map;

import me.tomjw64.HungerBarGames.Util.PlayerStorage;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class PlayerManager {
	private static Map<Player,PlayerStorage> inGame=new HashMap<Player,PlayerStorage>();
	private static Map<Player,PlayerStorage> specing=new HashMap<Player,PlayerStorage>();
	private static Map<Player,PlayerStorage> respawns=new HashMap<Player,PlayerStorage>();
	
	public static boolean isInGame(Player p)
	{
		return inGame.containsKey(p);
	}
}
