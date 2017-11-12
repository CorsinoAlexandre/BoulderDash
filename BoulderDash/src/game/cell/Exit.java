package game.cell;

import game.Position;
import game.actor.Rockford;
import game.map.bdlevel.BDLevelReader;
import game.SpriteChar;

public class Exit extends Cell
{
	private SpriteChar spritechar = SpriteChar.E;

	public Exit(Position pos)
	{
		super(pos, 2);
	}

	public void open(BDLevelReader levelReader, Rockford player)
	{
		if (player.getDiamonds() == levelReader.getDiamondsNeeded())
		{
			this.setSolid(1);
		}
	}
	
	// GETTERS
	
	public SpriteChar getSpritechar()
	{
		return spritechar;
	}

}
