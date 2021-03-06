package me.tomjw64.HungerBarGames.Commands.ModCommands;

import org.bukkit.command.CommandSender;

import me.tomjw64.HungerBarGames.Arena;
import me.tomjw64.HungerBarGames.Commands.HBGCommand;
import me.tomjw64.HungerBarGames.Managers.GamesManager;
import me.tomjw64.HungerBarGames.Util.Enums.Status;

public class Stop extends HBGCommand{

	@Override
	public void execute(CommandSender sender, String[] args)
	{
		Arena a=GamesManager.getArena(args[0]);
		if(a!=null)
		{
			if(a.getGame().getStatus()!=Status.IDLE)
			{
				sender.sendMessage(prefix+GREEN+"Game Cancelled!");
				a.getGame().forceStop();
			}
			else
			{
				sender.sendMessage(prefix+RED+"A game is not running in arena "+BLUE+args[0]+"!");
			}
		}
		else
		{
			sender.sendMessage(prefix+RED+"There is no arena by that name!");
		}
	}

	@Override
	public String cmd() {
		return "stop";
	}

	@Override
	public String usage() {
		return cmd()+ " [arena]";
	}

	@Override
	public String description() {
		return "cancels a game";
	}

	@Override
	public String permission() {
		return "HBG.mod.stop";
	}

	@Override
	public int numArgs() {
		return 1;
	}

}
