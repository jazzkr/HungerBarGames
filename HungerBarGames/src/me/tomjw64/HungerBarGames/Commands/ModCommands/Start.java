package me.tomjw64.HungerBarGames.Commands.ModCommands;

import me.tomjw64.HungerBarGames.Arena;
import me.tomjw64.HungerBarGames.Commands.HBGCommand;
import me.tomjw64.HungerBarGames.Managers.GamesManager;
import me.tomjw64.HungerBarGames.Util.Enums.Status;

import org.bukkit.command.CommandSender;

public class Start extends HBGCommand{

	@Override
	public void execute(CommandSender sender, String[] args)
	{
		Arena a=GamesManager.getArena(args[0]);
		if(a!=null)
		{
			if(a.getGame().getStatus()==Status.IDLE)
			{
				if(a.isBounded())
				{
					if(a.isSetup())
					{
						sender.sendMessage(prefix+GREEN+"Game Started!");
						a.getGame().startLobby(null);
					}
					else
					{
						sender.sendMessage(prefix+RED+"Arena has not been set up correctly!");
					}
				}
				else
				{
					sender.sendMessage(prefix+RED+"You need to set up your arena boundary first!");
				}
			}
			else
			{
				sender.sendMessage(prefix+RED+"A game is already running in arena "+BLUE+args[0]+"!");
			}
		}
		else
		{
			sender.sendMessage(prefix+RED+"There is no arena by that name!");
		}
	}

	@Override
	public String cmd() {
		return "start";
	}

	@Override
	public String usage() {
		return cmd()+" [arena]";
	}

	@Override
	public String description() {
		return "starts a non-repeating game";
	}

	@Override
	public String permission() {
		return "HBG.mod.start";
	}

	@Override
	public int numArgs() {
		return 1;
	}

}
