package me.tomjw64.HungerBarGames;
//
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.Map;
//import java.util.Set;
//
//import me.tomjw64.HungerBarGames.Managers.ConfigManager;
//import me.tomjw64.HungerBarGames.Util.ArenaLocations;
//import me.tomjw64.HungerBarGames.Util.ChestFiller;
//import me.tomjw64.HungerBarGames.Util.CuboidPoint;
//import me.tomjw64.HungerBarGames.Util.RollbackInfo;
//import me.tomjw64.HungerBarGames.Util.Arenas.ArenaInfo;
//import me.tomjw64.HungerBarGames.Util.Arenas.Boundary;
//import me.tomjw64.HungerBarGames.Util.Chests.ChestClass;
//
//import org.bukkit.Location;
//import org.bukkit.World;
//import org.bukkit.block.Block;
//import org.bukkit.block.Chest;
//
public class ArenaOld {}
//	//Name of arena
//	private String name;
//	//Whether or not to load the arena to database
//	private boolean changes;
//	//Arena Cuboid
//	private CuboidPoint cuboid1;
//	private CuboidPoint cuboid2;
//	//Holds player spawn points
//	private Map<Integer,Location> spawns=new HashMap<Integer,Location>();
//	//Holds chests associated with the arena
//	private Map<ChestClass,Set<Chest>> chests=new HashMap<ChestClass,Set<Chest>>();
//	private ChestClass autofill;
//	//Holds blocks to be rolled back
//	private Map<Block,RollbackInfo> rollbacks=new HashMap<Block,RollbackInfo>();
//	//The spectator spawn
//	private Location specPoint;
//	//The lobby spawn
//	private Location lobbyPoint;
//	//The maximum players set for this arena
//	private int maxPlayers;
//	//The minimum players set for this arena
//	private int minPlayers;
//	//Game being played in this arena
//	private Game game;
//	
//	public ArenaOld(String arenaName)
//	{
//		this(arenaName,null,null,ConfigManager.getMaxPlayers(),ConfigManager.getMinPlayers(),null,null,new HashMap<Integer,Location>(),new HashMap<ChestClass,Set<Chest>>(),null);
//	}
//	
//	public ArenaOld(String arenaName,CuboidPoint cp1,CuboidPoint cp2,int maxP,int minP,Location lobby,Location spec,Map<Integer,Location> spwns,Map<ChestClass,Set<Chest>> chsts,ChestClass filler)
//	{
//		name=arenaName;
//		changes=false;
//		cuboid1=cp1;
//		cuboid2=cp2;
//		if(maxP<2)
//		{
//			maxP=2;
//			changes=true;
//		}
//		maxPlayers=maxP;
//		if(minP<2||minP>maxP)
//		{
//			minP=2;
//			changes=true;
//		}
//		minPlayers=minP;
//		lobbyPoint=lobby;
//		specPoint=spec;
//		spawns=spwns;
//		chests=chsts;
//		autofill=filler;
//	}
//	
//	public void startGame(boolean repeat)
//	{
//		game=new Game(this,repeat);
//	}
//	
//	public void endGame(boolean repeat)
//	{
//		rollback();
//		if(repeat)
//		{
//			game=new Game(this,repeat);
//		}
//		else
//		{
//			game=null;
//		}
//	}
//	
//	public String getName()
//	{
//		return name;
//	}
//	
//	public Game getGame()
//	{
//		return game;
//	}
//	
//	public int getMaxPlayers()
//	{
//		return maxPlayers;
//	}
//	
//	public void setMaxPlayers(int max)
//	{
//		maxPlayers=max;
//		changes=true;
//	}
//	
//	public int getMinPlayers()
//	{
//		return minPlayers;
//	}
//	
//	public void setMinPlayers(int min)
//	{
//		if(min>=2)
//		{
//			minPlayers=min;
//		}
//		changes=true;
//	}
//	
//	public Location getLobby()
//	{
//		return lobbyPoint;
//	}
//	 
//	public void setLobby(Location lobby)
//	{
//		lobbyPoint=lobby;
//		changes=true;
//	}
//	
//	public int getNumSpawns()
//	{
//		return spawns.size();
//	}
//	
//	public void addSpawn(Integer pos,Location spawn)
//	{
//		spawns.put(pos,spawn);
//		changes=true;
//	}
//	
//	public Map<Integer,Location> getSpawns()
//	{
//		return spawns;
//	}
//	
//	public Location getSpec()
//	{
//		return specPoint;
//	}
//	
//	public void setSpec(Location spec)
//	{
//		specPoint=spec;
//		changes=true;
//	}
//	
//	public boolean getChanged()
//	{
//		return changes;
//	}
//	
//	public World getWorld()
//	{
//		if(cuboid1!=null)
//		{
//			return cuboid1.getWorld();
//		}
//		else if(cuboid2!=null)
//		{
//			return cuboid2.getWorld();
//		}
//		else
//		{
//			return null;
//		}
//	}
//	
//	public void fillChests()
//	{
//		for(Map.Entry<ChestClass,Set<Chest>> entry:chests.entrySet())
//		{
//			ChestClass cc=entry.getKey();
//			for(Chest c:entry.getValue())
//			{
//				cc.fillChest(c);
//				game.setFilled(c);
//			}
//		}
//	}
//	
//	public Map<ChestClass,Set<Chest>> getChests()
//	{
//		return chests;
//	}
//	
//	public void addChest(ChestClass cc, Chest c)
//	{
//		if(chests.keySet().contains(cc))
//		{
//			chests.get(cc).add(c);
//		}
//		else
//		{
//			Set<Chest> newChestSet=new HashSet<Chest>();
//			newChestSet.add(c);
//			chests.put(cc,newChestSet);
//		}
//		changes=true;
//	}
//	
//	public boolean isAssigned(ChestClass cc, Chest c)
//	{
//		if(chests.keySet().contains(cc))
//		{
//			return chests.get(cc).contains(c);
//		}
//		return false;
//	}
//	
//	public void setCuboid1(World w,int x, int z)
//	{
//		cuboid1=new CuboidPoint(w,x,z);
//		changes=true;
//	}
//	
//	public CuboidPoint getCuboid1()
//	{
//		return cuboid1;
//	}
//	
//	public void setCuboid2(World w,int x, int z)
//	{
//		cuboid2=new CuboidPoint(w,x,z);
//		changes=true;
//	}
//	public CuboidPoint getCuboid2()
//	{
//		return cuboid2;
//	}
//	
//	public boolean isInArena(Block b)
//	{
//		int x=b.getX();
//		int z=b.getZ();
//		return (x>=cuboid1.getX()||x>=cuboid2.getX())
//				&&(x<=cuboid1.getX()||x<=cuboid2.getX())
//				&&(z>=cuboid1.getZ()||z>=cuboid2.getZ())
//				&&(z<=cuboid1.getZ()||z<=cuboid2.getZ());
//	}
//	
//	public boolean isInArena(Location l)
//	{
//		return isInArena(l.getWorld().getBlockAt(l));
//	}
//	
//	public boolean isCuboidSet()
//	{
//		return (cuboid1!=null&&cuboid2!=null);
//	}
//	
//	public void addRollback(Block b,RollbackInfo ri)
//	{
//		if(!rollbacks.containsKey(b))
//		{
//			rollbacks.put(b,ri);
//		}
//	}
//	
//	public void rollback()
//	{
//		for(Map.Entry<Block,RollbackInfo> entry:rollbacks.entrySet())
//		{
//			Block b=entry.getKey();
//			b.setTypeId(entry.getValue().getID());
//			b.setData(entry.getValue().getData());
//		}
//		HungerBarGames.logger.info("Arena "+name+" rolled back!");
//	}
//	
//	public void setFiller(ChestClass cc)
//	{
//		autofill=cc;
//		changes=true;
//	}
//	
//	public ChestClass getFiller()
//	{
//		return autofill;
//	}
//	
//	public void unassign(Chest c)
//	{
//		for(Set<Chest> cts:chests.values())
//		{
//			cts.remove(c);
//		}
//	}
//}
