package game.model.item;

import game.model.Position;
import game.model.SolidTo;
import game.model.SpriteChar;

public class Diamond extends Fallable
{
	private SpriteChar spritechar = SpriteChar.X;
	
	// CONSTRUCTORS
	
	public Diamond(Position pos)
	{
		super(pos, true, false, true, false, true, SolidTo.ENEMY, StatusFallableEnum.IDLE);
	}
	
	public Diamond(Position pos, StatusFallableEnum state)
	{
		super(pos, true, false, true, false, true, SolidTo.ENEMY, state);
	}
	
	// GETTERS
	
	public SpriteChar getSpritechar()
	{
		return spritechar;
	}
	
	// COLLECTED
	
	/** Collected setea el diamante como recolectado
	 * 
	 */
	public void collected()
	{
		this.die();
	}
	
}
