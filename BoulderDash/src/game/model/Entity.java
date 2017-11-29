package game.model;

import java.util.HashMap;

import game.model.actor.Actor;
import game.model.actor.Rockford;
import game.model.item.Item;

/**
 * Elementos dinamicos que se pueden mover. Como Actor y Item.
 */
public abstract class Entity extends Element
{
	private HashMap<Integer, SpriteChar> passable = new HashMap<>();

	/**
	 * Genera una entidad en una posicion.
	 * 
	 * @param pos
	 */
	public Entity(Position pos)
	{
		super(pos);
	}

	/**
	 * Determina si esta entidad es un actor.
	 * 
	 * @return true o false
	 */
	public boolean isActor()
	{
		if (this instanceof Actor)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Determina si esta entidad es Rockford.
	 * 
	 * @return true o false
	 */
	public boolean isRockford()
	{
		if (this instanceof Rockford)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Determina si esta entidad es un Item.
	 * 
	 * @return true o false
	 */
	public boolean isItem()
	{
		if (this instanceof Item)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Devuelve los elementos que la entidad puede traspasar.
	 * 
	 * @return El hashmap de los elementos passables
	 */
	public HashMap<Integer, SpriteChar> getPassable()
	{
		return passable;
	}

	/**
	 * Setea los elementos que la entidad puede traspasar.
	 * 
	 * @param passable
	 */
	public void setPassable(HashMap<Integer, SpriteChar> passable)
	{
		this.passable = passable;
	}

	/**
	 * Cambia la posicion de la entidad antes de hacer makemove.
	 */
	abstract public void changePosition();

	/**
	 * Hace la movida de una entidad.
	 */
	abstract public void makeMove();

	/**
	 * Hace un comportamiento y borra la entidad.
	 */
	abstract public void die();

}
