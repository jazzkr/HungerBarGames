package me.tomjw64.HungerBarGames.Util;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;

public class Boundary {
	private World world;
	private Block center;
	private BoundaryType type;
	private int radius;
	
	public Boundary(World w,BoundaryType bt,Block c,int r)
	{
		world=w;
		type=bt;
		center=c;
		radius=r;
	}
	
	public boolean isIn(Block b)
	{
		int x=center.getX();
		int z=center.getZ();
		int bx=b.getX();
		int bz=b.getZ();
		if(type==BoundaryType.CIRCLE)
		{
			int asquared=(int)Math.pow(Math.abs(x-bx),2);
			int bsquared=(int)Math.pow(Math.abs(z-bz),2);
			return Math.sqrt(asquared+bsquared)<=50;
		}
		else
		{
			return Math.abs(x-bx)<=radius&&Math.abs(z-bz)<=radius;
		}
	}
	
	public boolean isIn(Location l)
	{
		return isIn(world.getBlockAt(l));
	}
	
}
