package me.tomjw64.HungerBarGames.Managers;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import me.tomjw64.HungerBarGames.Arena;
import me.tomjw64.HungerBarGames.HungerBarGames;
import me.tomjw64.HungerBarGames.Util.Playlist;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;

public class PlaylistManager {
	private static File configFile;
	private static FileConfiguration config;
	
	private static Set<Playlist> playlists=new HashSet<Playlist>();
	
	public static void loadPlaylists(HungerBarGames pl)
	{
		PluginDescriptionFile pdf=pl.getDescription();
		configFile=new File(pl.getDataFolder(),"playlists.yml");
		if(!configFile.exists())
		{
			configFile.getParentFile().mkdirs();
			try {
				configFile.createNewFile();
				HungerBarGames.logger.info("["+pdf.getName()+"] Generating playlist file!");
			} catch (IOException wtf){
				wtf.printStackTrace();
			}
		}
		config=new YamlConfiguration();
		try {
			config.load(configFile);
			HungerBarGames.logger.info("["+pdf.getName()+"] Loading playlists!");
		} catch (Exception wtf) {
			wtf.printStackTrace();
		}
		
		for(String x:config.getKeys(false))
		{
			Playlist play=new Playlist(x);
			for(String y:config.getStringList(x))
			{
				Arena a=GamesManager.getArena(y);
				if(a!=null)
				{
					play.addArena(a);
				}
			}
			playlists.add(play);
		}		
	}
	
	public static Playlist getPlaylist(String name)
	{
		for(Playlist pl:playlists)
		{
			if(pl.getName().equalsIgnoreCase(name))
			{
				return pl;
			}
		}
		return null;
	}
	
	public static Set<Playlist> getPlaylists()
	{
		return playlists;
	}
	
}
