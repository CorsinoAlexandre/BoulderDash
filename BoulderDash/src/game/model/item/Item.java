package game.model.item;

import game.model.Entity;
import game.model.ListOfEntities;
import game.model.Position;
import game.model.map.MapItem;

/**
 * Esta es la clase de todos los items, que son objetos no-enemigos que se
 * mueven Contienen una posicion heredada de entity, un Spritechar que
 * representa al item, y booleanos de estados.
 *
 */
public abstract class Item extends Entity
{

	private boolean collectable;
	private boolean moveable;
	private boolean rounded;

	/**
	 * Constructor de un item.
	 * 
	 * @param pos
	 * @param collectable
	 * @param moveable
	 * @param rounded
	 */
	public Item(Position pos, boolean collectable, boolean moveable, boolean rounded)
	{
		super(pos);
		this.collectable = collectable;
		this.moveable = moveable;
		this.rounded = rounded;
	}

	/**
	 * Devuelve si el item es un diamante.
	 * 
	 * @return boolean
	 */
	public boolean isDiamond()
	{
		if (this instanceof Diamond)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Devuelve si es una roca.
	 * 
	 * @return boolean
	 */
	public boolean isRock()
	{
		if (this instanceof Rock)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Devuelve si es collectable el objeto.
	 * 
	 * @return collectable
	 */
	public boolean isCollectable()
	{
		return collectable;
	}

	/**
	 * Devuelve si se puede desplazar el objeto.
	 * 
	 * @return moveable
	 */
	public boolean isMoveable()
	{
		return moveable;
	}

	/**
	 * Retorna si el objeto es redondo. Si un objeto puede deslizar.
	 * 
	 * @return rounded
	 */
	public boolean isRounded()
	{
		return rounded;
	}

	@Override
	public void die()
	{
		ListOfEntities.getList().remove(this);
		MapItem.removeItem(this.getPosition());
	}
}
