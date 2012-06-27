package me.tomjw64.HungerBarGames.Managers;

import java.io.File;
import java.io.IOException;

import me.tomjw64.HungerBarGames.HungerBarGames;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;

public class PlayerDataManager {
	private static File databaseFile;
	private static FileConfiguration database;
	
	public static void loadPlayerData(HungerBarGames pl)
	{
		PluginDescriptionFile pdf=pl.getDescription();	
		databaseFile=new File(pl.getDataFolder(),"playerdata.yml");		
		if(!databaseFile.exists())
		{
			databaseFile.getParentFile().mkdirs();
			try {
				databaseFile.createNewFile();
				HungerBarGames.logger.info("["+pdf.getName()+"] Generating player data file!");
			} catch (IOException wtf){
				wtf.printStackTrace();
			}
		}	
		database=new YamlConfiguration();
		try {
			database.load(databaseFile);
			HungerBarGames.logger.info("["+pdf.getName()+"] Loading player data file!");
		} catch (Exception wtf) {
			wtf.printStackTrace();
		}
	}
	
	public static FileConfiguration getPlayerData()
	{
		return database;
	}
	
	public static void savePlayerData()
	{
		try {
			database.save(databaseFile);
		} catch (IOException wtf) {
			wtf.printStackTrace();
		}
	}
	
}
