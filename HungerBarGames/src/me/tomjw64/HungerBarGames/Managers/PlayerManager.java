package me.tomjw64.HungerBarGames.Managers;

import java.util.HashMap;
import java.util.Map;

import me.tomjw64.HungerBarGames.Util.PlayerRestore;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class PlayerManager {
	private static Map<Player,PlayerRestore> inGame=new HashMap<Player,PlayerRestore>();
	private static Map<Player,PlayerRestore> specing=new HashMap<Player,PlayerRestore>();
	private static Map<Player,PlayerRestore> respawns=new HashMap<Player,PlayerRestore>();
	
	public static boolean isInGame(Player p)
	{
		return inGame.containsKey(p);
	}
}
