package me.tomjw64.HungerBarGames.Commands.GenCommands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.tomjw64.HungerBarGames.Game;
import me.tomjw64.HungerBarGames.Commands.HBGCommand;
import me.tomjw64.HungerBarGames.Managers.GamesManager;
import me.tomjw64.HungerBarGames.Util.Enums.Status;

public class Leave extends HBGCommand{

	@Override
	public void execute(CommandSender sender, String[] args) {
		if(sender instanceof Player)
		{
			Player p=(Player)sender;
			if(GamesManager.getGame(p,true)!=null)
			{
				Game g=GamesManager.getGame(p,true);
				if(g.getStatus()==Status.LOBBY)
				{
					g.getPlayerHandler().removeTribute(p);
				}
				else
				{
					g.getPlayerHandler().eliminate(p);
				}
				p.sendMessage(prefix+YELLOW+"You have left the game in arena "+BLUE+g.getArena().getName()+"!");
			}
			else if(GamesManager.getGame(p,false)!=null)
			{
				Game g=GamesManager.getGame(p,false);
				g.getPlayerHandler().removeSpectator(p);
			}
			else
			{
				p.sendMessage(prefix+RED+"You are not in a game or spectating!");
			}
		}
	}

	@Override
	public String cmd() {
		return "leave";
	}

	@Override
	public String usage() {
		return cmd();
	}

	@Override
	public String description() {
		return "leaves or stops spectating a game";
	}

	@Override
	public String permission() {
		return "HBG.player.leave";
	}

	@Override
	public int numArgs() {
		return 0;
	}

}
