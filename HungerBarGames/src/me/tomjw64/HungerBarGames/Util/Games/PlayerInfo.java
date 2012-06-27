package me.tomjw64.HungerBarGames.Util.Games;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class PlayerInfo {
	private Player p;
	private GameMode gamemode;
	private int health;
	private int hunger;
	private Location loc;
	private ItemStack[][] inv;

	public PlayerInfo(Player p)
	{
		this.p=p;
		gamemode=p.getGameMode();
		health=p.getHealth();
		hunger=p.getFoodLevel();
		loc=p.getLocation();
		PlayerInventory i=p.getInventory();
		inv[1]=i.getContents();
		inv[2]=i.getArmorContents();
	}
	
	public void restore()
	{
		p.setGameMode(gamemode);
		p.setHealth(health);
		p.setFoodLevel(hunger);
		p.teleport(loc);
		PlayerInventory i=p.getInventory();
		i.setContents(inv[1]);
		i.setArmorContents(inv[2]);
	}

}
