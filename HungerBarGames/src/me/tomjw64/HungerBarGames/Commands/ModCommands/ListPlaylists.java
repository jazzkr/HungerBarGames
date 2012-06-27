package me.tomjw64.HungerBarGames.Commands.ModCommands;

import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import me.tomjw64.HungerBarGames.Commands.HBGCommand;
import me.tomjw64.HungerBarGames.Managers.PlaylistManager;
import me.tomjw64.HungerBarGames.Util.Playlist;

public class ListPlaylists extends HBGCommand{

	@Override
	public void execute(CommandSender sender, String[] args)
	{
		Set<Playlist> playlists=PlaylistManager.getPlaylists();
		if(playlists.size()==0)
		{
			sender.sendMessage(prefix+RED+"There are no playlists currently available!");
		}
		else
		{
			String list=prefix+YELLOW+"Playlists: ";
			for(Playlist pl:playlists)
			{
				ChatColor color;
				if(pl.isActive())
				{
					color=GREEN;
				}
				else
				{
					color=RED;
				}
				list+=color+pl.getName()+WHITE+", ";
			}
			list=list.substring(0,list.length()-2);
			sender.sendMessage(list);
			sender.sendMessage(prefix+YELLOW+"Key: "
					+RED+"Inactive"+WHITE+"; "
					+GREEN+"Active");
		}
	}

	@Override
	public String cmd() {
		return "playlists";
	}

	@Override
	public String usage() {
		return cmd();
	}

	@Override
	public String description() {
		return "lists playlists";
	}

	@Override
	public String permission() {
		return "HBG.mod.listplaylists";
	}

	@Override
	public int numArgs() {
		return 0;
	}

}
