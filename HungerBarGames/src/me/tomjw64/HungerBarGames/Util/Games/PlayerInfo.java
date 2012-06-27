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
		inv=new ItemStack[2][1];
		inv[0]=i.getContents();
		inv[1]=i.getArmorContents();
	}
	
	public void restore()
	{
		p.setGameMode(gamemode);
		p.setHealth(health);
		p.setFoodLevel(hunger);
		p.teleport(loc);
		PlayerInventory i=p.getInventory();
		i.setContents(inv[0]);
		i.setArmorContents(inv[1]);
	}

}
