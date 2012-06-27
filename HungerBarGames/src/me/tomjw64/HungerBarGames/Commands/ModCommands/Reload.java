package me.tomjw64.HungerBarGames.Commands.ModCommands;

import me.tomjw64.HungerBarGames.Commands.HBGCommand;

import org.bukkit.command.CommandSender;

public class Reload extends HBGCommand{
	
	@Override
	public void execute(CommandSender sender, String[] args) {
		// TODO Reload
		
	}

	@Override
	public String cmd() {
		return "reload";
	}

	@Override
	public String usage() {
		return cmd();
	}

	@Override
	public String description() {
		return "does nothing";
	}

	@Override
	public String permission() {
		return "HBG.mod.reload";
	}

	@Override
	public int numArgs() {
		return 0;
	}

}
