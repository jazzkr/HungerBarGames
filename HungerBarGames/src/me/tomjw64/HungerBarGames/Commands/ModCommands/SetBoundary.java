package me.tomjw64.HungerBarGames.Commands.ModCommands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.tomjw64.HungerBarGames.Arena;
import me.tomjw64.HungerBarGames.CommandHandler;
import me.tomjw64.HungerBarGames.Commands.HBGCommand;
import me.tomjw64.HungerBarGames.Util.Arenas.Boundary;
import me.tomjw64.HungerBarGames.Util.Enums.BoundaryType;

public class SetBoundary extends HBGCommand{

	@Override
	public void execute(CommandSender sender, String[] args)
	{
		if(sender instanceof Player)
		{
			Player p=(Player)sender;
			Arena a=CommandHandler.getSelections().get(p);
			if(a!=null)
			{
				if(args[0].equalsIgnoreCase("square")||args[0].equalsIgnoreCase("s")||args[0].equalsIgnoreCase("circle")||args[0].equalsIgnoreCase("c"))
				{
					try
					{
						int radius=Integer.parseInt(args[1]);
						if(args[0].equalsIgnoreCase("square")||args[0].equalsIgnoreCase("s"))
						{
							a.setBoundary(new Boundary(p.getWorld(),BoundaryType.SQUARE,p.getLocation().getBlock(),radius));
						}
						else
						{
							a.setBoundary(new Boundary(p.getWorld(),BoundaryType.CIRCLE,p.getLocation().getBlock(),radius));
						}
						p.sendMessage(prefix+YELLOW+"Boundary set for arena "+BLUE+a.getName()+YELLOW+"!");
					}
					catch(Exception wtf)
					{
						sender.sendMessage(prefix+RED+"Could not process command!");
					}
				}
				else
				{
					p.sendMessage(prefix+RED+"Invalid shape!");
				}
			}
			else
			{
				p.sendMessage(prefix+RED+"You have no arena selected! Type "+BLUE+"/hbg select [arena]"+RED+" to select an arena!");
			}
		}
	}

	@Override
	public String cmd() {
		return "setboundary";
	}

	@Override
	public String usage() {
		return cmd()+" [square/circle] [radius]";
	}

	@Override
	public String description() {
		return "sets the arena boundary";
	}

	@Override
	public String permission() {
		return "HBG.admin.setboundary";
	}

	@Override
	public int numArgs() {
		return 2;
	}

}
