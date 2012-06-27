package me.tomjw64.HungerBarGames.Commands.GenCommands;

import me.tomjw64.HungerBarGames.Arena;
import me.tomjw64.HungerBarGames.Commands.HBGCommand;
import me.tomjw64.HungerBarGames.Managers.GamesManager;
import me.tomjw64.HungerBarGames.Util.Enums.Status;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Spec extends HBGCommand{

	@Override
	public void execute(CommandSender sender, String[] args)
	{
		if(sender instanceof Player)
		{
			Player p=(Player)sender;
			if(GamesManager.getGame(p,true)!=null)
			{
				if(GamesManager.getGame(p,false)!=null)
				{
					Arena a=GamesManager.getArena(args[0]);
					if(a!=null)
					{
						if(a.getGame().getStatus()==Status.IN_GAME)
						{
							a.getGame().getPlayerHandler().addSpectator(p);
						}
						else
						{
							p.sendMessage(prefix+RED+"There is not a game running in that arena!");
						}
					}
					else
					{
						p.sendMessage(prefix+RED+"There is no arena by that name!");
					}
				}
				else
				{
					p.sendMessage(prefix+RED+"You are already spectating a game! Leave before you spectate another!");
				}
			}
			else
			{
				p.sendMessage(prefix+RED+"You are in a game! Leave before you spectate!");
			}
		}
	}

	@Override
	public String cmd() {
		return "spec";
	}

	@Override
	public String usage() {
		return cmd()+" [arena]";
	}

	@Override
	public String description() {
		return "spectates a game";
	}

	@Override
	public String permission() {
		return "HBG.player.spec";
	}

	@Override
	public int numArgs() {
		return 1;
	}

}
