package game.model.element.entity.item;

import game.model.element.Position;
import game.model.element.SpriteChar;

/**
 * Clase que representa una item vacio.
 */
public class Empty extends Item
{

	/**
	 * Constructor de vacia.
	 * 
	 * @param pos
	 */
	public Empty(Position pos)
	{
		super(pos, false, false, false);
		this.setSpritechar(SpriteChar._);
	}

	/**
	 * 
	 */
	@Override
	public void changePosition()
	{

	}

	/**
	 * 
	 */
	@Override
	public void makeMove()
	{

	}

}