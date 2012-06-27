package me.tomjw64.HungerBarGames.Util.Arenas;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import me.tomjw64.HungerBarGames.Util.Chests.ChestClass;

import org.bukkit.block.Chest;

public class ChestInfo {
	private Map<ChestClass,Set<Chest>> chests;
	private ChestClass autofill;
	
	public ChestInfo()
	{
		this(new HashMap<ChestClass,Set<Chest>>(),null);
	}
	
	public ChestInfo(Map<ChestClass,Set<Chest>> chests,ChestClass autofill)
	{
		this.chests=chests;
		this.autofill=autofill;
	}
	
	public void addChest(ChestClass cc, Chest c)
	{
		if(chests.keySet().contains(cc))
		{
			chests.get(cc).add(c);
		}
		else
		{
			Set<Chest> newChestSet=new HashSet<Chest>();
			newChestSet.add(c);
			chests.put(cc,newChestSet);
		}
	}
	
	public void setAutoFiller(ChestClass cc)
	{
		autofill=cc;
	}
	
	public void unassign(Chest c)
	{
		for(Set<Chest> cts:chests.values())
		{
			cts.remove(c);
		}
	}
	
	public boolean isAssigned(ChestClass cc, Chest c)
	{
		if(chests.keySet().contains(cc))
		{
			return chests.get(cc).contains(c);
		}
		return false;
	}
	
	public Map<ChestClass,Set<Chest>> getChests()
	{
		return chests;
	}
	
	public ChestClass getAutoFiller()
	{
		return autofill;
	}
	
}
