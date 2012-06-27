package me.tomjw64.HungerBarGames.Util.Enums;

public enum BoundaryType {
	SQUARE(0),
	CIRCLE(1);
	
	private final int type;
	
	BoundaryType(int type)
	{
		this.type=type;
	}

	public int intValue()
	{
		return type;
	}
}
