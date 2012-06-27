package me.tomjw64.HungerBarGames.Managers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import me.tomjw64.HungerBarGames.Arena;
import me.tomjw64.HungerBarGames.HungerBarGames;
import me.tomjw64.HungerBarGames.Util.Arenas.ArenaInfo;
import me.tomjw64.HungerBarGames.Util.Arenas.ArenaWarps;
import me.tomjw64.HungerBarGames.Util.Arenas.Boundary;
import me.tomjw64.HungerBarGames.Util.Arenas.ChestInfo;
import me.tomjw64.HungerBarGames.Util.Chests.ChestClass;
import me.tomjw64.HungerBarGames.Util.Enums.BoundaryType;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;

public class DataManager {
	private static File databaseFile;
	private static FileConfiguration database;
	
	public static void loadDatabase(HungerBarGames pl)
	{
		PluginDescriptionFile pdf=pl.getDescription();
		databaseFile=new File(pl.getDataFolder(),"database.yml");
		if(!databaseFile.exists())
		{
			databaseFile.getParentFile().mkdirs();
			try {
				databaseFile.createNewFile();
				HungerBarGames.logger.info("["+pdf.getName()+"] Generating database!");
			} catch (IOException wtf){
				wtf.printStackTrace();
			}
		}
		database=new YamlConfiguration();
		try {
			database.load(databaseFile);
			HungerBarGames.logger.info("["+pdf.getName()+"] Loading database!");
		} catch (Exception wtf) {
			wtf.printStackTrace();
		}
		
		for(String s:database.getKeys(false))
		{
			GamesManager.addArena(new Arena(getArenaInfo(s),getBoundary(s),getWarps(s),getChestInfo(s)));			
		}		
	}

	private static ChestInfo getChestInfo(String s)
	{
		String path=s+".Chests.";
		ChestClass autofill=ChestClassManager.getChestClass(database.getString(path+"Autofiller"));
		Map<ChestClass,Set<Chest>> chests=new HashMap<ChestClass,Set<Chest>>();
		ConfigurationSection assignments=database.getConfigurationSection(path+"Assignments");
		if(assignments!=null)
		{
			for(String chestclass:assignments.getKeys(false))
			{
				ChestClass cc=ChestClassManager.getChestClass(chestclass);
				if(cc!=null)
				{
					Set<Chest> chestSet=new HashSet<Chest>();
					for(String loc:database.getStringList(s+".Chests.Assignments."+chestclass))
					{
						String[] info=loc.split(",");
						World world=Bukkit.getWorld(info[0]);
						if(world!=null)
						{
							int x=Integer.parseInt(info[1]);
							int y=Integer.parseInt(info[2]);
							int z=Integer.parseInt(info[3]);
							BlockState bs=world.getBlockAt(x,y,z).getState();
							if(bs instanceof Chest)
							{
								chestSet.add((Chest)bs);
							}
						}
					}
					chests.put(cc,chestSet);
				}
			}
		}
		return new ChestInfo(chests,autofill);
	}

	private static ArenaWarps getWarps(String s)
	{
		Location lobby=null;
		Location spec=null;
		Map<Integer,Location> spawns=new HashMap<Integer,Location>();
		String path=s+".Warps.Lobby.";
		World world;
		if(database.getString(path+"World")!=null)
		{
			world=Bukkit.getWorld(database.getString(path+".World"));
			if(world!=null)
			{
				double x=database.getDouble(path+".X");
				double y=database.getDouble(path+".Y");
				double z=database.getDouble(path+".Z");
				float yaw=Float.parseFloat(database.getString(path+".Yaw"));
				float pitch=Float.parseFloat(database.getString(path+".Pitch"));
				lobby=new Location(world,x,y,z,yaw,pitch);
			}
		}
		path=s+".Warps.Spec.";
		if(database.getString(path+"World")!=null)
		{
			world=Bukkit.getWorld(database.getString(path+".World"));
			if(world!=null)
			{
				double x=database.getDouble(path+".X");
				double y=database.getDouble(path+".Y");
				double z=database.getDouble(path+".Z");
				float yaw=Float.parseFloat(database.getString(path+".Yaw"));
				float pitch=Float.parseFloat(database.getString(path+".Pitch"));
				spec=new Location(world,x,y,z,yaw,pitch);
			}
		}
		ConfigurationSection spawnData=database.getConfigurationSection(s+".Warps.Spawns");
		if(spawnData!=null)
		{
			for(String spwn:spawnData.getKeys(false))
			{
				path=s+".Warps.Spawns."+spwn+".";
				Integer position=Integer.valueOf(spwn);
				if(database.getString(path+"World")!=null)
				{
					world=Bukkit.getWorld(database.getString(path+".World"));
					if(world!=null)
					{
						double x=database.getDouble(path+".X");
						double y=database.getDouble(path+".Y");
						double z=database.getDouble(path+".Z");
						float yaw=Float.parseFloat(database.getString(path+".Yaw"));
						float pitch=Float.parseFloat(database.getString(path+".Pitch"));
						spawns.put(position,new Location(world,x,y,z,yaw,pitch));
					}
				}
			}
		}
		return new ArenaWarps(spawns,lobby,spec);
	}

	private static Boundary getBoundary(String s)
	{
		String path=s+".Boundary.";
		if(database.getString(path+"World")!=null)
		{
			World world=Bukkit.getWorld(database.getString(path+"World"));
			if(world!=null)
			{
				BoundaryType type=(database.getInt(path+"Type")==0) ? BoundaryType.SQUARE : BoundaryType.CIRCLE;
				int x=database.getInt(path+"Center.X");
				int y=database.getInt(path+"Center.Y");
				int z=database.getInt(path+"Center.Z");
				Block center=world.getBlockAt(x,y,z);
				int radius=database.getInt(path+"Radius");
				return new Boundary(world,type,center,radius);
			}
		}
		return null;
	}

	private static ArenaInfo getArenaInfo(String s)
	{
		String path=s+".";
		int min=database.getInt(path+".Info.Min");
		int max=database.getInt(path+".Info.Max");
		return new ArenaInfo(s,min,max);
	}

	public static FileConfiguration getDatabase()
	{
		return database;
	}
	
	public static void saveDatabase()
	{
		try {
			database.save(databaseFile);
		} catch (IOException wtf) {
			wtf.printStackTrace();
		}
	}
	
	public static void saveArenas()
	{
		for(Arena a:GamesManager.getArenas())
		{
			String path=a.getName()+".";
			database.set(path+"Info.Min", a.getInfo().getMin());
			database.set(path+"Info.Max", a.getInfo().getMax());
			saveBoundary(a);
			saveWarps(a);
			saveChests(a);
		}
		saveDatabase();
	}
	
	private static void saveWarps(Arena a)
	{
		String path;
		Location l=a.getWarps().getLobby();
		if(l!=null)
		{
			path=a.getName()+".Warps.Lobby.";
			database.set(path+"World", l.getWorld().getName());
			database.set(path+"X", l.getX());
			database.set(path+"Y", l.getY());
			database.set(path+"Z", l.getZ());
			database.set(path+"Yaw", l.getYaw());
			database.set(path+"Pitch", l.getPitch());
		}
		Location s=a.getWarps().getSpec();
		if(s!=null)
		{
			path=a.getName()+".Warps.Spec.";
			database.set(path+"World", s.getWorld().getName());
			database.set(path+"X", s.getX());
			database.set(path+"Y", s.getY());
			database.set(path+"Z", s.getZ());
			database.set(path+"Yaw", s.getYaw());
			database.set(path+"Pitch", s.getPitch());
		}
		for(Map.Entry<Integer,Location> entry:a.getWarps().getSpawns().entrySet())
		{
			path=a.getName()+".Warps.Spawns."+entry.getKey().toString()+".";
			Location loc=entry.getValue();
			database.set(path+"World", loc.getWorld().getName());
			database.set(path+"X", loc.getX());
			database.set(path+"Y", loc.getY());
			database.set(path+"Z", loc.getZ());
			database.set(path+"Yaw", loc.getYaw());
			database.set(path+"Pitch", loc.getPitch());
		}
	}

	private static void saveChests(Arena a)
	{
		String path=a.getName()+".Chests.";
		ChestClass autofill=a.getChestInfo().getAutoFiller();
		if(autofill!=null)
		{
			database.set(path+"Autofiller",autofill.getName());
		}
		for(Map.Entry<ChestClass,Set<Chest>> entry:a.getChestInfo().getChests().entrySet())
		{
			path=a.getName()+".Chests.Assignments."+entry.getKey().getName();
			List<String> coords=new ArrayList<String>();
			for(Chest c:entry.getValue())
			{
				coords.add(c.getWorld().getName()+","+c.getX()+","+c.getY()+","+c.getZ());
			}
			database.set(path, coords);
		}
	}

	private static void saveBoundary(Arena a)
	{
		String path=a.getName()+".Boundary.";
		Boundary b=a.getBoundary();
		if(b!=null)
		{
			database.set(path+"World", b.getWorld().getName());
			database.set(path+"Type", b.getType().intValue());
			Block c=b.getCenter();
			database.set(path+"Center.X", c.getX());
			database.set(path+"Center.Y", c.getY());
			database.set(path+"Center.Z", c.getZ());
			database.set(path+"Radius", b.getRadius());
		}
	}

	public static void removeArena(String name)
	{
		database.set(name, null);
	}
	
	
}
